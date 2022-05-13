package ivan.demo.com;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import ivan.demo.com.utils.BrowserStackCloudUtil;
import ivan.demo.com.utils.PropsUtil;
import ivan.demo.com.utils.RuntimeUtil;
import ivan.demo.com.utils.WebDriverFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
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
        if (PROPERTIES.getProperty("emulator.location").equals("local")) {
            RuntimeUtil.startAppiumServer();
            RuntimeUtil.startEmulator();
        } else {
            String apkId = BrowserStackCloudUtil.uploadApkFile(getResourceFilePath());
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
        WebDriverFactory.initAndroidDriver(getResourceFilePath());
        driver = WebDriverFactory.getDriverInstance();
        touchAction = new TouchAction<>(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void tearDownDriver() {
        WebDriverFactory.quitAndroidDriver();
    }
}
