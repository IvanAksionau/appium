package ivan.demo.com.apidemos;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AppActions {

    private static AndroidDriver<AndroidElement> driver;

    public static void main(String[] args) throws MalformedURLException {
        driver = getPreparedAndroidDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();//get by text
        driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
        driver.findElementById("android:id/checkbox").click(); //get by id
        driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();//get by tagName and index
        driver.findElementByClassName("android.widget.EditText").sendKeys("Hello the Planet");//set by className and index
        driver.findElementsByClassName("android.widget.Button").get(1).click();//set by className and collection index

        driver.quit();
    }

    public static AndroidDriver<AndroidElement> getPreparedAndroidDriver() throws MalformedURLException {
        File apiFile = new File("src/main/resources/ApiDemos-debug.apk");

        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 4a API 31");
        cap.setCapability(MobileCapabilityType.APP, apiFile.getAbsolutePath());

        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");

        driver = new AndroidDriver<>(
                new URL("http://127.0.0.1:4723/wd/hub"), cap);
        return driver;
    }
}
