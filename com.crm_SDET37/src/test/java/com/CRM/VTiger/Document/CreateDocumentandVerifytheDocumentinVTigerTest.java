package com.CRM.VTiger.Document;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm_SDET37.GenericUtility.ExcelUtility;
import com.crm_SDET37.GenericUtility.FileUtility;
import com.crm_SDET37.GenericUtility.JavaUtility;
import com.crm_SDET37.GenericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This Class is Implemented by the Generic Utility
 * @author SHUBH
 *
 */

public class CreateDocumentandVerifytheDocumentinVTigerTest {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;

		// Fetch the Common data from Properties File
		FileUtility fileUtilLibrary = new FileUtility();
		WebDriverUtility webdriverUtilLibrary = new WebDriverUtility();
		ExcelUtility excelUtilLibrary = new ExcelUtility();
		JavaUtility javaUtilLibrary = new JavaUtility();
		int randomNumb = javaUtilLibrary.getRandomNum();

		String URL = fileUtilLibrary.getPropertyValue("url");
		String USERNAME = fileUtilLibrary.getPropertyValue("username");
		String PASSWORD = fileUtilLibrary.getPropertyValue("password");
		String BROWSER = fileUtilLibrary.getPropertyValue("browser");

		if (BROWSER.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		// Maximize the browser Window
		webdriverUtilLibrary.maximizeTheBrowser(driver);
		// create implicite wait
		webdriverUtilLibrary.waitImplicitlyToPageGetsLoad(driver);
		// Pass the URL of WebPage
		driver.get(URL);

		WebElement userNameTextField = driver.findElement(By.name("user_name"));
		userNameTextField.clear();
		userNameTextField.sendKeys(USERNAME);
		WebElement passwordTextField = driver.findElement(By.name("user_password"));
		passwordTextField.clear();
		passwordTextField.sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();

		driver.findElement(By.linkText("Documents")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();

		driver.findElement(By.name("notes_title")).sendKeys(randomNumb+"Need Admission in Institute");
		driver.findElement(By.xpath("//iFrame[@title='Rich text editor, notecontent, press ALT 0 for help.']"))
		.sendKeys("Software under Testing ");

		driver.findElement(By.name("filename")).sendKeys("E:\\123.png");
		driver.findElement(By.name("button")).click();
		WebElement documentinfoTitle = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (documentinfoTitle.getText().contains(randomNumb+"Need Admission in Institute")) {
			System.out.println("Pass : Document information is Created Successfully and Verified also !");
		} else {
			System.out.println("Fail : Document information isnot Created Successfully and not Verified also !");
		}
		// Identify the administrator icon
		WebElement administatoricon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		// Create the object of Action class
		webdriverUtilLibrary.mouseOverOnElement(driver, administatoricon);
		// Find the SignOut button and click on that
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// Terminate the Session
		driver.quit();
	}
}
