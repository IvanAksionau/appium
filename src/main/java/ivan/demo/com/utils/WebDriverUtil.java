package ivan.demo.com.utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static ivan.demo.com.utils.WebDriverFactory.getDriverInstance;

public final class WebDriverUtil {
    private WebDriverUtil() {
    }

    public static void getScreenshot(String name) {
        String filePath = System.getProperty("user.dir") + "\\target\\screenshot_" + name + ".png";
        File screenshot = ((TakesScreenshot) getDriverInstance()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
