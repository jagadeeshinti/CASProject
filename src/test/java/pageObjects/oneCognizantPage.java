package pageObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class oneCognizantPage extends basePage {

	// parameterized constructor
	public oneCognizantPage(WebDriver driver)
	{
		super(driver);
		
	}
	
	// xpaths to searchbar (chrome), search icon (edge), searchbar (edge), timesheet elements
	@FindBy(xpath="//*[@id='oneC_searchAutoComplete']")
	WebElement searchBar;
	
	@FindBy(xpath="//*[@class='searchTopBar']")
	WebElement searchEdgeIcon;
	
	@FindBy(xpath="//*[@id='oneCSearchTop']")
	WebElement searchBarEdge;
	
	@FindBy(xpath="//*[@id='newSearchQALST']//div[contains(text(),'Submit Timesheet')]")
	WebElement timeSheetIcon;
	
//		giving parameter as input from xml file using parameters which will be implemented from test case 
//		after giving parameter, it'll show timesheet icon on search feed 

	
	// if chrome, input giving method
	public void inputSearchBarChrome(String input)
	{
		searchBar.sendKeys(input);
		
		timeSheetIcon.click();
	}
	
	// if edge, input giving method
	public void inputSearchBarEdge(String input) throws InterruptedException
	{
		//searchBar.sendKeys(input);
		searchEdgeIcon.click();
		searchBarEdge.sendKeys(input);
		Thread.sleep(2000);
		timeSheetIcon.click();
	}
	
	// window handling to timesheet function by driver switching
	public void windowHandelsTimesheet(WebDriver driver) throws InterruptedException
	{
		Set <String> Window = driver.getWindowHandles();
	    List <String> Window1 = new ArrayList<String>(Window);
	    //Timesheet's Window Handle - Window1.get(2)
		driver.switchTo().window(Window1.get(2));
		
	}
	
}
