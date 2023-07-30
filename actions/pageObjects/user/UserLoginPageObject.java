package pageObjects.user;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageCreator;
import pageUIs.user.UserLoginPageUI;

public class UserLoginPageObject extends BasePage {
	WebDriver driver;

	public UserLoginPageObject(WebDriver driver) {
		this.driver = driver;
	}
	
	public void enterToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, UserLoginPageUI.EMAIL_TEXTBOX);
		sendKeysToElementByJS(driver, UserLoginPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void enterToPasswordTextBox(String password) {
		waitForElementVisible(driver, UserLoginPageUI.PASSWORD_TEXTBOX);
		sendKeysToElementByJS(driver, UserLoginPageUI.PASSWORD_TEXTBOX, password);
	}
	
	public HomePageObject clickToLoginButton() {
		waitForElementClickable(driver, UserLoginPageUI.LOGIN_BUTTON);
		clickToElement(driver, UserLoginPageUI.LOGIN_BUTTON);
		return PageCreator.getHomePage(driver);
	}
}
