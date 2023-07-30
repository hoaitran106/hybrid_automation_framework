package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageCreator;
import pageUIs.user.RegisterPageUI;

public class RegisterPageObject extends BasePage {

	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);

	}

	public HomePageObject clickToAppLogo() {
		waitForElementVisible(driver, RegisterPageUI.APP_LOGO);
		clickToElement(driver, RegisterPageUI.APP_LOGO);
		return PageCreator.getHomePage(driver);
	}

	public void enterToFirstNameTextBox(String firstNameValue) {
		waitForElementVisible(driver, RegisterPageUI.FIRSTNAME_TEXTBOX);
		sendKeysToElementByJS(driver, RegisterPageUI.FIRSTNAME_TEXTBOX, firstNameValue);
	}

	public void enterToLastNameTextBox(String lastNameValue) {
		waitForElementVisible(driver, RegisterPageUI.LASTNAME_TEXTBOX);
		sendKeysToElementByJS(driver, RegisterPageUI.LASTNAME_TEXTBOX, lastNameValue);
	}

	public void enterToEmailTextBox(String emailAddressValue) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeysToElementByJS(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddressValue);
	}

	public void enterToPasswordTextBox(String passwordValue) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeysToElementByJS(driver, RegisterPageUI.PASSWORD_TEXTBOX, passwordValue);
	}

	public void enterToConfirmPassWordTextBox(String passwordValue) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeysToElementByJS(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, passwordValue);
	}

	public String getRegisterSuccessMessage() {
		waitForElementVisible(driver, RegisterPageUI.REGISTRATION_COMPLETED_MSG);
		return getElementText(driver, RegisterPageUI.REGISTRATION_COMPLETED_MSG);
	}
	
	public String getEmailErrorMessageText() {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ERROR_MSG);
		return getElementText(driver, RegisterPageUI.PASSWORD_ERROR_MSG);
	}
}
