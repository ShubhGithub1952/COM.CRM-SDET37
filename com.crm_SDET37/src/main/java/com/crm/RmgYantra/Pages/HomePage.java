package com.crm.RmgYantra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm_SDET37.GenericUtility.BaseClass;

public class HomePage {
	//Initialization
	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver,this );
	}
	
	//Declaration
	@FindBy(xpath = "//a[text()='Organizations']") private WebElement organizationLinktext;
	@FindBy(linkText = "Contacts") private WebElement contactLinkText;
	@FindBy(linkText = "Opportunities") private WebElement opportunitiesLinkText;
	@FindBy(linkText = "Products") private WebElement productsLinkText;
	@FindBy(linkText = "Documents") private WebElement documentLinkText;
	@FindBy(linkText = "Email") private WebElement emailLinkText;
	@FindBy(linkText = "More") private WebElement moreDropDownLinkText;
	@FindBy(xpath = "//img[@src='themes/softed/images/user.PNG']") private WebElement administratorIconButton;
	@FindBy(xpath = "//a[text()='Sign Out']") private WebElement signoutbutton;
	
	//Utilization
	public WebElement getOrganizationLinktext() {
		return organizationLinktext;
	}
	public WebElement getContactLinkText() {
		return contactLinkText;
	}
	public WebElement getOpportunitiesLinkText() {
		return opportunitiesLinkText;
	}
	public WebElement getProductsLinkText() {
		return productsLinkText;
	}
	public WebElement getDocumentLinkText() {
		return documentLinkText;
	}
	public WebElement getEmailLinkText() {
		return emailLinkText;
	}
	public WebElement getMoreDropDownLinkText() {
		return moreDropDownLinkText;
	}
	public WebElement getAdministratorIconButton() {
		
		return administratorIconButton;
	}
	public WebElement getSignoutbutton() {
		return signoutbutton;
	}
	//Clicking on AdminiStrator Icon 
	public void administratorIcon() {
		administratorIconButton.click();
	}
	// click on SignOut Button
	public void Logout(WebDriver driver) {
		signoutbutton.click();
	}
	
	//Click on organization link Text
//	public void organizationlinkText() {
//		organizationLinktext.click();
//	}
	
	//Click on Contact Link Text
	public void contactLinkText() {
		contactLinkText.click();
	}
	
	//click on Opportunities
	public void opprtunitiesLinkText() {
		opportunitiesLinkText.click();
	}
} 