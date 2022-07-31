package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewAssetPage {
	
	@FindBy(xpath = "//input[@name='serialnumber']") private WebElement serialNumberField;
	@FindBy(xpath = "//input[@name='product_display']/..//img[@src='themes/softed/images/select.gif']") private WebElement productNameImage;
	@FindBy(xpath = "//input[@id='account_display']/..//img[@src='themes/softed/images/select.gif']") private WebElement customerNameImage;
	@FindBy(xpath = "//input[@id='assetname']") private WebElement assetNameField;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[2]") private WebElement saveButton;
	
	
	public NewAssetPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	
	public WebElement getSerialNumberField() {
		return serialNumberField;
	}



	public WebElement getProductNameImage() {
		return productNameImage;
	}



	public WebElement getCustomerNameImage() {
		return customerNameImage;
	}



	public WebElement getAssetNameField() {
		return assetNameField;
	}



	public WebElement getSaveButton() {
		return saveButton;
	}

}
