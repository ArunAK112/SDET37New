package com.crm.vtiger.Invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.lexnod.ObjectRepository.CreateNewInvoicePage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.InvoicePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.ProductNamePage;
import com.lexnod.ObjectRepository.SalesOrderNamePage;
import com.lexnod.ObjectRepository.SalesOrderPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateInvoiceWithSalesAndOrganizationTest extends BaseClass {

	@Test
	public void CreateInvoiceWithSalesAndOrganization() throws Throwable {

		

		// mouse hover to more
		HomePage home = new HomePage(driver);
		home.clickInvoiceModule(driver);
		
		// verifying
		String invoiceTitle = "Administrator - Invoice - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), invoiceTitle, "Invoice page is not displayed, FAIL");
		Reporter.log("Invoice page is displayed, PASS", true);

		// clicking on create invoice img
		InvoicePage invoicePage = new InvoicePage(driver);
		invoicePage.clickCreateInvoiceImage();

		// getting data from the excelfile
		// entering values in subject field
		CreateNewInvoicePage createNewInvoice = new CreateNewInvoicePage(driver);
		createNewInvoice.getSubjectField().sendKeys(eLib.getExcelData("Invoice", 1, 0));

		// entering customer number
		createNewInvoice.getCustomerNumberField().sendKeys(eLib.getExcelData("Invoice", 1, 1));

		// clicking on sales order
		createNewInvoice.getSelectSalesOrderImage().click();
		
		// performing action on create salesorder page
		String parentId = driver.getWindowHandle();
		wLib.switchToWindow("SalesOrder&action", driver);
		// selecting searchbox and sending value
		SalesOrderNamePage salesOrderName = new SalesOrderNamePage(driver);
		salesOrderName.getProjectName(eLib.getExcelData("Invoice", 1, 4));
		
		// switching to parent window
		driver.switchTo().window(parentId);

		// clicking on organization name img
		createNewInvoice.getSelectOrganizationNameImage().click();

		// performing actions on organization window
		wLib.switchToWindow("Accounts&action", driver);
		// sending values on search box
		OrganizationNamePage organizationName = new OrganizationNamePage(driver);
		organizationName.getOrganizationName(eLib.getExcelData("Invoice", 1, 5));
	
		// clicking on ok (ALERT)
		driver.switchTo().alert().accept();

		// switching back to parent window
		driver.switchTo().window(parentId);

		// passing value on billing address
		createNewInvoice.getBillingAddressField().sendKeys(eLib.getExcelData("Invoice", 1, 2));

		// passing value on shipping address
		createNewInvoice.getShippingAddressField().sendKeys(eLib.getExcelData("Invoice", 1, 3));

		// item details
		createNewInvoice.getItemDetailsIamge().click();

		// performing actions on products window
		wLib.switchToWindow("Products&action", driver);
		
		// sending value on search box
		ProductNamePage productName = new ProductNamePage(driver);
		productName.getProductName(eLib.getExcelData("Invoice", 1, 6));

		// switching to main window
		driver.switchTo().window(parentId);

		// entering qty
		createNewInvoice.getQuantityField().sendKeys(eLib.getExcelData("Invoice", 1, 7));

		// clicking on save button
		createNewInvoice.getSaveButton().click();

		// verification
		String invoice = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		Assert.assertTrue(invoice.contains(eLib.getExcelData("Invoice", 1, 0)), "Invoice is not created, FAIL");
		Reporter.log("Invoice is created, PASS");

	

	}

}
