# Number Adder - Android Application

A simple Android application that allows users to enter numbers, add them to a list, and view a summary with the total sum.

## Features

- **Number Input**: Enter numbers (supports decimals and negative numbers)
- **Add to List**: Add numbers to a scrollable list
- **Summary Display**: Shows total count of items and total sum
- **Clear All**: Button to clear all items and start fresh
- **Clean UI**: Modern, user-friendly interface with color-coded sections

## Project Structure

```
AndroidApp/
├── app/
│   ├── src/
│   │   └── main/
│   │       ├── java/com/example/numberadder/
│   │       │   └── MainActivity.java
│   │       ├── res/
│   │       │   ├── layout/
│   │       │   │   └── activity_main.xml
│   │       │   └── values/
│   │       │       ├── strings.xml
│   │       │       └── styles.xml
│   │       └── AndroidManifest.xml
│   └── build.gradle
├── build.gradle
└── README.md
```

## How to Build and Run

### Prerequisites
- Android Studio (latest version)
- Android SDK (API 21 or higher)
- Java JDK 8 or higher

### Steps

1. **Open in Android Studio**
   - Open Android Studio
   - Select "Open an Existing Project"
   - Navigate to the `AndroidApp` folder

2. **Sync Gradle**
   - Android Studio will automatically sync Gradle
   - Wait for dependencies to download

3. **Run the App**
   - Connect an Android device or start an emulator
   - Click the "Run" button (green play icon)
   - Or press `Shift + F10`

## Usage

1. **Enter a Number**: Type a number in the input field
2. **Add**: Click the "Add" button or press Enter
3. **View Items**: See all added numbers in the scrollable list
4. **View Summary**: Check the total count and sum at the bottom
5. **Clear**: Click "Clear All" to remove all items

## Features Details

- **Input Validation**: Only accepts valid numbers
- **Decimal Support**: Supports decimal numbers (e.g., 10.50)
- **Negative Numbers**: Supports negative numbers (e.g., -5)
- **Formatted Display**: Numbers are displayed with proper formatting (commas, 2 decimal places)
- **Scrollable List**: Can handle many items with scrolling
- **Visual Feedback**: Toast messages for user feedback

## Customization

You can customize the app by modifying:
- **Colors**: Edit `styles.xml` and layout colors
- **Layout**: Modify `activity_main.xml`
- **Functionality**: Update `MainActivity.java`

## Requirements

- **Minimum SDK**: 21 (Android 5.0 Lollipop)
- **Target SDK**: 34 (Android 14)
- **Compile SDK**: 34

## License

This is a simple example application for demonstration purposes.

