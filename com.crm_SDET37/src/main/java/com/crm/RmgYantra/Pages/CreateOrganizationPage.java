package com.crm.RmgYantra.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class CreateOrganizationPage {
	
	// Initialization
	public CreateOrganizationPage(WebDriver driver) {
		PageFactory.initElements(driver, this );
	}

	//Declaration 
	@FindBy(xpath = "//a[@class='hdrLink']/../..//img[@src='themes/softed/images/btnL3Add.gif']")
	private WebElement createOrgnizationPlusIcon;
	@FindBy(name = "accountname") private WebElement organizationNameTextField;
	@FindBy(xpath = "//select[@name='industry']") private WebElement industryOptionsButton;
	@FindBy(xpath = "//select[@name='accounttype']") private WebElement typeOptionsButton;
	@FindBy(name = "bill_street") private WebElement addressTextFieldofOrg;
	@FindBy(xpath = "//b[text()='Copy Billing address']/..//input[@name='cpy']") 
	private WebElement copyBillingAddressRadioButton;
	@FindBy(name = "button") private WebElement saveButton;

	//Utilization
	
	
	public WebElement getOrganizationNameTextField() {
		return organizationNameTextField;
	}
	public WebElement getCreateOrgnizationPlusIcon() {
		return createOrgnizationPlusIcon;
	}
	public WebElement getIndustryOptionsButton() {
		return industryOptionsButton;
	}
	public WebElement getTypeOptionsButton() {
		return typeOptionsButton;
	}
	public WebElement getAddressTextFieldofOrg() {
		return addressTextFieldofOrg;
	}
	public WebElement getCopyBillingAddressRadioButton() {
		return copyBillingAddressRadioButton;
	}
	public WebElement getSaveButton() {
		return saveButton;
	}
	
	//Method for create Organization 
	public void createOrganizationPlusIcon() {
		createOrgnizationPlusIcon.click();
	}
	
	//method for Passing the Organization Name
	public String organizationName(String orgnizationName) {
		organizationNameTextField.sendKeys(orgnizationName);
		return orgnizationName;
	}
	
	//Method for select industry DropDown
	public void industryOptions(String value) {
		Select select = new Select(industryOptionsButton);
		select.selectByValue(value);
	}
	
	//Method for Selecting Type DropDown
	public void typeOptions(String value) {
		Select select = new Select(typeOptionsButton);
		select.selectByValue(value);
	}

	//Pass the Address of the Organization
	public String billingAddresstextField(String address) {
		copyBillingAddressRadioButton.sendKeys(address);
		return address;
	}

	//Method for Save Data of the Organization 
	public void saveData() {
		saveButton.click();
	}
}
