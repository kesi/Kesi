package test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class WebTableElements {
	public static WebDriver  driver;

	public static String URL="https://demo.stqatools.com/WebTable.php";

	public static void Chrome_testing(String URL) throws Exception  {
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

	public static List<String> getRowData() throws Exception {
		//Chrome_testing(URL);
		String path = "drivers/chromedriver.exe";
		System.setProperty("webdriver.chrome.driver", path); 
		ChromeOptions option=new ChromeOptions();
		driver=new ChromeDriver(option);  
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();  
		driver.get(URL);
		List<WebElement> list=driver.findElements(By.xpath("//table[@class='table table-hover table-responsive table-list-search']//tbody/tr/td[2]"));
		int rowsize=list.size();
		System.out.println("Total rows are: "+rowsize);
		List<String> data=new ArrayList<String>();
		String text = "";
		/*for (WebElement e : list) {
			text=e.getText();
			data.add(text);
		}*/

		for(int i=1;i<=rowsize;i++){
			data.add(driver.findElement(By.xpath("//table[@class='table table-hover table-responsive table-list-search']/tbody/tr["+i+"]/td[2]")).getText());	
		}
		return data;	
	}

	public static ArrayList<String> getAllRowData() throws Exception{
		Chrome_testing(URL);
		List<WebElement> list=driver.findElements(By.xpath("//table[@class='table table-hover table-responsive table-list-search']//tbody/tr"));
		ArrayList<String> rowdata=new ArrayList<String>();
		for (int i = 1; i <=list.size(); i++) {
			rowdata.add(driver.findElement(By.xpath("//table[@class='table table-hover table-responsive table-list-search']/tbody/tr["+i+"]/td[1]")).getText());		
			rowdata.add(driver.findElement(By.xpath("//table[@class='table table-hover table-responsive table-list-search']/tbody/tr["+i+"]/td[2]")).getText());
			rowdata.add(driver.findElement(By.xpath("//table[@class='table table-hover table-responsive table-list-search']/tbody/tr["+i+"]/td[3]")).getText());
			rowdata.add(driver.findElement(By.xpath("//table[@class='table table-hover table-responsive table-list-search']/tbody/tr["+i+"]/td[4]")).getText());		
		}
		return rowdata;
	}

	public static ArrayList<String> getAllRowData1() throws Exception{
		Chrome_testing(URL);
		List<WebElement> rowcount=driver.findElements(By.xpath("//table[@class='table table-hover table-responsive table-list-search']//tbody/tr"));
		ArrayList<String> data=new ArrayList<String>();
		List<WebElement> header=driver.findElements(By.xpath("//table[@class='table table-hover table-responsive table-list-search']//thead//tr/th"));
		int col_count=header.size();
		for (int i = 1; i <=rowcount.size(); i++) {	
			for(int j=1;j<=col_count;j++){
				data.add(driver.findElement(By.xpath("//table[@class='table table-hover table-responsive table-list-search']/tbody/tr["+i+"]/td["+j+"]")).getText());
			}	
		}
		return data;
	}

	public static String getText() throws Exception{
		Chrome_testing(URL);
		return driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/table[1]/tbody[1]/tr[3]/td[4]")).getText();

	}

	public static String getAttributeValue() throws Exception{
		Chrome_testing(URL);
		return driver.findElement(By.xpath("//input[@id='system-search']")).getAttribute("placeholder");
		

	}

	public static void handlingKeyboard() throws Exception{
		Chrome_testing("http://192.168.10.16:9101/clientportal/ajg");
		/*driver.findElement(By.id("emailId")).sendKeys("xtdusr18@xeno.com");
		driver.findElement(By.id("password")).sendKeys("Satya@1234");
		driver.findElement(By.id("loginSubmit")).sendKeys(Keys.ENTER);
		String page=driver.getTitle();
		System.out.println("Page title: "+page);*/

		WebElement txtUsername = driver.findElement(By.id("emailId"));

		Actions builder = new Actions(driver);

		Action seriesOfActions = builder.moveToElement(txtUsername)
				.click()
				.keyDown(txtUsername, Keys.SHIFT)
				.sendKeys(txtUsername, "testing")
				.keyUp(txtUsername, Keys.SHIFT)
				.doubleClick(txtUsername)
				.contextClick()
				.build();
		seriesOfActions.perform() ;
		Thread.sleep(3000);

	}


	public static void mouseHover_Click() throws Exception{
		Chrome_testing("https://www.edureka.co/");		
		Actions builder = new Actions(driver);
		WebElement main_menu = driver.findElement(By.id("header_topcat"));
		WebElement sub_menu=driver.findElement(By.xpath("//li[@class='course_category_name_menu']//a[@id='devops-certification-courses']"));
		builder.moveToElement(main_menu).perform();
		Thread.sleep(3000);
		builder.click(sub_menu).build().perform();
		Thread.sleep(1000);
		String page =driver.getTitle();
		System.out.println("Page name: "+page);
	}


	public static void drag_Drop() throws Exception{
		Chrome_testing("https://jqueryui.com/droppable/");
		driver.switchTo().frame(0);
		WebElement SourceElement= driver.findElement(By.id("draggable"));
		WebElement TargetElement= driver.findElement(By.id("droppable"));
		Actions action = new Actions(driver);
		Thread.sleep(3000);
		action.dragAndDrop(SourceElement, TargetElement).build().perform();
		//action.clickAndHold(SourceElement).moveToElement(TargetElement).release().build().perform();
		Thread.sleep(3000);
	}
	
	public static void getFrames() throws Exception{
		Chrome_testing("https://demo.stqatools.com/Frames.php");
		JavascriptExecutor exe = (JavascriptExecutor) driver;
		Integer numberOfFrames = Integer.parseInt(exe.executeScript("return window.length").toString());
		System.out.println("NumberOfFrames: "+numberOfFrames);
		driver.switchTo().frame(5);
		driver.findElement(By.xpath("//button[@id='btnAlert']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();	
		driver.switchTo().defaultContent();	
		driver.findElement(By.xpath("//a[@class='navbar-brand']")).click();
		
	}
	
	public static void expliciWait() throws Exception{
		Chrome_testing("http://192.168.10.16:9101/clientportal/ajg");		
		driver.findElement(By.xpath("//input[@id='emailId']")).sendKeys("xtdusr18@xeno.com");
		driver.findElement(By.id("password")).sendKeys("Satya@1234");
		WebDriverWait wait = new WebDriverWait(driver, 2);
		WebElement submit=wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginSubmit1")));
		submit.click();
	}
	

	public static void main(String[] args) throws Exception {
		try {
			/*List<String>data=getRowData();
			System.out.println("Names are: "+data);*/

			/*ArrayList<String>allData=getAllRowData();
			System.out.println("All data: "+allData);*/

			/*ArrayList<String>allData=getAllRowData1();
			System.out.println("All data: "+allData);*/

			/*String text=getText();
			System.out.println("get text: "+text);*/

			/*String attributeValue=getAttributeValue();
			System.out.println("Attribute Value is: "+attributeValue);*/

			/*handlingKeyboard();*/
			
			/*mouseHover_Click();*/
			
			/*drag_Drop();*/
			
			getFrames();
			
			/*expliciWait();*/

			Thread.sleep(3000);
			driver.close();

		} catch (Exception e) {
			System.out.println("Exception message: "+e);
			Thread.sleep(3000);
			driver.close();
		}


	}




}
