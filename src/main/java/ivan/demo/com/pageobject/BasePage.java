package ivan.demo.com.pageobject;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import ivan.demo.com.utils.WebDriverFactory;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static io.appium.java_client.touch.LongPressOptions.longPressOptions;
import static io.appium.java_client.touch.offset.ElementOption.element;
import static java.time.Duration.ofSeconds;

public class BasePage {
    protected final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
    protected AndroidDriver<AndroidElement> driver;
    protected TouchAction<?> touchAction;

    protected BasePage() {
        this.driver = WebDriverFactory.getDriverInstance();
        touchAction = new TouchAction<>(this.driver);
        PageFactory.initElements(new AppiumFieldDecorator((driver)), this);
    }

    protected void pressElementWithDuration(AndroidElement element, long seconds) {
        touchAction.longPress(longPressOptions()
                        .withElement(element(element))
                        .withDuration(ofSeconds(seconds)))
                .release()
                .perform();
    }
}
