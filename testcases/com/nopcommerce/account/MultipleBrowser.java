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
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class MultipleBrowser extends BaseTest {
	
	private WebDriver driver;
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	WebDriverWait explicitWait;

	@Parameters("browsers")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName);
		explicitWait = new WebDriverWait(driver, Duration.ofSeconds(30));
		driver.get("https://demo.nopcommerce.com/");
	}
	
	@Test
	public void Account_01_Register_EmptyData() {
		homePage = new HomePageObject(driver);
		homePage.clickToRegisterLink();
		registerPage = new RegisterPageObject(driver);
		registerPage.clickToRegisterButton();
	}

	@Test
	public void Account_02_Register_IncorrectUsername() {
	}

	@Test
	public void Account_03_Register_IncorectPasword() {
	}

	@Test
	public void Account_04_Register_Success() {
		registerPage.clickToAppLogo();
		
		homePage = new HomePageObject(driver);
		registerPage = new RegisterPageObject(driver);
		
		homePage.clickToRegisterLink();

		registerPage.enterToFirstNameTextBox("John");
		registerPage.enterToLastNameTextBox("Kennedy");
		registerPage.enterToEmailTextBox(emailAddress);
		registerPage.enterToPasswordTextBox("123456");
		registerPage.enterToConfirmPassWordTextBox("123456");

		registerPage.clickToRegisterButton();

		Assert.assertEquals(registerPage.getRegisterSuccessMessage(), "Your registration completed");
	}
	
	@Test
	public void Account_05_Login_Success() {
		
		loginPage = new LoginPageObject(driver);
		homePage = new HomePageObject(driver);
		customerPage = new CustomerPageObject(driver);
	
		homePage.clickToLogInLink();
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPasswordTextBox("123456");
		loginPage.clickToLoginButton();
		
		homePage.clickToMyAccountLink();

		
		Assert.assertEquals(customerPage.getFirstNameTextBoxAttributeValue(), "John");
		Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(), "Kennedy");
		Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(), emailAddress);
	}

	@AfterClass
	public void afterClass() {
		closeBrowser();
	}

}
