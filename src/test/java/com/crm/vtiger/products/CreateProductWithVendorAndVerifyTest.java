package com.crm.vtiger.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lexnod.ObjectRepository.CreateNewProductPage;
import com.lexnod.ObjectRepository.CreateNewVendorPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.ProductsPage;
import com.lexnod.ObjectRepository.VendorsNamePage;
import com.lexnod.ObjectRepository.VendorsPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductWithVendorAndVerifyTest {

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
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(plib.getPropertyData("username"), plib.getPropertyData("password"));

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wlib.waitForPageTitle("Administrator", driver, 10);
		System.out.println("VTiger Home page is displayed, PASS");
		
		//mouse hover to more
		HomePage home = new HomePage(driver);
		home.clickVendorsModule(driver);
		
		//verify vendors page is displayed or not
		String vendorTitle = "Administrator - Vendors - vtiger CRM 5 - Commercial Open Source CRM";
		if(driver.getTitle().equals(vendorTitle))
		{
			System.out.println("Vendor page is displayed, PASS");
		}else
		{
			System.out.println("Vendor page is not displayed, FAIL");
		}
		
		//clicking on create vendor img
		VendorsPage vendorPage = new VendorsPage(driver);
		vendorPage.clickCreateVendorImage();
		
		//enter value in vendor name
		CreateNewVendorPage createNewVendor = new CreateNewVendorPage(driver);
		String vendorName = elib.getExcelData("vendors", 1, 0);
		createNewVendor.getVendorNameField().sendKeys(vendorName);
		
		//click on save button
		createNewVendor.getSaveButton().click();
		
		//verifying vendor is created or not
		String vendor=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(vendor.contains(vendorName))
		{
			System.out.println("Vendor is created, PASS");
		}else
		{
			System.out.println("Vendor is not created, FAIL");
		}
		
		//clicking on products module
		home.clickProductsModule();
		
		//verifying products page is displayed or not
		String productsTitle = "Administrator - Products - vtiger CRM 5 - Commercial Open Source CRM";
		if(driver.getTitle().equals(productsTitle))
		{
			System.out.println("Products page is displayed, PASS");
		}else
		{
			System.out.println("Products page is not displayed, FAIL");
		}
		
		//clicking on create product img
		ProductsPage productPage = new ProductsPage(driver);
		productPage.clickCreateProductsButton();
		
		//entering details in product name tab
		CreateNewProductPage createNewProduct = new CreateNewProductPage(driver);
		String productName = elib.getExcelData("Product", 1, 0);
		createNewProduct.getProductNameField().sendKeys(productName);
		
		//selecting vendorname
		createNewProduct.getVendorNameImage().click();
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Vendors&action", driver);
		
		//entering the vendor name
		VendorsNamePage vendorNamePage = new VendorsNamePage(driver);
		vendorNamePage.getVendorName(vendorName);
		
		//switch back to main window
		driver.switchTo().window(parentId);
		
		//click on save button
		createNewProduct.getSaveButton().click();
		
		//verify whether product is created or not
		String product = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if(product.contains(productName))
		{
			System.out.println("Product is created, PASS");
		}else
		{
			System.out.println("Product is not created, FAIL");
		}
		
		//signout
		home.clickSignoutLink(driver);
		
		// close the browser
		driver.close();
	}

}
