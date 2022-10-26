package com.crm_SDET37.GenericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;

public class DataBaseUtility {

	/**
	 * this Method is Used to Connect to DataBase
	 */
	static Driver driverRef;
	static Connection connection;
	static ResultSet result;
	public void connectToDB(String DBName) {
		try {
			driverRef=new Driver();
			DriverManager.registerDriver(driverRef);
			connection = DriverManager.getConnection(IConstants.DBUrl+DBName, IConstants.DBUserName, IConstants.DBPassword);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	/**
	 * This Method is used to Close the Connection DataBase
	 */
	public void closeDB() {
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * This Method is Used to Execute the Query
	 * @param query
	 * @param columnNum
	 * @param expectedData
	 * @return
	 * @throws SQLException
	 */

	public boolean executeQuery(String query,int columnNum,String expectedData) throws SQLException {
		result = connection.createStatement().executeQuery(query);
		boolean flag=false;
		while (result.next()) {
			if (result.getString(columnNum).equals(expectedData)) {
				flag=true;
				break;
			}
		}
		if (flag) {
			System.out.println("Data is Present !");
		}else {
			System.out.println("Data is Not Present !");
		}
		return flag;
	}

	/**
	 * This method is used to Update the Data inside the DataBase
	 * @param query
	 * @throws SQLException
	 */
	public void executeUpdate(String query) throws SQLException {
		int res = connection.createStatement().executeUpdate(query);
		if (res==1) {
			System.out.println("Data is Updated !");
		}else {
			System.out.println("Data is not Updated !");
		}
	}
}
