package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AssetsPage {
	
	@FindBy(xpath = "//img[@src='themes/softed/images/btnL3Add.gif']") private WebElement createAssetsImage;
	
	
	public AssetsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickCreateAssetsIamge()
	{
		createAssetsImage.click();
	}

}
