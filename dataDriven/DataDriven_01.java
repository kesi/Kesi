package dataDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class DataDriven_01 {
	String workdir=System.getProperty("user.dir");
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
		//Thread.sleep(5000);
	}

	
	/**
	 * get data from excel using testcase name and column name.
	 * 
	 * */
	public String getData_TCID_Colunm(String tcname,String colunmName) throws IOException{

		FileInputStream fis = new FileInputStream(workdir+"\\Data\\Login.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("TestData");
		XSSFRow row ;		
		int rows=sheet.getLastRowNum();	
		int rownumber=0;
		for(int k=1;k<=rows;k++){		
			row = sheet.getRow(k);	
			String cname=row.getCell(0).getStringCellValue().trim();		
			if(cname.contentEquals(tcname)){			
				rownumber=k;
				break;
			}
		}
		row = sheet.getRow(0);	
		int col_num = -1 ;
		for(int i=0; i<row.getLastCellNum(); i++)
		{
			if(row.getCell(i).getStringCellValue().trim().equals(colunmName))
				col_num = i;
		}
		row = sheet.getRow(rownumber);
		XSSFCell cell = row.getCell(col_num);
		

		return cell.getStringCellValue();
	}

	@Test(enabled=false)
	public void getData() throws Exception{

		XSSFWorkbook srcBook = new XSSFWorkbook(workdir+"\\Data\\Login.xlsx");  
		XSSFSheet sourceSheet = srcBook.getSheet("TestData");
		XSSFRow sourceRow = sourceSheet.getRow(1);
		XSSFCell cell1=sourceRow.getCell(1);
		XSSFCell cell2=sourceRow.getCell(2);
		XSSFCell cell3=sourceRow.getCell(3);
		
		String url=cell1.getStringCellValue();
		String username=cell2.getStringCellValue();
		String password=cell3.getStringCellValue();

		System.out.println("cell1: "+url);
		System.out.println("cell2: "+username);
		System.out.println("cell3: "+password);
		openBrowser(url);
		driver.findElement(By.id("emailId")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("loginSubmit")).click();
		
	}

	
	
	@Test(enabled=false)
	public void TC_Login_01() throws Exception{
		String tcname=new Exception().getStackTrace()[0].getMethodName().toString();
	
		String url=getData_TCID_Colunm(tcname,"URL");
		String username=getData_TCID_Colunm(tcname,"Username");
		String password=getData_TCID_Colunm(tcname,"Password");

		openBrowser(url);
		driver.findElement(By.id("emailId")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("loginSubmit")).click();
		
	}
	
	@Test(enabled=true)
	public void TC_Login_02() throws Exception{
		String tcname=new Exception().getStackTrace()[0].getMethodName().toString();
		System.out.println("TC Name: "+tcname);
		String url=getData_TCID_Colunm(tcname,"URL");
		String username=getData_TCID_Colunm(tcname,"Username");
		String password=getData_TCID_Colunm(tcname,"Password");

		openBrowser(url);
		driver.findElement(By.id("emailId")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("loginSubmit")).click();
		
	}
	
	

}
