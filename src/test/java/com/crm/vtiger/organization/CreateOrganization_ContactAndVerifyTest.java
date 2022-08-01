package com.crm.vtiger.organization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.ContactsPage;
import com.lexnod.ObjectRepository.CreateNewContactsPage;
import com.lexnod.ObjectRepository.CreateNewOrganizationPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.OrganizationsPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization_ContactAndVerifyTest {

	@Test
	public void createOrganizationAndContactAndVerify() throws Throwable {

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
		Assert.assertEquals(driver.getTitle(), organizationTitle,
				"Organization page is not displayed the displayed page is - " + driver.getTitle());
		System.out.println("Organization page is displayed");

		// clicking on create organization button
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.clickCreateOrganizationsImage();

		// VERIFYING CREATE NEW ORGANIZATION PAGE IS DISPLAYED OR NOT
		WebElement elementNewOrganization = driver.findElement(By.xpath("//span[text()='Creating New Organization']"));
		if (elementNewOrganization.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Create new organization page is displayed, PASS");
		} else {
			System.out.println("Create new organization page is not displayed, FAIL");
			Assert.assertTrue(false);
		}

		// Entering the organization name
		CreateNewOrganizationPage createNewOrganization = new CreateNewOrganizationPage(driver);
		String organizationName = elib.getExcelData("Organization", 2, 0)+jlib.getRandonNumber(100);
		createNewOrganization.getOrganizationNameField().sendKeys(organizationName);
		
		// Selecting Industry
		createNewOrganization.selectIndustryDropdown(elib.getExcelData("AllDropDown", 2, 1));

		// selecting type
		createNewOrganization.selectTypeDropdown(elib.getExcelData("AllDropDown", 3, 2));
		
		// entering email
		createNewOrganization.getEmailField().sendKeys(elib.getExcelData("Organization", 1, 1));

		// click on save button
		createNewOrganization.getSaveButton().click();

		// VERIFICATION
		WebElement informationElement = driver.findElement(By.linkText("More Information"));
		if (informationElement.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Organization Information page is displayed, PASS");
		} else {
			System.out.println("Organization Information page is not displayed, FAIL");
			Assert.assertTrue(false);
		}

		// clicking on contacts module
		home.clickContactsModule();

		// VERIFICATION CONTACTS PAGE IS DISPLAYED OR NOT
		String contactTitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), contactTitle,
				" Contacts page is not displayed the displayed page is - " + driver.getTitle());

		// clicking on create contact image
		ContactsPage conatctsPage = new ContactsPage(driver);
		conatctsPage.clickCreateContactsImage();

		// VERIFICATION
		WebElement createNewContactElement = driver.findElement(By.xpath("//span[text()='Creating New Contact']"));

		if (createNewContactElement.isDisplayed()) {
			Assert.assertTrue(true);
			System.out.println("Create new contact page is displayed, PASS");
		} else {
			System.out.println("Create new conatct page is not displayed, FAIL");
			Assert.assertTrue(false);
		}

		// selecting salutation
		CreateNewContactsPage createNewContacts = new CreateNewContactsPage(driver);
		createNewContacts.selectSalutationDropdownValue(elib.getExcelData("AllDropDown", 1, 0));
		
		// entering firstname
		String firstName = elib.getExcelData("contacts", 1, 0)+jlib.getRandonNumber(100);
		createNewContacts.getFirstnameField().sendKeys(firstName);
		
		// entering lastname
		createNewContacts.getLastnameField().sendKeys(elib.getExcelData("contacts", 1, 1)+jlib.getRandonNumber(100));

		// clicking on orgnization name
		createNewContacts.getOrganizationNameImage().click();

		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Accounts&action", driver);

		// searching the organization name and clicking it
		OrganizationNamePage organizationPage = new OrganizationNamePage(driver);
		organizationPage.getOrganizationName(elib.getExcelData("Organization", 2, 0));

		// switching to main window
		driver.switchTo().window(parentId);

		// clicking on save
		createNewContacts.getSaveButton().click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contact.contains(firstName)) {
			System.out.println("Contact is created, PASS");
		} else {
			System.out.println("Contact is not created, FAIL");
		}

		//signout
		home.clickSignoutLink(driver);

		// close the browser
		driver.close();

	}
}
