package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewOrganizationPage {
	
	@FindBy(name = "accountname") private WebElement organizationNameField;
	@FindBy(xpath = "//select[@name='industry']") private WebElement industryDropdown;
	@FindBy(xpath = "//select[@name='accounttype']") private WebElement typeDropdown;
	@FindBy(id = "email1") private WebElement emailField;
	@FindBy(xpath = "(//input[@class='crmbutton small save'])[1]") private WebElement saveButton;
	

	public NewOrganizationPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getOrganizationNameField() {
		return organizationNameField;
	}


	public WebElement getIndustryDropdown() {
		return industryDropdown;
	}


	public WebElement getTypeDropdown() {
		return typeDropdown;
	}


	public WebElement getEmailField() {
		return emailField;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
}
