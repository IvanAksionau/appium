package ivan.demo.com;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import ivan.demo.com.utils.PropsUtil;
import ivan.demo.com.utils.WebDriverUtil;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;
import java.util.Properties;

public class BaseTest implements FilePathProvider {
    protected static final int PORT = 4723;
    private static AppiumDriverLocalService service;
    protected AndroidDriver<AndroidElement> driver;
    protected TouchAction<?> touchAction;
    protected WebDriverWait wait;
    protected Properties properties = PropsUtil.getProperties();

    @BeforeClass(timeOut = 30000)
    public void setup() {
        service = new AppiumServiceBuilder()
                .withIPAddress(properties.getProperty("ip.address"))
                .usingPort(PORT)
                .build();
        if (!service.isRunning()) {
            service.start();
        }

//        try {
//            Runtime.getRuntime().exec(System.getProperty("user.dir") + "/src/main/resources/startEmulator.bat");
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @AfterClass
    public void stopService() {
        if (service != null) {
            service.stop();
        }
    }

    @BeforeMethod
    public void startDriver() throws MalformedURLException {
        WebDriverUtil.initAndroidDriver(getResourceFilePath());
        driver = WebDriverUtil.getDriverInstance();
        touchAction = new TouchAction<>(driver);
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void tearDownDriver() {
        if (WebDriverUtil.getDriverInstance() != null) {
            WebDriverUtil.getDriverInstance().quit();
        }
    }
}
