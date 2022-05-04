package ivan.demo.com.pageobject;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class CustomAdapterPage extends BasePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='People Names']")
    private AndroidElement peopleNamesNote;

    @AndroidFindBy(id = "android:id/title")
    private AndroidElement sampleMenuWindow;

    public CustomAdapterPage pressPeopleNamesNote() {
        pressElementWithDuration(peopleNamesNote, 2);
        return this;
    }

    public boolean isSampleMenuDisplayed() {
        return sampleMenuWindow.isDisplayed();
    }
}
