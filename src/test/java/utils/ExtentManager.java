package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    // private constructor عشان مانعملش new
    private ExtentManager() {}

    public static ExtentReports getInstance() {
        if (extent == null) {
            // مكان ملف التقرير
            ExtentSparkReporter spark =
                    new ExtentSparkReporter("test-output/ExtentReport.html");

            spark.config().setReportName("OpenCart Automation Report");
            spark.config().setDocumentTitle("Test Results");

            extent = new ExtentReports();
            extent.attachReporter(spark);

            extent.setSystemInfo("Tester", "Reda");
            extent.setSystemInfo("Environment", "QA");
        }
        return extent;
    }
}
