package com.crm.vtiger.Invoice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.lexnod.ObjectRepository.CreateNewInvoicePage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.InvoicePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationNamePage;
import com.lexnod.ObjectRepository.ProductNamePage;
import com.lexnod.ObjectRepository.SalesOrderNamePage;
import com.lexnod.ObjectRepository.SalesOrderPage;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateInvoiceWithSalesAndOrganizationTest {

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

		// mouse hover to more
		HomePage home = new HomePage(driver);
		home.clickInvoiceModule(driver);
		
		// verifying
		String invoiceTitle = "Administrator - Invoice - vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(invoiceTitle)) {
			System.out.println("Invoice page is displayed, PASS");
		} else {
			System.out.println("Invoice page is not displayed, FAIL");
		}

		// clicking on create invoice img
		InvoicePage invoicePage = new InvoicePage(driver);
		invoicePage.clickCreateInvoiceImage();

		// getting data from the excelfile
		// entering values in subject field
		CreateNewInvoicePage createNewInvoice = new CreateNewInvoicePage(driver);
		createNewInvoice.getSubjectField().sendKeys(elib.getExcelData("Invoice", 1, 0));

		// entering customer number
		createNewInvoice.getCustomerNumberField().sendKeys(elib.getExcelData("Invoice", 1, 1));

		// clicking on sales order
		createNewInvoice.getSelectSalesOrderImage().click();
		
		// performing action on create salesorder page
		String parentId = driver.getWindowHandle();
		wlib.switchToWindow("SalesOrder&action", driver);
		// selecting searchbox and sending value
		SalesOrderNamePage salesOrderName = new SalesOrderNamePage(driver);
		salesOrderName.getProjectName(elib.getExcelData("Invoice", 1, 4));
		
		// switching to parent window
		driver.switchTo().window(parentId);

		// clicking on organization name img
		createNewInvoice.getSelectOrganizationNameImage().click();

		// performing actions on organization window
		wlib.switchToWindow("Accounts&action", driver);
		// sending values on search box
		OrganizationNamePage organizationName = new OrganizationNamePage(driver);
		organizationName.getOrganizationName(elib.getExcelData("Invoice", 1, 5));
	
		// clicking on ok (ALERT)
		driver.switchTo().alert().accept();

		// switching back to parent window
		driver.switchTo().window(parentId);

		// passing value on billing address
		createNewInvoice.getBillingAddressField().sendKeys(elib.getExcelData("Invoice", 1, 2));

		// passing value on shipping address
		createNewInvoice.getShippingAddressField().sendKeys(elib.getExcelData("Invoice", 1, 3));

		// item details
		createNewInvoice.getItemDetailsIamge().click();

		// performing actions on products window
		wlib.switchToWindow("Products&action", driver);
		
		// sending value on search box
		ProductNamePage productName = new ProductNamePage(driver);
		productName.getProductName(elib.getExcelData("Invoice", 1, 6));

		// switching to main window
		driver.switchTo().window(parentId);

		// entering qty
		createNewInvoice.getQuantityField().sendKeys(elib.getExcelData("Invoice", 1, 7));

		// clicking on save button
		createNewInvoice.getSaveButton().click();

		// verification
		String invoice = driver.findElement(By.xpath("//span[@class='lvtHeaderText']")).getText();
		if (invoice.contains(elib.getExcelData("Invoice", 1, 0))) {
			System.out.println("Invoice is created, PASS");
		} else {
			System.out.println("Invoice is not created, FAIL");
		}

		//signout
		home.clickSignoutLink(driver);

		// close the browser
		driver.close();

	}

}
