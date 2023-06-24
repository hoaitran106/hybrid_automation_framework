package com.nopcommerce.account;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import commons.BasePage;
import pageObjects.CustomerPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class Account_01_Register extends BasePage {

	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private LoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private CustomerPageObject customerPage;
	private String emailAddress = getEmailRandom();

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://demo.nopcommerce.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

		homePage = new HomePageObject(driver);
	}

	@Test
	public void Account_01_Register_EmptyData() {
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
	
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPasswordTextBox("123456");

		homePage.clickToMyAccountLink();

		Assert.assertEquals(customerPage.getFirstNameTextBoxAttributeValue(), "John");
		Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(), "Kennedy");
		Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(), emailAddress);
	}

	@AfterClass
	public void afterClass() {
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "join" + rand.nextInt(99999) + "@kennedy.us";
	}

}
