package com.crm.vtiger.contacts;

import static org.testng.Assert.assertThrows;
import static org.testng.Assert.assertTrue;

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
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.IAutoConstants;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * 
 * @author ARUN
 *
 */
public class CreateContactsAndVerifyTest extends BaseClass {

	@Test(groups = "smokeTest")
	public void CreateContactsAndVerify() throws Throwable {


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

		// selecting salutation
		CreateNewContactsPage createNewContact = new CreateNewContactsPage(driver);
		createNewContact.selectSalutationDropdownValue(eLib.getExcelData("AllDropDown", 1, 0));

		// entering firstname
		String firstName = eLib.getExcelData("contacts", 3, 0);
		createNewContact.getFirstnameField().sendKeys(firstName);
		
		// entering lastname
		createNewContact.getLastnameField().sendKeys(eLib.getExcelData("contacts", 3, 1));

		// clicking on save
		createNewContact.getSaveButton().click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(contact.contains(firstName), "Contact is not created, FAIL");
		Reporter.log("Contact is created, PASS", true);


	}

}
