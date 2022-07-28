package com.crm.vtiger.campaign;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
		driver.findElement(By.name("user_name")).sendKeys(plib.getPropertyData("username"));
		driver.findElement(By.name("user_password")).sendKeys(plib.getPropertyData("password"));
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wlib.waitForPageTitle("Administrator", driver, 10);
		System.out.println("VTiger Home page is displayed, PASS");

		// mouse hover to more
		WebElement moreElement = driver.findElement(By.xpath("//a[text()='More']"));
		wlib.mouseHoverOnElement(moreElement, driver);

		// click on campaigns
		driver.findElement(By.xpath("//a[text()='Campaigns']")).click();

		// verify campaign page is displayed or not
		String campaignTitle = "Administrator - Campaigns - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(campaignTitle)) {
			System.out.println("Campaign page is displayed, PASS");
		} else {
			System.out.println("Campaign page is not displayed, FAIL");
		}

		// click on create campaign img
		driver.findElement(By.xpath("//img[@title='Create Campaign...']")).click();

		// enter campaign name
		driver.findElement(By.name("campaignname")).sendKeys("Advertisement");

		// click on add product img
		driver.findElement(By.xpath("//img[@title='Select']")).click();

		// selecting product
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Products&action", driver);

		// entering the product name
		driver.findElement(By.id("search_txt")).sendKeys("RK Product");

		// click on search button
		driver.findElement(By.name("search")).click();

		// select product
		driver.findElement(By.xpath("//a[text()='RK Product']")).click();

		// switch to main window
		driver.switchTo().window(parentId);

		// click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// verify the campaign is created or not
		String campaign = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (campaign.contains("Advertisement")) {
			System.out.println("Campaign is created, PASS");
		} else {
			System.out.println("Campaign is not created, FAIL");
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
