# 🔧 Network Troubleshooting - GitHub Connection

## Error: "Could not resolve host: github.com"

This means your computer can't reach GitHub's servers. Here are solutions:

## ✅ Quick Fixes:

### 1. Check Internet Connection
- Make sure you're connected to the internet
- Try opening https://github.com in your browser

### 2. Try Using SSH Instead of HTTPS
If HTTPS doesn't work, try SSH:

```bash
# Change remote to SSH
git remote set-url origin git@github.com:tzion-gvili/number-adder-app.git

# Then push
git push -u origin main
```

**Note:** You'll need SSH keys set up for this to work.

### 3. Check Firewall/Proxy
- If you're on a corporate network, GitHub might be blocked
- Try using a different network (mobile hotspot, home WiFi)
- Check if a VPN is needed

### 4. Try Different DNS
Temporarily use Google DNS:
- Windows: Change DNS to 8.8.8.8 and 8.8.4.4
- Or use: 1.1.1.1 (Cloudflare)

### 5. Use GitHub Desktop (Alternative)
If command line doesn't work:
1. Download GitHub Desktop: https://desktop.github.com/
2. Sign in with your GitHub account
3. Add the repository
4. Push with a click!

### 6. Manual Upload (Last Resort)
If nothing works:
1. Go to: https://github.com/tzion-gvili/number-adder-app
2. Click "uploading an existing file"
3. Drag and drop all files from `c:\Coffeebar\AndroidApp`
4. Commit directly on GitHub

## 🔍 Diagnostic Commands:

```powershell
# Test connectivity
ping github.com

# Check DNS
nslookup github.com

# Test HTTPS connection
curl https://github.com
```

## 💡 Most Common Solutions:

1. **Corporate Network**: Use mobile hotspot or home WiFi
2. **VPN Required**: Connect to VPN if your company requires it
3. **Firewall Blocking**: Temporarily disable firewall to test
4. **DNS Issue**: Change DNS servers (8.8.8.8, 8.8.4.4)

## 🚀 Alternative: Use GitHub Web Interface

If network issues persist, you can upload files directly:
1. Create repository on GitHub.com
2. Click "uploading an existing file"
3. Drag files from `c:\Coffeebar\AndroidApp`
4. Commit and push

This bypasses git entirely!

