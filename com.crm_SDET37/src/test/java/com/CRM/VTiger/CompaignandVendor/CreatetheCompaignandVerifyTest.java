package com.CRM.VTiger.CompaignandVendor;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm_SDET37.GenericUtility.ExcelUtility;
import com.crm_SDET37.GenericUtility.FileUtility;
import com.crm_SDET37.GenericUtility.JavaUtility;
import com.crm_SDET37.GenericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * This class is Implimeted by Generic Utility
 * @author SHUBH
 *
 */
public class CreatetheCompaignandVerifyTest {
	//public static void main(String[] args) throws Throwable {
	@Test
	public void createCompaignandVerify() throws Throwable {
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

		//Identify the USerName Text field 
		WebElement userNameTextField = driver.findElement(By.name("user_name"));

		// Clear the UserName text Field
		userNameTextField.clear();

		//Pass The UserName in UserNAme text Field
		userNameTextField.sendKeys(USERNAME);

		// Identify the PassWord Text Field
		WebElement passwordTextField = driver.findElement(By.name("user_password"));

		//Clear the PassWord Text Field
		passwordTextField.clear();

		//Enter the PAssword
		passwordTextField.sendKeys(PASSWORD);

		//Identify the Login button and Click on it
		driver.findElement(By.id("submitButton")).click();

		//Identify the More dropDown and Click on it
		driver.findElement(By.linkText("More")).click();

		//Identify the Campaigns link button and click on it 
		driver.findElement(By.linkText("Campaigns")).click();

		//Create the Compaign and Entered the Copaign Name
		driver.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();
		String compaignName = excelUtilLibrary.getDataFromExcel("Sheet2", 1, 4);
		driver.findElement(By.name("campaignname")).sendKeys(compaignName+randomNumb);
		driver.findElement(By.cssSelector("img[src='themes/softed/images/select.gif']")).click();
		webdriverUtilLibrary.switchToWindow("\"module=Products&action=Popup\"", driver);

		driver.findElement(By.linkText("HardDisk_SSD512")).click();
		webdriverUtilLibrary.switchToWindow("module=Campaigns&action", driver);
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		WebElement compaignCreatedTitle = driver.findElement(By.cssSelector("span[class='dvHeaderText']"));
		if (compaignCreatedTitle.getText().contains(compaignName+randomNumb)) {
			System.out.println(
					"Pass : The Compaign is Created Successfully and displaying on Compaign Information Page !");
		} else {
			System.out.println("Fail : The Compaign isnot Created and not displaying on Compaign Information Page !");
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
