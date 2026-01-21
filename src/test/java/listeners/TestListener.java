package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public synchronized void onTestStart(ITestResult result) {
        ExtentTest extentTest =
                extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        if (test.get() != null) {
            test.get().pass("Test passed");
        }
    }

    @Override
    public synchronized void onTestFailure(ITestResult result) {

        if (test.get() == null) return; // SAFETY

        test.get().fail(result.getThrowable());

        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest) {
            BaseTest baseTest = (BaseTest) testClass;

            String screenshotPath = ScreenshotUtils.takeScreenshot(
                    baseTest.getDriver(),
                    result.getMethod().getMethodName()
            );

            if (screenshotPath != null) {
                test.get().addScreenCaptureFromPath(screenshotPath);
            }
        }
    }

    @Override
    public synchronized void onFinish(ITestContext context) {
        extent.flush();
    }
}
