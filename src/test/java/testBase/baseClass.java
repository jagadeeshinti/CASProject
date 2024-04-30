package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class baseClass 
{
	public static WebDriver driver;
	public static Logger logger;
	public Properties p;
	
	
//		Taking argument of type of browser as a parameter from xml file 
//		Giving link as config file as appURL
//		Initiating browser as per argument

	@BeforeClass
	@Parameters({"browser"})
	public void setUpBrowser(String br) throws IOException, InterruptedException 
	{
			//Loading property file
			FileReader file=new FileReader(".//src//test//resources//config.properties");
			p=new Properties();
			p.load(file);
			
			//loading log4j file
			logger= LogManager.getLogger(this.getClass());
			
			if(br.equalsIgnoreCase("Chrome"))
			{
				driver=new ChromeDriver(); 
				System.out.println("Testing in Chrome...........");
				System.out.println("");
			}
			else if(br.equalsIgnoreCase("Edge")) 
			{
				driver=new EdgeDriver();
				System.out.println("Testing in Edge...........");
				System.out.println("");
			}
			
			driver.get(p.getProperty("appURL"));
			Thread.sleep(30000);
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
 
	}
	
	// Quitting browser function
	@AfterClass
	public void tearDown() 
	{
		
		driver.quit();
		baseClass.getLogger().info("closing the browser");
	}
	
	// Declaring and Initiating logger
	public static Logger getLogger()
    {
    	logger=LogManager.getLogger(); //Log4j
    	return logger;
    }
 
	// Screenshot method
	public String captureScreenShot(String name) 
	
	{
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
		String targetFilePath=System.getProperty("user.dir")+"\\screenshots\\" + name + "_" + timeStamp + ".png";
		File targetFile=new File(targetFilePath);
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}

