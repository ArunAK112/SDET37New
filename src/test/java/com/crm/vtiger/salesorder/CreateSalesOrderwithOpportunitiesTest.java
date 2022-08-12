package com.crm.vtiger.salesorder;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CreateNewSalesOrderPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OpportunitiesNamePage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.ProductNamePage;
import com.lexnod.ObjectRepository.SalesOrderPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSalesOrderwithOpportunitiesTest extends BaseClass {

	@Test
	public void CreateSalesOrderwithOpportunities() throws Throwable {

		
		// mouse hover to more and click sales order
		HomePage home = new HomePage(driver);
		home.clickSalesOrderModule(driver);
		
		// verification
		String salesOrderTitle = "Administrator - Sales Order - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), salesOrderTitle, "Sales order page is not displayed, FAIL");
		Reporter.log("Sales order page is displayed, PASS", true);

		// clicking on create sales order img
		SalesOrderPage salesOrder = new SalesOrderPage(driver);
		salesOrder.clickCreateSalesOrderImage();

		// entering values on subjectfield
		CreateNewSalesOrderPage createNewSalesOder = new CreateNewSalesOrderPage(driver);
		String subject = eLib.getExcelData("salesOrder", 1, 0)+jLib.getRandonNumber(100);
		createNewSalesOder.getSubjectField().sendKeys(subject);
		
		// clicking opportunity img
		createNewSalesOder.getOpportunityNameImage().click();

		// switching window
		String parentId = driver.getWindowHandle();
		wLib.switchToWindow("Potentials&action", driver);

		// selecting opportunities
		OpportunitiesNamePage opportunityName = new OpportunitiesNamePage(driver);
		opportunityName.getOpportunityName(eLib.getExcelData("opportunities", 1, 0));

		// switching back to parent
		driver.switchTo().window(parentId);

		// selecting status
		createNewSalesOder.selectStatusDropdown(eLib.getExcelData("AllDropDown", 3, 4));
		
		// entering due date
		createNewSalesOder.getDueDateField().sendKeys(eLib.getExcelData("salesOrder", 1, 1));
//		driver.findElement(By.xpath("//img[@id='jscal_trigger_duedate']")).click();
//		WebElement date = driver.findElement(By.xpath("//td[contains(text(),'15')]"));
//		date.click();
		System.out.println("Due date selected sucessfully");

		// clicking on organization img
		createNewSalesOder.getOrganizationsNameImage().click();

		// switching window organization
		wLib.switchToWindow("Accounts&action", driver);

		// clicking on organization name
		OrganizationNamePage organizationName = new OrganizationNamePage(driver);
		organizationName.getOrganizationName(eLib.getExcelData("Organization", 2, 0));

		// alert in accounts and actions
		driver.switchTo().alert().accept();

		// switching back to parent
		driver.switchTo().window(parentId);

		// selecting invoice status dropdown
		createNewSalesOder.selectInvoiceStatusDropdown(eLib.getExcelData("AllDropDown", 2, 4));

		// entering billing adddress
		createNewSalesOder.getBillingAddressField().sendKeys(eLib.getExcelData("salesOrder", 1, 2));

		// entering shipping address
		createNewSalesOder.getShippingAddressField().sendKeys(eLib.getExcelData("salesOrder", 1, 3));

		// clicking on select item img
		createNewSalesOder.getItemDetailsImage().click();

		// switching the window to select product item
		wLib.switchToWindow("Products&action", driver);

		// clicking on product
		ProductNamePage productName = new ProductNamePage(driver);
		productName.getProductName(eLib.getExcelData("Product", 1, 0));

		// switching back to parent
		driver.switchTo().window(parentId);

		// clicking on qty
		createNewSalesOder.getQuantityField().sendKeys(eLib.getExcelData("salesOrder", 1, 4));

		// clicking on save
		createNewSalesOder.getSaveButton().click();

		// verify sales order is created or not
		String salesText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		Assert.assertTrue(salesText.contains(subject),"Sales order is not created, FAIL" );
		Reporter.log("Sales order is created, PASS", true);

	}

}
