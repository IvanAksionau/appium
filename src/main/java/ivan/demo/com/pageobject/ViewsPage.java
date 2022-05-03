package ivan.demo.com.pageobject;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class ViewsPage extends BasePage {
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Expandable Lists']")
    private AndroidElement expandableLists;

    public ExpandableListsPage openExpandableLists() {
        expandableLists.click();
        return new ExpandableListsPage();
    }
}
