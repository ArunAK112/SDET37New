package com.crm.vtiger.Invoice;

import java.io.FileInputStream;
import java.util.Set;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateInvoiceWithSalesAndOrganizationTest {

	public static void main(String[] args) throws Throwable {

		ExcelFileLibrary elib = new ExcelFileLibrary();
		PropertyFileLibrary plib = new PropertyFileLibrary();
		JavaUtility jlib = new JavaUtility();
		WebDriverCommonLibrary wlib = new WebDriverCommonLibrary();

		WebDriver driver = null;

		String browser = plib.getPropertyData("browser");

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
		wlib.maximizeTheWindow(driver);

		// passing the url
		driver.get(plib.getPropertyData("url"));

		// passing wait condition
		wlib.waitTillPageGetsLoadImplicitlyWait(driver, 10);

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		String loginTitle = "vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger Login page is not displayed, FAIL");
		}

		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys(plib.getPropertyData("username"));
		driver.findElement(By.name("user_password")).sendKeys(plib.getPropertyData("password"));
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wlib.waitForPageTitle("Administrator", driver, 10);
		System.out.println("VTiger Home page is displayed, PASS");

		// mouse hover to more
		WebElement moreElement = driver.findElement(By.xpath("//a[text()='More']"));
		wlib.mouseHoverOnElement(moreElement, driver);

		// clicking on invoice link
		driver.findElement(By.xpath("//a[@href='index.php?module=Invoice&action=index']")).click();

		// verifying
		String invoiceTitle = "Administrator - Invoice - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(invoiceTitle)) {
			System.out.println("Invoice page is displayed, PASS");
		} else {
			System.out.println("Invoice page is not displayed, FAIL");
		}

		// clicking on create invlice img
		driver.findElement(By.xpath("//img[@title='Create Invoice...']")).click();

		// getting data from the excelfile
		// entering values in subject field
		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys(elib.getExcelData("Invoice", 1, 0));

		// entering customer number
		driver.findElement(By.xpath("//input[@name='customerno']")).sendKeys(elib.getExcelData("Invoice", 1, 1));

		// clicking on sales order
		driver.findElement(By.xpath("//input[@name='salesorder_name']/..//img[@src='themes/softed/images/select.gif']"))
				.click();

		// performing action on create salesorder page
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("SalesOrder&action", driver);
		// selecting searchbox and sending value
		driver.findElement(By.id("search_txt")).sendKeys(elib.getExcelData("Invoice", 1, 4));

		// clicking on search now buttom
		driver.findElement(By.xpath("//input[@name='search']")).click();

		// selecting value
		driver.findElement(By.xpath("//a[text()='AKM']")).click();

		// switching to parent window
		driver.switchTo().window(parentId);

		// clicking on organization name img
		driver.findElement(By.xpath("//input[@id='single_accountid']/..//img[@title='Select']")).click();

		// performing actions on organization window
		wlib.switchToWindow("Accounts&action", driver);
		// sending values on search box
		driver.findElement(By.id("search_txt")).sendKeys(elib.getExcelData("Invoice", 1, 5));

		// clicking on search now buttom
		driver.findElement(By.xpath("//input[@name='search']")).click();

		// selecting values
		driver.findElement(By.xpath("//a[text()='AK Enterprises']")).click();

		// clicking on ok (ALERT)
		driver.switchTo().alert().accept();

		// switching back to parent window
		driver.switchTo().window(parentId);

		// passing value on billing address
		driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys(elib.getExcelData("Invoice", 1, 2));

		// passing value on shipping address
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys(elib.getExcelData("Invoice", 1, 3));

		// item details
		driver.findElement(By.xpath("//img[@id='searchIcon1']")).click();

		// performing actions on products window
		wlib.switchToWindow("Products&action", driver);
		// sending value on search box
		driver.findElement(By.id("search_txt")).sendKeys(elib.getExcelData("Invoice", 1, 6));

		// click on search now button
		driver.findElement(By.xpath("//input[@name='search']")).click();

		// selecting values
		driver.findElement(By.xpath("//a[text()='RK Product']")).click();

		// switching to main window
		driver.switchTo().window(parentId);

		// entering qty
		driver.findElement(By.xpath("//input[@id='qty1']")).sendKeys(elib.getExcelData("Invoice", 1, 7));

		// clicking on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();

		// verification
		String invoice = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (invoice.contains(elib.getExcelData("Invoice", 1, 0))) {
			System.out.println("Invoice is created, PASS");
		} else {
			System.out.println("Invoice is not created, FAIL");
		}

		// mouse hover to administration link
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wlib.mouseHoverOnElement(adminElement, driver);

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();

	}

}
