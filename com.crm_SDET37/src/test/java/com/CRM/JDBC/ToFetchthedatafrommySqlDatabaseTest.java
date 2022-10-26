package com.CRM.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class ToFetchthedatafrommySqlDatabaseTest {
public static void main(String[] args) throws SQLException {
	//Driver driverRef= new Driver();
	Driver driverRef= new Driver();
	DriverManager.registerDriver(driverRef);
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/students","root","root");
	Statement statment = connection.createStatement();
	String query = "select * from student";
	ResultSet result = statment.executeQuery(query);
	while (result.next()) {
		System.out.println(result.getString(1)+"\t"+result.getString(2)+"\t"+result.getString(3)+"\t"+result.getString(4)+"\t"+result.getString(5)+"\t");
	}
	connection.close();
}
}
