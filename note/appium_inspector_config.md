# Appium Inspector Configuration

This document provides setup instructions for different environments where you can run your Appium tests. 

## Physical Android Device Setup

To set up a physical Android device, use the following configuration:

```json
{
  "appium:deviceName": "R58M52NYQTN",
  "platformName": "Android",
  "appium:automationName": "UIAutomator2",
  "appium:disableIdLocatorAutocompletion": true,
  "appium:noReset": true,
  "appium:app": "/path/to/your.apk"
}
```

To set up an IOS emulator, use the following configuration:

```json
{
  "appium:deviceName": "iphone 14",
  "platformName": "iOS",
  "appium:disableIdLocatorAutocompletion": true,
  "appium:automationName": "xcuitest",
  "noReset": true,
  "appium:udid": "E655E1AD-1ADA-4752-9B8B-54280AA23B25",
  "appium:app": "/path/to/your.zip"
}
```

To set up an Android emulator, use the following configuration:

```json
{
  "appium:deviceName": "emulator-5554",
  "platformName": "Android",
  "appium:platformVersion": "14",
  "appium:disableIdLocatorAutocompletion": true,
  "noReset": true,
  "appium:app": "/path/to/your.apk",
  "appium:automationName": "UIAutomator2"
}
```
Please replace `/path/to/your.apk` and `/path/to/your.zip` with the actual paths to your APK and ZIP files, respectively.
