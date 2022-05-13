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
- before you come to command bellow, please create emulator via Android Studio or consider creation from code 
- cd to C:\Users\user.name\AppData\Local\Android\Sdk\emulator
- run command in cmd as 'emulator -avd emulator-name'
- if not started - check the emulator name: in ~/AppData/Local/Android/Sdk/emulator run command 'emulator -list-avds'
- to find out an emulator name - : in ~/AppData/Local/Android/Sdk/emulator run command 'adb devices'

# Maven commands:
- mvn test - so <activeByDefault>true</activeByDefault> profile will be executed, configured into pom.xml
- mvn test -Psmoke - so you can execute a specific test profile(ex. 'smoke'), configured into pom.xml
- mvn test -Demulator.name=emulator-5554 - so you can specify any property

# Commands to run Jenkins:
- be informed, that a file 'jenkins.war' is added to project root path
- java -jar jenkins.war -httpPort=8080
- http://localhost:8080/

# BrowserStack:
- in global.properties file you should specify browserstack.user and browserstack.key values, what you got during registration on BrowserStack site

# Useful links:
- https://appium.io/docs/en/writing-running-appium/web/chromedriver/
- https://developer.android.com/studio/debug/dev-options.html
- https://docs.oracle.com/javase/7/docs/api/java/lang/ThreadLocal.html
- https://www.browserstack.com/