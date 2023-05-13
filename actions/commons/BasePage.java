package commons;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

/*	Web Browser	*/	
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}
	
	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}
	
	public String getCurrentPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}
	
	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}
	
	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}
	
	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}
	
	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}
	
	public Alert waitForAlertPresence(WebDriver driver) {
		return new WebDriverWait(driver, Duration.ofSeconds(30)).until(ExpectedConditions.alertIsPresent());
	}
	
	public void acceptToAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}
	
	public void cancelToAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}
	
	public void getTextInAlert(WebDriver driver) {
		waitForAlertPresence(driver).getText();
	}
	
	public void sendkeyToAlert(WebDriver driver, String keysToSend) {
		waitForAlertPresence(driver).sendKeys(keysToSend);
	}
	
	/* Windows handle */
	public void switchToWindowByID(WebDriver driver, String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for(String id : allIDs) {
			if(!id.equals(pageID)) {
				driver.switchTo().window(id);
				sleepInSeconds(1);
				break;
			}	
		}
	}
	
	public void switchToWindowByTitle(WebDriver driver, String pageTile) {
		Set<String> allIDs = driver.getWindowHandles();
		for(String id : allIDs) {
			driver.switchTo().window(id);
			sleepInSeconds(1);
			
			String actualPageTitle = driver.getTitle();
			if(actualPageTitle.equals(pageTile)) {
				break;
			}
		}
	}
	
	public void closeAllWindowWithoutParrentID(WebDriver driver, String parrentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for(String id : allIDs) {
			if(!id.equals(parrentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
		}
		driver.switchTo().window(parrentID);
	}
	
	private void sleepInSeconds(int second) {
		try {
			Thread.sleep(second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
