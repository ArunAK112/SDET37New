package com.crm.vtiger.opportunities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunitiesWithContactAndVerifyTest {

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

		// clicking on opportunities
		driver.findElement(By.xpath("//a[@href='index.php?module=Potentials&action=index']")).click();

		// verify opportunity page is displayed or not
		String opportunityTitle = "Administrator - Opportunities - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(opportunityTitle)) {
			System.out.println("Opportunity page is displayed, PASS");
		} else {
			System.out.println("Opportunity page is not displayed, FAIL");
		}

		// clicking on createopportunity img
		driver.findElement(By.xpath("//img[@title='Create Opportunity...']")).click();

		// enter value in opportunity name field
		driver.findElement(By.xpath("//input[@name='potentialname']")).sendKeys("AK Opportunity");

		// select relatedto dropdown
		WebElement dropdownAddress = driver.findElement(By.id("related_to_type"));
		Select select = new Select(dropdownAddress);
		select.selectByValue("Contacts");

		// click on relatedto img
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif' and @tabindex='']")).click();

		// switching the window
		String parentId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();
		for (String check : allId) {
			String title = driver.switchTo().window(check).getTitle();
			if (title.contains("Contacts&action")) {
				break;
			}
		}

		// enter value in search field
		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys("K");

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
		dateElement.sendKeys("2022-07-15");

		// selecting sales stage dropdown
		WebElement salesDropdownElement = driver.findElement(By.xpath("//select[@name='sales_stage']"));
		Select select1 = new Select(salesDropdownElement);
		select1.selectByValue("Id. Decision Makers");

		// click on campaign source img
		driver.findElement(By.xpath("//input[@name='campaignname']/..//img")).click();

		// switching to window
		Set<String> allId2 = driver.getWindowHandles();
		for (String check2 : allId2) {
			String title2 = driver.switchTo().window(check2).getTitle();
			if (title2.contains("Campaigns&action")) {
				break;
			}
		}

		// clciking on campaign name
		driver.findElement(By.xpath("//a[text()='Advertisement']")).click();

		// switching back to main window
		driver.switchTo().window(parentId);

		// click on save button
		driver.findElement(By.xpath("(//input[@type='submit'])[1]")).click();

		// verification
		String opportunity = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (opportunity.contains("AK Opportunity")) {
			System.out.println("Opportunity is created, PASS");
		} else {
			System.out.println("Opportunity is not created, FAIL");
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
