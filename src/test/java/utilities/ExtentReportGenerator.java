package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.baseClass;

public class ExtentReportGenerator implements ITestListener {

	// Spark reporter for the extent report
	public ExtentSparkReporter sparkReporter;
	// Extent report object
	public ExtentReports extent;
	// Test object for the extent report
	public ExtentTest test;
 
	// Name of the report
	String repName;
 
	// This method is invoked at the start of the test execution
	public void onStart(ITestContext testContext) 
	{
		// Generate a timestamp for the report name
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		repName = "Test-Report-" + timeStamp + ".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\" + repName);
 
		// Configure the spark reporter
		sparkReporter.config().setDocumentTitle("BeCognizant Header view Report"); 
		sparkReporter.config().setReportName("BeCognizant Functional Testing"); 
		sparkReporter.config().setTheme(Theme.STANDARD);
		// Initialize the extent report and attach the spark 
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		// Set system information for the report
		extent.setSystemInfo("Application", "Timesheet");
		extent.setSystemInfo("User Id", System.getProperty("user.name"));
		extent.setSystemInfo("User Name", "Jagadeesh Inti" );
		extent.setSystemInfo("Environemnt", "Quality Assurance");
		// Get the browser parameter from the test context and set it in the report
		String browser = testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
		// Get the included groups from the test context and set it in the report
		List<String> includedGroups = testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty())
		{
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
 
	// This method is invoked when a test passes
	public void onTestSuccess(ITestResult result) 
	{
		// Create a new test in the report and assign the group category
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups()); // to display groups in report
		// Log the test status in the report
		test.log(Status.PASS,result.getName()+" got successfully executed");
	}
 
	// This method is invoked when a test fails
	public void onTestFailure(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		// Log the test status and the exception message in the report
		test.log(Status.FAIL,result.getName()+" got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		try
		{
			// Capture a screenshot and add it to the report
			String imgPath = new baseClass().captureScreenShot(result.getName());
			test.addScreenCaptureFromPath(imgPath);
		} 
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}
 
	// This method is invoked when a test is skipped
	public void onTestSkipped(ITestResult result) 
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+" got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
 
	// This method is invoked at the end of the test execution
	public void onFinish(ITestContext testContext) 
	{
		// Flush the extent report to write the data to the report file
		extent.flush();
		String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+repName;
		File extentReport = new File(pathOfExtentReport);
		try 
		{
			// Open the extent report in the default browser
			Desktop.getDesktop().browse(extentReport.toURI());
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
 
	}
	
	
}
