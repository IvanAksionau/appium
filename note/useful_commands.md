# Useful Commands

This document enumerates useful commands that you can use during Android and iOS development.

## For Android

- **List all connected devices:**
```adb devices```

- **Get the name of a specific AVD:**
```adb -s emulator-5554 emu avd name```

## For iOS

- **List all available devices:**
```xcrun simctl list devices```

## Common Commands

- **Run Appium with adb_shell allowed:**
```appium --allow-insecure=adb_shell```
