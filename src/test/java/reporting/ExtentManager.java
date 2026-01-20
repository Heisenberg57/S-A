package reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports getInstance(){
        if(extent==null){
            ExtentSparkReporter reporter = new ExtentSparkReporter("reports/extent-report.html");

            reporter.config().setReportName("Automation Test Report");
            reporter.config().setDocumentTitle("Selenium Framework Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }

        return extent;
    }
}
