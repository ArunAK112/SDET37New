package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OpportunitiesPage {
	
	@FindBy(xpath = "//img[@title='Create Opportunity...']") private WebElement createOpportunitiesImage;
	
	public OpportunitiesPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickCreateOpportinitiesImage()
	{
		createOpportunitiesImage.click();
	}

}
