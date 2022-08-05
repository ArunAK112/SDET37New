package com.crm.vtiger.Assets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.AssetsPage;
import com.lexnod.ObjectRepository.CreateNewAssetPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.ProductNamePage;
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

	@Test
	
	public void CreateAssetsWithInvoice() throws Throwable {

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
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(plib.getPropertyData("username"), plib.getPropertyData("password"));
		
		
		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wlib.waitForPageTitle("Administrator", driver, 10);
		System.out.println("VTiger Home page is displayed, PASS");

		// mouse hover to more and click on assets
		HomePage home = new HomePage(driver);
		home.clickAssetsModule(driver);

		// verification
		String assetsTitle = "Administrator - Assets - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(assetsTitle)) {
			System.out.println("Asserts page is displayed, PASS");
		} else {
			System.out.println("Asserts page is not displayed, FAIL");
		}

		// clicking on new assets img
		AssetsPage assetPage = new AssetsPage(driver);
		assetPage.clickCreateAssetsIamge();

		// code for deriving data from excel sheet

//		//entering assetNo field
//		driver.findElement(By.xpath("//input[@id='asset_no']")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(0).getStringCellValue());

		CreateNewAssetPage createNewAssets = new CreateNewAssetPage(driver);
		// entering asset serial number
		createNewAssets.getSerialNumberField().sendKeys(elib.getExcelData("Assets", 1, 1));

		// clicking on product name img
		createNewAssets.getProductNameImage().click();

		// performing action on product tab
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Products&action", driver);

		ProductNamePage productName = new ProductNamePage(driver);
		productName.getProductName(elib.getExcelData("Assets", 1, 2));
	
		// switching to parent window
		driver.switchTo().window(parentId);

		// clicking on customer name img
		createNewAssets.getCustomerNameImage().click();
		
		// performing action on organizations window
		wlib.switchToWindow("Accounts&action", driver);
		
		OrganizationNamePage organizationName = new OrganizationNamePage(driver);
		organizationName.getOrganizationName(elib.getExcelData("Assets", 1, 3));

		// switch to main window
		driver.switchTo().window(parentId);

		// entering asset name
		createNewAssets.getAssetNameField().sendKeys(elib.getExcelData("Assets", 1, 4));

		// click on save button
		createNewAssets.getSaveButton().click();

		// verification
		String asset = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (asset.contains(elib.getExcelData("Assets", 1, 4))) {
			System.out.println("Asset is created, PASS");
		} else {
			System.out.println("Asset is not created, FAIL");
		}

		// click on signout link
		home.clickSignoutLink(driver);

		// close the browser
		driver.quit();
		
		
	}

}
