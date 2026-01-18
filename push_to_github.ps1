# PowerShell script to push to GitHub
# Make sure you've created the repository on GitHub first!

Write-Host "========================================" -ForegroundColor Cyan
Write-Host "GitHub Push Script" -ForegroundColor Cyan
Write-Host "========================================" -ForegroundColor Cyan
Write-Host ""

# Get GitHub username
$username = Read-Host "Enter your GitHub username"

# Get repository name
$repoName = Read-Host "Enter repository name (default: number-adder-app)"
if ([string]::IsNullOrWhiteSpace($repoName)) {
    $repoName = "number-adder-app"
}

Write-Host ""
Write-Host "Repository URL will be: https://github.com/$username/$repoName" -ForegroundColor Yellow
Write-Host ""
$confirm = Read-Host "Have you created this repository on GitHub? (y/n)"

if ($confirm -ne "y" -and $confirm -ne "Y") {
    Write-Host ""
    Write-Host "Please create the repository first:" -ForegroundColor Yellow
    Write-Host "1. Go to: https://github.com/new" -ForegroundColor White
    Write-Host "2. Repository name: $repoName" -ForegroundColor White
    Write-Host "3. Choose Public" -ForegroundColor White
    Write-Host "4. DO NOT check any boxes" -ForegroundColor White
    Write-Host "5. Click 'Create repository'" -ForegroundColor White
    Write-Host ""
    $ready = Read-Host "Press Enter when repository is created, or 'q' to quit"
    if ($ready -eq "q") {
        exit
    }
}

Write-Host ""
Write-Host "Setting up remote and pushing..." -ForegroundColor Green
Write-Host ""

# Remove existing remote if any
git remote remove origin 2>$null

# Add remote
git remote add origin "https://github.com/$username/$repoName.git"

# Rename branch to main (GitHub standard)
git branch -M main

# Push to GitHub
Write-Host "Pushing to GitHub..." -ForegroundColor Yellow
git push -u origin main

if ($LASTEXITCODE -eq 0) {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Green
    Write-Host "✅ Successfully pushed to GitHub!" -ForegroundColor Green
    Write-Host "========================================" -ForegroundColor Green
    Write-Host ""
    Write-Host "Repository URL: https://github.com/$username/$repoName" -ForegroundColor Cyan
    Write-Host ""
    Write-Host "To enable GitHub Pages:" -ForegroundColor Yellow
    Write-Host "1. Go to: https://github.com/$username/$repoName/settings/pages" -ForegroundColor White
    Write-Host "2. Source: Deploy from a branch" -ForegroundColor White
    Write-Host "3. Branch: main, Folder: / (root)" -ForegroundColor White
    Write-Host "4. Click Save" -ForegroundColor White
    Write-Host ""
    Write-Host "Your app will be live at:" -ForegroundColor Green
    Write-Host "https://$username.github.io/$repoName/NumberAdder_Web.html" -ForegroundColor Cyan
} else {
    Write-Host ""
    Write-Host "========================================" -ForegroundColor Red
    Write-Host "❌ Push failed!" -ForegroundColor Red
    Write-Host "========================================" -ForegroundColor Red
    Write-Host ""
    Write-Host "Possible issues:" -ForegroundColor Yellow
    Write-Host "- Repository doesn't exist or name is wrong" -ForegroundColor White
    Write-Host "- Authentication required (you may need to enter credentials)" -ForegroundColor White
    Write-Host "- Check if repository is created at: https://github.com/$username/$repoName" -ForegroundColor White
}

