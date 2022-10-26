package com.CRM.VTiger.Assets;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
public class CreateAssetsandVerifyTest {
	//public static void main(String[] args) throws Throwable {
	@Test
	public void createAssetadVerifyTest() throws Throwable {
		WebDriver driver = null;
		// To Fetch data from Properties file
		FileUtility fileUtilLibrary = new FileUtility();
		String URL = fileUtilLibrary.getPropertyValue("url");
		String USERNAME = fileUtilLibrary.getPropertyValue("username");
		String PASSWORD = fileUtilLibrary.getPropertyValue("password");
		String BROWSER = fileUtilLibrary.getPropertyValue("browser");
		String DATE = fileUtilLibrary.getPropertyValue("date");

		//Create the Object of WebDriver Util Package to access the Method inside it
		WebDriverUtility WebDriverUtilLibrary = new WebDriverUtility();

		//Create the Object of ExcelUtility Package to access the Method insdie it
		ExcelUtility excelUtilLibrary = new ExcelUtility();

		// Fetch data from Excel Sheet Create the Object of WorkBook
		String serialNumber1 = excelUtilLibrary.getDataFromExcel("Sheet4", 1, 4);
		String assetName = excelUtilLibrary.getDataFromExcel("Sheet4", 1, 5);

		// Create the Object of Random class
		JavaUtility javaUtilLibrary = new JavaUtility();
		int randonNumb = javaUtilLibrary.getRandomNum();

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
		WebDriverUtilLibrary.maximizeTheBrowser(driver);
		// Create the Implicit wait
		WebDriverUtilLibrary.waitImplicitlyToPageGetsLoad(driver);

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

		// Create the Object of Action class use Overing on More DropdDown
		WebDriverUtilLibrary.mouseOverOnElement(driver, moreDropDown);
		// Find the Element of Assets and click on it
		driver.findElement(By.linkText("Assets")).click();
		// Find the Element of Create Assets and click on it
		driver.findElement(By.xpath("//a[@class='hdrLink']/../..//img[@src='themes/softed/images/btnL3Add.gif']"))
		.click();
		// Identify the Serial Number Text field and Enter the Serial Number
		driver.findElement(By.xpath("//input[@name='serialnumber']")).sendKeys(serialNumber1 +randonNumb);
		// Identify the Date text Field and enter the Valid date
		driver.findElement(By.xpath("//input[@name='datesold']")).sendKeys(DATE);
		// Identify the add Invoice WebElement and click on it
		driver.findElement(
				By.xpath("//input[@name='invoiceid_display']/..//img[@src='themes/softed/images/select.gif']")).click();
		// Identify the Window Ids and transfer the Driver control from Parent window to
		// the Child window
		WebDriverUtilLibrary.switchToWindow("module=Invoice&action", driver);

		// identify the Invoice link and click on that
		driver.findElement(By.linkText("Purchase Product PP0.181")).click();
		// Handle the Pop up
		// driver.switchTo().alert().accept();
		// Transfer back driver control from child window to the Parent window

		WebDriverUtilLibrary.switchToWindow("module=Assets&action", driver);

		// Identify the Customer Name WebElement and click on that
		driver.findElement(By.xpath("//input[@name='account_display']/..//img[@src='themes/softed/images/select.gif']"))
		.click();
		// Identify the Multiple Child Window and transfer driver control on Child
		WebDriverUtilLibrary.switchToWindow("module=Accounts&action", driver);

		// find the Customer WebElement and Click on it
		driver.findElement(By.linkText("JSpider Institute Technology76")).click();

		// transfer driver control back to the parent window
		WebDriverUtilLibrary.switchToWindow("module=Assets&action", driver);

		// Identify the Product Name WebElement and click on that
		driver.findElement(By.xpath("//input[@name='product_display']/..//img[@src='themes/softed/images/select.gif']"))
		.click();
		// Identify the Multiple window and transfer the Driver control
		WebDriverUtilLibrary.switchToWindow("module=Products&action", driver);

		// Identify the Product WebElement and Click on it
		driver.findElement(By.linkText("HardDisk_SSD512")).click();

		// Transfer back driver control on Parent window of Assert WebPage
		WebDriverUtilLibrary.switchToWindow("module=Assets&action", driver);

		// Identify the Assert Name TExt Field and enter the Name
		driver.findElement(By.name("assetname")).sendKeys(assetName+randonNumb);
		// save the Assert Page Details
		driver.findElement(By.name("button")).click();

		// identify the WebElement of Invoice created
		
		WebElement AssetTitle = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));
		SoftAssert softAssert = new SoftAssert();
		softAssert.assertTrue(AssetTitle.getText().contains(assetName+randonNumb));
		softAssert.assertAll();
		//if (AssetTitle.getText().contains(assetName+randonNumb)) {
			//System.out.println("Pass : Invoice has Created Successfully and Verified ! ");
		//} else {
		//	System.out.println("Fail : Invoice hasnot Created Successfully and not Verified ! ");
		//}
		// Identify the administrator icon
		WebElement administatoricon = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		// Create the object of Action class
		WebDriverUtilLibrary.mouseOverOnElement(driver, administatoricon);
		// Find the SignOut button and click on that
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// Terminate the Session
		driver.quit();
	}
}
