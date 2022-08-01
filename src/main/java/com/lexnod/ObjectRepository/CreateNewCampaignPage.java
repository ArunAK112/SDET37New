package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewCampaignPage {
	
	@FindBy(name = "campaignname") private WebElement camapaignNameField;
	@FindBy(xpath = "//img[@title='Select']") private WebElement addProductImage;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]") private WebElement savebutton;
	
	
	public CreateNewCampaignPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getCamapaignNameField() {
		return camapaignNameField;
	}


	public WebElement getAddProductImage() {
		return addProductImage;
	}


	public WebElement getSavebutton() {
		return savebutton;
	}
	
	

}
