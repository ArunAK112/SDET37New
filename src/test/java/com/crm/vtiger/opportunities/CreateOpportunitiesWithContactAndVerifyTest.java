package com.crm.vtiger.opportunities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.lexnod.ObjectRepository.CampaignsNamePage;
import com.lexnod.ObjectRepository.ContactsNamePage;
import com.lexnod.ObjectRepository.CreateNewOpportunitiesPage;
import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;
import com.lexnod.ObjectRepository.OpportunitiesPage;
import com.lexnod.genericLib.BaseClass;
import com.lexnod.genericLib.ExcelFileLibrary;
import com.lexnod.genericLib.JavaUtility;
import com.lexnod.genericLib.PropertyFileLibrary;
import com.lexnod.genericLib.WebDriverCommonLibrary;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateOpportunitiesWithContactAndVerifyTest extends BaseClass{

	@Test
	public void CreateOpportunitiesWithContactAndVerify() throws Throwable {

		

		// clicking on opportunities
		HomePage home = new HomePage(driver);
		home.clickOpportunitiesModule();

		// verify opportunity page is displayed or not
		String opportunityTitle = "Administrator - Opportunities - vtiger CRM 5 - Commercial Open Source CRM";
		Assert.assertEquals(driver.getTitle(), opportunityTitle , "Opportunity page is not displayed, FAIL");
		Reporter.log("Opportunity page is displayed, PASS", true);
		

		// clicking on createopportunity img
		OpportunitiesPage opportunitiesPage = new OpportunitiesPage(driver);
		opportunitiesPage.clickCreateOpportinitiesImage();

		// enter value in opportunity name field
		CreateNewOpportunitiesPage createNewOpportunity = new CreateNewOpportunitiesPage(driver);
		String opportunityName = eLib.getExcelData("opportunities", 1, 0)+ jLib.getRandonNumber(100);
		createNewOpportunity.getOpportunitiesNameField().sendKeys(opportunityName);
		
		// select relatedto dropdown
		createNewOpportunity.selectRelatedToDropdown(eLib.getExcelData("AllDropDown", 2, 5));

		// click on relatedto img
		createNewOpportunity.getRelatedToImage().click();

		// switching the window
		String parentId = driver.getWindowHandle();
		wLib.switchToWindow("Contacts&action", driver);

		// enter value in search field
		ContactsNamePage contactsName = new ContactsNamePage(driver);
		contactsName.getContactsName(eLib.getExcelData("contacts", 1, 1));

		// switch to main window
		driver.switchTo().window(parentId);

		// expected close date element
		WebElement dateElement = createNewOpportunity.getExpectedCloseDateField();
	
		// clearing the date field
		dateElement.clear();

		// entering date in date field
		dateElement.sendKeys(eLib.getExcelData("opportunities", 1, 1));

		// selecting sales stage dropdown
		createNewOpportunity.selectSalesStageDropdown(eLib.getExcelData("AllDropDown", 5, 6));
		
		// click on campaign source img
		createNewOpportunity.getCampaignSourceImage().click();

		// switching to window
		wLib.switchToWindow("Campaigns&action", driver);

		// clciking on campaign name
		CampaignsNamePage campaignName = new CampaignsNamePage(driver);
		campaignName.getCampaignName(eLib.getExcelData("Campaign", 1, 0));

		// switching back to main window
		driver.switchTo().window(parentId);

		// click on save button
		createNewOpportunity.getSaveButton().click();

		// verification
		String opportunity = driver.findElement(By.xpath("//span[@class='dvHeaderText']")).getText();
		Assert.assertTrue(opportunity.contains(opportunityName), "Opportunity is not created, FAIL");
		Reporter.log("Opportunity is created, PASS", true);

		

	}

}
