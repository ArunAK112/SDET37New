package com.lexnod.genericLib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

/**
 * 
 * @author ARUN
 *
 */

public class DataBaseLibrary {
	static Driver driverRef;
	static Connection connection;
	static ResultSet result;
	
	public void connectToDatabase(String DBname)
	{
		try {
			driverRef = new Driver();
			DriverManager.registerDriver(driverRef);
			connection = DriverManager.getConnection(IAutoConstants.DBUrl+DBname, IAutoConstants.DBUsername, IAutoConstants.DBPassword);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * this method is used to close the connection
	 */
	
	public void closeBatabase()
	{
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * this method is used to execute query
	 * @param query
	 * @param columnNumber
	 * @param expectedData
	 * @return
	 * @throws Throwable
	 */
	public boolean executeQuery(String query, int columnNumber, String expectedData) throws Throwable
	{
		result=connection.createStatement().executeQuery(query);
		boolean flag = false;
		while(result.next())
		{
			if(result.getString(columnNumber).contains(expectedData))
			{
				flag=true;
				break;
			}
		}
		if(flag==true)
		{
			System.out.println("data is present");
			return flag;
		}else
		{
			System.out.println("data is not present");
			return flag;
		}
	}
	/**
	 * this methos id used to perform execute update
	 * @param query
	 * @throws Throwable
	 */
	
	public void executeUpdate(String query) throws Throwable
	{
		int result = connection.createStatement().executeUpdate(query);
		if(result==1)
		{
			System.out.println("data is updated");
		}else
		{
			System.out.println("data is not updated");
		}
		
		
	}

}
