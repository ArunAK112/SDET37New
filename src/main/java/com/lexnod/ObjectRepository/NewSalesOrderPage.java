package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewSalesOrderPage {
	
	@FindBy(xpath = "//input[@name='subject']") private WebElement subjectField;
	@FindBy(xpath = "//img[@tabindex='']") private WebElement opportunityNameImage;
	@FindBy(xpath = "//select[@name='sostatus']") private WebElement statusDropDown;
	@FindBy(xpath = "jscal_field_duedate") private WebElement dueDateField;
	@FindBy(xpath = "(//img[@src='themes/softed/images/select.gif'])[4]") private WebElement organizationsNameImage;
	@FindBy(xpath = "//select[@name='invoicestatus']") private WebElement invoiceStatusDropdown;
	@FindBy(xpath = "//textarea[@name='bill_street']") private WebElement billingAddressField;
	@FindBy(xpath = "//textarea[@name='ship_street']") private WebElement shippingAddressField;
	@FindBy(xpath = "//img[@id='searchIcon1']") private WebElement itemDetailsImage;
	@FindBy(xpath = "//input[@id='qty1']") private WebElement quantityField;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[2]") private WebElement saveButton;
	
	
	public NewSalesOrderPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getSubjectField() {
		return subjectField;
	}


	public WebElement getOpportunityNameImage() {
		return opportunityNameImage;
	}


	public WebElement getStatusDropDown() {
		return statusDropDown;
	}


	public WebElement getDueDateField() {
		return dueDateField;
	}


	public WebElement getOrganizationsNameImage() {
		return organizationsNameImage;
	}


	public WebElement getInvoiceStatusDropdown() {
		return invoiceStatusDropdown;
	}


	public WebElement getBillingAddressField() {
		return billingAddressField;
	}


	public WebElement getShippingAddressField() {
		return shippingAddressField;
	}


	public WebElement getItemDetailsImage() {
		return itemDetailsImage;
	}


	public WebElement getQuantityField() {
		return quantityField;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
	
	

}
