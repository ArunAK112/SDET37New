package com.crm.vtiger.email;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.ComposeEmailPage;
import com.lexnod.ObjectRepository.ContactsNamePage;
import com.lexnod.ObjectRepository.EmailPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateEmailAndVerifyTest extends BaseClass{

	@Test
	public void CreateEmailAndVerify() throws Throwable {

		

		// click on email module
		HomePage home = new HomePage(driver);
		home.clickEmailModule();

		// verify email page is displayed or not
		String emailTitle = "Administrator - Email - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), emailTitle, "Email page is not displayed, FAIL");
		Reporter.log("Email page is displayed, PASS", true);

		// click on compose buttom
		EmailPage emailPage = new EmailPage(driver);
		emailPage.clickComposeButton();

		// switching to the window for entering the values in the field
		String parentId = driver.getWindowHandle();
		wLib.switchToWindow(driver, "Compose Mail");
		// clicking on select to image to select email
		ComposeEmailPage composeEmail = new ComposeEmailPage(driver);
		composeEmail.getSelectToImage().click();

		// entering into sub child
		String subParent = driver.getWindowHandle();
		wLib.switchToWindow("Contacts&action", driver);

		// search box
		ContactsNamePage conatctsName = new ContactsNamePage(driver);
		conatctsName.getContactsName(eLib.getExcelData("contacts", 1, 1));

		// conformation
		System.out.println("Email is selected");

		// come back to subparent page
		driver.switchTo().window(subParent);

		// entering subject
		composeEmail.getSubjectField().sendKeys(eLib.getExcelData("composeMail", 1, 0));

		// entering details in body
		composeEmail.getBodyField().sendKeys(eLib.getExcelData("composeMail", 1, 1));

		// conformation
		System.out.println("body field are filled");

		// click on save button
		composeEmail.getSaveButton().click();

		// comming back to main window
		driver.switchTo().window(parentId);

		// conformation
		System.out.println("email is created");

		
	}

}
