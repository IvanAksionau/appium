package ivan.demo.com.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
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
            driverInstance = initRemoteAppiumServer();
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

    private static AndroidDriver<AndroidElement> initRemoteAppiumServer() {
        caps = new DesiredCapabilities();

        caps.setCapability("browserstack.user", PROPERTIES.getProperty("browserstack.user"));
        caps.setCapability("browserstack.key", PROPERTIES.getProperty("browserstack.key"));
        caps.setCapability(MobileCapabilityType.APP, PROPERTIES.getProperty("browserstack.appId"));
//        caps.setCapability("deviceOrientation", "landscape");
//        caps.setCapability("autoGrantPermissions", "true"); // for auto accepting permission pop-ups
//        caps.setCapability("browserstack.gpsLocation", "40.730610,-73.935242");// Simulate GPS location
        caps.setCapability("device", PROPERTIES.getProperty("browserstack.device"));
        caps.setCapability("os_version", PROPERTIES.getProperty("browserstack.os.version"));
        caps.setCapability("project", "TA with Appium test project example");
        caps.setCapability("build", LocalDate.now().toString());
//        caps.setCapability("name", "first_test");

        try {
            appiumServerUrl = new URL(PROPERTIES.getProperty("browserstack.server.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL value is not correct", e);
        }

        try {
            return new AndroidDriver<>(appiumServerUrl, caps);
        } catch (SessionNotCreatedException e) {
            LOGGER.info(e.getMessage());
            throw new SkipException("Check if Android device is connected", e);
        }
    }

    public static void quitAndroidDriver() {
        if (getDriverInstance() != null) {
            getDriverInstance().quit();
        }
    }
}
