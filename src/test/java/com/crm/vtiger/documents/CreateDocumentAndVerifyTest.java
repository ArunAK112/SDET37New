package com.crm.vtiger.documents;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CreateNewDocumentsPage;
import com.lexnod.ObjectRepository.DocumentsPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateDocumentAndVerifyTest extends BaseClass {

	@Test
	public void CreateDocumentAndVerify() throws Throwable {

		

		// click on documents module
		HomePage home = new HomePage(driver);
		home.clickDocumentsModule();

		// verify documents page is displayed or not
		String documentsTitle = "Administrator - Documents - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), documentsTitle, "Document page is not displayed, FAIL");
		Reporter.log("Document page is displayed, PASS", true);

		// clicking on create document img
		DocumentsPage documentsPage = new DocumentsPage(driver);
		documentsPage.clickCreateDocumentsImage();

		// enter title field
		CreateNewDocumentsPage createNewDocuments = new CreateNewDocumentsPage(driver);
		String titleField = eLib.getExcelData("Documents", 1, 0);
		createNewDocuments.createDocument(titleField, eLib.getExcelData("Documents", 1, 1),"C:\\Users\\ARUN\\OneDrive\\Desktop\\DEMO.txt" );
		
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
		Assert.assertTrue(documents.contains(titleField), "Document is not created, FAIL");
		Reporter.log("Document is created, PASS", true);
		

	}

}
