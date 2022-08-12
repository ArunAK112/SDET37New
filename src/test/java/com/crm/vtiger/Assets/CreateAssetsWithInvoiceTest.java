package com.crm.vtiger.Assets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.AssetsPage;
import com.lexnod.ObjectRepository.CreateNewAssetPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.ProductNamePage;
import com.lexnod.genericLib.BaseClass;
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
//@Listeners(com.lexnod.genericLib.ListnerImplimentation.class)
public class CreateAssetsWithInvoiceTest extends BaseClass{

	@Test(groups = "smokeTest", retryAnalyzer = com.lexnod.genericLib.RetryAnalyzerImp.class)
	
	public void CreateAssetsWithInvoice() throws Throwable {

	
		// mouse hover to more and click on assets
		HomePage home = new HomePage(driver);
		home.clickAssetsModule(driver);

		// verification
		String assetsTitle = "Administrator - Assets - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), assetsTitle, "Asserts page is not displayed, FAIL");
		Reporter.log("Asserts page is displayed, PASS", true);

		// clicking on new assets img
		AssetsPage assetPage = new AssetsPage(driver);
		assetPage.clickCreateAssetsIamge();

		// code for deriving data from excel sheet

//		//entering assetNo field
//		driver.findElement(By.xpath("//input[@id='asset_no']")).sendKeys(workbook.getSheet("Assets").getRow(1).getCell(0).getStringCellValue());

		CreateNewAssetPage createNewAssets = new CreateNewAssetPage(driver);
		// entering asset serial number
		createNewAssets.getSerialNumberField().sendKeys(eLib.getExcelData("Assets", 1, 1));

		// clicking on product name img
		createNewAssets.getProductNameImage().click();

		// performing action on product tab
		String parentId = driver.getWindowHandle();
		wLib.switchToWindow("Products&action", driver);

		ProductNamePage productName = new ProductNamePage(driver);
		productName.getProductName(eLib.getExcelData("Assets", 1, 2));
	
		// switching to parent window
		driver.switchTo().window(parentId);

		// clicking on customer name img
		createNewAssets.getCustomerNameImage().click();
		
		// performing action on organizations window
		wLib.switchToWindow("Accounts&action", driver);
		
		OrganizationNamePage organizationName = new OrganizationNamePage(driver);
		organizationName.getOrganizationName(eLib.getExcelData("Assets", 1, 3));

		// switch to main window
		driver.switchTo().window(parentId);

		// entering asset name
		createNewAssets.getAssetNameField().sendKeys(eLib.getExcelData("Assets", 1, 4));

		// click on save button
		createNewAssets.getSaveButton().click();

		// verification
		String asset = driver.findElement(By.xpath("//span[@class='dvHeaderText'")).getText();
		Assert.assertTrue(asset.contains(eLib.getExcelData("Assets", 1, 4)), "Asset is not created, FAIL");
		Reporter.log("Asset is created, PASS", true);
	}

}
