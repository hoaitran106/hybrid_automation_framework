package pageObjects.admin;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import commons.PageCreator;
import pageUIs.admin.AdminDashBoardPageUI;

public class AdminDashBoardPageObject extends BasePage {
	WebDriver driver;
	public AdminDashBoardPageObject(WebDriver driver) {
		this.driver = driver;
	}
	public AdminLoginPageObject clickToLogoutLink() {
		waitForElementClickable(driver, AdminDashBoardPageUI.LOGOUT_BUTTON);
		clickToElement(driver, AdminDashBoardPageUI.LOGOUT_BUTTON);
		return PageCreator.getAdminLoginPageObject(driver);
	}
}
