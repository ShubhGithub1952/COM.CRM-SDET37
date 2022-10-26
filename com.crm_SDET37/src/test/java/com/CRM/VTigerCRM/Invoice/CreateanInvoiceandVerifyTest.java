package com.CRM.VTigerCRM.Invoice;

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
 * Create the Invoice using Generic utility is Completed !
 * @author SHUBH
 *
 */
public class CreateanInvoiceandVerifyTest {
	public static void main(String[] args) throws Throwable {
		WebDriver driver = null;
		// To Fetch data from Properties file
		// Create the Object of FileInputStream
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

		// Fetch data from Excel Sheet Create the Object of WorkBook
		String subject1 = excelUtilLibrary.getDataFromExcel("Sheet4", 1, 1);

		// Start the Empty Browser upon Condition of Browser Type
		// Write the Condition for ChromeDriver Browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			// Setup the Browser Specific class
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		// Write the Condition for FireFox browser
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			// Setup the Browser Specific class
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else {
			System.out.println("Kindly Use Either ChromeBrowser or FireFoxBrowser !");
		}

		// Maximize the Browser
		webdriverUtilLibrary.maximizeTheBrowser(driver);

		// Create the Implicit wait
		webdriverUtilLibrary.waitImplicitlyToPageGetsLoad(driver);

		// Pass the Main URL of the Application
		driver.get(URL);

		// Identify the UserName Text Field
		WebElement userNameTextField = driver.findElement(By.name("user_name"));

		// Clear the Text Field
		userNameTextField.clear();

		// Enter the UserName
		userNameTextField.sendKeys(USERNAME);

		// Identify the PAssword TextField
		WebElement passWordTextField = driver.findElement(By.name("user_password"));

		// Clear the PAssWord TextField
		passWordTextField.clear();

		// Enter the PassWord
		passWordTextField.sendKeys(PASSWORD);

		// Identify the Login Button and Click on that
		driver.findElement(By.id("submitButton")).click();

		System.out.println("WelCome to the VTiger Application !");

		// Identify the More Major webElement or More DropDown
		WebElement moreDropDown = driver.findElement(By.linkText("More"));
		webdriverUtilLibrary.mouseOverOnElement(driver, moreDropDown);

		// Identify the Invoice WebElement and click on that
		driver.findElement(By.linkText("Invoice")).click();

		// Identify the Create Invoice WebElemt and Click on it
		driver.findElement(By.xpath("//a[@class='hdrLink']/../..//img[@src='themes/softed/images/btnL3Add.gif']"))
		.click();

		// Identify the Subject text Field and Pass the Name of Subject
		driver.findElement(By.name("subject")).sendKeys(subject1+randomNumb);

		// Identify the SalesOrder Select button and Click on it
		driver.findElement(By.xpath("//input[@name='salesorder_name']/..//img[@src='themes/softed/images/select.gif']"))
		.click();

		// Identify the Child Window
		webdriverUtilLibrary.switchToWindow("SalesOrder&action", driver);

		// Select the Sales Order upon webElemt Strategy and Click
		driver.findElement(By.xpath("(//a[contains(.,'Purchasing Delivery')])[5]")).click();

		// Transfer control back to the Parent Window of invoice Page
		webdriverUtilLibrary.switchToWindow("localhost:8888/index", driver);

		// Identify the Select Contact Name WebElement and Click on that
		driver.findElement(By.xpath("//input[@name='contact_name']/..//img[@src='themes/softed/images/select.gif']"))
		.click();

		// Identify the multiple window and save into the object Reference Variable

		// Transfer the Driver control on child Window of Contact page
		webdriverUtilLibrary.switchToWindow("Contacts&action", driver);

		// Identify the Element of contact and select contact
		driver.findElement(By.xpath("(//a[contains(.,'Shubham BP Pitale')])[4]")).click();

		// Handle the Alert PopUp
		webdriverUtilLibrary.switchToAlertPopUpAndAccept(driver,"Shubham BP Pitale27" );
		// Transfer driver back to the Parent Window
		webdriverUtilLibrary.switchToWindow("localhost:8888/index", driver);

		// Identify the *Organization NameElement and Select the Organization name
		driver.findElement(By.xpath("//input[@name='account_name']/..//img[@src='themes/softed/images/select.gif']"))
		.click();

		// Identify the windowIds and transfer driver control
		webdriverUtilLibrary.switchToWindow("Accounts&action", driver);

		// Find the Organization Name and CLick on that
		driver.findElement(By.xpath("(//a[contains(.,'State Bank of India')])[2]")).click();

		// Handle the Pop Up
		webdriverUtilLibrary.switchToAlertPopUpAndAccept(driver, "Alert Handled");

		// Transfer driver control back to the Parent Window
		webdriverUtilLibrary.switchToWindow("localhost:8888/index", driver);


		// Identify the Billing address area text field and enter address
		String shippingAddress = excelUtilLibrary.getDataFromExcel("Sheet4", 1, 3);
		driver.findElement(By.name("bill_street")).sendKeys(shippingAddress);

		// identify the Copy Billing Radio button and click on it
		driver.findElement(By.xpath("//b[text()='Copy Billing address']/..//input[@name='cpy']")).click();

		// Identify the Save button and Click on it
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

		// Verify the invoice has Created or not
		// identify the WebElement of Invoice created
		WebElement invoiceTitle = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));

		if (invoiceTitle.getText().contains(subject1+randomNumb)) {
			System.out.println("Pass : Invoice has Created Successfully and Verified ! ");
		} else {
			System.out.println("Fail : Invoice hasnot Created Successfully and not Verified ! ");
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
