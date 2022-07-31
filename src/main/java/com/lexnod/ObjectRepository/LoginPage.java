package com.lexnod.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * 
 * @author ARUN
 *
 */

public class LoginPage {
	
	
	//declaration
	@FindBy(name="user_name") private WebElement userNameTextbox;
	@FindBy(name="user_password") private WebElement passwordTextbox;
	@FindBy(id="submitButton") private WebElement loginButton;
		
	//initialization
	public LoginPage(WebDriver driver)
	{
		PageFactory.initElements(driver, this);
	}
	
	//utilization
	public WebElement getUserNameTextbox() {
		return userNameTextbox;
	}
	public WebElement getPasswordTextbox() {
		return passwordTextbox;
	}
	public WebElement getLoginButton() {
		return loginButton;
	}
	
	public void login(String userName, String password)
	{
		userNameTextbox.sendKeys(userName);
		passwordTextbox.sendKeys(password);
		loginButton.click();
	}
	
	

}
