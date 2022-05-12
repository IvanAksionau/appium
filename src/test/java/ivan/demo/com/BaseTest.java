package ivan.demo.com;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import ivan.demo.com.utils.PropsUtil;
import ivan.demo.com.utils.RuntimeUtil;
import ivan.demo.com.utils.WebDriverFactory;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public class BaseTest implements FilePathProvider {
    protected static final Properties PROPERTIES = PropsUtil.getProps();
    protected AndroidDriver<AndroidElement> driver;
    protected TouchAction<?> touchAction;
    protected WebDriverWait wait;

    @BeforeClass()
    public void setup() {
        RuntimeUtil.startAppiumServer();
        if (PROPERTIES.getProperty("emulator.location").equals("local")) {
            RuntimeUtil.startEmulator();
        }
    }

    @AfterClass
    public void stopService() {
        RuntimeUtil.stopAppiumServer();
        if (PROPERTIES.getProperty("emulator.location").equals("local")) {
            RuntimeUtil.stopEmulator();
        }
    }

    @BeforeMethod
    public void startDriver() {
        try {
            WebDriverFactory.initAndroidDriver(getResourceFilePath());
        } catch (SessionNotCreatedException e) {
            throw new SkipException("Check if Android device is connected", e);
        }
        driver = WebDriverFactory.getDriverInstance();
        touchAction = new TouchAction<>(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void tearDownDriver() {
        WebDriverFactory.quitAndroidDriver();
    }
}
