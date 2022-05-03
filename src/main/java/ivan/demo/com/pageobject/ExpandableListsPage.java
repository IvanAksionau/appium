package ivan.demo.com.pageobject;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ExpandableListsPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='1. Custom Adapter']")
    private AndroidElement customAdapter;

    public CustomAdapterPage openCustomAdapter() {
        customAdapter.click();
        return new CustomAdapterPage();
    }
}
