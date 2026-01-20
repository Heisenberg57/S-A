package listeners;

import base.BaseTest;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import jdk.jfr.internal.consumer.OngoingStream;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reporting.ExtentManager;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

//    @Override
//    public void onTestFailure(ITestResult result){
//        Object testClass = result.getInstance();
//
//        if (testClass instanceof BaseTest){
//            BaseTest baseTest = (BaseTest) testClass;
//
//            ScreenshotUtils.takeScreenshot(
//                    baseTest.getDriver(),
//                    result.getMethod().getMethodName()
//
//            );

    private static ExtentReports extent = ExtentManager.getInstance();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result){
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestSuccess(ITestResult result){
        test.get().pass("Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result){
        Object testClass = result.getInstance();

        if(testClass instanceof BaseTest){
            BaseTest baseTest = (BaseTest) testClass;

            String screenshotPath = ScreenshotUtils.takeScreenshot(
                    baseTest.getDriver(),
                    result.getMethod().getMethodName()
            );

            test.get().fail(result.getThrowable());

            if(screenshotPath!=null){
                test.get().addScreenCaptureFromPath(screenshotPath);
            }




        }
    }

    @Override
    public void onFinish(ITestResult result){
        extent.flush();
    }
}

