package com.crm.RmgYantra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactDashBoard {
	//Initializaton 
	public ContactDashBoard(WebDriver driver) {
		PageFactory.initElements(driver, this) ;
	}

	//Declaration 
	@FindBy(xpath = "//span[@class='dvHeaderText']") private WebElement createdContactDashboardTitle;

	//Utilization 
	public WebElement getCreatedContactDashboardTitle() {
		return createdContactDashboardTitle;
	}

	//Method for Verify the Created Contact 
	public void verifyCreatedContactTitle(String expectedTitle ) {
		String actualTitle = createdContactDashboardTitle.getText();
		System.out.println(actualTitle);
		if (actualTitle.contains(expectedTitle)) {
			System.out.println("Pass :- Contact is Created and Verified !");
		}else {
			System.out.println("Fail :- Contact is not Created and not Verified !");
		}
	}

}
