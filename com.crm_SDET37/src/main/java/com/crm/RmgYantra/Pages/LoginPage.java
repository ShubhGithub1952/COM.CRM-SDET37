package com.crm.RmgYantra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm_SDET37.GenericUtility.BaseClass;

public class LoginPage {
	//Initialization
	public LoginPage(WebDriver driver) {
		
		PageFactory.initElements(driver, this);
	}

	//Declaration
	@FindBy(name="user_name") private WebElement userNameTestField;
	
	@FindBy(name="user_password") private WebElement passwordtextField;
	@FindBy(id = "submitButton") private WebElement loginButton;
	
	//Utilization
//	public WebElement getUserNameTestField() {
//		return userNameTestField;
//	}
//	public WebElement getPasswordtextField() {
//		return passwordtextField;
//	}
//	public WebElement getLoginButton() {
//		return loginButton;
//	}
	
	public void loginToRmgYantraPage(String USERNAME, String PASSWORD ) {
		userNameTestField.clear();
		userNameTestField.sendKeys(USERNAME);
		passwordtextField.clear();
		passwordtextField.sendKeys(PASSWORD);
		loginButton.click();
	}
	
}
