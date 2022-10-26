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
 * 
 * @author SHUBH
 * This class is Implimeted by Generic Utility
 */
public class CreateVendorandVerifyinVTigerTest {

	//public static void main(String[] args) throws Throwable {
	@Test
	public void createVendorandVerify() throws Throwable {

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
		driver.findElement(By.linkText("More")).click();
		driver.findElement(By.linkText("Vendors")).click();
		driver.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();
		// Fetch the data from Excel File with help of WebDriverUtilLibrary
		String vendorName = excelUtilLibrary.getDataFromExcel("Sheet2", 1, 5);
		driver.findElement(By.name("vendorname")).sendKeys(vendorName+randomNumb);
		//Fetch the Vendor mail id from Excel Sheet 
		String vendorMailId = excelUtilLibrary.getDataFromExcel("Sheet2",1, 6);
		driver.findElement(By.id("email")).sendKeys(vendorMailId+randomNumb);
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		WebElement createdVendorName = driver.findElement(By.xpath("//span[@class='lvtHeaderText']"));
		if (createdVendorName.getText().contains(vendorName+randomNumb)) {
			System.out.println("Pass : Vendor is Created and displayed on Administatrs Vendors Page");
		} else {
			System.out.println("Fail : Vendor isnot Created and not displayed on Administatrs Vendors Page");
		}
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.cssSelector("img[src='themes/softed/images/btnL3Add.gif']")).click();
		String productName = excelUtilLibrary.getDataFromExcel("Sheet2", 1, 7);
		driver.findElement(By.name("productname")).sendKeys(productName+randomNumb);

		WebElement productCategoryDropdown = driver.findElement(By.cssSelector("select[name='productcategory']"));
		webdriverUtilLibrary.select(productCategoryDropdown, "Software");

		driver.findElement(By.cssSelector("img[src='themes/softed/images/select.gif']")).click();

		webdriverUtilLibrary.switchToWindow("Vendors&action=Popup&html", driver);

		driver.findElement(By.linkText(vendorName+randomNumb)).click();

		webdriverUtilLibrary.switchToWindow("Products&action=EditView&return_action", driver);

		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		WebElement productCreatedTitle = driver.findElement(By.cssSelector("span[class='lvtHeaderText']"));
		if (productCreatedTitle.getText().contains(productName+randomNumb)) {
			System.out.println("Pass : Vendor is Selected in Product and Title of Product successfully Verified !");
		} else {
			System.out.println(
					"Fail : Vendor isnot Selected in Product and Title of Product not successfully Verified !");
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
