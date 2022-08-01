package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewProductPage {
	
	@FindBy(name = "productname") private WebElement productNameField;
	@FindBy(xpath = "//img[@title='Select']") private WebElement vendorNameImage;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]") private WebElement saveButton;
	
	
	public CreateNewProductPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getProductNameField() {
		return productNameField;
	}


	public WebElement getVendorNameImage() {
		return vendorNameImage;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
}
