package com.CRM.VTiger.Opportunity;

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
 * This class is Implemented by Generic Utility
 * @author SHUBH
 *
 */
public class CreateOpportunitywithOrganisationandContactTest {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;

		// Fetch the Common data from Properties File
		FileUtility fileUtilLibrary = new FileUtility();
		WebDriverUtility webdriverUtilLibrary = new WebDriverUtility();
		ExcelUtility excelUtilLibrary = new ExcelUtility();
		JavaUtility javaUtilLibrary = new JavaUtility();
		int randomNumb = javaUtilLibrary.getRandomNum();

		String URL = fileUtilLibrary.getPropertyValue("url");
		// Store the username inside the USERNAME variable
		String USERNAME = fileUtilLibrary.getPropertyValue("username");
		// Store the password inside the PASSWORD variable
		String PASSWORD = fileUtilLibrary.getPropertyValue("password");
		// Store the browser inside the BROWSER variable
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
		driver.findElement(By.linkText("Opportunities")).click();
		driver.findElement(By.xpath("//img[@alt='Create Opportunity...']")).click();
		String opportunityName = excelUtilLibrary.getDataFromExcel("Sheet3", 1, 3);
		driver.findElement(By.name("potentialname")).sendKeys(opportunityName+randomNumb);
		WebElement relatedOpportunityDropdown = driver.findElement(By.id("related_to_type"));
		webdriverUtilLibrary.select(relatedOpportunityDropdown, "Contacts");

		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[1]")).click();
		webdriverUtilLibrary.switchToWindow("module=Contacts&action", driver);

		driver.findElement(By.xpath("(//a[contains(.,'Shubham BP Pitale')])[3]")).click();
		webdriverUtilLibrary.switchToWindow("module=Potentials&action", driver);
		driver.findElement(By.xpath("(//img[@src='themes/softed/images/select.gif'])[2]")).click();
		webdriverUtilLibrary.switchToWindow("module=Campaigns&action", driver);

		driver.findElement(By.xpath("(//a[contains(.,'Qspider Training Institute')])[1]")).click();

		webdriverUtilLibrary.switchToWindow("module=Potentials&action", driver);

		driver.findElement(By.name("button")).click();

		WebElement createdOpportunity = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		if (createdOpportunity.getText().contains(opportunityName+randomNumb)) {
			System.out.println("Pass : New Opportunity is Created and displayed on Opportunity Page also !");
		} else {
			System.out.println("Fail : New Opportunity isnot Created and not displayed on Opportunity Page also !");
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
