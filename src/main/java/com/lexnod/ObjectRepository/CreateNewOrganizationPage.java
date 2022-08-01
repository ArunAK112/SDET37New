package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lexnod.genericLib.WebDriverCommonLibrary;

public class CreateNewOrganizationPage extends WebDriverCommonLibrary{
	
	@FindBy(name = "accountname") private WebElement organizationNameField;
	@FindBy(xpath = "//select[@name='industry']") private WebElement industryDropdown;
	@FindBy(xpath = "//select[@name='accounttype']") private WebElement typeDropdown;
	@FindBy(id = "email1") private WebElement emailField;
	@FindBy(xpath = "(//input[@class='crmbutton small save'])[1]") private WebElement saveButton;
	

	public CreateNewOrganizationPage(WebDriver driver)
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
	
	public void selectIndustryDropdown(String value)
	{
		select(industryDropdown, value);
	}
	
	public void selectTypeDropdown(String value)
	{
		select(typeDropdown, value);
	}
}
