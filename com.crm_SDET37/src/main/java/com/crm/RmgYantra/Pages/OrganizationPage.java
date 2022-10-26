package com.crm.RmgYantra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationPage {
//Initialization
	public OrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver,this );
	}
	
	//Declaration 
	@FindBy(xpath = "//a[@class='hdrLink']/../..//img[@src='themes/softed/images/btnL3Add.gif']") 
	private WebElement createOrganizationPlusButton;

	//Utilization
	public WebElement getCreateOrganizationPlusButton() {
		return createOrganizationPlusButton;
	}
	
	public void CreateOrganizationIcon() {
		createOrganizationPlusButton.click();
	}
}
