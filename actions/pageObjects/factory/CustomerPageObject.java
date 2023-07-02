package pageObjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class CustomerPageObject extends BasePageFactory{
	
	@FindBy(id = "FirstName")
	private WebElement firstNameTextBox;
	
	@FindBy(id = "LastName")
	private WebElement lastNameTextBox;
	
	@FindBy(id = "Email")
	private WebElement emailAddressTextBox;
	
	WebDriver driver;
	public CustomerPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public String getFirstNameTextBoxAttributeValue() {
		waitForElementVisible(driver, firstNameTextBox);
		return getElementAttribute(driver, firstNameTextBox, "value");
	}
	
	public String getLastNameTextBoxAttributeValue() {
		waitForElementVisible(driver, lastNameTextBox);
		return getElementAttribute(driver, lastNameTextBox, "value");
	}
	
	public String getEmailAddressTextBoxAttributeValue() {
		waitForElementVisible(driver, emailAddressTextBox);
		return getElementAttribute(driver, emailAddressTextBox, "value");
	}
}
