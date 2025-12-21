package utils;

/*Only three steps to generate the extend repports

1. Add depenedency
2. Add ExtendReporter in Utility (This implements a listener class of TestNG, by impplementing that interface ITestListener you are overriding all methods 
in that interface to this ExtendReporter)
3. Run the cases through TestNG.xml file, which all Utilities we created we need to place it in TestNG.xml file.
*/

/*For Allure report it is even simpler, you just have to add the allure dependency, but it will generate only json files in the allure-results folder
To get it into a report, we need few steps.
1. Install allure dependency
2. Install apache maven at OS level
3. Install allure binaries for windows - allurereport.org/docs/install-for-windows
4. Generate the JSON in output in allure-results folder
5. Right click and open the allure-results folder in terminal
6. Give the command "allure serve" + path of the allure results folder
   eg: allure serve C:\Automation\myWorkSpace\OnlineAPI\allure-results*/

import org.testng.ITestListener;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.testng.ITestContext;

import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class implements TestNG's ITestListener interface to generate an Extent Report
 * for test execution results.
 */
public class ExtentReporter implements ITestListener {
    private static ExtentReports extent;
    private static ExtentSparkReporter sparkReporter;
    private static ThreadLocal<ExtentTest> testNode = new ThreadLocal<>();
    private static Map<String, ExtentTest> testSuiteMap = new ConcurrentHashMap<>();
    private static String reportName;

    /**
     * Initializes the ExtentReports instance if not already created.
     * Ensures thread safety for test reporting.
     *
     * @return ExtentReports instance
     */
    public synchronized static ExtentReports getExtentInstance() {
        if (extent == null) {
            // Generate a unique report name with timestamp
            String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
            reportName = "Extent-Report-" + timeStamp + ".html";
            String reportPath = System.getProperty("user.dir") + "\\reports\\" + reportName;

            // Configure the ExtentSparkReporter
            sparkReporter = new ExtentSparkReporter(reportPath);
            sparkReporter.config().setDocumentTitle("API Automation Test Report");
            sparkReporter.config().setReportName("API Test Execution Summary");
            sparkReporter.config().setTheme(Theme.STANDARD);

            // Initialize ExtentReports and attach the reporter
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            extent.setSystemInfo("Host Name", "Localhost");
            extent.setSystemInfo("Environment", "QA");
            extent.setSystemInfo("User", System.getProperty("user.name"));
        }
        return extent;
    }

    /**
     * Called when a test suite starts. Creates a test suite node in the Extent Report.
     *
     * @param context The test execution context
     */
    @Override
    public void onStart(ITestContext context) {
        String suiteName = context.getSuite().getName();
        ExtentTest suiteNode = getExtentInstance().createTest(suiteName);
        testSuiteMap.put(context.getName(), suiteNode);

        suiteNode.info("Suite started: " + suiteName);
        suiteNode.assignCategory("Suite: " + suiteName);
    }

    /**
     * Called when a test method starts. Creates a test node within the suite.
     *
     * @param result The test result object
     */
    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest suiteNode = testSuiteMap.get(result.getTestContext().getName());
        ExtentTest methodNode = suiteNode.createNode(result.getMethod().getMethodName());
        methodNode.assignCategory(result.getTestContext().getName());
        testNode.set(methodNode);
    }

    /**
     * Called when a test method passes. Logs success status in the report.
     *
     * @param result The test result object
     */
    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentTest test = testNode.get();
        test.log(Status.PASS, result.getMethod().getMethodName() + " passed.");
        test.info("Execution Time: " + getExecutionTime(result) + " ms");
    }

    /**
     * Called when a test method fails. Logs failure status and error message in the report.
     *
     * @param result The test result object
     */
    @Override
    public void onTestFailure(ITestResult result) {
        ExtentTest test = testNode.get();
        test.log(Status.FAIL, result.getMethod().getMethodName() + " failed.");
        test.log(Status.FAIL, result.getThrowable());
        test.info("Execution Time: " + getExecutionTime(result) + " ms");
    }

    /**
     * Called when a test method is skipped. Logs skipped status in the report.
     *
     * @param result The test result object
     */
    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentTest test = testNode.get();
        test.log(Status.SKIP, result.getMethod().getMethodName() + " skipped.");
        test.info("Reason: " + result.getThrowable());
        test.info("Execution Time: " + getExecutionTime(result) + " ms");
    }

    /**
     * Called when the test suite execution is finished.
     * Logs summary information and generates the final report.
     *
     * @param context The test execution context
     */
    @Override
    public void onFinish(ITestContext context) {
        ExtentTest suiteNode = testSuiteMap.get(context.getName());
        suiteNode.info("Suite finished: " + context.getSuite().getName());
        suiteNode.info("Passed: " + context.getPassedTests().size());
        suiteNode.info("Failed: " + context.getFailedTests().size());
        suiteNode.info("Skipped: " + context.getSkippedTests().size());

        getExtentInstance().flush();
        //openReport(); // Uncomment to open the report automatically after execution
    }

    /**
     * Opens the generated Extent Report in the default web browser.
     */
    private void openReport() {
        try {
            File reportFile = new File(System.getProperty("user.dir") + "\\reports\\" + reportName);
            Desktop.getDesktop().browse(reportFile.toURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Calculates and returns the execution time of a test method.
     *
     * @param result The test result object
     * @return Execution time in milliseconds
     */
    private long getExecutionTime(ITestResult result) {
        return result.getEndMillis() - result.getStartMillis();
    }
}
