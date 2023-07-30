package com.nopcommerce.account;

import java.time.Duration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageCreator;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;

public class MultipleBrowser extends BaseTest {
	
	private WebDriver driver;
	private HomePageObject homePage;
	private UserLoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@Parameters({"browsers", "userUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String userUrl) {
		driver = getBrowserDriver(browserName, userUrl);
		homePage = PageCreator.getHomePage(driver);
	}
	
	@Test
	public void Account_01_Register_EmptyData() {
		
		registerPage = homePage.clickToRegisterLink();
		registerPage.clickToRegisterButton();
	}

	@Test
	public void Account_02_Register_IncorrectUsername() {
	}

	@Test
	public void Account_03_Register_Success() {
		homePage = registerPage.clickToAppLogo();
		registerPage = homePage.clickToRegisterLink();
		
		registerPage.enterToFirstNameTextBox("John");
		registerPage.enterToLastNameTextBox("Kennedy");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPasswordTextBox("123456");
		registerPage.enterToConfirmPassWordTextBox("123456");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Test
	public void Account_04_Login_Success() {
		homePage = registerPage.clickToAppLogo();
		loginPage = homePage.clickToLogInLink();
		
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPasswordTextBox("123456");
		loginPage.clickToLoginButton();
		
		customerPage = homePage.clickToMyAccountLink();

		Assert.assertEquals(customerPage.getFirstNameTextBoxAttributeValue(), "John");
		Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(), "Kennedy");
		Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(), emailAddress);
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
