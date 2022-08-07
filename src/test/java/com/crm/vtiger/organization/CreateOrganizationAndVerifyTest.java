package com.crm.vtiger.organization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CreateNewOrganizationPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationsPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationAndVerifyTest {
	@Test(groups = "smokeTest")

	public void CreateOrganizationAndVerify() throws Throwable {

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

		// click on organization module
		HomePage home = new HomePage(driver);
		home.clickOrganizationModule();
		
		// VERIFY ORGANIZATION PAGE IS DISPLAYED OR NOT
		String organizationTitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(organizationTitle)) {
			System.out.println("Organization page is displayed, PASS");
		} else {
			System.out.println("Organization page is not displayed, FAIL");
		}

		// clicking on create organization button
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.clickCreateOrganizationsImage();

		// Entering the organization name
		CreateNewOrganizationPage createNewOrganization = new CreateNewOrganizationPage(driver);
		String organizationName = elib.getExcelData("Organization", 2, 0)+jlib.getRandonNumber(100);
		createNewOrganization.getOrganizationNameField().sendKeys(organizationName);

		// click on save button
		createNewOrganization.getSaveButton().click();
		
		// verification
		String organizationNameVerify = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationNameVerify.contains(organizationName)) {
			System.out.println("Organization name created, TRUE");
		} else {
			System.out.println("Organization name not created, FALSE");
		}

		//signout
		home.clickSignoutLink(driver);

		// close the browser
		driver.close();
		
	}

}
