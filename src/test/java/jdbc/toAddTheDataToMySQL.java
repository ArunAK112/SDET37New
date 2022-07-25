package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class toAddTheDataToMySQL {

	public static void main(String[] args) throws SQLException {
		
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		//connect to my sql
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
		//creating statement
		Statement statement = connection.createStatement();
		//write query
		String query = "insert into student values('VIRAT','KHOLI','RMG003','1989/10/24','MALE')";
		//executing the update
		int result = statement.executeUpdate(query);
		//checking and adding
		if(result==1)
		{
			System.out.println("data is created");
		}else
		{
			System.out.println("data is not created");
		}
		connection.close();
		
	}

}
