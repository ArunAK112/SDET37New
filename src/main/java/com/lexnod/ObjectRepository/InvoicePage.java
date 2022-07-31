package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class InvoicePage {
	
	@FindBy(xpath = "//img[@title='Create Invoice...']") private WebElement createInvoiceImage;
	
	
	public InvoicePage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public void clickCreateInvoiceImage()
	{
		createInvoiceImage.click();
	}
	
}
