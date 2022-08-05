package com.crm.vtiger.documents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CreateNewDocumentsPage;
import com.lexnod.ObjectRepository.DocumentsPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentAndVerifyTest {

	@Test
	public void CreateDocumentAndVerify() throws Throwable {

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

		// click on documents module
		HomePage home = new HomePage(driver);
		home.clickDocumentsModule();

		// verify documents page is displayed or not
		String documentsTitle = "Administrator - Documents - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(documentsTitle)) {
			System.out.println("Document page is displayed, PASS");
		} else {
			System.out.println("Document page is not displayed, FAIL");
		}

		// clicking on create document img
		DocumentsPage documentsPage = new DocumentsPage(driver);
		documentsPage.clickCreateDocumentsImage();

		// enter title field
		CreateNewDocumentsPage createNewDocuments = new CreateNewDocumentsPage(driver);
		String titleField = elib.getExcelData("Documents", 1, 0);
		createNewDocuments.createDocument(titleField, elib.getExcelData("Documents", 1, 1),"C:\\Users\\ARUN\\OneDrive\\Desktop\\DEMO.txt" );
		
//		Thread.sleep(3000);
//		driver.switchTo().frame(frameElement);
//		
//		WebElement Element = driver.findElement(By.xpath("//body[@class='cke_show_borders']"));
//		
//		System.out.println(Element.isEnabled());
//		JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("arguments[0].innerText='demo entry'", Element);
//		
//		//switchback to default frame
//		driver.switchTo().defaultContent();

		// verify documents is created or not
		String documents = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		if (documents.contains(titleField)) {
			System.out.println("Document is created, PASS");
		} else {
			System.out.println("Document is not created, FAIL");
		}
		//signout
		home.clickSignoutLink(driver);

		// close the browser
		driver.close();

	}

}
