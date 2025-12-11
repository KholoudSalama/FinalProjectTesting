package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import utils.DriverManager;
import utils.ExtentReportManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    protected WebDriver driver;
    protected SoftAssert softAssert;
    protected static ExtentReports extent;
    protected ExtentTest test;
    protected static final String BASE_URL = "https://awesomeqa.com/ui/index.php?route=common/home";

    @BeforeSuite
    public void setUpSuite() {
        extent = ExtentReportManager.createInstance();
    }

    @BeforeMethod
    public void setUp(ITestResult result) {
        driver = DriverManager.getDriver();
        softAssert = new SoftAssert();

        String testName = result.getMethod().getMethodName();
        test = extent.createTest(testName);
        ExtentReportManager.setTest(test);

        driver.get(BASE_URL);
        test.log(Status.INFO, "Navigated to: " + BASE_URL);
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
            captureScreenshot(result.getMethod().getMethodName());
        } else if (result.getStatus() == ITestResult.SUCCESS) {
            test.log(Status.PASS, "Test Passed");
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
        }

        softAssert.assertAll();
        DriverManager.quitDriver();
    }

    @AfterSuite
    public void tearDownSuite() {
        ExtentReportManager.flush();
    }

    protected void captureScreenshot(String testName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String destination = System.getProperty("user.dir") + "/screenshots/" + testName + "_" + timestamp + ".png";

            Files.createDirectories(Paths.get(System.getProperty("user.dir") + "/screenshots"));
            Files.copy(source.toPath(), Paths.get(destination));

            test.addScreenCaptureFromPath(destination);
        } catch (IOException e) {
            test.log(Status.WARNING, "Failed to capture screenshot: " + e.getMessage());
        }
    }

    protected void logInfo(String message) {
        test.log(Status.INFO, message);
    }

    protected void logPass(String message) {
        test.log(Status.PASS, message);
    }

    protected void logFail(String message) {
        test.log(Status.FAIL, message);
    }
}