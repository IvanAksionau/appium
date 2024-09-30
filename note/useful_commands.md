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

- **List all booted devices:**
```xcrun simctl list | egrep '(Booted)'```

- **Reset the iOS simulator by using the command line. The following command will erase all content and settings from the simulator:**
```xcrun simctl erase F5615B5E-A2A5-4355-B9CD-00CDD8CA3FA2```

- **Get id of connected physical device:**
```idevice_id -l```

- **kill appium session:**
```kill -9 $(pgrep -f appium)```


## Common Commands

- **Run Appium with adb_shell allowed:**
```appium --allow-insecure=adb_shell```
