package com.crm.vtiger.salesorder;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSalesOrderwithOpportunitiesTest {

	public static void main(String[] args) throws Throwable {

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

		// mouse hover to more
		WebElement moreElement = driver.findElement(By.xpath("//a[text()='More']"));
		Actions action = new Actions(driver);
		action.moveToElement(moreElement).perform();

		// click on sales order
		driver.findElement(By.xpath("//a[text()='Sales Order']")).click();

		// verification
		String salesOrderTitle = "Administrator - Sales Order - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(salesOrderTitle)) {
			System.out.println("Sales order page is displayed, PASS");
		} else {
			System.out.println("Sales order page is not displayed, FAIL");
		}

		// clicking on create sales order img
		driver.findElement(By.xpath("//img[@title='Create Sales Order...']")).click();

		// entering values on subjectfield
		driver.findElement(By.xpath("//input[@name='subject']")).sendKeys("AKM");

		// clicking opportunity img
		driver.findElement(By.xpath("//img[@tabindex='']")).click();

		// switching window
		String parentId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();

		for (String check : allId) {
			String title = driver.switchTo().window(check).getTitle();
			if (title.contains("Potentials&action")) {
				break;
			}
		}

		// selecting opportunities
		driver.findElement(By.xpath("//a[text()='AK Opportunity']")).click();

		// switching baack to parent
		driver.switchTo().window(parentId);

		// selecting status
		Select select = new Select(driver.findElement(By.xpath("//select[@name='sostatus']")));
		select.selectByValue("Created");

		// entering due date
		//driver.findElement(By.id("jscal_field_duedate")).sendKeys("2022-07-15");
		driver.findElement(By.xpath("//img[@id='jscal_trigger_duedate']")).click();		
		WebElement date = driver.findElement(By.xpath("//td[contains(text(),'15')]"));
		date.click();
		System.out.println("Due date selected sucessfully");
		
		// clicking on organization img
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[4]")).click();

		// switching window organization
		Set<String> allId2 = driver.getWindowHandles();
		for (String check2 : allId2) {
			String title2 = driver.switchTo().window(check2).getTitle();
			if (title2.contains("Accounts&action")) {
				break;
			}
		}

		// clicking on organization name
		driver.findElement(By.xpath("//a[text()='AK Enterprises']")).click();

		// alert in accounts and actions
		driver.switchTo().alert().accept();

		// switching back to parent
		driver.switchTo().window(parentId);

		// selecting invoice status dropdown
		Select select2 = new Select(driver.findElement(By.xpath("//select[@name='invoicestatus']")));
		select2.selectByValue("Approved");

		// scrolldown
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].scrollIntoView();",
				driver.findElement(By.xpath("//b[text()='Address Information']")));

		// entering billing adddress
		driver.findElement(By.xpath("//textarea[@name='bill_street']")).sendKeys("Dummy address for billing address");

		// entering shipping address
		driver.findElement(By.xpath("//textarea[@name='ship_street']")).sendKeys("dummy shipping address");

		// scrolldown to item details
		jse.executeScript("arguments[0].scrollIntoView();", driver.findElement(By.xpath("//b[text()='Item Details']")));

		// clicking on select item img
		driver.findElement(By.xpath("//img[@id='searchIcon1']")).click();

		// switching the window to select product item
		Set<String> allId3 = driver.getWindowHandles();
		for (String check3 : allId3) {
			String title3 = driver.switchTo().window(check3).getTitle();
			if (title3.contains("Products&action")) {
				break;
			}
		}

		// clicking on product
		driver.findElement(By.xpath("//a[text()='RK Product']")).click();

		// switching back to parent
		driver.switchTo().window(parentId);

		// clicking on qty
		driver.findElement(By.xpath("//input[@id='qty1']")).sendKeys("1");

		// clicking on save
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[2]")).click();

		// verify sales order is created or not
		String salesText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (salesText.contains("AKM")) {
			System.out.println("Sales order is created, PASS");
		} else {
			System.out.println("Sales order is not created, FAIL");
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
