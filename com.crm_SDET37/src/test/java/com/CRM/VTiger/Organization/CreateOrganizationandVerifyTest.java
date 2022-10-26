package com.CRM.VTiger.Organization;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import com.crm.RmgYantra.Pages.CreateOrganizationPage;
import com.crm.RmgYantra.Pages.HomePage;
import com.crm.RmgYantra.Pages.LoginPage;
import com.crm.RmgYantra.Pages.OrganizationDashBoardPage;
import com.crm_SDET37.GenericUtility.ExcelUtility;
import com.crm_SDET37.GenericUtility.FileUtility;
import com.crm_SDET37.GenericUtility.JavaUtility;
import com.crm_SDET37.GenericUtility.WebDriverUtility;
import com.google.common.annotations.VisibleForTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
/**
 * Create thr Org and Create the Contact is Completed by using Generic Utility
 * @author SHUBH
 *
 */

public class CreateOrganizationandVerifyTest {
	//	public static void main(String[] args) throws Throwable {
	@Test(groups = "AdhocTest")
	public void createOrganizationandVerifyTest() throws Throwable {
		WebDriver driver=null;

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
			driver= new ChromeDriver();
		}else if (BROWSER.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}

		// Maximize the browser Window
		webdriverUtilLibrary.maximizeTheBrowser(driver);
		// create implicit wait
		webdriverUtilLibrary.waitImplicitlyToPageGetsLoad(driver);
		// Pass the URL of WebPage
		driver.get(URL);

		// identify the UserName text Field and Password TextFild and Entered the UserName and Password
		LoginPage loginPage = new LoginPage(driver);
		loginPage.loginToRmgYantraPage(USERNAME, PASSWORD);

		// click on organization major tab
		HomePage homePage = new HomePage(driver);
		homePage.getOrganizationLinktext().click();

		// click on create the Organization icon
		CreateOrganizationPage createOrganization = new CreateOrganizationPage(driver);
		createOrganization.createOrganizationPlusIcon();

		// Pass the Organization Name in the organization name text field
		String orgName = excelUtilLibrary.getDataFromExcel("Sheet3", 1, 4);
		createOrganization.organizationName(orgName+randomNumb);

		// identify the industry dropDown and select the communication option
		createOrganization.industryOptions("Communications");

		// identify the type dropDown
		createOrganization.typeOptions("Investor");

		// Save the Organization data
		createOrganization.saveData();

		OrganizationDashBoardPage organizationDashBoardPage = new OrganizationDashBoardPage(driver);
		// Verify the organization name created or not
		organizationDashBoardPage.orgNameTitle(orgName+randomNumb);


		// Identify the administrator icon
		homePage.administratorIcon();

		// Find the SignOut button and click on that
		homePage.Logout(driver);

		// Terminate the Session
		driver.quit();
	}
}
