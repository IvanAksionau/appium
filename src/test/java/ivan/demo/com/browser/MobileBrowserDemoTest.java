package ivan.demo.com.browser;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import ivan.demo.com.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileBrowserDemoTest extends BaseTest {

    @Test
    public void checkProductDescription() {
        driver.findElementByLinkText("Browse Products").click();

        WebElement element = driver.findElementByLinkText("Devops");
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
        element.click();
        String text = "this course help us to understand automation testing";

        Assert.assertEquals(
                driver.findElementByXPath("//p[@class='product-description']").getText(), text.toUpperCase());
    }

    @Override
    protected AndroidDriver<AndroidElement> getPreparedAndroidDriver() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability(MobileCapabilityType.DEVICE_NAME, "Pixel 2 API 31");
        cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiAutomator2");
        cap.setCapability(MobileCapabilityType.BROWSER_NAME, "Chrome");
//        cap.setCapability("chromedriverExecutable","src/main/resources/chromedriver.exe");

        URL appiumServerLocation = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AndroidDriver<>(appiumServerLocation, cap);
        driver.get("https://rahulshettyacademy.com/angularAppdemo/");
        return driver;
    }
}
