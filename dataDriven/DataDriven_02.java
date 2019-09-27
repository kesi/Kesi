package dataDriven;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * @author Admin
 *
 */
public class DataDriven_02
{
	WebDriver driver;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;

	@BeforeTest
	public void TestSetup()
	{
		String path = "drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path); 
		ChromeOptions option=new ChromeOptions();
		driver=new ChromeDriver(option);  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();  	
	}

	@Test
	public void ReadData() throws IOException, InterruptedException
	{
		// Import excel sheet.
		File src=new File("D:/Automation/Selenium/Data/Login.xlsx");
		// Load the file.
		FileInputStream finput = new FileInputStream(src);
		// Load he workbook.
		workbook = new XSSFWorkbook(finput);
		// Load the sheet in which data is stored.
		sheet= workbook.getSheet("TestData");
		int rows=sheet.getLastRowNum();

		for(int i=1; i<=rows; i++)
		{
			// Import data for URL.
			System.out.println("Row "+i+" Data is: ");
			String url = sheet.getRow(i).getCell(1).getStringCellValue();
			System.out.println("URl is: "+url);
			driver.get(url);
			driver.manage().window().maximize();

			// Import data for Email.
			String username = sheet.getRow(i).getCell(2).getStringCellValue();
			System.out.println("username is: "+username);
			driver.findElement(By.id("emailId")).sendKeys(username);

			// Import data for password.
			String password = sheet.getRow(i).getCell(3).getStringCellValue();
			System.out.println("password is: "+password);
			driver.findElement(By.id("password")).sendKeys(password);

			driver.findElement(By.id("loginSubmit")).click();
			
			String expPagetitle = sheet.getRow(i).getCell(4).getStringCellValue();			
			System.out.println("expPagetitle is: "+expPagetitle);
			Thread.sleep(3000);
			System.out.println(" ");
			
			String actPageTitle=driver.getTitle();
			String status="";
			if(actPageTitle.contentEquals(expPagetitle)){
				status="PASS";
			}
			else{
				status="FAIL";
			}

			
			// Create cell where data needs to be written.
			sheet.getRow(i).createCell(5).setCellValue(status);

			// Specify the file in which data needs to be written.
			FileOutputStream fileOutput = new FileOutputStream(src);

			// finally write content
			workbook.write(fileOutput);

			// close the file
			fileOutput.close();

		}
	} 

	@AfterClass
	public void close(){
		driver.close();
	}

}
