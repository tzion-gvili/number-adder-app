# GitHub Setup Instructions

## Step 1: Create GitHub Repository

1. Go to [GitHub.com](https://github.com) and sign in
2. Click the **"+"** icon in the top right → **"New repository"**
3. Repository name: `number-adder-app` (or any name you prefer)
4. Description: "Simple Android and Web app for adding numbers and calculating totals"
5. Choose **Public** (so GitHub Pages works for free)
6. **DO NOT** initialize with README, .gitignore, or license (we already have these)
7. Click **"Create repository"**

## Step 2: Connect and Push

After creating the repository, GitHub will show you commands. Use these:

```bash
cd "c:\dev\docs\cursorBay\results\AndroidApp"
git remote add origin https://github.com/YOUR_USERNAME/number-adder-app.git
git branch -M main
git push -u origin main
```

Replace `YOUR_USERNAME` with your actual GitHub username.

## Step 3: Enable GitHub Pages (For Web Version)

1. Go to your repository on GitHub
2. Click **Settings** tab
3. Scroll down to **Pages** (in left sidebar)
4. Under **Source**, select **"Deploy from a branch"**
5. Select branch: **main**
6. Select folder: **/ (root)**
7. Click **Save**
8. Wait a few minutes, then your app will be live at:
   `https://YOUR_USERNAME.github.io/number-adder-app/NumberAdder_Web.html`

## Alternative: Using GitHub CLI (if installed)

If you have GitHub CLI installed, you can do it all from command line:

```bash
cd "c:\dev\docs\cursorBay\results\AndroidApp"
gh repo create number-adder-app --public --source=. --remote=origin --push
gh repo view --web
```

Then enable GitHub Pages as described in Step 3 above.

## Quick Access

Once GitHub Pages is enabled, you can access your app from any device at:
- **Web Version**: `https://YOUR_USERNAME.github.io/number-adder-app/NumberAdder_Web.html`
- **Repository**: `https://github.com/YOUR_USERNAME/number-adder-app`

## Need Help?

If you encounter any issues:
1. Make sure you're logged into GitHub
2. Check that the repository name doesn't already exist
3. Verify your git credentials are set up correctly

