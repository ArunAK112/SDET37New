package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesOrderPage {
	
	@FindBy(xpath = "//img[@title='Create Sales Order...']") private WebElement createSalesOrderImage;
	
	
	public SalesOrderPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickCreateSalesOrderImage()
	{
		createSalesOrderImage.click();
	}

}
