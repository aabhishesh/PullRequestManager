# CONFIGURATION
$webhookUrl     = "http://localhost:8080/github/webhook"
$githubSecret   = "DragonSLayer@2569"
$testMode       = $false  # set to $false to send real events

# Read templates (update paths if needed)
$prTemplate      = Get-Content -Raw -Path "./create-pr-refined.json"   | ConvertFrom-Json
$commentTemplate = Get-Content -Raw -Path "./comment-refined.json"     | ConvertFrom-Json

# Repositories and titles
$repos   = @("repoA","repoB","repoC","repoD","repoE","repoF","repoG","repoH","repoI","repoJ")
$titles  = @(
    "Fix login bug", "Add unit test", "Improve docs", "Refactor config", "Build deploy fix",
    "Increase timeout", "Cleanup logs", "Optimize queries", "Split service", "Enable tracing"
)
$rand = [System.Random]::new()
$usedStories = @{}
$userStoryMap = @{}

function Get-UniqueUserStory {
    do {
        $num = $rand.Next(100000,999999)
        $story = "US$num"
    } while ($usedStories.ContainsKey($story))
    $usedStories[$story] = $true
    return $story
}

function Slugify([string]$text) {
    # branch and PR safe
    return ($text -replace '[^\w\s-]', '' -replace '\s+', '-').ToLower()
}

function GithubSignature($payload, $secret) {
    $hmac = New-Object System.Security.Cryptography.HMACSHA1
    $hmac.Key = [Text.Encoding]::ASCII.GetBytes($secret)
    $hashBytes = $hmac.ComputeHash([Text.Encoding]::ASCII.GetBytes($payload))
    return "sha1=" + ($hashBytes | ForEach-Object { $_.ToString("x2") }) -join ""
}

function Send-Webhook($event, $json) {
    $sig = GithubSignature $json $githubSecret
    if ($testMode) {
        Write-Host "[TEST MODE] Would send ${event}: $json"
        return
    }
    Invoke-RestMethod -Uri $webhookUrl -Method Post -Headers @{
        "X-GitHub-Event" = $event
        "X-Hub-Signature" = $sig
        "Content-Type" = "application/json"
    } -Body $json
}

function Create-PRSet([string]$userStory) {
    $prSet = @()
    # 1-5 PRs for up to 10 repos (pick unique random repos)
    $prCount = $rand.Next(1,6)
    $assignedRepos = $repos | Get-Random -Count $prCount
    $prNumPerRepo = @{}  # To track PR number per repo

    foreach ($repo in $assignedRepos) {
        # Random repo-local PR number (for simulation/testing)
        if (-not $prNumPerRepo.ContainsKey($repo)) { $prNumPerRepo[$repo] = 1 }
        else { $prNumPerRepo[$repo]++ }

        $titleText = $titles | Get-Random
        $prTitle   = "$userStory-$(Slugify $titleText)"
        $branch    = $prTitle

        # Build PR payload
        $prPayload = $prTemplate | ConvertTo-Json -Depth 10 | ConvertFrom-Json
        $prPayload.action = "opened"
        $prPayload.repository.name = $repo

        $prObj = [PSCustomObject]@{
            title = $prTitle
            head  = @{ ref = $branch }
            base  = @{ ref = "main" }
            number = $prNumPerRepo[$repo]
        }
        $prPayload.pull_request = $prObj

        $json = $prPayload | ConvertTo-Json -Depth 10 -Compress
        Send-Webhook "pull_request" $json

        $prSet += [PSCustomObject]@{
            repo = $repo
            number = $prNumPerRepo[$repo]
            title = $prTitle
        }
        Write-Host "✅ Created PR #$($prNumPerRepo[$repo]) in $repo for $prTitle"
    }
     $userStoryMap[$userStory] = $prSet
    return $prSet
}

# function Send-Comment-On-PR([object[]]$prSet) {
#     if (-not $prSet -or $prSet.Count -eq 0) { return }
#     $targetPr = $prSet | Get-Random
#
#     $cmPayload = $commentTemplate | ConvertTo-Json -Depth 10 | ConvertFrom-Json
#     $cmPayload.action = "created"
#     $cmPayload.comment.body = "/TEST_AND_MERGE"
#     $cmPayload.issue.number = $targetPr.number
#     $cmPayload.repository.name = $targetPr.repo
#     $cmPayload.issue.title = $targetPr.title
#
#     $json = $cmPayload | ConvertTo-Json -Depth 10 -Compress
#     Send-Webhook "issue_comment" $json
#
#     Write-Host "💬 Comment sent to PR #$($targetPr.number) in $($targetPr.repo): $($targetPr.title)"
# }

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

function Send-Comment-On-PR {
Write-Host "Inside Send-Comment-On-PR"
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
    $payload.issue.number = $pr.number

    # --- FIX: Ensure the issue object exists and set the title
    if (-not $payload.PSObject.Properties['issue']) {
        $payload | Add-Member -MemberType NoteProperty -Name "issue" -Value (@{})
    }
    $payload.issue.title = $pr.title

    $payload.repository.name = $pr.repo
    $json = $payload | ConvertTo-Json -Depth 10 -Compress
    Send-GitHubWebhook -event "issue_comment" -json $json
    Write-Host "💬 Comment on PR #$($pr.number) in $($pr.repo) → $($pr.title)"
}



# -------------------------------------------
# GITHUB REAL COMMIT LOGIC (add this section)
# -------------------------------------------
$githubToken = "<YOUR_GITHUB_TOKEN>"
$org         = "your-real-org-name"   # <-- CHANGE to your GitHub organization

function Create-DummyBranchAndCommit {
    param (
        [string]$repo,
        [string]$branch,
        [string]$userStory,
        [string]$filePath = "dummy.txt"
    )
    $headers = @{
        Authorization = "Bearer $githubToken"
        Accept        = "application/vnd.github+json"
        "User-Agent"  = "PowerShell"
    }
    # Get SHA of main branch
    $mainRefUrl = "https://api.github.com/repos/$org/$repo/git/ref/heads/main"
    try {
        $mainRef = Invoke-RestMethod -Uri $mainRefUrl -Headers $headers
        $mainSha = $mainRef.object.sha
    } catch {
        Write-Host "❌ Could not fetch main branch for $repo: $_"
        return
    }
    # Create new branch for user story
    $branchRefUrl = "https://api.github.com/repos/$org/$repo/git/refs"
    $bodyBranch = @{
        ref = "refs/heads/$branch"
        sha = $mainSha
    } | ConvertTo-Json
    try {
        Invoke-RestMethod -Uri $branchRefUrl -Headers $headers -Method Post -Body $bodyBranch
        Write-Host "✅ Created branch $branch in $repo"
    } catch {
        # branch may already exist: ignore error
        if ($_.Exception.Response.StatusCode -ne 422) {
            Write-Host "❌ Error creating branch in $repo: $_"
        }
    }
    # Add dummy commit on the new branch (add/update the dummy file)
    $commitUrl = "https://api.github.com/repos/$org/$repo/contents/$filePath"
    $contentString = "Dummy change for $userStory"
    $encodedContent = [System.Convert]::ToBase64String([System.Text.Encoding]::UTF8.GetBytes($contentString))
    $commitBody = @{
        message = "Dummy commit for $userStory"
        content = $encodedContent
        branch  = $branch
    } | ConvertTo-Json
    try {
        Invoke-RestMethod -Uri $commitUrl -Headers $headers -Method Put -Body $commitBody
        Write-Host "✅ Dummy commit pushed to $repo on $branch"
    } catch {
        Write-Host "❌ Could not commit to $repo/$branch: $_"
    }
}
# -------------------------------------------


while ($true) {
    $userStory = Get-UniqueUserStory
    Create-PRSet $userStory  # 1. Create PRs
    Start-Sleep -Seconds ($rand.Next(4, 8))
    Send-Comment-On-PR                    # 2. Comment on one PR that just got created
    Start-Sleep -Seconds ($rand.Next(3, 6))
}
