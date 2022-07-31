package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NewDocumentsPage {
	
	@FindBy(name = "notes_title") private WebElement titleField;
	@FindBy(xpath = "//iframe[@title='Rich text editor, notecontent, press ALT 0 for help.']") private WebElement notesField;
	@FindBy(name = "filename") private WebElement filenameBrowseButton;
	@FindBy(xpath = "(//input[@type='submit'])[2]") private WebElement saveButton;
	
	
	public NewDocumentsPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	public void createDocument(String title, String notes, String fileUpload)
	{
		titleField.sendKeys(title);
		notesField.sendKeys(notes);
		filenameBrowseButton.sendKeys(fileUpload);
		saveButton.click();
	}
}
