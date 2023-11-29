# Appium 2 Installation Guide on MacBook

## 1. Install Homebrew (if not already installed):

- **open the Terminal on your MacBook**
- **Enter the following command to install Homebrew:**
```/bin/bash -c "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/HEAD/install.sh)"```
- **Follow the on-screen instructions to complete the installation**
- **After installation, you might need to add Homebrew to your PATH. The Terminal will provide the necessary instructions for this**

## 2. Install Node.js and npm:
- **npm is distributed with Node.js, which means that when you download Node.js, you automatically get npm installed on your computer**
- **To install Node.js and npm, use Homebrew by typing the following command in the Terminal:**
```brew install node"```
- **After installation, verify the installation of Node.js and npm by typing:**
```node -v"```
```npm -v"```

## 3. Install Appium via Homebrew:
- **Install Appium with the following command:**
```brew install appium"```

## 4. Install Appium 2.0 Beta:
- **For the Appium 2.0 beta version, use the NPM command:**
```npm install -g appium@next"```

## 5. Install Appium Drivers:
- **Install specific drivers for the platforms you want to test. For example, to install the XCUITest and UiAutomator2 drivers, use:**
```appium driver install xcuitest"```
```appium driver install uiautomator2"```

## 6. List and Update Drivers:
- **To list available drivers and check which ones are installed, use:**
```appium driver list"```

## 7. When it is done try command to see the installed version number:
- **To list available drivers and check which ones are installed, use:**
```appium -v"```
