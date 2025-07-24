# ========== CONFIGURATION ==========
$token = "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"     # <-- Replace with your PAT
$org = "temp-prm-demo-org"                             # <-- Replace with your org name
$baseRepoName = "sample-repo"
$repoCount = 10
$visibility = "public"                                  # or "private"
$dummyReadmeContent = "Sample readme" #<-- DO NOT REMOVE THIS, THE "@" TERMINATES THE HERE-STRING

# ========== FUNCTION ==========
function Create-GitHubRepo {
    param(
        [string]$repoName
    )

    $url = "https://api.github.com/orgs/$org/repos"
    $headers = @{
        Authorization = "Bearer $token"
        Accept        = "application/vnd.github+json"
        "User-Agent"  = "PowerShell"
    }

    $body = @{
        name      = $repoName
        private   = if ($visibility -eq "private") { $true } else { $false }
        auto_init = $true
    } | ConvertTo-Json -Depth 3

    try {
        $response = Invoke-RestMethod -Uri $url -Method Post -Headers $headers -Body $body
        Write-Host "✅ Created repository: $repoName" -ForegroundColor Green
        Update-Readme -repoName $repoName
    }
    catch {
        Write-Host "❌ Failed to create $repoName - $($_.Exception.Message)" -ForegroundColor Red
    }
}

function Update-Readme {
    param(
        [string]$repoName
    )

    $getUrl = "https://api.github.com/repos/$org/$repoName/contents/README.md"
    $headers = @{
        Authorization = "Bearer $token"
        Accept        = "application/vnd.github+json"
        "User-Agent"  = "PowerShell"
    }

    try {
        $existing = Invoke-RestMethod -Uri $getUrl -Method Get -Headers $headers
        $updateUrl = "https://api.github.com/repos/$org/$repoName/contents/README.md"
        $encodedContent = [System.Convert]::ToBase64String([System.Text.Encoding]::UTF8.GetBytes($dummyReadmeContent))
        $updateBody = @{
            message = "Update README with dummy content"
            content = $encodedContent
            sha     = $existing.sha
        } | ConvertTo-Json -Depth 3
        Invoke-RestMethod -Uri $updateUrl -Method Put -Headers $headers -Body $updateBody
        Write-Host "📝 Updated README.md for: $repoName" -ForegroundColor Cyan
    }
    catch {
        Write-Host "⚠️ Could not update README for $repoName - $($_.Exception.Message)" -ForegroundColor Yellow
    }
}

# ========== MAIN LOOP ==========
for ($i = 1; $i -le $repoCount; $i++) {
    $repoName = "$baseRepoName-$i"
    Create-GitHubRepo -repoName $repoName
}