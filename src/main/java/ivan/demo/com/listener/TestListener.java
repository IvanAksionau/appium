package ivan.demo.com.listener;

import ivan.demo.com.utils.WebDriverUtil;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.internal.TestResult;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        String date = "_" + new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.US).format(new Date());
        WebDriverUtil.getScreenshot(result.getMethod().getMethodName() + date);
    }
}
