package com.crm.vtiger.organization;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CreateNewOrganizationPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OrganizationsPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOrganizationAndVerifyTest extends BaseClass {
	@Test(groups = "smokeTest")

	public void CreateOrganizationAndVerify() throws Throwable {

		
		// click on organization module
		HomePage home = new HomePage(driver);
		home.clickOrganizationModule();
		
		// VERIFY ORGANIZATION PAGE IS DISPLAYED OR NOT
		String organizationTitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), organizationTitle, "Organization page is not displayed, FAIL");
		Reporter.log("Organization page is displayed, PASS", true);
	

		// clicking on create organization button
		OrganizationsPage organizationsPage = new OrganizationsPage(driver);
		organizationsPage.clickCreateOrganizationsImage();

		// Entering the organization name
		CreateNewOrganizationPage createNewOrganization = new CreateNewOrganizationPage(driver);
		String organizationName = eLib.getExcelData("Organization", 2, 0)+jLib.getRandonNumber(100);
		createNewOrganization.getOrganizationNameField().sendKeys(organizationName);

		// click on save button
		createNewOrganization.getSaveButton().click();
		
		// verification
		String organizationNameVerify = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(organizationNameVerify.contains(organizationName), "Organization name not created, FALSE");
		Reporter.log("Organization name created, TRUE", true);

		
	}

}
