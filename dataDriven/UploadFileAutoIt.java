package dataDriven;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

public class UploadFileAutoIt {

	WebDriver driver;


	public  void openBrowser(String URL) throws Exception  {
		//String dir=System.getProperty("user.dir");	
		String path = "drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path); 
		ChromeOptions option=new ChromeOptions();
		driver=new ChromeDriver(option);  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();  
		driver.get(URL);
		Thread.sleep(1000);
	}
	
	@Test
	public void uploadFile() throws Exception{
		
		openBrowser("https://demo.stqatools.com/fileupload.php");
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='fileToUpload']")).click();
		Thread.sleep(3000);
		Runtime.getRuntime().exec("D:\\Automation\\Selenium\\AutoIT\\upload.exe");
		Thread.sleep(1000);
		
	}
}
