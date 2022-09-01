package rmgyantra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class AddProject {
//CHANGES
	public static void main(String[] args) throws SQLException {
		
		Driver driverRef;
		Connection connection = null;
		try {
			driverRef = new Driver();
		//registering driver
		DriverManager.registerDriver(driverRef);
		//giving connection
		connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		//creating statement for adding query
		Statement statement = connection.createStatement();
		//adding query
		String query="insert into project values ('IDI004','Saravanan G M','09-july-2022','LEXNOD','On Going',11)";
		//executing the update
		int result = statement.executeUpdate(query);
		
		if(result==1)
		{
		System.out.println("data is created, PASS");
		}
		
		} catch (SQLException e) 
		{
			System.out.println("Data is not created, DUPLICATE VALUE, FAIL");
		} finally
		{
			connection.close();
			System.out.println("Database closed");
		}
		//push to git hub
	}

}
