package pageObjects.factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import commons.BasePageFactory;

public class LoginPageObject extends BasePageFactory {
	
	@FindBy(id = "Email")
	private WebElement emailTextBox;
	
	@FindBy(id = "Password")
	private WebElement passWordTextBox;
	
	@FindBy(xpath = "//button[text()='Log in']")
	private WebElement loginButton;
	
	WebDriver driver;

	public LoginPageObject(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(this.driver, this);
	}
	
	public void enterToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, emailTextBox);
		sendKeyToElement(driver, emailTextBox, emailAddress);
	}

	public void enterToPasswordTextBox(String password) {
		waitForElementVisible(driver, passWordTextBox);
		sendKeyToElement(driver, passWordTextBox, password);
	}
	
	public void clickToLoginButton() {
		waitForElementClickable(driver, loginButton);
		clickToElement(driver, loginButton);
	}
}
