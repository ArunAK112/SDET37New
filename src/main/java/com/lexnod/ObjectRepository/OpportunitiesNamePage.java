package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesNamePage {
	@FindBy(id="search_txt") private WebElement searchBox;
	@FindBy(xpath = "//input[@name='search']") private WebElement searchNowButton;
	@FindBy(id="1") private WebElement selectFirstMatchingValue;
	
	public OpportunitiesNamePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void getOrganizationName(String opportunityName)
	{
		searchBox.sendKeys(opportunityName);
		searchNowButton.click();
		selectFirstMatchingValue.click();
	}


}
