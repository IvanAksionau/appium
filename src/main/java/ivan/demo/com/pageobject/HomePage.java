package ivan.demo.com.pageobject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Views']")
    private WebElement views;
    private AndroidDriver<AndroidElement> driver;

    public HomePage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator((driver)), this);
    }

    public ViewsPage openVies(){
        views.click();
        return new ViewsPage(driver);
    }
}
