package scripts;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjects {
@Test
	public void createProject() throws InterruptedException {
		//setting up browser
		WebDriverManager.firefoxdriver().setup();
		
		WebDriver driver=new FirefoxDriver();
		//maximizing window
		driver.manage().window().maximize();
		
		//entering url of RMG Yantra
		driver.get("http://localhost:8084/");
		
		//VERIFICATION ON SIGNIN PAGE
		String expectedTitle = "React App";
		if(expectedTitle.equals(driver.getTitle()))
		{
			System.out.println("TestYantra Signin page is displayed");
		}else
		{
			System.out.println("TestYantra Signin page is not displayed");
		}
		
		//giving timeout of polling period of 500ms
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		
		//entering username, password and click on signin
		driver.findElement(By.id("usernmae")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).submit();
		
		//VERIFICATION ON HOME PAGE
		WebElement element = driver.findElement(By.xpath("//a[text()='Test Yantra']"));
		if(element.isDisplayed())
		{
			System.out.println("TestYantra Home Page is diaplayed, PASS");
		}else
		{
			System.out.println("TestYantra Home page is not displayed, FAIL");
		}
		
		//clicking on projects
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		
		//createProject Element
		WebElement createProjectElement = driver.findElement(By.xpath("//span[text()='Create Project']"));
		
		
		//VERIFICATION ON CREATE PROJECT PAGE
		if(createProjectElement.isDisplayed())
		{
			System.out.println("Create Project Page is displayed, PASS");
		}else
		{
			System.out.println("Create Project page is not displayed, FAIL");
		}
		
		//clicking on create project
		createProjectElement.click();
		Thread.sleep(1000);
		
		//entering details in create project
		driver.findElement(By.name("projectName")).sendKeys("LEXNOD1");
		driver.findElement(By.name("createdBy")).sendKeys("ARUN");
		
		//reference for select element
		WebElement selectElement = driver.findElement(By.xpath("//label[text()='Project Status ']/..//select[@name='status']"));
		Select select = new Select(selectElement);
	
		select.selectByVisibleText("Created");
		driver.findElement(By.xpath("//input[@value='Add Project']")).click();	
		Thread.sleep(1000);
		
		//VERIFICATION ON PROJECT NAME
		String passAlertMessage = driver.findElement(By.xpath("//div[@class='Toastify__toast-body']")).getText();
		System.out.println(passAlertMessage);
		if(passAlertMessage.contains("Sucessfuly Added"))
		{
			System.out.println("Project is created, PASS");
		}else
		{
			System.out.println("Project is not created, FAIL");
		}
		
		//close the browser
		driver.close();
		
	}

}
