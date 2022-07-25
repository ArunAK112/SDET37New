package jdbc;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class AddColumnIntoTable {

	public static void main(String[] args) throws SQLException {

		Driver driverRef = new Driver();
		DriverManager.registerDriver(driverRef);
		
	}

}
