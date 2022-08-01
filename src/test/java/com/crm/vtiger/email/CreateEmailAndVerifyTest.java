package com.crm.vtiger.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lexnod.ObjectRepository.ComposeEmailPage;
import com.lexnod.ObjectRepository.ContactsNamePage;
import com.lexnod.ObjectRepository.EmailPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateEmailAndVerifyTest {

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

		// click on email module
		HomePage home = new HomePage(driver);
		home.clickEmailModule();

		// verify email page is displayed or not
		String emailTitle = "Administrator - Email - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(emailTitle)) {
			System.out.println("Email page is displayed, PASS");
		} else {
			System.out.println("Email page is not displayed, FAIL");
		}

		// click on compose buttom
		EmailPage emailPage = new EmailPage(driver);
		emailPage.clickComposeButton();

		// switching to the window for entering the values in the field
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow(driver, "Compose Mail");
		// clicking on select to image to select email
		ComposeEmailPage composeEmail = new ComposeEmailPage(driver);
		composeEmail.getSelectToImage().click();

		// entering into sub child
		String subParent = driver.getWindowHandle();
		wlib.switchToWindow("Contacts&action", driver);

		// search box
		ContactsNamePage conatctsName = new ContactsNamePage(driver);
		conatctsName.getContactsName(elib.getExcelData("contacts", 1, 1));

		// conformation
		System.out.println("Email is selected");

		// come back to subparent page
		driver.switchTo().window(subParent);

		// entering subject
		composeEmail.getSubjectField().sendKeys(elib.getExcelData("composeMail", 1, 0));

		// entering details in body
		composeEmail.getBodyField().sendKeys(elib.getExcelData("composeMail", 1, 1));

		// conformation
		System.out.println("body field are filled");

		// click on save button
		composeEmail.getSaveButton().click();

		// comming back to main window
		driver.switchTo().window(parentId);

		// conformation
		System.out.println("email is created");

		//signout
		home.clickSignoutLink(driver);

		// close the browser
		driver.close();
	}

}
