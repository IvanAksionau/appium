package ivan.demo.com.apidemos;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.TapOptions;
import ivan.demo.com.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class AppiumDemoTest extends BaseTest {

    @Test
    public void androidUIAutomatorDemoTest() {
//      execute Android code
//        driver.findElementByAndroidUIAutomator("attribute("value")");
        driver.findElementByAndroidUIAutomator("text(\"Views\")").click();

//      driver.findElementByAndroidUIAutomator("new UiSelector().property(value)").click();
        int size = driver.findElementsByAndroidUIAutomator("new UiSelector().clickable(true)").size();
        System.out.print(size);

        Assert.assertTrue(size > 5);
    }

    @Test
    public void pressAndHoldDemoTest() {
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();//get by text
        WebElement exListElement =
                driver.findElementByXPath("//android.widget.TextView[@text='Expandable Lists']");

        touchAction.tap(TapOptions.tapOptions().withElement(element(exListElement)))
                .perform();

        driver.findElementByXPath("//android.widget.TextView[@text='1. Custom Adapter']").click();

        AndroidElement pNamesElement =
                driver.findElementByXPath("//android.widget.TextView[@text='People Names']");
        touchAction.longPress(longPressOptions()
                        .withElement(element(pNamesElement))
                        .withDuration(ofSeconds(2)))
                .release()
                .perform();
        Assert.assertTrue(driver.findElementById("android:id/title").isDisplayed());
    }

    @Test
    public void swipeDemoTest() {
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Date Widgets']").click();//get by text
        driver.findElementByAndroidUIAutomator("new UiSelector().text(\"2. Inline\")").click();//get by text

        driver.findElementByXPath("//*[@content-desc='9']").click();
        AndroidElement element9 = driver.findElementByXPath("//*[@content-desc='15']");
        AndroidElement element15 = driver.findElementByXPath("//*[@content-desc='45']");

        touchAction.longPress(longPressOptions()
                        .withElement(element(element9))
                        .withDuration(ofSeconds(2)))
                .moveTo(element(element15))
                .release()
                .perform();
    }

    @Test
    public void scrollDemoTest() {
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"WebView\"))");

        Assert.assertTrue(
                driver.findElementByXPath("//android.widget.TextView[@text='WebView']").isDisplayed());
    }

    @Test
    public void dragAndDropDemoTest() {
        driver.findElementByXPath("//android.widget.TextView[@text='Views']").click();
        driver.findElementByXPath("//android.widget.TextView[@text='Drag and Drop']").click();
        AndroidElement srcElement = driver.findElementsByClassName("android.view.View").get(0);
        AndroidElement destElement = driver.findElementsByClassName("android.view.View").get(1);
//        touchAction.longPress(longPressOptions().withElement(element(srcElement)))
//                .moveTo(element(destElement))
//                .release()
//                .perform();
        touchAction.longPress(element(srcElement))
                .moveTo(element(destElement))
                .release()
                .perform();


        Assert.assertTrue(true, "No errors was thrown");
    }

    @Override
    public String getResourceFilePath() {
        return "src/main/resources/ApiDemos-debug.apk";
    }
}
