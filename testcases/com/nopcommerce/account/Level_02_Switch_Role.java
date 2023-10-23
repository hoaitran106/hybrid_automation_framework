package com.nopcommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.PageCreator;
import pageObjects.admin.AdminDashBoardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.AddressPageObject;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.OrderPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointPageObject;

public class Level_02_Switch_Role extends BaseTest {

	private WebDriver driver;
	private String projectPath = System.getProperty("user.dir");
	private HomePageObject homePage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private RegisterPageObject registerPage;

	private String emailAddress = getEmailRandom();
	private String adminUrl, userUrl;

	private AdminDashBoardPageObject adminDashBoardPage;

	@Parameters({"browserName", "adminUrl", "userUrl" })
	@BeforeClass
	public void beforeClass(String browserName, String adminUrl, String userUrl) {
		System.setProperty("webdriver.gecko.driver", projectPath + "\\browserDrivers\\geckodriver.exe");

		driver = getBrowserDriver(browserName, userUrl);
		homePage = new HomePageObject(driver);

		this.userUrl = userUrl;
		this.adminUrl = adminUrl;
	}

	@Test
	public void Level_01_User_To_Admin() {
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

		homePage = registerPage.clickToAppLogo();
		userLoginPage = homePage.clickToLogInLink();

		userLoginPage.enterToEmailTextBox(emailAddress);
		userLoginPage.enterToPasswordTextBox("123456");

		homePage = userLoginPage.clickToLoginButton();

		homePage.clickToLogoutLink();

		// Home Page(User) -> Login Page (Admin)
		homePage.openPageUrl(driver, adminUrl);

		adminLoginPage = PageCreator.getAdminLoginPageObject(driver);
		adminDashBoardPage = adminLoginPage.loginToAdmin("admin@yourstore.com", "admin");

		Assert.assertTrue(adminDashBoardPage.isPageLoadedSuccess(driver));
	}

	
  @Test 
  public void Level_01_Admin_To_User() {
  
	  adminLoginPage = adminDashBoardPage.clickToLogoutLink();
	  adminLoginPage.openPageUrl(driver, userUrl);
	  
	  //Home Page 
	  homePage = PageCreator.getHomePage(driver);
	  //Do something
  }
	 

	/*
	 * @Test public void User_03_Switch_Page() { 
	 * //Customer page -> Address Page
	 * addressPage = customerPage.openAddressPage();
	 * 
	 * //Address page -> Order Page orderPage = addressPage.openOrderPage();
	 * 
	 * //Order page -> Customer Page customerPage = orderPage.openCustomerPage();
	 * 
	 * //---Cross sub page---
	 * 
	 * //Order page -> Address Page addressPage = orderPage.openAddressPage();
	 * 
	 * //Address page -> Reward Point Page rewardPointPage =
	 * addressPage.openRewardPointPage();
	 * 
	 * //Reward Point page -> Customer Page customerPage =
	 * rewardPointPage.openCustomerPage();
	 * 
	 * //Customer page -> Reward Point Page rewardPointPage =
	 * customerPage.openRewardPointPage(); }
	 */

	@AfterClass
	public void afterClass() {
	}

	public String getEmailRandom() {
		Random rand = new Random();
		return "join" + rand.nextInt(99999) + "@kennedy.us";
	}

}
