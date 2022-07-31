package com.crm.vtiger.email;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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
		driver.findElement(By.name("user_name")).sendKeys(plib.getPropertyData("username"));
		driver.findElement(By.name("user_password")).sendKeys(plib.getPropertyData("password"));
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wlib.waitForPageTitle("Administrator", driver, 10);
		System.out.println("VTiger Home page is displayed, PASS");

		// click on email module
		driver.findElement(By.xpath("//a[text()='Email']")).click();

		// verify email page is displayed or not
		String emailTitle = "Administrator - Email - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(emailTitle)) {
			System.out.println("Email page is displayed, PASS");
		} else {
			System.out.println("Email page is not displayed, FAIL");
		}

		// click on compose buttom
		driver.findElement(By.xpath("//a[text()='Compose']")).click();

		// switching to the window for entering the values in the field
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow(driver, "Compose Mail");
		// clicking on select to image to select email
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();

		// entering into sub child
		String subParent = driver.getWindowHandle();
		wlib.switchToWindow("Contacts&action", driver);

		// search box
		driver.findElement(By.id("search_txt")).sendKeys(elib.getExcelData("contacts", 1, 1));

		// click on search button
		driver.findElement(By.name("search")).click();

		// clicking on contact
		driver.findElement(By.id("1")).click();

		// conformation
		System.out.println("Email is selected");

		// come back to subparent page
		driver.switchTo().window(subParent);

		// entering subject
		driver.findElement(By.id("subject")).sendKeys(elib.getExcelData("composeMail", 1, 0));

		// entering details in body
		driver.findElement(By.xpath("//iframe[@title='Rich text editor, description, press ALT 0 for help.']"))
				.sendKeys(elib.getExcelData("composeMail", 1, 1));

		// conformation
		System.out.println("body field are filled");

		// click on save button
		driver.findElement(By.xpath("(//input[@title='Save [Alt+S]'])[1]")).click();

		// comming back to main window
		driver.switchTo().window(parentId);

		// conformation
		System.out.println("email is created");

		// mouse hover to administration link
		WebElement adminElement = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wlib.mouseHoverOnElement(adminElement, driver);

		// click on signout link
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		// close the browser
		driver.close();
	}

}
