package ivan.demo.com.pageobject;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CustomAdapterPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='People Names']")
    private AndroidElement peopleNamesNote;

    public void pressPeopleNamesNote() {
        pressElementWithDuration(peopleNamesNote, 2);
    }
}
