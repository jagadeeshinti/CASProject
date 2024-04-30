package testCases;

import java.io.IOException;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.beCognizantPage;
import pageObjects.oneCognizantPage;
import pageObjects.timeSheetPage;
import testBase.baseClass;

public class TestCase_001_TimeSheetValidation extends baseClass {

	// for all methods loggers are generated and excel sheet is generated based on respective requirement or validation of that page
	
	// driver is declared in baseclass and initialized while object creation in each method
	
	// BeCognizant user verification by creating an object and implementing all its methods
	@Test(priority=1)
	public void userVerification() throws InterruptedException, IOException 
	{
		logger.info("----- starting TC001_beCognizant -----");
		beCognizantPage beCog = new beCognizantPage(driver);
		beCog.clickOnProfile();
		logger.info("clicked on profile");
		Thread.sleep(3000);
		System.out.println("Account verified");
		logger.info("verified the account");
		beCog.getProfileData();
		captureScreenShot("img_userprofile");
		beCog.closeProfile();
		Thread.sleep(15000);
		beCog.openOneCognizant(driver);
		logger.info("opening the OneCognizant");
		Thread.sleep(10000);
		beCog.windowHandlesOneCog(driver);
		logger.info("handled the OneCognizant window");
		captureScreenShot("img_onecognizant");
	}
	
	// OneCognizant validations by creating an object and implementing all its methods
	// by taking paramters (search, browser) from xml 
	@Test(priority=2)
	@Parameters({"search","browser"})
	public void oneCognizantFunctions(String search, String br) throws InterruptedException
	{
		logger.info("Navigated to OneCognizant");
		oneCognizantPage oneCog = new oneCognizantPage(driver); 
		Thread.sleep(4000);
		if(br.equalsIgnoreCase("edge")) {
			logger.info("opened on Edge.....");
			logger.info("clicked on search Icon");
			oneCog.inputSearchBarEdge(search);
			
			logger.info("input timesheet in search bar");
			captureScreenShot("img_timesheetIcon");
		}
		else {
			logger.info("opened on Edge.....");
			logger.info("clicked on search Icon");
			oneCog.inputSearchBarChrome(search);
			
			logger.info("input timesheet in search bar");
			captureScreenShot("img_timesheetIcon");
		}
		
		Thread.sleep(10000);
		oneCog.windowHandelsTimesheet(driver);
		logger.info("handled the timesheet window");
	}
	
	// Timesheet validation by creating an object and implementing all its methods
	@Test(priority=3)
	public void timeSheetNavigation() throws Exception
	{
		logger.info("Navigated to timesheet");
		timeSheetPage ts = new timeSheetPage(driver);
		
		ts.headerValidation();
		captureScreenShot("img_timesheet");
		logger.info("validating the header");
		
		Thread.sleep(1000);
		ts.threeWeeksTimesheetInfo();
		logger.info("three weeks of timesheet");
		captureScreenShot("img_firstThreeWeeks");
		
		Thread.sleep(1000);
		ts.currentWeekInfo();
		logger.info("Displayed current week");
		captureScreenShot("img_currentWeek");
		ts.dateValidationTimesheet();
		
		Thread.sleep(5000);
		ts.tsStatusApprovedInfo();
		logger.info("displayed  tsStatusApprovedInfo");
		captureScreenShot("img_StatusApproved");
		
		Thread.sleep(5000);
		ts.tsStatusOverdueInfo();
		logger.info("displayed tsStatusOverdueInfo");
		captureScreenShot("img_StatusOverdue");
		
		Thread.sleep(5000);
		ts.tsStatusPartiallyApprovedInfo();
		logger.info("displayed tsStatusPartiallyApprovedInfo");
		captureScreenShot("img_StatusPartiallyApproved");
		
		Thread.sleep(5000);
		ts.tsStatusPendingInfo();
		logger.info("displayed tsStatusPendingInfo ");
		captureScreenShot("img_StatusPending");
	
		Thread.sleep(5000);
		ts.tsStatusSavedInfo();
		logger.info("displayed tsStatusSavedInfo");
		captureScreenShot("img_StatusSaved");
		
		Thread.sleep(5000);
		ts.tsStatusSentBackforRevisionInfo();
		logger.info("displayed tsStatusSentBackforRevisionInfo");
		captureScreenShot("img_StatusSentBackforRevision");
		
		Thread.sleep(8000);
		ts.tsStatusSubmittedforApprovalInfo();
		logger.info("displayed tsStatusSubmittedforApprovalInfo");
		captureScreenShot("img_StatusSubmittedforApproval");
		
		logger.info("----- closing the browser -----");	
	}
	
	
}
