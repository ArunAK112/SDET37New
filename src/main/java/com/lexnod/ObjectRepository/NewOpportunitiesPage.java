package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewOpportunitiesPage {
	
	@FindBy(xpath = "//input[@name='potentialname']") private WebElement opportunitiesNameField;
	@FindBy(id = "related_to_type") private WebElement relatedToDropdown;
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif' and @tabindex='']") private WebElement relatedToImage;
	@FindBy(xpath = "//input[@id='jscal_field_closingdate']") private WebElement expectedCloseDateField;
	@FindBy(xpath = "//select[@name='sales_stage']") private WebElement salesStageDropdown;
	@FindBy(xpath = "//input[@name='campaignname']/..//img") private WebElement campaignSourceImage;
	@FindBy(xpath = "(//input[@type='submit'])[1]") private WebElement saveButton;
	
	
	public NewOpportunitiesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}


	public WebElement getOpportunitiesNameField() {
		return opportunitiesNameField;
	}


	public WebElement getRelatedToDropdown() {
		return relatedToDropdown;
	}


	public WebElement getRelatedToImage() {
		return relatedToImage;
	}


	public WebElement getExpectedCloseDateField() {
		return expectedCloseDateField;
	}


	public WebElement getSalesStageDropdown() {
		return salesStageDropdown;
	}


	public WebElement getCampaignSourceImage() {
		return campaignSourceImage;
	}


	public WebElement getSaveButton() {
		return saveButton;
	}
	
	
	
}
