# Configuration

$webhookUrl = "http://localhost:8080/github/webhook"
$githubSecret = "DragonSLayer@2569"
$testMode = $false # set to $false to send real events

$prTemplate = Get-Content -Raw -Path "./create-pr-refined.json" | ConvertFrom-Json
$commentTemplate = Get-Content -Raw -Path "./comment-refined.json" | ConvertFrom-Json

$repos = @("repoA", "repoB", "repoC", "repoD", "repoE", "repoF", "repoG", "repoH", "repoI", "repoJ")
$titles = @(
    "Fix login bug", "Add unit test", "Improve docs", "Refactor config", "Build deploy fix",
    "Increase timeout", "Cleanup logs", "Optimize queries", "Split service", "Enable tracing"
)
$rand = New-Object System.Random
$userStorySet = New-Object 'System.Collections.Generic.HashSet[string]'
$repoPrCount = @{}
$userStoryMap = @{}

function Generate-Branch($userStory, $title) {
    return ($userStory + "-" + $title.ToLower()) -replace '[^\w\s-]', '' -replace '\s+', '-'
}

function Generate-NewUserStory {
    do {
        $newStory = "US$($rand.Next(100000, 999999))"
    } while ($userStorySet.Contains($newStory))
    $userStorySet.Add($newStory)
    return "$newStory".ToUpper()
}

function Get-GitHubSignature {
    param ($payload, $secret)
    $hmac = New-Object System.Security.Cryptography.HMACSHA1
    $hmac.Key = [Text.Encoding]::ASCII.GetBytes($secret)
    $hashBytes = $hmac.ComputeHash([Text.Encoding]::ASCII.GetBytes($payload))
    return "sha1=" + ($hashBytes | ForEach-Object { $_.ToString("x2") }) -join ""
}

function Send-GitHubWebhook {
    param ($event, $json)
    $sig = Get-GitHubSignature -payload $json -secret $githubSecret
    if ($testMode) {
        Write-Host "`n[TEST MODE] Would send $event with payload:"
        Write-Output $json
        return
    }
    Invoke-RestMethod -Uri $webhookUrl -Method Post -Headers @{
        "Content-Type" = "application/json"
        "X-GitHub-Event" = $event
        "X-Hub-Signature" = $sig
    } -Body $json
}

function Create-PR {
    param ($userStory)
    $userStory = "$userStory".Trim().ToUpper()
    $assignedRepos = $repos | Get-Random -Count ($rand.Next(2, 5))
    $prs = @()

    foreach ($repo in $assignedRepos) {
        if (-not $repoPrCount.ContainsKey($repo)) {
            $repoPrCount[$repo] = 1
        } else {
            $repoPrCount[$repo]++
        }

        $prNum = $repoPrCount[$repo]
        $titleRaw = $titles | Get-Random
        $titleText = "$titleRaw".Trim()
        $prTitle = "$userStory - $titleText"
        $branch = Generate-Branch $userStory $titleText
        $payload = $prTemplate | ConvertTo-Json -Depth 10 | ConvertFrom-Json
        $payload.action = "opened"
        $payload.repository.name = $repo
        $pullRequest = [PSCustomObject]@{
            title = "$prTitle"
            head = @{ ref = "$branch" }
            base = @{ ref = "main" }
            number = $prNum
        }
        $payload.pull_request = $pullRequest

        $jsonPayload = $payload | ConvertTo-Json -Depth 10 -Compress
        Send-GitHubWebhook -event "pull_request" -json $jsonPayload
        Write-Host "✅ PR #$prNum for $repo → $prTitle"
        $prs += [PSCustomObject]@{
            repo = $repo
            number = $prNum
            title = $prTitle
        }
    }
    $userStoryMap[$userStory] = $prs
}

function Comment-On-OnePR {
    if ($userStoryMap.Count -eq 0) { return }

    $story = ($userStoryMap.Keys | Get-Random)
    $prs = $userStoryMap[$story]
    if (-not $prs -or $prs.Count -eq 0) {
        Write-Host "⚠️ No PRs found under $story to comment on."
        return
    }
    $pr = $prs | Get-Random

    $payload = $commentTemplate | ConvertTo-Json -Depth 10 | ConvertFrom-Json
    $payload.action = "created"
    $payload.comment.body = "/TEST_AND_MERGE"
    $payload.repository.name = $pr.repo

    # Ensure $payload.issue is a hashtable/object and title/number are set
    if (-not $payload.PSObject.Properties.Match('issue')) {
        $payload | Add-Member -MemberType NoteProperty -Name 'issue' -Value @{}
    }
    $payload.issue.number = $pr.number
    $payload.issue.title = $pr.title

    $json = $payload | ConvertTo-Json -Depth 10 -Compress
    Send-GitHubWebhook -event "issue_comment" -json $json
    Write-Host "💬 Comment on PR #$($pr.number) in $($pr.repo) → $($pr.title)"
}


# === MAIN LOOP ===

while ($true) {
    $userStory = Generate-NewUserStory
    Create-PR -userStory $userStory
    Start-Sleep -Seconds ($rand.Next(4, 8))
    Comment-On-OnePR
    Start-Sleep -Seconds ($rand.Next(3, 6))
}
