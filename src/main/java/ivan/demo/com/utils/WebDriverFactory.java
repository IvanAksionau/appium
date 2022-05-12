package ivan.demo.com.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public final class WebDriverFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverFactory.class);
    private static final Properties PROPERTIES = PropsUtil.getProps();
    private static AndroidDriver<AndroidElement> driverInstance;
    private static DesiredCapabilities caps;
    private static URL appiumServerUrl;

    private WebDriverFactory() {
    }

    public static AndroidDriver<AndroidElement> getDriverInstance() {
        return driverInstance;
    }

    public static void initAndroidDriver(String apkFilePath) {
        boolean isCloudBased = PROPERTIES.getProperty("emulator.location").equals("remote");
        if (isCloudBased) {
            driverInstance = initRemoteAppiumServer(apkFilePath);
        } else {
            driverInstance = initLocalAppiumServer(apkFilePath);
        }

        driverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.info("Android Driver was initiated");
    }

    private static AndroidDriver<AndroidElement> initLocalAppiumServer(String apkFilePath) {
        File apiFile = new File(apkFilePath);
        caps = new DesiredCapabilities();

        caps.setCapability(MobileCapabilityType.DEVICE_NAME, PROPERTIES.getProperty("device.name"));
        caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, PROPERTIES.getProperty("automation.name")); //uiAutomator2 framework is for Android testing
        caps.setCapability(MobileCapabilityType.APP, apiFile.getAbsolutePath());
        //check device is connected in cmd with 'adb devices' command
//        caps.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        try {
            appiumServerUrl = new URL(PROPERTIES.getProperty("appium.server.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL value is not correct", e);
        }
        return new AndroidDriver<>(appiumServerUrl, caps);
    }

    private static AndroidDriver<AndroidElement> initRemoteAppiumServer(String apkFilePath) {
        String appId = BrowserStackCloudUtil.uploadApkFile(apkFilePath);
        caps = new DesiredCapabilities();

        caps.setCapability("browserstack.user", PROPERTIES.getProperty("browserstack.user"));
        caps.setCapability("browserstack.key", PROPERTIES.getProperty("browserstack.key"));
        caps.setCapability(MobileCapabilityType.APP, appId);
//        caps.setCapability("deviceOrientation", "landscape");
//        caps.setCapability("autoGrantPermissions", "true"); // for auto accepting permission pop-ups
//        caps.setCapability("browserstack.gpsLocation", "40.730610,-73.935242");// Simulate GPS location
        caps.setCapability("device", "Google Pixel 3");
        caps.setCapability("os_version", "9.0");
        caps.setCapability("project", "First Java Project");
        caps.setCapability("build", "browserstack-build-1");
        caps.setCapability("name", "first_test");

        try {
            appiumServerUrl = new URL(PROPERTIES.getProperty("browserstack.server.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL value is not correct", e);
        }
        return new AndroidDriver<>(appiumServerUrl, caps);
    }

    public static void quitAndroidDriver() {
        if (getDriverInstance() != null) {
            getDriverInstance().quit();
        }
    }
}
