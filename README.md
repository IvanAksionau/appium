# Appium basics for Android AP

Preconditions:
- Download/install Java
- Download/install Android Studio and find out the Android SDK path
- Download/install Node(to install Appium server, as Appium server is written on Node)
- Set Java, Android SDK and Node Home Paths in Windows system
![img.png](img.png)     ![img_1.png](img_1.png)
- Install and start Appium server via node (run in cmd "npm install appium")

# Commands list(in case investigation):
- appium - it will start appium server
- appium --allow-insecure chromedriver_autodownload - it will start appium server and download appropriate version of chrome driver
- npm install appium --chromedriver_version="91" - so you can install specific chrome driver version(or download it directly to expected folder )
- UIAutomatorViewer - will start tool to search APP elements location during codding

# Start emulator manually(in case investigation):
- cd to C:\Users\user.name\AppData\Local\Android\Sdk\emulator
- run command in cmd as 'emulator -avd emulator-name'
- if not started - check the emulator name: in ~/AppData/Local/Android/Sdk/emulator run command 'emulator -list-avds'
- to find out an emulator name - : in ~/AppData/Local/Android/Sdk/emulator run command 'adb devices'