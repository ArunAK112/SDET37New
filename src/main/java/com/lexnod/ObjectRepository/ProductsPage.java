package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductsPage {
	
	@FindBy(xpath = "//img[@title='Create Product...']") private WebElement createProductsButton;
	
	
	public ProductsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void clickCreateProductsButton()
	{
		createProductsButton.click();
	}

}
