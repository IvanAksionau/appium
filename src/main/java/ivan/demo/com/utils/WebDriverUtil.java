package ivan.demo.com.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public final class WebDriverUtil {
    private final static Properties properties = PropsUtil.getProperties();
    private static AndroidDriver<AndroidElement> driverInstance;

    public static AndroidDriver<AndroidElement> getDriverInstance() {
        return driverInstance;
    }

    public static void initAndroidDriver(String path) throws MalformedURLException {
        File apiFile = new File(path);

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, properties.getProperty("device.name"));
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, properties.getProperty("automation.name")); //uiAutomator2 framework is for Android testing
        cap.setCapability(MobileCapabilityType.APP, apiFile.getAbsolutePath());

        //check device is connected in cmd with 'adb devices' command
//        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");

        URL appiumServerLocation = new URL(properties.getProperty("appium.server.url"));
        driverInstance = new AndroidDriver<>(appiumServerLocation, cap);
        driverInstance.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
}
