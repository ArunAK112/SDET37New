 package com.crm.vtiger.contacts;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.ContactsPage;
import com.lexnod.ObjectRepository.CreateNewContactsPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
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
public class CreateContactWithMoreDataAndVerifyTest extends BaseClass {

	@Test
	public void CreateContactWithMoreDataAndVerify() throws Throwable {

		
		
		// clicking on contacts module
		HomePage home = new HomePage(driver);
		home.clickContactsModule();

		// verify contacts page is displayed or not
		String contactTitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), contactTitle, "Contacts page is not displayed, FAIL");
		Reporter.log("Contacts page is displayed, PASS", true);

		// clicking on create contact image
		ContactsPage contactsPage = new ContactsPage(driver);
		contactsPage.clickCreateContactsImage();

		CreateNewContactsPage createNewContacts = new CreateNewContactsPage(driver);
		// selecting salutation
		createNewContacts.selectSalutationDropdownValue(eLib.getExcelData("AllDropDown", 1, 0));

		// entering firstname
		String firstName = eLib.getExcelData("contacts", 2, 0)+jLib.getRandonNumber(100);
		createNewContacts.getFirstnameField().sendKeys(firstName);

		// entering lastname
		createNewContacts.getLastnameField().sendKeys(eLib.getExcelData("contacts", 2, 1));

		// clicking on orgnization name
		createNewContacts.getOrganizationNameImage().click();
		String parentId = driver.getWindowHandle();
		wLib.switchToWindow("Accounts&action", driver);

		// searching the organization name and clicking it
		OrganizationNamePage organizationName = new OrganizationNamePage(driver);
		organizationName.getOrganizationName(eLib.getExcelData("Organization", 2, 1));
		
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
