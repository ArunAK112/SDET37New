package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewContactsPage {
	
	@FindBy(xpath = "//select[@name='salutationtype']") private WebElement salutationDropdown;
	@FindBy(xpath = "//input[@name='firstname']") private WebElement firstnameField;
	@FindBy(xpath = "//input[@name='lastname']") private WebElement lastnameField;
	@FindBy(xpath = "//tbody/tr[5]/td[2]/img[1]") private WebElement organizationNameImage;
	@FindBy(xpath = "//input[@class='crmButton small save']") private WebElement saveButton;

	
	public NewContactsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getSalutationDropdown() {
		return salutationDropdown;
	}


	public WebElement getFirstnameField() {
		return firstnameField;
	}


	public WebElement getLastnameField() {
		return lastnameField;
	}


	public WebElement getOrganizationNameImage() {
		return organizationNameImage;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
}
