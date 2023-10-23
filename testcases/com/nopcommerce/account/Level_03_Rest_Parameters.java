package com.nopcommerce.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import commons.BaseTest;
import commons.GlobalConstants;
import commons.PageCreator;
import pageObjects.admin.AdminDashBoardPageObject;
import pageObjects.admin.AdminLoginPageObject;
import pageObjects.user.AddressPageObject;
import pageObjects.user.CustomerPageObject;
import pageObjects.user.HomePageObject;
import pageObjects.user.OrderPageObject;
import pageObjects.user.UserLoginPageObject;
import pageObjects.user.RegisterPageObject;
import pageObjects.user.RewardPointPageObject;

public class Level_03_Rest_Parameters extends BaseTest {

	private WebDriver driver;
	private HomePageObject homePage;
	private UserLoginPageObject userLoginPage;
	private AdminLoginPageObject adminLoginPage;
	private RegisterPageObject registerPage;
	
	private CustomerPageObject customerPage;
	private AddressPageObject addressPage;
	private OrderPageObject orderPage;
	private RewardPointPageObject rewardPointPage;

	private String emailAddress = getEmailRandom();
	private String adminUrl = GlobalConstants.DEV_ADMIN_URL;
	private String userUrl = GlobalConstants.DEV_USER_URL;
	
	private AdminDashBoardPageObject adminDashBoardPage;
	
	@Parameters({"browserName" })
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = getBrowserDriver(browserName, userUrl);
		homePage = new HomePageObject(driver);
	}
	
	@Test
	public void TC_01_UserRegisterAndLogin() {
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
		
		customerPage = homePage.clickToMyAccountLink();

		Assert.assertEquals(customerPage.getFirstNameTextBoxAttributeValue(), "John");
		Assert.assertEquals(customerPage.getLastNameTextBoxAttributeValue(), "Kennedy");
		Assert.assertEquals(customerPage.getEmailAddressTextBoxAttributeValue(), emailAddress);
	}
	
		
	  @Test public void TC_02_RestParams() { 
		  //Customer page -> Address Page
		  customerPage.openDynamicSideBarPage("Addresses");
		  addressPage = PageCreator.getAddressPageObject(driver);
		  
		  //Address page -> Order Page 
		  addressPage.openDynamicSideBarPage("Orders");
		  orderPage = PageCreator.getOrderPageObject(driver);
		  
		  //Order page -> Customer Page 
		  orderPage.openDynamicSideBarPage("Customer info");
		  customerPage = PageCreator.getCustomerPage(driver);
		  
		  //---Cross sub page---
		  
		  //Order page -> Address Page 
		  orderPage.openDynamicSideBarPage("Addresses");
		  
		  //Address page -> Reward Point Page
		  addressPage.openDynamicSideBarPage("Reward points");
		  rewardPointPage = PageCreator.getRewardPointPageObject(driver);
		  
		  //Reward Point page -> Customer Page
		  rewardPointPage.openDynamicSideBarPage("Customer info");
		  
		  //Customer page -> Reward Point Page
		  customerPage.openDynamicSideBarPage("Reward points"); 
	  }
	 
		@AfterClass
		public void afterClass() {
		}
	
		public String getEmailRandom() {
			Random rand = new Random();
			return "join" + rand.nextInt(99999) + "@kennedy.us";
		}
		
		//Var Arg = Rest Parameter
		public void clickToElement(String locatorValue, String... values) { //Array String
			locatorValue = String.format(locatorValue, (Object[]) values); //String.format(string, Object)
			System.out.println("Click to: " + locatorValue);
		}

}
