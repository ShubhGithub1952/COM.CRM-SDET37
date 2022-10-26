package com.CRM.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class CreatingProjectofReactAppTest {
public static void main(String[] args) throws SQLException {
	Driver driverRef= new Driver();
	DriverManager.registerDriver(driverRef);
	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects","root","root");
	Statement statment = connection.createStatement();
	//String query = "select * from project";
	//ResultSet resultQuery = statment.executeQuery(query);
	int result = statment.executeUpdate("insert into Project values ('project_P01','Shubham','2022/07/10','KAPSER','Created','6')");
	if (result==1) {
		System.out.println("Pass :- Project is Created !");
	}else {
		System.out.println("Fail :- Project is not Created !");
	}
	// while (resultQuery.next()) {
	//	System.out.println(resultQuery.getString(1)+"\t"+resultQuery.getString(2)+"\t"+resultQuery.getString(2)+"\t");
	//}
	connection.close();
}
}
