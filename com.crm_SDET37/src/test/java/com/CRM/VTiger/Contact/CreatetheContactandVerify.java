package com.CRM.VTiger.Contact;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.crm.RmgYantra.Pages.ContactDashBoard;
import com.crm.RmgYantra.Pages.CreateContactPage;
import com.crm.RmgYantra.Pages.HomePage;
import com.crm.RmgYantra.Pages.LoginPage;
import com.crm_SDET37.GenericUtility.BaseClass;
import com.crm_SDET37.GenericUtility.ExcelUtility;
import com.crm_SDET37.GenericUtility.FileUtility;
import com.crm_SDET37.GenericUtility.JavaUtility;
import com.crm_SDET37.GenericUtility.WebDriverUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

@Listeners(com.crm_SDET37.GenericUtility.ListnerImplementation.class)
public class CreatetheContactandVerify extends BaseClass{
	//public static void main(String[] args) throws Throwable {
	@Test(retryAnalyzer = com.crm_SDET37.GenericUtility.RetryAnalyImplementation.class)
	public void createContact() throws Throwable {
		
		Random random = new Random();
		int randNumb = random.nextInt(100);
		// Click on ;t link Text Major tab
		HomePage homePage = new HomePage(driver);
		homePage.contactLinkText();
		//driver.findElement(By.linkText("Contacts")).click();
		// Enter the First name in First Name Text Field from ExcelSheet
		String firstName = eLib.getDataFromExcel("Sheet2", 2, 0);

		// Enter the Last name in Last name text Field from ExcelSheet
		String lastName = eLib.getDataFromExcel("Sheet2", 2, 1);

		// Click on Create the Contact
		CreateContactPage createContact = new CreateContactPage(driver);
		createContact.createContact("Mr.",firstName,lastName+randNumb);

		// Verify the Name created on contact Page
		ContactDashBoard ContactDashboard = new ContactDashBoard(driver);
		ContactDashboard.verifyCreatedContactTitle(lastName+randNumb);
		//Assert.fail();

		// Identify the administrator icon
		homePage.administratorIcon();

	}
}
