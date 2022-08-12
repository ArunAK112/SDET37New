package com.crm.vtiger.products;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CreateNewProductPage;
import com.lexnod.ObjectRepository.CreateNewVendorPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.ProductsPage;
import com.lexnod.ObjectRepository.VendorsNamePage;
import com.lexnod.ObjectRepository.VendorsPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProductWithVendorAndVerifyTest extends BaseClass{

	@Test
	public void CreateProductWithVendorAndVerify() throws Throwable {
		
		//mouse hover to more
		HomePage home = new HomePage(driver);
		home.clickVendorsModule(driver);
		
		//verify vendors page is displayed or not
		String vendorTitle = "Administrator - Vendors - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), vendorTitle, "Vendor page is not displayed, FAIL");
		Reporter.log("Vendor page is displayed, PASS", true);
		
		//clicking on create vendor img
		VendorsPage vendorPage = new VendorsPage(driver);
		vendorPage.clickCreateVendorImage();
		
		//enter value in vendor name
		CreateNewVendorPage createNewVendor = new CreateNewVendorPage(driver);
		String vendorName = eLib.getExcelData("vendors", 1, 0);
		createNewVendor.getVendorNameField().sendKeys(vendorName);
		
		//click on save button
		createNewVendor.getSaveButton().click();
		
		//verifying vendor is created or not
		String vendor=driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		Assert.assertTrue(vendor.contains(vendorName),"Vendor is not created, FAIL" );
		Reporter.log("Vendor is created, PASS", true);
		
		//clicking on products module
		home.clickProductsModule();
		
		//verifying products page is displayed or not
		String productsTitle = "Administrator - Products - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), productsTitle, "Products page is not displayed, FAIL");
		Reporter.log("Products page is displayed, PASS", true);
		
		//clicking on create product img
		ProductsPage productPage = new ProductsPage(driver);
		productPage.clickCreateProductsButton();
		
		//entering details in product name tab
		CreateNewProductPage createNewProduct = new CreateNewProductPage(driver);
		String productName = eLib.getExcelData("Product", 1, 0);
		createNewProduct.getProductNameField().sendKeys(productName);
		
		//selecting vendorname
		createNewProduct.getVendorNameImage().click();
		String parentId = driver.getWindowHandle();
		wLib.switchToWindow("Vendors&action", driver);
		
		//entering the vendor name
		VendorsNamePage vendorNamePage = new VendorsNamePage(driver);
		vendorNamePage.getVendorName(vendorName);
		
		//switch back to main window
		driver.switchTo().window(parentId);
		
		//click on save button
		createNewProduct.getSaveButton().click();
		
		//verify whether product is created or not
		String product = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		Assert.assertTrue(product.contains(productName), "Product is not created, FAIL");
		Reporter.log("Product is created, PASS", true);
	}

}
