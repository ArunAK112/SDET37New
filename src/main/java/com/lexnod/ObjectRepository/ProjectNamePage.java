package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProjectNamePage {
	@FindBy(id="search_txt") private WebElement searchBox;
	@FindBy(xpath = "//input[@name='search']") private WebElement searchNowButton;
	@FindBy(id="1") private WebElement selectFirstMatchingValue;
	
	
	public ProjectNamePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void searchProjectName(String projectName)
	{
		searchBox.sendKeys(projectName);
		searchNowButton.click();
		selectFirstMatchingValue.click();
	}

}
