package com.inetBanking.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reporting extends TestListenerAdapter {

    public ExtentHtmlReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    // Initialize ExtentReports and attach the ExtentHtmlReporter
    public void onStart(ITestContext testContext) {
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        String reportName = "Test-Report-" + timeStamp + ".html";
        htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir") + "/test-output/" + reportName);
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        extent.setSystemInfo("Host Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("User", "Vipul Sharma");

        htmlReporter.config().setDocumentTitle("InetBanking Test Report");
        htmlReporter.config().setReportName("Functional Test Report");
        htmlReporter.config().setTheme(Theme.STANDARD);
    }

    // Create an Extent report with test name and description
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName(), result.getMethod().getDescription());
    }

    // Print test results in the report
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + " Test Case PASSED", ExtentColor.GREEN));
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " Test Case FAILED", ExtentColor.RED));

        String screenshotPath = System.getProperty("user.dir") + "/Screenshots/" + result.getName() + ".png";
        File f = new File(screenshotPath);
        if (f.exists()) {
            try {
                test.fail("Screenshot is below: " + test.addScreenCaptureFromPath(screenshotPath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test Case Skipped: " + result.getMethod().getMethodName());
    }

    public void onFinish(ITestContext testContext) {
        extent.flush();
    }
}
