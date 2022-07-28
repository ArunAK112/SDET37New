package com.crm.vtiger.organization;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

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
		driver.findElement(By.name("user_name")).sendKeys(plib.getPropertyData("username"));
		driver.findElement(By.name("user_password")).sendKeys(plib.getPropertyData("password"));
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wlib.waitForPageTitle("Administrator", driver, 10);
		System.out.println("VTiger Home page is displayed, PASS");

		// click on organization module
		driver.findElement(By.linkText("Organizations")).click();

		// VERIFY ORGANIZATION PAGE IS DISPLAYED OR NOT
		String organizationTitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), organizationTitle,
				"Organization page is not displayed the displayed page is - " + driver.getTitle());
		System.out.println("Organization page is displayed");

		// clicking on create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

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
		String organizationName = elib.getExcelData("Organization", 2, 0)+jlib.getRandonNumber(100);
		driver.findElement(By.name("accountname")).sendKeys(organizationName);

		// Selecting Industry
		WebElement industryDropDown = driver.findElement(By.xpath("//select[@name='industry']"));
		wlib.getAllTheOptionsFromDropdown(industryDropDown);
		wlib.select(industryDropDown, elib.getExcelData("AllDropDown", 2, 1));

		// selecting type
		WebElement typeDropdown = driver.findElement(By.xpath("//select[@name='accounttype']"));
		wlib.getAllTheOptionsFromDropdown(typeDropdown);
		wlib.select(typeDropdown, elib.getExcelData("AllDropDown", 2, 3));

		// entering email
		driver.findElement(By.id("email1")).sendKeys(elib.getExcelData("Organization", 1, 1));

		// click on save button
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();

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
		driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();

		// VERIFICATION CONTACTS PAGE IS DISPLAYED OR NOT
		String contactTitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), contactTitle,
				" Contacts page is not displayed the displayed page is - " + driver.getTitle());

		// clicking on create contact image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

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
		WebElement salutationDropdown = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		Select select2 = new Select(salutationDropdown);
		wlib.getAllTheOptionsFromDropdown(salutationDropdown);
		select2.selectByValue(elib.getExcelData("AllDropDown", 1, 0));

		// entering firstname
		String firstName = elib.getExcelData("contacts", 1, 0)+jlib.getRandonNumber(100);
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(firstName);

		// entering lastname
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(elib.getExcelData("contacts", 1, 1)+jlib.getRandonNumber(100));

		// clicking on orgnization name
		driver.findElement(By.xpath("//tbody/tr[5]/td[2]/img[1]")).click();
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Accounts&action", driver);

		// searching the organization name and clicking it
		driver.findElement(By.name("search_text")).sendKeys(elib.getExcelData("Organization", 2, 0));
		driver.findElement(By.name("search")).click();
		driver.findElement(By.id("1")).click();

		// switching to main window
		driver.switchTo().window(parentId);

		// clicking on save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contact.contains(firstName)) {
			System.out.println("Contact is created, PASS");
		} else {
			System.out.println("Contact is not created, FAIL");
		}

		// mouse hover to administration link
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wlib.mouseHoverOnElement(adminElement, driver);

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();

	}
}
