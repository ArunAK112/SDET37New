package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ComposeEmailPage {
	
	@FindBy(xpath = "//img[@src='themes/softed/images/select.gif']") private WebElement selectToImage;
	@FindBy(id = "subject") private WebElement subjectField;
	@FindBy(xpath = "//iframe[@title='Rich text editor, description, press ALT 0 for help.']") private WebElement bodyField;
	@FindBy(xpath = "(//input[@title='Save [Alt+S]'])[1]") private WebElement saveButton;
	
	public ComposeEmailPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}

	public WebElement getSelectToImage() {
		return selectToImage;
	}

	public WebElement getSubjectField() {
		return subjectField;
	}

	public WebElement getBodyField() {
		return bodyField;
	}

	public WebElement getSaveButton() {
		return saveButton;
	}
	
	

}
