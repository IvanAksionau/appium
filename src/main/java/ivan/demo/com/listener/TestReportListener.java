package ivan.demo.com.listener;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.util.List;
import java.util.Map;

public class TestReportListener implements IReporter {
    private static final String FILE_PATH = System.getProperty("user.dir") + System.getProperty("test.report.path");
    private static final ExtentReports REPORTS = new ExtentReports(FILE_PATH);

    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        for (ISuite suite : suites) {
            Map<String, ISuiteResult> result = suite.getResults();

            for (ISuiteResult r : result.values()) {
                ITestContext context = r.getTestContext();
                buildTestNodes(context.getPassedTests(), LogStatus.PASS);
                buildTestNodes(context.getFailedTests(), LogStatus.FAIL);
                buildTestNodes(context.getSkippedTests(), LogStatus.SKIP);
            }
        }
        REPORTS.flush();
    }

    private void buildTestNodes(IResultMap tests, LogStatus status) {
        ExtentTest test;
        if (tests.size() > 0) {
            for (ITestResult result : tests.getAllResults()) {
                test = REPORTS.startTest(result.getMethod().getMethodName());

                for (String group : result.getMethod().getGroups()) {
                    test.assignCategory(group);
                }

                String message = "Test " + status.toString().toLowerCase() + "ed";
                if (result.getThrowable() != null) {
                    message = result.getThrowable().getMessage();
                }
                test.log(status, message);
                REPORTS.endTest(test);
            }
        }
    }
}