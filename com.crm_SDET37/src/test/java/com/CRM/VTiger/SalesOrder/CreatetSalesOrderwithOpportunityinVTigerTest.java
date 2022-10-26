package com.CRM.VTiger.SalesOrder;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Window;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.crm_SDET37.GenericUtility.ExcelUtility;
import com.crm_SDET37.GenericUtility.FileUtility;
import com.crm_SDET37.GenericUtility.JavaUtility;
import com.crm_SDET37.GenericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * Create the SalesService with Opportunity Using General Utility is Completed
 * @author SHUBH
 *
 */
public class CreatetSalesOrderwithOpportunityinVTigerTest {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;

		// Fetch the Common data from Properties File
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

		// create implicit wait
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
		WebElement moreDropdownButton = driver.findElement(By.xpath("//td[@class='tabUnSelected']/a[@href='javascript:;']"));
		webdriverUtilLibrary.mouseOverOnElement(driver, moreDropdownButton);
		driver.findElement(By.linkText("Sales Order")).click();
		driver.findElement(By.xpath("//img[@src='themes/softed/images/btnL3Add.gif']")).click();
		// click on Subject Text Field and enter the Text
		driver.findElement(By.name("subject")).sendKeys("Purchasing Delivery"+randomNumb);
		// Select the Contact Name and Handle the Child Windows
		driver.findElement(By.xpath("//input[@name='contact_name']/..//img[@src='themes/softed/images/select.gif']")).click();
		// Identify Child Window and Select the Contact and Mover Driver control to the
		// Parent Window
		webdriverUtilLibrary.switchToWindow("module=Contacts&action", driver);

		// Select contact which is on Child Window
		driver.findElement(By.xpath("(//a[contains(.,'Shubham BP')])[3]")).click();
		webdriverUtilLibrary.switchToAlertPopUpAndAccept(driver, BROWSER);
		// Transfer the Driver control from Child window to the Parent Window
		webdriverUtilLibrary.switchToWindow("module=SalesOrder&action", driver);
		// Select the Approved element from the Status DropDown
		WebElement selectDropDown = driver.findElement(By.xpath("//select[@name='sostatus']"));
		webdriverUtilLibrary.select(selectDropDown, "Approved");

		// Select the Opportunity Name by Transferring Driver control From Sales Order
		// Page to the Opportunity page
		driver.findElement(By.xpath("//input[@name='potential_name']/..//img[@src='themes/softed/images/select.gif']")).click();
		// Identify the Window Ids and Based identification
		webdriverUtilLibrary.switchToWindow("module=Potentials&action", driver);

		// select the Opportunity which present on Child window
		driver.findElement(By.linkText("New Opportunity1")).click();
		// now transfer the Driver control from Opportunity child window to the
		// SalesOrder Page
		webdriverUtilLibrary.switchToWindow("module=SalesOrder&action", driver);

		driver.findElement(By.name("duedate")).sendKeys("2022-08-15");
		// identify the organization name and select the Organization
		driver.findElement(By.xpath("//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']"))
		.click();
		// identify the child window and transfer the Driver Control
		webdriverUtilLibrary.switchToWindow("module=Accounts&action", driver);

		// Select the Organization present on child Window
		driver.findElement(By.xpath("(//a[contains(.,'State Bank of India')])[4]")).click();
		// Handle the Pop-Up
		webdriverUtilLibrary.switchToAlertPopUpAndAccept(driver, BROWSER);
		// Transfer driver control back from organization child window to the Sales
		// Order Window
		webdriverUtilLibrary.switchToWindow("module=SalesOrder&action", driver);

		// Pass the Billing Address
		driver.findElement(By.name("bill_street")).sendKeys("At Post Bangalore,Karnataka-560011");
		// copy the Billing Address
		driver.findElement(By.xpath("//b[text()='Copy Billing address']/..//input[@name='cpy']")).click();
		// Select the Item from Item Text Field
		driver.findElement(By.xpath("//input[@name='productName1']/..//img[@src='themes/images/products.gif']"))
		.click();
		// transfer the Driver control from Sales Order Page to the Item Production name
		// Page
		webdriverUtilLibrary.switchToWindow("module=Products&action", driver);

		// Identify the Item from Product Item Page and Select
		driver.findElement(By.linkText("HardDisk_SSD5121SK046")).click();
		// now transfer driver Control from Product name page to the Sales Order Page
		webdriverUtilLibrary.switchToWindow("module=SalesOrder&action", driver);

		// Enter the Quantity of the Order
		driver.findElement(By.name("qty1")).sendKeys("10");
		// Save the Details of Sales and Order Page
		driver.findElement(By.name("button")).click();
		// Verify the order has created or not
		WebElement salesOrderCreatedTitle = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (salesOrderCreatedTitle.getText().contains("Purchasing Delivery"+randomNumb)) {
			System.out.println("Pass : Sales Order has Succesfully Created ! ");
		} else {
			System.out.println("Fail : Sales Order hasnot Succesfully Created ! ");
		}
		// Identify the administrator icon
		WebElement administatoricon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		// Create the object of Action class
		webdriverUtilLibrary.mouseOverOnElement(driver, administatoricon);
		// Find the SignOut button and click on that
		 JavascriptExecutor jse = (JavascriptExecutor)driver;
		 jse.executeScript("Window.ScrollBy(0,200)");
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// Terminate the Session
		driver.quit();
	}
}
