package pageObjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePage;
import commons.BasePageFactory;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePageFactory {

	@FindBy(id = "FirstName")
	private WebElement firstNameTextBox;
	
	@FindBy(id = "LastName")
	private WebElement lastNameTextBox;
	
	@FindBy(id = "Email")
	private WebElement emailTextBox;
	
	@FindBy(id = "Password")
	private WebElement passwordTextBox;
	
	@FindBy(id = "ConfirmPassword")
	private WebElement confirmPasswordTextBox;
	
	@FindBy(id = "register-button")
	private WebElement registerButton;
	
	@FindBy(id = "FirstName-error")
	private WebElement firstNameErrorMessage;
	
	@FindBy(id = "LastName-error")
	private WebElement lastNameErrorMessage;
	
	@FindBy(id = "Email-error")
	private WebElement emailErrorMessage;
	
	@FindBy(id = "Password-error")
	private WebElement passwordErrorMessage;
	
	@FindBy(id = "ConfirmPassword-error")
	private WebElement ConfirmPasswordErrorMessage;
	
	@FindBy(xpath ="//div[@class='result']")
	private WebElement registrationCompletedMessage;
	
	@FindBy(id = "//div[@class='header-logo']//img")
	private WebElement appliationLogo;
	
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, registerButton);
		clickToElement(driver, registerButton);

	}

	public void clickToAppLogo() {
		waitForElementVisible(driver, appliationLogo);
		clickToElement(driver, appliationLogo);
	}

	public void enterToFirstNameTextBox(String firstNameValue) {
		waitForElementVisible(driver, firstNameTextBox);
		sendKeyToElement(driver, firstNameTextBox, firstNameValue);
	}

	public void enterToLastNameTextBox(String lastNameValue) {
		waitForElementVisible(driver, lastNameTextBox);
		sendKeyToElement(driver, lastNameTextBox, lastNameValue);
	}

	public void enterToEmailTextBox(String emailAddressValue) {
		waitForElementVisible(driver, emailTextBox);
		sendKeyToElement(driver, emailTextBox, emailAddressValue);
	}

	public void enterToPasswordTextBox(String passwordValue) {
		waitForElementVisible(driver, passwordTextBox);
		sendKeyToElement(driver, passwordTextBox, passwordValue);
	}

	public void enterToConfirmPassWordTextBox(String passwordValue) {
		waitForElementVisible(driver, confirmPasswordTextBox);
		sendKeyToElement(driver, confirmPasswordTextBox, passwordValue);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, registrationCompletedMessage);
		return getElementText(driver, registrationCompletedMessage);
	}
	
	public String getEmailErrorMessageText() {
		waitForElementVisible(driver, emailErrorMessage);
		return getElementText(driver, passwordErrorMessage);
	}
}
