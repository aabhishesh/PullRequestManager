[Console]::OutputEncoding = [System.Text.Encoding]::UTF8


$baseUrl = "http://localhost:8080"

function Get-RandomUserStory {
    $id = Get-Random -Minimum 100000 -Maximum 999999
    return "US$id"
}

function Get-RandomPR {
    $num = Get-Random -Minimum 100 -Maximum 999
    return @{
        prLink = "https://github.com/example/repo-$(Get-Random -Minimum 1 -Maximum 3)/pull/$num"
        prNumber = "$num"
        repoName = "repo-$(Get-Random -Minimum 1 -Maximum 3)"
        authorName = "user$(Get-Random -Minimum 1 -Maximum 5)"
    }
}

while ($true) {
    Write-Host "`n==== Starting new simulation cycle ====" -ForegroundColor Cyan

    # Generate 2–4 user stories per cycle
    $userStories = @()
    $storyCount = Get-Random -Minimum 2 -Maximum 4
    for ($i = 0; $i -lt $storyCount; $i++) {
        $userStories += Get-RandomUserStory
    }

    # Map to track PRs registered per story
    $storyMap = @{}

    # Register 2–4 PRs for each story
    foreach ($story in $userStories) {
        $prCount = Get-Random -Minimum 2 -Maximum 4
        $storyMap[$story] = @()

        for ($j = 0; $j -lt $prCount; $j++) {
            $pr = Get-RandomPR
            $storyMap[$story] += $pr

            $registerPayload = @{
                pr = $pr
                userStoryNumber = $story
            } | ConvertTo-Json -Depth 3

            Write-Host "`n[REGISTER] PR for ${story}:"

            Write-Host $registerPayload -ForegroundColor Yellow

            try {
                Invoke-RestMethod -Uri "$baseUrl/pr/register" -Method POST -Body $registerPayload -ContentType "application/json"
            } catch {
                Write-Host "❌ Failed to register PR for ${story}: $($_)" -ForegroundColor Red

            }

            Start-Sleep -Milliseconds 300
        }
    }

    # Short pause before comments
    Start-Sleep -Seconds 2
#sample commit
    # Send TEST_AND_MERGE comment for each story
    foreach ($story in $userStories) {
        $prs = $storyMap[$story]
        $selectedPR = $prs | Get-Random

        $commentPayload = @{
            pr = $selectedPR
            comment = "TEST_AND_MERGE"
            userStoryNumber = $story
        } | ConvertTo-Json -Depth 3

        Write-Host "`n[COMMENT] $story with TEST_AND_MERGE:"
        Write-Host $commentPayload -ForegroundColor Green

        try {
            Invoke-RestMethod -Uri "$baseUrl/pr/comment" -Method POST -Body $commentPayload -ContentType "application/json"
        } catch {
            Write-Host "❌ Failed to comment for ${story}: $($_)" -ForegroundColor Red

        }

        Start-Sleep -Milliseconds 800
    }

    Write-Host "`n✅ Cycle complete. Waiting before next..." -ForegroundColor Cyan
    Start-Sleep -Seconds 5
}
