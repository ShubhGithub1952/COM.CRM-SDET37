package com.CRM.VTiger.Mail;

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
 * This class is Implemented by the Generic Utility
 * @author SHUBH
 *
 */
public class ComposeMailandVerifyinVTigerTest {
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

		//Give the Implicite Wait for WebElement
		webdriverUtilLibrary.waitImplicitlyToPageGetsLoad(driver);

		// Pass the URL of WebPage
		driver.get(URL);

		// Identify the UserName Text Field
		WebElement userNameTextField = driver.findElement(By.name("user_name"));

		// Clear the UserName Text field
		userNameTextField.clear();

		// Pass the Valid UserName
		userNameTextField.sendKeys(USERNAME);

		// Identify the PassWordText Field
		WebElement passwordTextField = driver.findElement(By.name("user_password"));

		// Clear the PassWord Text Field
		passwordTextField.clear();

		// Enter the Password
		passwordTextField.sendKeys(PASSWORD);

		// Identify login button and click on that
		driver.findElement(By.id("submitButton")).click();

		// Identify Mail element and click on that
		driver.findElement(By.linkText("Email")).click();

		// Identify the Compose element and CLick on that
		driver.findElement(By.linkText("Compose")).click();

		// Identify the Parent window and Child Window and transfer the driver control
		// from Parent window to Child window
		webdriverUtilLibrary.switchToWindow("Emails&action=Emails", driver);

		//identify the Mail element DropDown
		WebElement mailDropdown = driver.findElement(By.xpath("//select[@name='parent_type']"));

		//Use select class for select Options
		webdriverUtilLibrary.select(mailDropdown, "Vendors");

		//identify the Plus icon of add Vendor and click on it
		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();

		//Identify the multiple Window 
		webdriverUtilLibrary.switchToWindow("module=Vendors&action=Popup", driver);

		//upon transferring the driver control click on Vendor Name
		driver.findElement(By.xpath("(//a[contains(.,'TestYantraLogInfoTech')])[2]")).click();

		//Transfer back driver control on Compose mail Window
		webdriverUtilLibrary.switchToWindow("module=Emails&action=Emails", driver);
		//Identify the CC text field and Pass the Mail id from fetching excel
		String ccMailID = excelUtilLibrary.getDataFromExcel("Sheet2", 1, 6);
		driver.findElement(By.id("cc_name")).sendKeys(ccMailID);

		//identify the about text field and pass about reference 
		driver.findElement(By.name("subject")).sendKeys("About Admission"+randomNumb);

		//Transfer the driver control from compose mail to the main Parent Page
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		webdriverUtilLibrary.switchToWindow("module=Emails&action", driver);
		//Identify the element of Saved Mail Element  
		WebElement SaveMail = driver.findElement(By.xpath("//b[text()='About Admission ']"));

		//Verify the Mail has Created or not 
		if (SaveMail.getText().contains("About Admission")) {
			System.out.println("Pass : Mail Successfully Saved and displayed on All Mail Page !");
		} else {
			System.out.println("Fail : Mail Successfully not Saved and not displayed on All Mail Page !");
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
