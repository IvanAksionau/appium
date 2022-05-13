package ivan.demo.com.listener;

import ivan.demo.com.utils.BrowserStackCloudUtil;
import ivan.demo.com.utils.PropsUtil;
import ivan.demo.com.utils.WebDriverUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

public class TestListener implements ITestListener {
    private static final Properties PROPERTIES = PropsUtil.getProps();

    @Override
    public void onTestSuccess(ITestResult result) {
        if (!PROPERTIES.getProperty("emulator.location").equals("local")) {
            BrowserStackCloudUtil.setTestSuccessStatus(result);
        }
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String date = "_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US).format(new Date());
        WebDriverUtil.getScreenshot(result.getMethod().getMethodName() + date);
        if (!PROPERTIES.getProperty("emulator.location").equals("local")) {
            BrowserStackCloudUtil.setTestFailureStatus(result);
        }
    }
}
