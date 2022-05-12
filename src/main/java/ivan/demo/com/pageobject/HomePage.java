package ivan.demo.com.pageobject;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class HomePage extends BasePage {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Views']")
    private AndroidElement views;

    public ViewsPage openVies() {
        LOGGER.info("open Vies page");
        views.click();
        return new ViewsPage();
    }
}
