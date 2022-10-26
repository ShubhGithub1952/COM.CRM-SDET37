package com.CRM.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;


public class InsertingthedataintoProjectTest {
public static void main(String[] args) throws SQLException {
	Driver driverRef;
	Connection connection = null;
	try {
		driverRef = new Driver();
	DriverManager.registerDriver(driverRef);
	connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
	Statement statment = connection.createStatement();
	int result = statment.executeUpdate("insert into project values('Project_003','Shubham','2022/07/09','demo','Ceated','4')");
	if (result==1) {
		System.out.println("Pass :- Project is Created !");
	}
	} catch (SQLException e) {
		System.out.println("data is not created");
	}finally
	{
		connection.close();
		System.out.println("Database closed");
	}
}
}
