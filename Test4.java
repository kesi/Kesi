package sample;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test4 {
	public WebDriver driver;
	 
	 @Parameters({"browser"})
	 @BeforeClass
	 public void beforeClass(String browser) throws MalformedURLException{
	  DesiredCapabilities capability= new DesiredCapabilities();
	  capability.setBrowserName(browser);
	  	  driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capability);
	  	
	 }
	  
	 @Test
	 public void search() throws Exception {
		 driver.get("http://qa.xtbills.com/");
		 driver.findElement(By.id("TxtEmail")).sendKeys("kyenugu@xtglobal.com");
		 driver.findElement(By.id("TxtPassword")).sendKeys("xeno@123"); 
		 driver.findElement(By.id("BtnLogin")).click(); 
		 Thread.sleep(15000);
		
	 }
	 @AfterClass
	 public void afterClass(){
	  driver.quit();
	 }
}
