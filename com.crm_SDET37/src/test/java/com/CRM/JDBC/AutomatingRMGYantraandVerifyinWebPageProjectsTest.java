package com.CRM.JDBC;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomatingRMGYantraandVerifyinWebPageProjectsTest {
	public static void main(String[] args) throws Throwable {
		String projectName = "KAPSER-002";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("http://localhost:8084/");
		WebElement userNameTextField = driver.findElement(By.id("usernmae"));
		userNameTextField.clear();
		System.out.println("CheckingStatment");
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
		List<WebElement> ListProjectname = driver.findElements(By.xpath("//td[2]"));
		Thread.sleep(4000);
		boolean temp = false;
		for (WebElement ele : ListProjectname) {
			if (ele.getText().equals(projectName)) {
				temp = true;
				System.out.println(ele.getText());
			}
		}
		if (temp == true) {
			System.out.println("Pass : Project is Created ! ");
		} else {
			System.out.println("Fail : Project isnot Created ! ");
		}
	}
}
