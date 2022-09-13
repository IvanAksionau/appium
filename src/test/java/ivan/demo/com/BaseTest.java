package ivan.demo.com;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import ivan.demo.com.utils.BrowserStackCloudUtil;
import ivan.demo.com.utils.PropsUtil;
import ivan.demo.com.utils.RuntimeUtil;
import ivan.demo.com.utils.WebDriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

public abstract class BaseTest {
    protected static final Properties PROPERTIES = PropsUtil.getProps();
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    protected AndroidDriver<AndroidElement> driver;
    protected TouchAction<?> touchAction;
    protected WebDriverWait wait;

    @BeforeClass()
    public void setup() {
        setResourceFilePath();
        if (PROPERTIES.getProperty("emulator.location").equals("local")) {
            RuntimeUtil.startAppiumServer();
            RuntimeUtil.startEmulator();
        } else {
            String apkId = BrowserStackCloudUtil.uploadApkFile(PROPERTIES.getProperty("apk"));
            PROPERTIES.setProperty("browserstack.appId", apkId);
        }
    }

    @AfterClass
    public void stopService() {
        if (PROPERTIES.getProperty("emulator.location").equals("local")) {
            RuntimeUtil.stopAppiumServer();
            RuntimeUtil.stopEmulator();
        }
    }

    @BeforeMethod
    public void startDriver() {
        LOGGER.info("startDriver method was called -----" + Thread.currentThread().getId());
        driver = WebDriverFactory.getInstance();
        touchAction = new TouchAction<>(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void tearDownDriver() {
        WebDriverFactory.quitAndroidDriver();
    }

    protected abstract void setResourceFilePath();
}
