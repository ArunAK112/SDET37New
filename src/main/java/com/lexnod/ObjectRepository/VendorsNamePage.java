package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class VendorsNamePage {
	
	@FindBy(id="search_txt") private WebElement searchBox;
	@FindBy(xpath = "//input[@name='search']") private WebElement searchNowButton;
	@FindBy(id="1") private WebElement selectFirstMatchingValue;
	
	public VendorsNamePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	
	public void getVendorName(String vendorName)
	{
		searchBox.sendKeys(vendorName);
		searchNowButton.click();
		selectFirstMatchingValue.click();
	}


}
