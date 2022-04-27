package ivan.demo.com;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest implements FilePathProvider {
    protected AndroidDriver<AndroidElement> driver;
    protected TouchAction<?> touchAction;
    protected WebDriverWait wait;

    protected AndroidDriver<AndroidElement> getPreparedAndroidDriver() throws MalformedURLException {
        File apiFile = new File(getResourceFilePath());

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4a API 31");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2"); //uiAutomator2 framework is for Android testing
        cap.setCapability(MobileCapabilityType.APP, apiFile.getAbsolutePath());

        //check device is connected in cmd with 'adb devices' command
//        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Device");

        URL appiumServerLocation = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(appiumServerLocation, cap);
        return driver;
    }

    @BeforeClass
    public void setup() throws MalformedURLException {
        driver = getPreparedAndroidDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        touchAction = new TouchAction<>(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
