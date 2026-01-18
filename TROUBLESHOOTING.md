# 🔧 Troubleshooting GitHub Pages

## ⏱️ Deployment Time

GitHub Pages deployment typically takes:
- **2-5 minutes** for the first deployment
- **1-2 minutes** for subsequent updates

## ✅ Check Deployment Status

### Step 1: Check GitHub Pages Settings
1. Go to your repository: `https://github.com/YOUR_USERNAME/number-adder-app`
2. Click **Settings** tab
3. Click **Pages** (left sidebar)
4. Look for:
   - ✅ Green checkmark = Deployed successfully
   - ⏳ Yellow circle = Still deploying
   - ❌ Red X = Error (check the error message)

### Step 2: Check the URL
Your app should be at:
**https://YOUR_USERNAME.github.io/number-adder-app/NumberAdder_Web.html**

## 🐛 Common Issues

### Issue 1: "404 Not Found"
**Possible causes:**
- GitHub Pages not enabled
- Wrong branch selected (should be `main`, not `master`)
- Wrong folder selected (should be `/ (root)`)
- File not in root directory

**Solution:**
1. Go to Settings → Pages
2. Source: Deploy from a branch
3. Branch: `main` (if you see `master`, change it)
4. Folder: `/ (root)`
5. Click Save
6. Wait 2-3 minutes

### Issue 2: "Site not found"
**Possible causes:**
- Repository is private (GitHub Pages only works with public repos on free accounts)
- Repository name doesn't match

**Solution:**
- Make sure repository is **Public**
- Check repository name matches: `number-adder-app`

### Issue 3: Still loading after 5+ minutes
**Solution:**
1. Check repository → Actions tab for any build errors
2. Try disabling and re-enabling GitHub Pages
3. Check if `NumberAdder_Web.html` is in the root directory

### Issue 4: Wrong branch name
If your branch is `master` instead of `main`:
```powershell
cd "c:\Coffeebar\AndroidApp"
git branch -M main
git push -u origin main
```
Then update GitHub Pages to use `main` branch.

## 🔍 Verify Files Are Pushed

Check if your files are on GitHub:
1. Go to: `https://github.com/YOUR_USERNAME/number-adder-app`
2. You should see `NumberAdder_Web.html` in the file list
3. If not, you need to push your code

## 📝 Quick Checklist

- [ ] Repository is **Public**
- [ ] Branch is **main** (or **master** if that's what you're using)
- [ ] GitHub Pages is enabled (Settings → Pages)
- [ ] Source is set to "Deploy from a branch"
- [ ] Folder is set to `/ (root)`
- [ ] `NumberAdder_Web.html` is in the root directory
- [ ] Waited at least 2-3 minutes after enabling

## 🆘 Still Not Working?

1. **Check the Actions tab** in your repository for build logs
2. **Try accessing the raw file directly:**
   `https://raw.githubusercontent.com/YOUR_USERNAME/number-adder-app/main/NumberAdder_Web.html`
3. **Check browser console** for any errors (F12 → Console)
4. **Try a different browser** or incognito mode

## 💡 Alternative: Use Raw GitHub URL

While waiting for GitHub Pages, you can access the file directly:
**https://raw.githubusercontent.com/YOUR_USERNAME/number-adder-app/main/NumberAdder_Web.html**

This works immediately but won't have the nice URL.

