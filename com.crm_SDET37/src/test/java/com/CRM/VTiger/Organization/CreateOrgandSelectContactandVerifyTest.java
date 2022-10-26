package com.CRM.VTiger.Organization;

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
 * Create the Organization and Creating Contact Using General Utility is Completed
 * @author SHUBH
 *
 */
public class CreateOrgandSelectContactandVerifyTest {

	//public static void main(String[] args) throws Throwable {
	@Test(groups = "smokeTest")
	public void createOrganizationandVerify() throws Throwable{
		WebDriver driver = null;
		// Fetch the Common data from Properties File
		FileUtility fileUtilLibrary = new FileUtility();
		WebDriverUtility webdriverUtilLibrary = new WebDriverUtility();
		ExcelUtility excelUtilLibrary = new ExcelUtility();

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
		}else {
			System.out.println("Kindly Elect One of the Browser !");
		}
		// Maximize the browser Window
		webdriverUtilLibrary.maximizeTheBrowser(driver);

		// create implicite wait
		webdriverUtilLibrary.waitImplicitlyToPageGetsLoad(driver);

		// Pass the URL of WebPage
		driver.get(URL);

		//Identify the UserName text field and and Pass the UserName
		WebElement userNameTextField = driver.findElement(By.name("user_name"));
		userNameTextField.clear();
		userNameTextField.sendKeys(USERNAME);

		//Identify the Password TextField and enter the Password
		WebElement passwordTextField = driver.findElement(By.name("user_password"));
		passwordTextField.clear();
		passwordTextField.sendKeys(PASSWORD);

		//Identify the Login button and Click on it
		driver.findElement(By.id("submitButton")).click();

		//Identify the organization major link Text and Click on it
		driver.findElement(By.linkText("Organizations")).click();

		//Identify the Create Organization button and Click on it
		driver.findElement(By.cssSelector("img[title='Create Organization...']")).click();
		// Select the Organization text Field and enter the Organization Name from Excel Sheet
		String organizationName = excelUtilLibrary.getDataFromExcel("Sheet4", 2, 2);

		//Create the Random Name for OrgName and pass the Organization Name
		JavaUtility javaUtilLibrary = new JavaUtility();
		int randomNumb = javaUtilLibrary.getRandomNum();
		driver.findElement(By.name("accountname")).sendKeys(organizationName+randomNumb);

		// Identify the Industry dropdown and select the Banking element
		WebElement industryDropdown = driver.findElement(By.xpath("//select[@name='industry']"));
		webdriverUtilLibrary.getAllTheOptiondFromDropDown(industryDropdown);
		webdriverUtilLibrary.select(industryDropdown, "Banking");

		// Identify the Account Type Dropdown and select the Analyst Options
		WebElement accountTypeDropdown = driver.findElement(By.name("accounttype"));
		webdriverUtilLibrary.getAllTheOptiondFromDropDown(accountTypeDropdown);
		webdriverUtilLibrary.select(accountTypeDropdown, "Analyst");
		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();
		WebElement orgHeader = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));

		//wait.until(ExpectedConditions.visibilityOf(orgHeader));
		if (orgHeader.getText().contains(organizationName+randomNumb)) {
			System.out.println("Pass :- SBI Organization Information is Created !");
		} else {
			System.out.println("Fail :- SBI Organization Information isnot Created !");
		}
		driver.findElement(By.linkText("Contacts")).click();

		driver.findElement(By.cssSelector("img[alt='Create Contact...']")).click();

		WebElement firstNameInitialdropDown = driver.findElement(By.name("salutationtype"));
		webdriverUtilLibrary.getAllTheOptiondFromDropDown(firstNameInitialdropDown);
		webdriverUtilLibrary.select(firstNameInitialdropDown, "Mr.");

		String firstName = excelUtilLibrary.getDataFromExcel("Sheet2", 1, 0);
		driver.findElement(By.name("firstname")).sendKeys(firstName);
		String lastName = excelUtilLibrary.getDataFromExcel("Sheet2", 1, 1);
		driver.findElement(By.name("lastname")).sendKeys(lastName+randomNumb);

		driver.findElement(By.xpath("//img[@src='themes/softed/images/select.gif']")).click();
		webdriverUtilLibrary.switchToWindow("Accounts&action=Popup&popuptype", driver);

		driver.findElement(By.xpath("//a[contains(.,'State Bank of India')]")).click();
		webdriverUtilLibrary.switchToWindow("Contacts&action=EditView&return_action", driver);

		driver.findElement(By.cssSelector("input[title='Save [Alt+S]']")).click();

		WebElement contactInfoHeaderTitle = driver.findElement(By.xpath("//span[@class='dvHeaderText']"));

		if (contactInfoHeaderTitle.getText().contains(lastName)) {
			System.out.println("Pass : Contact is Created on Contact Major Tab !");
		} else {
			System.out.println("Fail : Contact isnot Created Contact Major tab !");
		}
		driver.quit();
	}
}
