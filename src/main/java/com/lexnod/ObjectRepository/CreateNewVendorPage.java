package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewVendorPage {
	
	@FindBy(name = "vendorname") private WebElement vendorNameField;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]") private WebElement saveButton;
	
	
	public CreateNewVendorPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getVendorNameField() {
		return vendorNameField;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}
	
	

}
