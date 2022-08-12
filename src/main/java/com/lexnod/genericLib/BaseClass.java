package com.lexnod.genericLib;
/**
 * 
 * @author SanjayBabu
 *
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.lexnod.ObjectRepository.HomePage;
import com.lexnod.ObjectRepository.LoginPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass 

{
	public static WebDriver sdriver;
	public WebDriver driver;
	public DataBaseLibrary dLib=new DataBaseLibrary();
	public ExcelFileLibrary eLib=new ExcelFileLibrary();
	public PropertyFileLibrary fLib=new PropertyFileLibrary();
	public WebDriverCommonLibrary  wLib=new WebDriverCommonLibrary();
	public JavaUtility jLib=new JavaUtility();

	/**
	 * connecting to database
	 */
//	@BeforeSuite
//	public void dbConfig()
//	{
//		dLib.connectToDB();
//	}
	/**
	 * launching the browser
	 * @throws Throwable
	 */
	//@Parameters("BROWSER")
	@BeforeClass
	public void launchTheBrowser()
	{  
		String BROWSER = null;
		try {
			BROWSER = fLib.getPropertyData("browser");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		System.out.println(BROWSER);
		String URL = null;
		try {
			URL = fLib.getPropertyData("url");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		if(BROWSER.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
		}else if(BROWSER.equalsIgnoreCase("chrome"))
		{
			//	ChromeOptions chromeOptions=new ChromeOptions();
			//	chromeOptions.setBinary("C:\\Users\\SanjayBabu\\AppData\\Local\\Google\\Chrome\\Application\\chrome.exe");
			//	driver=new ChromeDriver(chromeOptions);
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();
		}
		else {
			driver=new ChromeDriver();
		}

		System.out.println("Browser successfully launched");
		//implicitly wait
		sdriver=driver;
		wLib.waitTillPageGetsLoadImplicitlyWait(driver, 10);;
		//enter the URL of the Application
		sdriver.get(URL);
		//maximize the screen
		driver.manage().window().maximize();
	}
	/**
	 * login to application
	 */
	@BeforeMethod
	public void loginToAppln()
	{
		String USERNAME = null;
		try {
			USERNAME = fLib.getPropertyData("username");
		} catch (Throwable e) {
			e.printStackTrace();
		}
		String PASSWORD = null;
		try {
			PASSWORD = fLib.getPropertyData("password");
		} catch (Throwable e) {
			e.printStackTrace();
		}

		LoginPage lpage = new LoginPage(driver);
		lpage.login(USERNAME, PASSWORD);
		System.out.println("Login successful");
	}
	/**
	 * logout from application
	 */
	@AfterMethod
	public void logoutFromAppln()
	{
		HomePage homePage=new HomePage(driver);
		homePage.clickSignoutLink(driver);
		System.out.println("Logout successful");
	}
	/**
	 * close the browser
	 */
	@AfterClass
	public void closeTheBrowser()
	{
		driver.quit();
		System.out.println("Browser successfully closed");
	}
	/**
	 * close database configuration
	 */
//	@AfterSuite
//	public void closeDBconfig()
//	{
//		dLib.closeDB();
//	}
	
	
}