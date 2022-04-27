package ivan.demo.com.generalstore;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import ivan.demo.com.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class GeneralStoreDemoTest extends BaseTest {

    @Test
    public void fillStartUpFormAndApply() {
        driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Let's it to begin");

        driver.hideKeyboard();//hide opened keyboard
        driver.findElementByXPath("//*[@text='Female']").click();
        driver.findElementById("android:id/text1").click();

        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"))");
        driver.findElementByXPath("//*[@text='Argentina']").click();

        driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();
        Assert.assertTrue(true, "No errors was thrown");
    }

    @Test
    public void validateToastErrorMessage() {
//        standard class name for toast message
//        android.widget.Toast
        driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

        String attribute = driver.findElementByXPath("//android.widget.Toast[1]").getAttribute("name");
        Assert.assertEquals(attribute, "Please enter your name", "Error message is expected");
    }

    @Test
    public void scrollToAndAddToCart() {
        driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Let's it to begin");
        driver.hideKeyboard();//hide opened keyboard
        driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))");
        driver.findElementByXPath(
                        "//*[@text='Jordan 6 Rings']//parent::android.widget.LinearLayout//*[@text='ADD TO CART']")
                .click();
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

        String attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.androidsample.generalstore:id/productName"))).getAttribute("name");

        Assert.assertEquals(attribute, "Jordan 6 Rings");
    }

    @Test
    public void switchFromNativeAPPToWebView() throws InterruptedException {
        driver.findElementById("com.androidsample.generalstore:id/nameField").sendKeys("Let's it to begin");
        driver.hideKeyboard();//hide opened keyboard
        driver.findElementById("com.androidsample.generalstore:id/btnLetsShop").click();

        driver.findElementByAndroidUIAutomator(
                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"Jordan 6 Rings\"))");
        driver.findElementByXPath(
                        "//*[@text='Jordan 6 Rings']//parent::android.widget.LinearLayout//*[@text='ADD TO CART']")
                .click();
        driver.findElementById("com.androidsample.generalstore:id/appbar_btn_cart").click();

//        AndroidElement checkBox = driver.findElementByClassName("android.widget.CheckBox");
//        touchAction.tap(tapOptions().withElement(element(checkBox))).perform();

        AndroidElement cond = driver.findElementByXPath("//*[@text='Please read our terms of conditions']");
        touchAction.longPress(longPressOptions()
                        .withElement(element(cond))
                        .withDuration(ofSeconds(3)))
                .release()
                .perform();
        String attribute = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("com.androidsample.generalstore:id/alertTitle"))).getAttribute("name");
        Assert.assertEquals(attribute, "Terms Of Conditions");

        driver.findElementById("android:id/button1").click();
        driver.findElementById("com.androidsample.generalstore:id/btnProceed").click();
        Thread.sleep(5000);

        String webViewKey = "WEBVIEW";
        String key = driver.getContextHandles().stream().filter(it -> it.contains(webViewKey)).findFirst().orElse("");
        driver.context(key); //set context to deal with WEB view
        System.out.println(driver.getCurrentUrl());

        driver.pressKey(new KeyEvent(AndroidKey.BACK));
        driver.context("NATIVE_APP"); //set context to deal with NATIVE view
    }

    @Override
    public String getResourceFilePath() {
        return "src/main/resources/General-Store.apk";
    }
}
