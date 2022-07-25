package com.crm.vtiger.Assets;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateAssetsWithInvoiceTest {

	public static void main(String[] args) throws Throwable {

		WebDriver driver = null;

		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/data/config.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);

		String browser = properties.getProperty("browser");

		if (browser.equalsIgnoreCase("firefox")) {
			// setting up browser
			WebDriverManager.firefoxdriver().setup();

			// creating object for browser
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("chrome")) {
			// setting up browser
			WebDriverManager.chromedriver().setup();

			// creating object for browser
			driver = new ChromeDriver();
		}

		// maximizing the browser
		driver.manage().window().maximize();

		// passing the url
		driver.get(properties.getProperty("url"));

		// passing wait condition
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		String loginTitle = "vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger Login page is not displayed, FAIL");
		}

		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys(properties.getProperty("username"));
		driver.findElement(By.name("user_password")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// passing explicitly wait for getting home page to loaded
		wait.until(ExpectedConditions.titleContains("Administrator"));
		System.out.println("VTiger Home page is displayed, PASS");

		// mouse hover to more
		WebElement moreElement = driver.findElement(By.xpath("//a[text()='More']"));
		Actions action = new Actions(driver);
		action.moveToElement(moreElement).perform();
		
		//click on assets
		driver.findElement(By.xpath("//a[@name='Assets']")).click();
		
		//verification
		String assetsTitle = "Administrator - Assets - vtiger CRM 5 - Commercial Open Source CRM";
		if(driver.getTitle().equals(assetsTitle))
		{
			System.out.println("Asserts page is displayed, PASS");
		}else
		{
			System.out.println("Asserts page is not displayed, FAIL");
		}
		
		//clicking on new assets img
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		
		//code for deriving data from excel sheet
		FileInputStream fileInputStreamExcel = new FileInputStream("./src/test/resources/data/InputData.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStreamExcel);
		
//		//entering assetNo field
//		driver.findElement(By.xpath("//input[@id='asset_no']")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(0).getStringCellValue());
		
		//entering asset serial number
		driver.findElement(By.xpath("//input[@name='serialnumber']")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(1).getStringCellValue());
		
		//clicking on product name img
		driver.findElement(By.xpath("//input[@name='product_display']/..//img[@src='themes/softed/images/select.gif']")).click();
		
		//performing action on product tab
		String parentId = driver.getWindowHandle();
		Set<String> productAllId = driver.getWindowHandles();
		
		for(String productCheck : productAllId)
		{
			driver.switchTo().window(productCheck);
			if (driver.getCurrentUrl().contains("Products&action"))
			{
				// selecting searchbox and sending value
				driver.findElement(By.id("search_txt")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(2).getStringCellValue());

				// clicking on search now buttom
				driver.findElement(By.xpath("//input[@name='search']")).click();

				// selecting value
				driver.findElement(By.xpath("//a[text()='RK Product']")).click();

			}
		}
		
		//switching to parent window
		driver.switchTo().window(parentId);
		
		//clicking on customer name img
		driver.findElement(By.xpath("//input[@id='account_display']/..//img[@src='themes/softed/images/select.gif']")).click();
		
		//performing action on customer name window
		Set<String> customerAllId = driver.getWindowHandles();
		
		for(String customerCheck : customerAllId)
		{
			driver.switchTo().window(customerCheck);
			if(driver.getCurrentUrl().contains("Accounts&action"))
			{
				// selecting searchbox and sending value
				driver.findElement(By.id("search_txt")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(3).getStringCellValue());

				// clicking on search now buttom
				driver.findElement(By.xpath("//input[@name='search']")).click();

				// selecting value
				driver.findElement(By.xpath("//a[text()='AK Enterprises']")).click();
				
			}
		}
		
		//switch to main window
		driver.switchTo().window(parentId);
		
		//entering asset name
		driver.findElement(By.xpath("//input[@id='assetname']")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(4).getStringCellValue());
		
		//click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		//verification
		String asset = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if(asset.contains(workbook.getSheet("Assets").getRow(1).getCell(4).getStringCellValue()))
		{
			System.out.println("Asset is created, PASS");
		}else
		{
			System.out.println("Asset is not created, FAIL");
		}
		
		// mousehover to administer and click on signout
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(adminElement).perform();

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();
		
	}

}
