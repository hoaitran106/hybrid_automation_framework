package pageUIs;

import org.openqa.selenium.By;

public class RegisterPageUI {
	public static final By FIRSTNAME_TEXTBOX = By.xpath("//input[@id='FirstName']");
	public static final By LASTNAME_TEXTBOX = By.xpath("//input[@id='LastName']");
	public static final By EMAIL_TEXTBOX = By.xpath("//input[@id='Email']");
	public static final By PASSWORD_TEXTBOX = By.xpath("//input[@id='Password']");
	public static final By CONFIRM_PASSWORD_TEXTBOX = By.xpath("//input[@id='ConfirmPassword']");
	public static final By REGISTER_BUTTON = By.xpath("//button[@id='register-button']");
	public static final By FIRSTNAME_ERROR_MSG = By.xpath("//span[@id='FirstName-error']");
	public static final By LASTNAME_ERROR_MSG = By.xpath("//span[@id='LastName-error']");
	public static final By EMAIL_ERROR_MSG = By.xpath("//span[@id='Email-error']");
	public static final By PASSWORD_ERROR_MSG = By.xpath("//span[@id='Password-error']");
	public static final By CONFIRM_PASSWORD_ERROR_MSG = By.xpath("//input[@id='ConfirmPassword-error']");
	public static final By REGISTRATION_COMPLETED_MSG = By.xpath("//div[@class='result']");
	public static final By APP_LOGO = By.xpath("//div[@class='header-logo']//img");
}
