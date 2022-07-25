package com.crm.vtiger.contacts;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author ARUN
 *
 */
public class CreateContactWithMoreDataAndVerifyTest {

	public static void main(String[] args) throws Throwable {

		WebDriver driver = null;

		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/data/config.properties");
		Properties properties = new Properties();
		properties.load(fileInputStream);

		String browser = properties.getProperty("browser");

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
		driver.manage().window().maximize();

		// passing the url
		driver.get(properties.getProperty("url"));

		// passing wait condition
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		String loginTitle = "vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger Login page is not displayed, FAIL");
		}

		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys(properties.getProperty("username"));
		driver.findElement(By.name("user_password")).sendKeys(properties.getProperty("password"));
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// passing explicitly wait for getting home page to loaded
		wait.until(ExpectedConditions.titleContains("Administrator"));
		System.out.println("VTiger Home page is displayed, PASS");

		// clicking on contacts module
		driver.findElement(By.xpath("//a[@href='index.php?module=Contacts&action=index']")).click();

		// verify contacts page is displayed or not
		String contactTitle = "Administrator - Contacts - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(contactTitle)) {
			System.out.println("Contacts page is displayed, PASS");
		} else {
			System.out.println("Contacts page is not displayed, FAIL");
		}

		// clicking on create contact image
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// selecting salutation
		Select select2 = new Select(driver.findElement(By.xpath("//select[@name='salutationtype']")));
		select2.selectByValue("Mr.");

		// entering firstname
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("CM");

		// entering lastname
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Punk");

		// clicking on orgnization name
		driver.findElement(By.xpath("//tbody/tr[5]/td[2]/img[1]")).click();
		String parentId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();

		for (String popup : allId) {
			driver.switchTo().window(popup);
			String actTitle = driver.getTitle();
			if (actTitle.contains("Accounts&action")) {
				break;
			}
		}

		// searching the organization name and clicking it
		driver.findElement(By.name("search_text")).sendKeys("AK New Enterprises");
		driver.findElement(By.name("search")).click();
		driver.findElement(By.xpath("//a[text()='AK New Enterprises']")).click();

		// switching to main window
		driver.switchTo().window(parentId);

		// clicking on save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contact.contains("CM")) {
			System.out.println("Contact is created, PASS");
		} else {
			System.out.println("Contact is not created, FAIL");
		}

		// mouse hover to administration link
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action = new Actions(driver);
		action.moveToElement(adminElement).perform();

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();
	}

}