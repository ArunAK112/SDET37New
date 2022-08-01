package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lexnod.genericLib.WebDriverCommonLibrary;


public class HomePage extends WebDriverCommonLibrary{
	
	@FindBy(xpath="//a[text()='Organizations' and @href='index.php?module=Accounts&action=index']") private WebElement organizationModule;
	@FindBy(xpath = "//a[@href='index.php?module=Calendar&action=index']") private WebElement calenderModule;
	@FindBy(xpath = "//a[@href='index.php?module=Leads&action=index']") private WebElement leadsModule;
	@FindBy(xpath = "//a[@href='index.php?module=Contacts&action=index' ]") private WebElement contactsModule;
	@FindBy(xpath = "//a[@href='index.php?module=Potentials&action=index' ]") private WebElement opportunitiesModule;
	@FindBy(xpath = "//a[@href='index.php?module=Products&action=index' ]") private WebElement productsModule;
	@FindBy(xpath = "//a[@href='index.php?module=Documents&action=index' ]") private WebElement documentsModule;
	@FindBy(xpath = "//a[@href='index.php?module=Emails&action=index' ]") private WebElement emailModule;
	@FindBy(xpath = "//a[@href='index.php?module=HelpDesk&action=index' ]") private WebElement troubleTicketsModule;
	@FindBy(xpath = "//a[@href='index.php?module=Dashboard&action=index' ]") private WebElement dashboardModule;
	@FindBy(xpath = "//a[@href='index.php?module=Invoice&action=index' ]") private WebElement invoiceModule;
	@FindBy(xpath = "//a[@href='index.php?module=SalesOrder&action=index' ]") private WebElement salesOrderModule;
	@FindBy(xpath = "//a[@href='index.php?module=Quotes&action=index' ]") private WebElement quotesModule;
	@FindBy(xpath = "//a[@href='index.php?module=Services&action=index' ]") private WebElement servicesModuel;
	@FindBy(xpath = "//a[@href='index.php?module=Assets&action=index' ]") private WebElement assetsModule;
	@FindBy(xpath = "//a[@href='index.php?module=PurchaseOrder&action=index' ]") private WebElement purchaseOrderModule;
	@FindBy(xpath = "//a[@href='index.php?module=PriceBooks&action=index' ]") private WebElement priceBooksModule;
	@FindBy(xpath = "//a[@href='index.php?module=Vendors&action=index' ]") private WebElement vendorsModule;
	@FindBy(xpath = "//a[@href='index.php?module=ServiceContracts&action=index' ]") private WebElement serviceContractsModule;
	@FindBy(xpath = "//a[@href='index.php?module=ProjectMilestone&action=index' ]") private WebElement projectMilestoneModule;
	@FindBy(xpath = "//a[@href='index.php?module=ProjectTask&action=index' ]") private WebElement projectTasksModule;
	@FindBy(xpath = "//a[@href='index.php?module=Project&action=index' ]") private WebElement projectsModule;
	@FindBy(xpath = "//a[@href='index.php?module=Faq&action=index' ]") private WebElement faqModule;
	@FindBy(xpath = "//a[@href='index.php?module=Campaigns&action=index' ]") private WebElement campaignsModule;
	@FindBy(xpath = "//a[@href='index.php?module=Reports&action=index' ]") private WebElement reportsModule;
	@FindBy(xpath = "//a[text()='More']") private WebElement moreButton;
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']") private WebElement administrationButton;
	@FindBy(xpath = "//a[text()='Sign Out']") private WebElement signoutButton;
	
	public HomePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	public void clickOrganizationModule()
	{
		organizationModule.click();
	}
	
	public void clickAssetsModule(WebDriver driver)
	{
		mouseHoverOnElement(moreButton, driver);
		assetsModule.click();
	}
	
	public void clickCampaignModule(WebDriver driver)
	{
		mouseHoverOnElement(moreButton, driver);
		campaignsModule.click();
	}
	
	public void clickContactsModule()
	{
		contactsModule.click();
	}
	
	public void clickDocumentsModule()
	{
		documentsModule.click();
	}
	
	public void clickEmailModule()
	{
		emailModule.click();
	}
	
	public void clickInvoiceModule(WebDriver driver)
	{
		mouseHoverOnElement(moreButton, driver);
		invoiceModule.click();
	}
	
	public void clickOpportunitiesModule()
	{
		opportunitiesModule.click();
	}
	
	public void clickProductsModule()
	{
		productsModule.click();
	}
	
	public void clickSalesOrderModule(WebDriver driver)
	{
		mouseHoverOnElement(moreButton, driver);
		salesOrderModule.click();
	}
	
	public void clickVendorsModule(WebDriver driver)
	{
		mouseHoverOnElement(moreButton, driver);
		vendorsModule.click();
	}
	
	
	public void clickSignoutLink(WebDriver driver)
	{
		mouseHoverOnElement(administrationButton, driver);
		signoutButton.click();
	}
	

}
