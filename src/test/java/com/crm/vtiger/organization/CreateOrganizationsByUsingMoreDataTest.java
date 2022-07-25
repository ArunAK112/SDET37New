package com.crm.vtiger.organization;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;


public class CreateOrganizationsByUsingMoreDataTest {


	public static void main(String[] args) {

		// setting up browser
		WebDriverManager.firefoxdriver().setup();

		// creating object for browser
		WebDriver driver = new FirefoxDriver();

		// maximizing the browser
		driver.manage().window().maximize();

		// passing the url
		driver.get("http://localhost:8888/");

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
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// passing explicitly wait for getting home page to loaded
		wait.until(ExpectedConditions.titleContains("Administrator"));
		System.out.println("VTiger Home page is displayed, PASS");

		// click on organization module
		driver.findElement(By.linkText("Organizations")).click();

		// VERIFY ORGANIZATION PAGE IS DISPLAYED OR NOT
		String organizationTitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(organizationTitle)) {
			System.out.println("Organization page is displayed, PASS");
		} else {
			System.out.println("Organization page is not displayed, FAIL");
		}

		// clicking on create organization button
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();

		// Entering the organization name
		driver.findElement(By.name("accountname")).sendKeys("AK New Enterprises2");
		
		//select industry type dropdown
		Select select = new Select(driver.findElement(By.name("industry")));
		select.selectByValue("Communications");
		
		//select account type drop down
		Select select2 = new Select(driver.findElement(By.name("accounttype")));
		select2.selectByValue("Investor");

		// click on save button
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[1]")).click();

		// verification
		String organizationName = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (organizationName.contains("AK New Enterprises2")) {
			System.out.println("Organization name created, TRUE");
		} else {
			System.out.println("Organization name not created, FALSE");
		}

		// mouse hover to administration link
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions action1 = new Actions(driver);
		action1.moveToElement(adminElement).perform();

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();
	}

}
