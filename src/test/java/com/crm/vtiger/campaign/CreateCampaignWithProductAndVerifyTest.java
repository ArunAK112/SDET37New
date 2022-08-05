package com.crm.vtiger.campaign;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CampaignsPage;
import com.lexnod.ObjectRepository.CreateNewCampaignPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
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

public class CreateCampaignWithProductAndVerifyTest {

	@Test
	
	public void CreateCampaignWithProductAndVerify() throws Throwable {

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

		// mouse hover to more click on campaigns
		HomePage home =new HomePage(driver);
		home.clickCampaignModule(driver);

		// verify campaign page is displayed or not
		String campaignTitle = "Administrator - Campaigns - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(campaignTitle)) {
			System.out.println("Campaign page is displayed, PASS");
		} else {
			System.out.println("Campaign page is not displayed, FAIL");
		}

		// click on create campaign img
		CampaignsPage campaignPage = new CampaignsPage(driver);
		campaignPage.clickCreateCampaignImage();

		// enter campaign name
		CreateNewCampaignPage createNewCampaign = new CreateNewCampaignPage(driver);
		String campaignName = elib.getExcelData("Campaign", 1, 0)+jlib.getRandonNumber(100);
		createNewCampaign.getCamapaignNameField().sendKeys(campaignName);

		// click on add product img
		createNewCampaign.getAddProductImage().click();

		// selecting product
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Products&action", driver);

		// entering the product name
		ProductNamePage productName = new ProductNamePage(driver);
		productName.getProductName(elib.getExcelData("Product", 1, 0));

		// switch to main window
		driver.switchTo().window(parentId);

		// click on save button
		createNewCampaign.getSavebutton().click();

		// verify the campaign is created or not
		String campaign = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (campaign.contains(campaignName)) {
			System.out.println("Campaign is created, PASS");
		} else {
			System.out.println("Campaign is not created, FAIL");
		}

		// signout
		home.clickSignoutLink(driver);

		// close the browser
		driver.close();
	}

}
