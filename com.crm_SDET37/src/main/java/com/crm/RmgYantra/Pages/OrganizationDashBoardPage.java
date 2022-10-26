package com.crm.RmgYantra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationDashBoardPage {
	//InitiaLization 
	public  OrganizationDashBoardPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	// Declaration
	@FindBy(xpath = "//span[@class='dvHeaderText']")
	private WebElement CreatedOrgNameDashbordTitle;
	
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']") private WebElement adminiStratorIcon;
	
	@FindBy(xpath = "//a[text()='Sign Out']") private WebElement signOutButton;

	//Utilization
	public WebElement getCreatedOrgNameDashbordTitle() {
		return CreatedOrgNameDashbordTitle;
	}

	public WebElement getAdminiStratorIcon() {
		return adminiStratorIcon;
	}

	public WebElement getSignOutButton() {
		return signOutButton;
	}
	
	//Method to Verify the Organization Name
	public void orgNameTitle(String orgName) {
		if (CreatedOrgNameDashbordTitle.getText().contains(orgName)) {
			System.out.println("Pass :- Organization is Created and displayed in Orgnization DashBoard !");
		}else {
			System.out.println("Fail :- Organization isnot Created and not displayed in Orgnization DashBoard !");
		}
	}
	
	//Method to find the Administrator Icon
	public void adminisatrtorIcon(WebDriver driver) {
		Actions actions = new Actions(driver);
		actions.moveToElement(adminiStratorIcon).perform();
	}
	//Method to click on SignOut button
	public void signOutButton() {
		signOutButton.click();
	}
}

