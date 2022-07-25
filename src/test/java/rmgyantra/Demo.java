package rmgyantra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class Demo {
	
	public static void main(String[] args) throws SQLException {
		
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		Statement statement = connection.createStatement();
		int result = statement.executeUpdate("insert into project values ('ID00566','AK','11/07/2022','Demo111','Created','10')");
		if(result==1)
		{
			System.out.println("Data is created, PASS");
		}else
		{
			System.out.println("Data is not created, FAIL");
		}
		connection.close();
	}
}
