# Quick Push to GitHub

## Step 1: Create Repository on GitHub
1. Go to: **https://github.com/new**
2. Repository name: `number-adder-app`
3. Description: "Number Adder app - Android & Web"
4. Choose **Public** ✅
5. **DO NOT** check any boxes
6. Click **"Create repository"**

## Step 2: Run These Commands

Replace `YOUR_USERNAME` with your actual GitHub username:

```powershell
cd "c:\dev\docs\cursorBay\results\AndroidApp"
git remote add origin https://github.com/YOUR_USERNAME/number-adder-app.git
git branch -M main
git push -u origin main
```

## Step 3: Enable GitHub Pages

1. Go to: `https://github.com/YOUR_USERNAME/number-adder-app/settings/pages`
2. Source: **Deploy from a branch**
3. Branch: **main**, Folder: **/ (root)**
4. Click **Save**

## Done! 🎉

Your app will be live at:
**https://YOUR_USERNAME.github.io/number-adder-app/NumberAdder_Web.html**

