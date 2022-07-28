package com.lexnod.genericLib;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * 
 * @author ARUN
 *
 */
public class WebDriverCommonLibrary {

	/**
	 * to maximize the browser
	 * 
	 * @param driver
	 */

	public void maximizeTheWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	/**
	 * to get the page titile
	 * 
	 * @return
	 */

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	/**
	 * to minimize the browser
	 * 
	 * @param driver
	 */

	public void minimizeTheWindow(WebDriver driver) {
		driver.manage().window().minimize();
	}

	/**
	 * to refresh the browser
	 * 
	 * @param driver
	 */
	public void refreshThePage(WebDriver driver) {
		driver.navigate().refresh();
	}

	/**
	 * to go back to the prevoius page
	 * 
	 * @param driver
	 */
	public void backToPreviousPage(WebDriver driver) {
		driver.navigate().back();
	}

	/**
	 * to navigate forward/next page
	 * 
	 * @param driver
	 */
	public void forwardToNextPage(WebDriver driver) {
		driver.navigate().forward();
	}

	/**
	 * this method wait till page gets loaded
	 * 
	 * @param driver
	 * @param duartion
	 */
	public void waitTillPageGetsLoadImplicitlyWait(WebDriver driver, int duartion) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duartion));

	}

	/**
	 * this method will wait until element is clicked
	 * 
	 * @param driver
	 * @param element
	 * @param duration
	 */
	public void waitTillElementToClick(WebDriver driver, WebElement element, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	/**
	 * this method will wait until element is visible
	 * 
	 * @param driver
	 * @param element
	 * @param duration
	 */
	public void waitTillElementToVisible(WebDriver driver, WebElement element, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * this method wait until title is loaded
	 * 
	 * @param title
	 * @param driver
	 * @param duration
	 */
	public void waitForPageTitle(String title, WebDriver driver, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.titleContains(title));
	}

	/**
	 * this method wait until url is loaded
	 * 
	 * @param driver
	 * @param duration
	 * @param url
	 */
	public void waitTillPageLoadUrl(WebDriver driver, int duration, String url) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.urlContains(url));
	}

	/**
	 * this method will ignore no such element exception for the particular
	 * 
	 * @param duration
	 * @param driver
	 */
	public void ignoreNoSuchElementException(int duration, WebDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.ignoring(NoSuchElementException.class);
	}

	/**
	 * this method wait till the alert message is visible
	 * 
	 * @param driver
	 * @param duration
	 */
	public void waitForAlertMessage(WebDriver driver, int duration) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration));
		wait.until(ExpectedConditions.alertIsPresent());
	}

	/**
	 * this method is used to switch the frame using index
	 * 
	 * @param driver
	 * @param index
	 */
	public void switchToFrame(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	/**
	 * this method is used to switch the frame using element
	 * 
	 * @param driver
	 * @param element
	 */
	public void switchToFrame(WebDriver driver, WebElement element) {
		driver.switchTo().frame(element);
	}

	/**
	 * this method is used to switch the frame using id or name
	 * 
	 * @param driver
	 * @param nameOrId
	 */
	public void switchToFrame(WebDriver driver, String nameOrId) {
		driver.switchTo().frame(nameOrId);
	}

	/**
	 * this method is used to switch to main frame
	 * 
	 * @param driver
	 */
	public void switchToMainFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	/**
	 * used to select dropdown by using index
	 * 
	 * @param element
	 * @param index
	 */
	public void select(WebElement element, int index) {
		Select select = new Select(element);
		select.selectByIndex(index);
	}

	/**
	 * used to select dropdown by using value
	 * 
	 * @param element
	 * @param value
	 */
	public void select(WebElement element, String value) {
		Select select = new Select(element);
		select.selectByValue(value);
	}

	/**
	 * used to select dropdown by using visible text
	 * 
	 * @param text
	 * @param element
	 */
	public void select(String text, WebElement element) {
		Select select = new Select(element);
		select.selectByVisibleText(text);
	}

	/**
	 * used to get all options from the dropdown
	 * 
	 * @param element
	 */
	public void getAllTheOptionsFromDropdown(WebElement element) {
		Select select = new Select(element);
		List<WebElement> options = select.getOptions();
		for (WebElement webElement : options) {
			String text = webElement.getText();
			System.out.println(text);
		}
	}

	/**
	 * used to mousehover on the element
	 * 
	 * @param element
	 * @param driver
	 */
	public void mouseHoverOnElement(WebElement element, WebDriver driver) {
		Actions action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	/**
	 * used to perform rightclick
	 * 
	 * @param driver
	 * @param element
	 */
	public void rightClick(WebDriver driver, WebElement element) {
		Actions action = new Actions(driver);
		action.contextClick(element).perform();
	}

	/**
	 * used to double click on element
	 * 
	 * @param element
	 * @param driver
	 */
	public void doubleClick(WebElement element, WebDriver driver) {
		Actions action = new Actions(driver);
		action.doubleClick(element).perform();
	}

	/**
	 * used to press enter key
	 * 
	 * @param driver
	 */
	public void clickOnEnterKey(WebDriver driver) {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.ENTER).perform();
	}

	/**
	 * used to take screenshots
	 * 
	 * @param driver
	 * @param screenShotName
	 */
	public void takesScreenShot(WebDriver driver, String screenShotName) {
		TakesScreenshot screenshots = (TakesScreenshot) driver;
		File src = screenshots.getScreenshotAs(OutputType.FILE);
		File dest = new File("./screenShots" + screenShotName + ".PNG");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * used to wait and click
	 * 
	 * @param driver
	 */
	public void waitAndClickUsingCustomizeWait(WebDriver driver) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		wait.pollingEvery(Duration.ofSeconds(10));
		wait.ignoring(NoSuchElementException.class);
		try {
			wait.wait(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * used to wait and click on element
	 * 
	 * @param element
	 * @param duration
	 * @param pollingTime
	 */
	public void waitAndClick(WebElement element, int duration, long pollingTime) {
		int count = 0;
		while (count < duration) {
			try {
				element.click();
				break;
			} catch (Exception e) {
				try {
					Thread.sleep(pollingTime);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				count++;
			}
		}
	}

	/**
	 * used to switch the window using title
	 * 
	 * @param driver
	 * @param actualTitle
	 */
	public void switchToWindow(WebDriver driver, String actualTitle) {
		Set<String> allId = driver.getWindowHandles();
		for (String check : allId) {
			driver.switchTo().window(check);
			String title = driver.getTitle();
			if (title.contains(actualTitle)) {
				break;
			}
		}
	}

	/**
	 * used to switch window using url
	 * 
	 * @param actualUrl
	 * @param driver
	 */
	public void switchToWindow(String actualUrl, WebDriver driver) {
		Set<String> allId = driver.getWindowHandles();
		Iterator<String> check = allId.iterator();
		while (check.hasNext()) {
			String windowId = check.next();
			driver.switchTo().window(windowId);
			String url = driver.getCurrentUrl();
			if (url.contains(actualUrl)) {
				break;
			}
		}
	}

	/**
	 * used to switch to alert and accept
	 * 
	 * @param driver
	 * @param expectedMessage
	 */
	public void switchToAlertPopupAndAccept(WebDriver driver, String expectedMessage) {
		Alert alert = driver.switchTo().alert();
		if (alert.getText().trim().equalsIgnoreCase(expectedMessage.trim())) {
			System.out.println("Alert message is verified");
		} else {
			System.out.println("Alert messsage is not verified");
		}
		alert.accept();
	}

	/**
	 * used to switch to alert and click on dismiss
	 * 
	 * @param driver
	 * @param expectedMessage
	 */
	public void switchToAlertPopupAndDismiss(WebDriver driver, String expectedMessage) {
		Alert alert = driver.switchTo().alert();
		if (alert.getText().trim().equalsIgnoreCase(expectedMessage.trim())) {
			System.out.println("Alert message is verified");
		} else {
			System.out.println("Alert messsage is not verified");
		}
		alert.dismiss();
	}

}
