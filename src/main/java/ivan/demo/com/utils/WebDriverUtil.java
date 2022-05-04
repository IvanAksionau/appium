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

public final class WebDriverUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebDriverUtil.class);
    private final static Properties PROPERTIES = PropsUtil.getProperties();
    private static AndroidDriver<AndroidElement> driverInstance;

    private WebDriverUtil() {
    }

    public static AndroidDriver<AndroidElement> getDriverInstance() {
        return driverInstance;
    }

    public static void quitAndroidDriver(){
        if (getDriverInstance() != null) {
            getDriverInstance().quit();
        }
    }

    public static void initAndroidDriver(String path) {
        URL appiumServerLocation;
        File apiFile = new File(path);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, PROPERTIES.getProperty("device.name"));
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, PROPERTIES.getProperty("automation.name")); //uiAutomator2 framework is for Android testing
        cap.setCapability(MobileCapabilityType.APP, apiFile.getAbsolutePath());

        //check device is connected in cmd with 'adb devices' command
//        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");
        try {
            appiumServerLocation = new URL(PROPERTIES.getProperty("appium.server.url"));
        } catch (MalformedURLException e) {
            throw new RuntimeException("URL value is not correct", e);
        }
        driverInstance = new AndroidDriver<>(appiumServerLocation, cap);
        driverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        LOGGER.info("Android Driver was initiated");
    }
}
