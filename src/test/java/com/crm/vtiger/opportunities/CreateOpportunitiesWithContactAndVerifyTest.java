package com.crm.vtiger.opportunities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lexnod.ObjectRepository.CreateNewOpportunitiesPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OpportunitiesPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunitiesWithContactAndVerifyTest {

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

		// clicking on opportunities
		HomePage home = new HomePage(driver);
		home.clickOpportunitiesModule();

		// verify opportunity page is displayed or not
		String opportunityTitle = "Administrator - Opportunities - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(opportunityTitle)) {
			System.out.println("Opportunity page is displayed, PASS");
		} else {
			System.out.println("Opportunity page is not displayed, FAIL");
		}

		// clicking on createopportunity img
		OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
		opportunitiesPage.clickCreateOpportinitiesImage();

		// enter value in opportunity name field
		CreateNewOpportunitiesPage createNewOpportunity = new CreateNewOpportunitiesPage(driver);
		String opportunityName = elib.getExcelData("opportunities", 1, 0)+ jlib.getRandonNumber(100);
		createNewOpportunity.getOpportunitiesNameField().sendKeys(opportunityName);
		
		// select relatedto dropdown
		//createNewOpportunity.selectRelatedToDropdown("");
		WebElement dropdownAddress = driver.findElement(By.id("related_to_type"));
		wlib.getAllTheOptionsFromDropdown(dropdownAddress);
		wlib.select("Contacts", dropdownAddress);

		// click on relatedto img
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif' and @tabindex='']")).click();

		// switching the window
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Contacts&action", driver);

		// enter value in search field
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(elib.getExcelData("contacts", 1, 1));

		// click on search now button
		driver.findElement(By.xpath("//input[@name='search']")).click();

		// clicking on contact
		driver.findElement(By.xpath("//a[text()='Arun K']")).click();

		// switch to main window
		driver.switchTo().window(parentId);

		// expected close date element
		WebElement dateElement = driver.findElement(By.xpath("//input[@id='jscal_field_closingdate']"));

		// clearing the date field
		dateElement.clear();

		// entering date in date field
		dateElement.sendKeys(elib.getExcelData("opportunities", 1, 1));

		// selecting sales stage dropdown
		WebElement salesDropdownElement = driver.findElement(By.xpath("//select[@name='sales_stage']"));
		wlib.getAllTheOptionsFromDropdown(salesDropdownElement);
		wlib.select(salesDropdownElement, "Id. Decision Makers");

		// click on campaign source img
		driver.findElement(By.xpath("//input[@name='campaignname']/..//img")).click();

		// switching to window
		wlib.switchToWindow("Campaigns&action", driver);

		// clciking on campaign name
		driver.findElement(By.xpath("//a[text()='Advertisement']")).click();

		// switching back to main window
		driver.switchTo().window(parentId);

		// click on save button
		driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();

		// verification
		String opportunity = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (opportunity.contains(opportunityName)) {
			System.out.println("Opportunity is created, PASS");
		} else {
			System.out.println("Opportunity is not created, FAIL");
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
