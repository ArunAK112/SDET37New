package com.crm.vtiger.Assets;

import java.time.Duration;
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

/**
 * 
 * @author ARUN
 *
 */
public class CreateAssetsWithInvoiceTest {

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

		// click on assets
		driver.findElement(By.xpath("//a[@name='Assets']")).click();

		// verification
		String assetsTitle = "Administrator - Assets - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(assetsTitle)) {
			System.out.println("Asserts page is displayed, PASS");
		} else {
			System.out.println("Asserts page is not displayed, FAIL");
		}

		// clicking on new assets img
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		// code for deriving data from excel sheet

//		//entering assetNo field
//		driver.findElement(By.xpath("//input[@id='asset_no']")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(0).getStringCellValue());

		// entering asset serial number
		driver.findElement(By.xpath("//input[@name='serialnumber']")).sendKeys(elib.getExcelData("Assets", 1, 1));

		// clicking on product name img
		driver.findElement(By.xpath("//input[@name='product_display']/..//img[@src='themes/softed/images/select.gif']"))
				.click();

		// performing action on product tab
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Products&action", driver);

		// selecting searchbox and sending value
		driver.findElement(By.id("search_txt")).sendKeys(elib.getExcelData("Assets", 1, 2));

		// clicking on search now buttom
		driver.findElement(By.xpath("//input[@name='search']")).click();

		// selecting value
		driver.findElement(By.xpath("//a[text()='RK Product']")).click();

		// switching to parent window
		driver.switchTo().window(parentId);

		// clicking on customer name img
		driver.findElement(By.xpath("//input[@id='account_display']/..//img[@src='themes/softed/images/select.gif']"))
				.click();

		// performing action on customer name window
		wlib.switchToWindow("Accounts&action", driver);
		// selecting searchbox and sending value
		driver.findElement(By.id("search_txt")).sendKeys(elib.getExcelData("Assets", 1, 3));

		// clicking on search now buttom
		driver.findElement(By.xpath("//input[@name='search']")).click();

		// selecting value
		driver.findElement(By.xpath("//a[text()='AK Enterprises']")).click();

		// switch to main window
		driver.switchTo().window(parentId);

		// entering asset name
		driver.findElement(By.xpath("//input[@id='assetname']")).sendKeys(elib.getExcelData("Assets", 1, 4));

		// click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();

		// verification
		String asset = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (asset.contains(elib.getExcelData("Assets", 1, 4))) {
			System.out.println("Asset is created, PASS");
		} else {
			System.out.println("Asset is not created, FAIL");
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
