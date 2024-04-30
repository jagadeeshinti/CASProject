package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import utilities.ExcelUtilities;

public class beCognizantPage extends basePage{

	WebDriver driver;
	
	// parameterized constructor
	public beCognizantPage(WebDriver driver) 
	{
		super(driver);
	
	}
	
	// xpaths for userprofile, name, emailId, profile close, OneCognizant elements

	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement userprofile;
	
	@FindBy(id="mectrl_currentAccount_primary")
	WebElement name;
	
	@FindBy(id="mectrl_currentAccount_secondary")
	WebElement emailId;
	
	@FindBy(xpath="//*[@class='_8ZYZKvxC8bvw1xgQGSkvvA==']")
	WebElement profileXpathClose;
	
	@FindBy(id="mectrl_headerPicture")
	WebElement profileIdClose;
	
	@FindBy(xpath="//div[@title='OneCognizant']")
	WebElement oneCognizant;
	
	

	// clicking on profile function
	public void clickOnProfile()
	{
		userprofile.click();
		
	}
	
	// fetching profile data function
	public void getProfileData() throws IOException
	{
		
		String userName = name.getText();
		String userEmail = emailId.getText();
		System.out.println("--------------- Personal Info ---------------");
		System.out.println("Name     : "+userName+"\nMail Id  : "+userEmail);
		System.out.println("---------------------------------------------");
		System.out.println("");
		ExcelUtilities.writeExcel("Profile Info", 0, 0, userName);
		ExcelUtilities.writeExcel("Profile Info", 0, 1, userEmail);
		
	}
	
	// closing profile function
	public void closeProfile()
	{
		
		try
		{
			profileXpathClose.click();
		}
		catch(Exception e)
		{
			profileIdClose.click();
		}
		
	}
	
	// redirecting / opening OneCognizant function
	public void openOneCognizant(WebDriver driver) throws InterruptedException
	{
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement ss = driver.findElement(By.xpath("//*[@href='/sites/Business/SitePages/Gen-AI-Hub.aspx']"));
		js.executeScript("arguments[0].scrollIntoView();",ss);
		Thread.sleep(8000);
		WebElement flag=driver.findElement(By.xpath("//div[@title='OneCognizant']"));
		js.executeScript("arguments[0].scrollIntoView();",flag);
		Thread.sleep(4000);
		flag.click();
	}
	
	// window handling to OneCognizant function by driver switching
	public void windowHandlesOneCog(WebDriver driver) throws InterruptedException
	{
		
		Thread.sleep(10000);
		Set <String> Window = driver.getWindowHandles();
	    List <String> Window1 = new ArrayList<String>(Window); 
	    //One Cognizant's Window Handle - Window1.get(1)
	    driver.switchTo().window(Window1.get(1));
	    
	}
	
}
