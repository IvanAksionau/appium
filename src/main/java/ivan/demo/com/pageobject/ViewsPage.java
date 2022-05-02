package ivan.demo.com.pageobject;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class ViewsPage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expandable Lists']")
    private WebElement expandableLists;
    private AndroidDriver<AndroidElement> driver;

    public ViewsPage(AndroidDriver<AndroidElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator((driver)), this);
    }

    public void openExpandableLists(){
        expandableLists.click();
    }
}
