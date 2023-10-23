package pageUIs.user;

import org.openqa.selenium.By;

public class MyAccountSideBarPageUI {
	public static final By ADDRESS_LINK_TEXT = By.xpath("//div[@class='side-2']//a[text()='Addresses']");
	public static final By ORDER_LINK_TEXT = By.xpath("//div[@class='side-2']//a[text()='Orders']");
	public static final By REWARD_POINT_LINK_TEXT = By.xpath("//div[@class='side-2']//a[text()='Reward points']");
	public static final By CUSTOMER_INFO_LINK_TEXT = By.xpath("//div[@class='side-2']//a[text()='Customer info']");
	
	//Dynamic Locator
	public static final String DYNAMIC_SIDEBAR_LINK_TEXT = "XPath=//div[@class='side-2']//a[text()='%s']";
}
