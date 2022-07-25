package com.crm.vtiger.contacts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.vtiger.genericLib.FileLib;
import com.vtiger.genericLib.IAutoConsts;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * 
 * @author ARUN
 *
 */
public class CreateContactsAndVerifyTest implements IAutoConsts {

	public static void main(String[] args) throws Throwable {
		
		FileLib flib = new FileLib();

		// setting up browser
		WebDriverManager.firefoxdriver().setup();

		// creating object for browser
		WebDriver driver = new FirefoxDriver();

		// maximizing the browser
		driver.manage().window().maximize();

		// passing the url
		driver.get(flib.readPropertyData(PROP_PATH, "url"));

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
		driver.findElement(By.name("user_name")).sendKeys(flib.readPropertyData(PROP_PATH, "username"));
		driver.findElement(By.name("user_password")).sendKeys(flib.readPropertyData(PROP_PATH,"password"));
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
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys("Arun");

		// entering lastname
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys("Punk");

		// clicking on save
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// verify whether contact is created or not
		String contact = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (contact.contains("Arun")) {
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
