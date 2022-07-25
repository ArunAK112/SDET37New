package rmgyantra;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class FetchDataFromTable {

	public static void main(String[] args) throws SQLException {
		
		Driver driverRef = new Driver();
		//registering driver
		DriverManager.registerDriver(driverRef);
		//getting connection
				Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		//create statement 
		Statement statement = connection.createStatement();
		//writing and executing query
	       	ResultSet result = statement.executeQuery("select * from project");
		
		//getting the output
		while(result.next())
		{
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+
			result.getString(4)+"\t"+result.getString(5)+"\t"+result.getString(6));
		}
		//closing the connection
		connection.close();
		
	}

}
