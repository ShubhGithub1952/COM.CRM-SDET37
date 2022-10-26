package com.crm.RmgYantra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateContactPage {
	//Initialization
	public CreateContactPage(WebDriver driver) {
		PageFactory.initElements(driver, this );
	}

	//Declaration 
	@FindBy(xpath = "//img[@title='Create Contact...']") private WebElement createContactIcon;
	@FindBy(name = "salutationtype") private WebElement initialNameDropDownButton;
	@FindBy(name = "firstname") private WebElement firstNameTextField;
	@FindBy(name="lastname") private WebElement lastNameTextField;
	@FindBy(name="button") private WebElement saveButton;

	//Utilization
	public WebElement getCreateContactIcon() {
		return createContactIcon;
	}
	public WebElement getInitialNameDropDownButton() {
		return initialNameDropDownButton;
	}
	public WebElement getFirstNameTextField() {
		return firstNameTextField;
	}
	public WebElement getLastNameTextField() {
		return lastNameTextField;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}

	public void createContact(String value,String firstName,String lastName) {
		createContactIcon.click();
		Select initialNameDropDown = new Select(initialNameDropDownButton);
		initialNameDropDown.selectByValue(value);
		firstNameTextField.sendKeys(firstName);
		lastNameTextField.sendKeys(lastName);
		saveButton.click();
	}

}
