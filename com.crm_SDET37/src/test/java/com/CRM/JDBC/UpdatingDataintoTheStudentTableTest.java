package com.CRM.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class UpdatingDataintoTheStudentTableTest {
public static void main(String[] args) throws SQLException {
	Driver driverRef= new Driver();
	DriverManager.registerDriver(driverRef);
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
	Statement statment = connection.createStatement();
	int result = statment.executeUpdate("insert into project values ('Proj_001','Shubham','2022/07/10','Kapser_Web','Created','4')");
	if (result==1) {
		System.out.println("Pass : The Data is Updated into Project Database !");
	}else {
		System.out.println("Fail : The Data isnot Updated into Project Database !");
	}
	connection.close();
}
}
