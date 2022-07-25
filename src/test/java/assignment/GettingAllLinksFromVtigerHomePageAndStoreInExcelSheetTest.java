package assignment;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.time.Duration;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GettingAllLinksFromVtigerHomePageAndStoreInExcelSheetTest {

	public static void main(String[] args) throws Throwable {

		// setting up browser
		WebDriverManager.firefoxdriver().setup();

		// creating object for browser
		WebDriver driver = new FirefoxDriver();

		// maximizing the browser
		driver.manage().window().maximize();

		// passing the url
		driver.get("http://localhost:8888/");

		// passing wait condition
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

		// VERIFYING V-TIGER LOGIN PAGE IS DISPLAYED OR NOT
		String loginTitle = "vtiger CRM 5 - Commercial Open Source CRM";
		if (driver.getTitle().equals(loginTitle)) {
			System.out.println("VTiger Login Page is Displayed, PASS");
		} else {
			System.out.println("VTiger Login page is not displayed, FAIL");
		}

		// giving login details and clicking on login
		driver.findElement(By.name("user_name")).sendKeys("admin");
		driver.findElement(By.name("user_password")).sendKeys("admin");
		driver.findElement(By.id("submitButton")).submit();

		// VERIFICATION V-TIGER HOME PAGE IS DISPLAYED OR NOT
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// passing explicitly wait for getting home page to loaded
		wait.until(ExpectedConditions.titleContains("Administrator"));
		System.out.println("VTiger Home page is displayed, PASS");

		FileInputStream fileInputStream = new FileInputStream("./src/test/resources/data/InputData.xlsx");
		Workbook workbook = WorkbookFactory.create(fileInputStream);

		List<WebElement> links = driver.findElements(By.xpath("//a"));
		int count = links.size();

		for (int i = 0; i < count; i++) {
			
			workbook.getSheet("newLink").createRow(i).createCell(0).setCellValue(links.get(i).getAttribute("href"));

		}

		FileOutputStream fileOutputStream = new FileOutputStream("./src/test/resources/data/InputData.xlsx");
		workbook.write(fileOutputStream);
		System.out.println("Data stored in excel sheet");
		
	}

}
