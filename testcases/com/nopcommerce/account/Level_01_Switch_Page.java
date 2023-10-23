package com.nopcommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import pageObjects.user.AddressPageObject;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.OrderPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointPageObject;

public class Level_01_Switch_Page extends BaseTest {

	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private UserLoginPageObject loginPage;
	private RegisterPageObject registerPage;
	private CustomerPageObject customerPage;
	
	private AddressPageObject addressPage;
	private OrderPageObject orderPage;
	private RewardPointPageObject rewardPointPage;
	
	private String emailAddress = getEmailRandom();
	private String adminUrl, userUrl;

	@Parameters({"browser", "adminUrl", "userUrl"})
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = getBrowserDriver(browserName, userUrl);
		homePage = new HomePageObject(driver);
		
		this.userUrl = userUrl;
		this.adminUrl = adminUrl;
	}
	

	@Test
	public void User_01_Register_Success() {
		registerPage = homePage.clickToRegisterLink();

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
	public void User_02_Login_Success() {
		homePage = registerPage.clickToAppLogo();
		loginPage = homePage.clickToLogInLink();
		
		loginPage.enterToEmailTextBox(emailAddress);
		loginPage.enterToPasswordTextBox("123456");
		homePage = loginPage.clickToLoginButton();
		
		customerPage = homePage.clickToMyAccountLink();

		Assert.assertEquals(customerPage.getFirstNameTextBoxAttributeValue(), "John");
		Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(), "Kennedy");
		Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(), emailAddress);
	}

	@Test
	public void User_03_Switch_Page() {
		//Customer page -> Address Page
		addressPage = customerPage.openAddressPage();
		
		//Address page -> Order Page
		orderPage = addressPage.openOrderPage();
		
		//Order page -> Customer Page
		customerPage = orderPage.openCustomerPage();
		
		//---Cross sub page---
		
		//Order page -> Address Page
		addressPage = orderPage.openAddressPage();
		
		//Address page -> Reward Point Page
		rewardPointPage = addressPage.openRewardPointPage();
		
		//Reward Point page -> Customer Page
		customerPage = rewardPointPage.openCustomerPage();
		
		//Customer page -> Reward Point Page
		rewardPointPage = customerPage.openRewardPointPage();
	}

	@AfterClass
	public void afterClass() {
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "join" + rand.nextInt(99999) + "@kennedy.us";
	}

}
