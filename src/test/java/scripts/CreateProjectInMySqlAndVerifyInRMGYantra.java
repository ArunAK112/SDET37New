package scripts;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CreateProjectInMySqlAndVerifyInRMGYantra {
	
	public static void main(String[] args) throws Throwable {
		
		Driver driverRef;
		Connection connection= null;
		String projectName = "DREAM11";
		
		try {
			driverRef = new Driver();
			
			//registering driver
			DriverManager.registerDriver(driverRef);
			
			//passing url and getting connection
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
			
			//creating statement
			Statement statement = connection.createStatement();
			
			//inserting our data into the table
			int result = statement.executeUpdate("insert into project values ('TY_proj_020','ARUN K','11/07/2022','DREAM11','On Going','8')");
			
			//verification
			if(result==1)
			{
				System.out.println("Data is created, PASS");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Data is not created, FALSE");
		}finally
		{
			connection.close();
			System.out.println("Data base closed");
		}
		Thread.sleep(2000);
		
		//CHECKING IN RMGYANTRA
		WebDriverManager.chromedriver().setup();
		
		//launching firefox driver
		//WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		
		//maximizing the browser
		driver.manage().window().maximize();
		
		//enter the RMG yantra url
		driver.get("http://localhost:8084/");
		
		////VERIFICATION ON SIGNIN PAGE
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
		
		Thread.sleep(3000);
		//clicking on projects
		driver.findElement(By.xpath("//a[text()='Projects']")).click();
		
		//printing the projects
		java.util.List<WebElement> allElement = driver.findElements(By.xpath("//td[2]"));
		
		boolean temp = false;
		for(WebElement a:allElement)
		{
			if(a.getText().equals(projectName))
			{
				temp=true;
				System.out.println(a.getText());
			}
		}
		if(temp==true)
		{
			System.out.println("Project is created, PASS");
		}else
		{
			System.out.println("Project is not created, FAIL");
		}
		
		Thread.sleep(3000);
		//close the browser
		driver.close();
		
	}

	
		
		
	}

