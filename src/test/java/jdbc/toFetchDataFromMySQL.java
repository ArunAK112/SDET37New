package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;



public class toFetchDataFromMySQL {

	public static void main(String[] args) throws SQLException {
		
		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		//connecting eclipse to my sql, take that url from google, search my sql url
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
		//creating a statement
		Statement statement = connection.createStatement();
		//writing query
		String query = "select * from student";
		//executing the query
		ResultSet result = statement.executeQuery(query);
		//getting the output
		while(result.next())
		{
			System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4)+"\t"+result.getString(5));
		}
		connection.close();
		
	}

}
