package com.crm.vtiger.Invoice;

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

public class CreateInvoiceWithSalesAndOrganizationTest {

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
		
		//clicking on invoice link
		driver.findElement(By.xpath("//a[@href='index.php?module=Invoice&action=index']")).click();
		
		//verifying
		String invoiceTitle = "Administrator - Invoice - vtiger CRM 5 - Commercial Open Source CRM";
		if(driver.getTitle().equals(invoiceTitle))
		{
			System.out.println("Invoice page is displayed, PASS");
		}else
		{
			System.out.println("Invoice page is not displayed, FAIL");
		}
		
		//clicking on create invlice img
		driver.findElement(By.xpath("//img[@title='Create Invoice...']")).click();
		
		//getting data from the excelfile
		FileInputStream fileInputStreamExcel = new FileInputStream("./src/test/resources/data/InputData.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStreamExcel);
		
		//entering values in subject field
		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(0).getStringCellValue());
		
		//entering customer number
		driver.findElement(By.xpath("//input[@name='customerno']")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(1).getStringCellValue());
		
		//clicking on sales order
		driver.findElement(By.xpath("//input[@name='salesorder_name']/..//img[@src='themes/softed/images/select.gif']")).click();
		
		//performing action on create salesorder page
		String parentId = driver.getWindowHandle();
		Set<String> salesAllId = driver.getWindowHandles();
		
		for(String checkSales:salesAllId)
		{
			driver.switchTo().window(checkSales);
			if(driver.getCurrentUrl().contains("SalesOrder&action"))
			{
				//selecting searchbox and sending value
				driver.findElement(By.id("search_txt")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(4).getStringCellValue());
				
				//clicking on search now buttom
				driver.findElement(By.xpath("//input[@name='search']")).click();
				
				//selecting value
				driver.findElement(By.xpath("//a[text()='AKM']")).click();
				
			}
		}
		
		//switching to parent window
		driver.switchTo().window(parentId);
		
		//clicking on organization name img
		driver.findElement(By.xpath("//input[@id='single_accountid']/..//img[@title='Select']")).click();
		
		//performing actions on organization window
		Set<String> organizationAllId = driver.getWindowHandles();
		
		for(String checkOrganization : organizationAllId)
		{
			driver.switchTo().window(checkOrganization);
			if(driver.getCurrentUrl().contains("Accounts&action"))
			{
				//sending values on search box
				driver.findElement(By.id("search_txt")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(5).getStringCellValue());
				
				//clicking on search now buttom
				driver.findElement(By.xpath("//input[@name='search']")).click();
				
				//selecting values
				driver.findElement(By.xpath("//a[text()='AK Enterprises']")).click();
				
				//clicking on ok (ALERT)
				driver.switchTo().alert().accept();
			}
		}
		
		//switching back to parent window
		driver.switchTo().window(parentId);
		
		//passing value on billing address
		driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(2).getStringCellValue());
	
		//passing value on shipping address
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(3).getStringCellValue());
		
		//item details
		driver.findElement(By.xpath("//img[@id='searchIcon1']")).click();
		
		//performing actions on products window
		Set<String> productAllId = driver.getWindowHandles();
		
		for(String productCheck : productAllId)
		{
			driver.switchTo().window(productCheck);
			if(driver.getCurrentUrl().contains("Products&action"))
			{
				//sending value on search box
				driver.findElement(By.id("search_txt")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(6).getStringCellValue());
				
				//click on search now button
				driver.findElement(By.xpath("//input[@name='search']")).click();
				
				//selecting values
				driver.findElement(By.xpath("//a[text()='RK Product']")).click();
			}
		}
		
		//switching to main window
		driver.switchTo().window(parentId);
		
		//entering qty
		driver.findElement(By.xpath("//input[@id='qty1']")).sendKeys(workbook.getSheet("Invoice").getRow(1).getCell(7).getStringCellValue());
		
		//clicking on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();
		
		//verification
		String invoice = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(invoice.contains(workbook.getSheet("Invoice").getRow(1).getCell(0).getStringCellValue()))
		{
			System.out.println("Invoice is created, PASS");
		}else
		{
			System.out.println("Invoice is not created, FAIL");
		}
		
		//mousehover to administer and click on signout
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(adminElement).perform();

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();
		
	}

}
