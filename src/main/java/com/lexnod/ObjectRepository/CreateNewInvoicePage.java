package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewInvoicePage {
	
	@FindBy(xpath = "//input[@name='subject']") private WebElement subjectField;
	@FindBy(xpath = "//input[@name='customerno']") private WebElement customerNumberField;
	@FindBy(xpath = "//input[@name='salesorder_name']/..//img[@src='themes/softed/images/select.gif']") private WebElement selectSalesOrderImage;
	@FindBy(xpath = "//input[@id='single_accountid']/..//img[@title='Select']") private WebElement selectOrganizationNameImage;
	@FindBy(xpath = "//textarea[@name='bill_street']") private WebElement billingAddressField;
	@FindBy(xpath = "//textarea[@name='ship_street']") private WebElement shippingAddressField;
	@FindBy(xpath = "//img[@id='searchIcon1']") private WebElement itemDetailsIamge;
	@FindBy(xpath = "//input[@id='qty1']") private WebElement quantityField;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[2]") private WebElement saveButton;
	
	public CreateNewInvoicePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getSubjectField() {
		return subjectField;
	}

	public WebElement getCustomerNumberField() {
		return customerNumberField;
	}

	public WebElement getSelectSalesOrderImage() {
		return selectSalesOrderImage;
	}

	public WebElement getSelectOrganizationNameImage() {
		return selectOrganizationNameImage;
	}

	public WebElement getBillingAddressField() {
		return billingAddressField;
	}

	public WebElement getShippingAddressField() {
		return shippingAddressField;
	}

	public WebElement getItemDetailsIamge() {
		return itemDetailsIamge;
	}

	public WebElement getQuantityField() {
		return quantityField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
	
	
}
