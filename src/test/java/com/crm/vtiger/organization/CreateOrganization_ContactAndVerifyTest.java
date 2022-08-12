package com.crm.vtiger.organization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.ContactsPage;
import com.lexnod.ObjectRepository.CreateNewContactsPage;
import com.lexnod.ObjectRepository.CreateNewOrganizationPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.OrganizationsPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganization_ContactAndVerifyTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void createOrganizationAndContactAndVerify() throws Throwable {

		

		// click on organization module
		HomePage home = new HomePage(driver);
		home.clickOrganizationModule();

		// VERIFY ORGANIZATION PAGE IS DISPLAYED OR NOT
		String organizationTitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), organizationTitle,
				"Organization page is not displayed the displayed page is - " + driver.getTitle());
		Reporter.log("Organization page is displayed");

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
		String organizationName = eLib.getExcelData("Organization", 2, 0)+jLib.getRandonNumber(100);
		createNewOrganization.getOrganizationNameField().sendKeys(organizationName);
		
		// Selecting Industry
		createNewOrganization.selectIndustryDropdown(eLib.getExcelData("AllDropDown", 2, 1));

		// selecting type
		createNewOrganization.selectTypeDropdown(eLib.getExcelData("AllDropDown", 3, 2));
		
		// entering email
		createNewOrganization.getEmailField().sendKeys(eLib.getExcelData("Organization", 1, 1));

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
		createNewContacts.selectSalutationDropdownValue(eLib.getExcelData("AllDropDown", 1, 0));
		
		// entering firstname
		String firstName = eLib.getExcelData("contacts", 1, 0)+jLib.getRandonNumber(100);
		createNewContacts.getFirstnameField().sendKeys(firstName);
		
		// entering lastname
		createNewContacts.getLastnameField().sendKeys(eLib.getExcelData("contacts", 1, 1)+jLib.getRandonNumber(100));

		// clicking on orgnization name
		createNewContacts.getOrganizationNameImage().click();

		String parentId = driver.getWindowHandle();
		wLib.switchToWindow("Accounts&action", driver);

		// searching the organization name and clicking it
		OrganizationNamePage organizationPage = new OrganizationNamePage(driver);
		organizationPage.getOrganizationName(eLib.getExcelData("Organization", 2, 0));

		// switching to main window
		driver.switchTo().window(parentId);

		// clicking on save
		createNewContacts.getSaveButton().click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(contact.contains(firstName), "Contact is not created, FAIL");
		Reporter.log("Contact is created, PASS", true);


	}
}
