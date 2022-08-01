package com.crm.vtiger.salesorder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;

import com.lexnod.ObjectRepository.CreateNewSalesOrderPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OpportunitiesNamePage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.ProductNamePage;
import com.lexnod.ObjectRepository.SalesOrderPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateSalesOrderwithOpportunitiesTest {

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
		LoginPage loginPage = new LoginPage(driver);
		loginPage.login(plib.getPropertyData("username"), plib.getPropertyData("password"));

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		wlib.waitForPageTitle("Administrator", driver, 10);
		System.out.println("VTiger Home page is displayed, PASS");
		
		// mouse hover to more and click sales order
		HomePage home = new HomePage(driver);
		home.clickSalesOrderModule(driver);
		
		// verification
		String salesOrderTitle = "Administrator - Sales Order - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(salesOrderTitle)) {
			System.out.println("Sales order page is displayed, PASS");
		} else {
			System.out.println("Sales order page is not displayed, FAIL");
		}

		// clicking on create sales order img
		SalesOrderPage salesOrder = new SalesOrderPage(driver);
		salesOrder.clickCreateSalesOrderImage();

		// entering values on subjectfield
		CreateNewSalesOrderPage createNewSalesOder = new CreateNewSalesOrderPage(driver);
		String subject = elib.getExcelData("salesOrder", 1, 0)+jlib.getRandonNumber(100);
		createNewSalesOder.getSubjectField().sendKeys(subject);
		
		// clicking opportunity img
		createNewSalesOder.getOpportunityNameImage().click();

		// switching window
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("Potentials&action", driver);

		// selecting opportunities
		OpportunitiesNamePage opportunityName = new OpportunitiesNamePage(driver);
		opportunityName.getOpportunityName(elib.getExcelData("opportunities", 1, 0));

		// switching back to parent
		driver.switchTo().window(parentId);

		// selecting status
		createNewSalesOder.selectStatusDropdown(elib.getExcelData("AllDropDown", 3, 4));
		
		// entering due date
		createNewSalesOder.getDueDateField().sendKeys(elib.getExcelData("salesOrder", 1, 1));
//		driver.findElement(By.xpath("//img[@id='jscal_trigger_duedate']")).click();
//		WebElement date = driver.findElement(By.xpath("//td[contains(text(),'15')]"));
//		date.click();
		System.out.println("Due date selected sucessfully");

		// clicking on organization img
		createNewSalesOder.getOrganizationsNameImage().click();

		// switching window organization
		wlib.switchToWindow("Accounts&action", driver);

		// clicking on organization name
		OrganizationNamePage organizationName = new OrganizationNamePage(driver);
		organizationName.getOrganizationName(elib.getExcelData("Organization", 2, 0));

		// alert in accounts and actions
		driver.switchTo().alert().accept();

		// switching back to parent
		driver.switchTo().window(parentId);

		// selecting invoice status dropdown
		createNewSalesOder.selectInvoiceStatusDropdown(elib.getExcelData("AllDropDown", 2, 4));

		// entering billing adddress
		createNewSalesOder.getBillingAddressField().sendKeys(elib.getExcelData("salesOrder", 1, 2));

		// entering shipping address
		createNewSalesOder.getShippingAddressField().sendKeys(elib.getExcelData("salesOrder", 1, 3));

		// clicking on select item img
		createNewSalesOder.getItemDetailsImage().click();

		// switching the window to select product item
		wlib.switchToWindow("Products&action", driver);

		// clicking on product
		ProductNamePage productName = new ProductNamePage(driver);
		productName.getProductName(elib.getExcelData("Product", 1, 0));

		// switching back to parent
		driver.switchTo().window(parentId);

		// clicking on qty
		createNewSalesOder.getQuantityField().sendKeys(elib.getExcelData("salesOrder", 1, 4));

		// clicking on save
		createNewSalesOder.getSaveButton().click();

		// verify sales order is created or not
		String salesText = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (salesText.contains(subject)) {
			System.out.println("Sales order is created, PASS");
		} else {
			System.out.println("Sales order is not created, FAIL");
		}

		//signout
		home.clickSignoutLink(driver);
		
		// close the browser
		driver.close();

	}

}
