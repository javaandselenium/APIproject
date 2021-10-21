package com.Ty.rmgyantra.genericlib;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.cj.jdbc.Driver;

public class DataBaseUtilies {
	static Driver driverRef;
	public static Connection con;
	public static ResultSet result;
	
	
	
	public void connectToDB() throws SQLException {
		driverRef=new Driver();
		DriverManager.registerDriver(driverRef);
		con=DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
		
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return con.createStatement().executeQuery(query);
	}

	
	public boolean executeQueryAndGetData(String query,int columnname,String expData) throws SQLException {
		 result = con.createStatement().executeQuery(query);
		
		
		while(result.next())
		{
			if(result.getString(columnname).equals(expData)) {
				break;
			}
			else
			{
				//System.out.println("data no found");
			}
		}
		return false;
		
		
	}
	
	public void closeDb() throws SQLException {
		con.close();
	}
}
