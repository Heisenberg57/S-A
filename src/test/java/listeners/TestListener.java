package listeners;

import base.BaseTest;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.ScreenshotUtils;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result){
        Object testClass = result.getInstance();

        if (testClass instanceof BaseTest){
            BaseTest baseTest = (BaseTest) testClass;

            ScreenshotUtils.takeScreenshot(
                    baseTest.getDriver(),
                    result.getMethod().getMethodName()

            );
        }
    }
}
