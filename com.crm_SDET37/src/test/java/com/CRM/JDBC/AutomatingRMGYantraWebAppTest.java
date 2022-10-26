package com.CRM.JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import com.mysql.cj.jdbc.Driver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomatingRMGYantraWebAppTest {
	public static void main(String[] args) throws SQLException {
		String projectName = "KAPSER-002";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8084/");
		WebElement userNameTextField = driver.findElement(By.id("usernmae"));
		userNameTextField.clear();
		userNameTextField.sendKeys("rmgyantra");
		WebElement passwordTextField = driver.findElement(By.id("inputPassword"));
		passwordTextField.clear();
		passwordTextField.sendKeys("rmgy@9999");
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		driver.findElement(By.linkText("Projects")).click();
		driver.findElement(By.xpath("//span[contains(.,'Create Project')]")).click();
		driver.findElement(By.name("projectName")).sendKeys("KAPSER-002");
		driver.findElement(By.name("createdBy")).sendKeys("Pravin Gandhi");
		WebElement dropDown = driver
				.findElement(By.xpath("//label[text()='Project Status ']/..//select[@name='status']"));
		Select select = new Select(dropDown);
		select.selectByValue("Created");
		driver.findElement(By.cssSelector("input[type='submit']")).click();
		Connection connection = null;
		try {
			Driver driverref = new Driver();
			DriverManager.registerDriver(driverref);
			// Statment to connect My Sql DB using URL
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/projects", "root", "root");
			// Create a Statment
			Statement statment = connection.createStatement();
			// Write a Query
			String query = "select * from project";
			ResultSet result = statment.executeQuery(query);
			while (result.next()) {
				String allProject = result.getString(4);
				if (allProject.contains(projectName)) {
					System.out.println("Pass : Project is Created !");
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			System.out.println("Fail : Project isnot Created !");
		} finally {
			connection.close();
		}
	}
}
