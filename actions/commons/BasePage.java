package commons;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	private long longTimeOut = GlobalConstants.LONG_TIMEOUT;

	public static BasePage getBasePage() {
		return new BasePage();
	}

	/* Web Browser */
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
		return new WebDriverWait(driver, Duration.ofSeconds(longTimeOut)).until(ExpectedConditions.alertIsPresent());
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

	public Set<Cookie> getBrowserCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
	}

	public void deleteCookies(WebDriver driver) {
		driver.manage().deleteAllCookies();
	}

	/* Windows handle */
	public void switchToWindowByID(WebDriver driver, String pageID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(pageID)) {
				driver.switchTo().window(id);
				sleepInSeconds(1);
				break;
			}
		}
	}

	public void switchToWindowByTitle(WebDriver driver, String pageTile) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			sleepInSeconds(1);

			String actualPageTitle = driver.getTitle();
			if (actualPageTitle.equals(pageTile)) {
				break;
			}
		}
	}

	public void closeAllWindowWithoutParrentID(WebDriver driver, String parrentID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(parrentID)) {
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

	/* Web Elements */
	
	public By getByLocator(String locatorValue) {
		By by = null;
		if(locatorValue.startsWith("xpath=") || locatorValue.startsWith("Xpath=") || locatorValue.startsWith("XPath=") || locatorValue.startsWith("XPATH=")) {
			by = By.xpath(locatorValue.substring(6));
		}else if(locatorValue.startsWith("css=") || locatorValue.startsWith("Css=") || locatorValue.startsWith("CSS=")) {
			by = By.cssSelector(locatorValue.substring(4));
		}else if(locatorValue.startsWith("id=") || locatorValue.startsWith("Id=") || locatorValue.startsWith("ID=")) {
			by = By.id(locatorValue.substring(3));
		}else if(locatorValue.startsWith("name=") || locatorValue.startsWith("Name=") || locatorValue.startsWith("NAME=")) {
			by = By.name(locatorValue.substring(5));
		}else if(locatorValue.startsWith("class=") || locatorValue.startsWith("Class=") || locatorValue.startsWith("CLASS")) {
			by = By.className(locatorValue.substring(6));
		}else if(locatorValue.startsWith("tagname=") || locatorValue.startsWith("Tagname=") || locatorValue.startsWith("TagName=") || locatorValue.startsWith("TAGNAME=")) {
			by = By.tagName(locatorValue.substring(8));
		}else {
			throw new RuntimeException("Locator type is not valid!");
		}
		return by;
	}
	
	public String getDynamicLocator(String locator, String... restParam) {
		return String.format(locator, (Object[]) restParam);
	}

	public WebElement getWebElement(WebDriver driver, By locator) {
		return driver.findElement(locator);
	}

	public WebElement getWebElement(WebDriver driver, String locator) {
		return driver.findElement(getByLocator(locator));
	}

	public List<WebElement> getListWebElement(WebDriver driver, By locator) {
		return driver.findElements(locator);
	}

	public void clickToElement(WebDriver driver, By locator) {
		getWebElement(driver, locator).click();
	}

	public void clickToElement(WebDriver driver, String locator, String... restParam) {
		getWebElement(driver, getDynamicLocator(locator, restParam)).click(); 
	}

	public void sendKeyToElement(WebDriver driver, By locator, String value) {
		getWebElement(driver, locator).clear();
		getWebElement(driver, locator).sendKeys(value);
	}
	
	public void sendkeyToElement(WebDriver driver, String locator, String valueToSend, String... restParam) {
		getWebElement(driver, getDynamicLocator(locator, restParam)).clear();
		getWebElement(driver, getDynamicLocator(locator, restParam)).sendKeys(valueToSend);;
	}
	
	public String getElementText(WebDriver driver, By locator) {
		return getWebElement(driver, locator).getText();
	}
	
	public String getElementText(WebDriver driver, String locator, String...restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).getText();
	}

	public String getElementAttribute(WebDriver driver, By locator, String attributeName) {
		return getWebElement(driver, locator).getAttribute(attributeName);
	}
	
	public String getElementAttribute(WebDriver driver, String locator, String attributeName, String...restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, By locator, String cssPropertyName) {
		return getWebElement(driver, locator).getCssValue(cssPropertyName);
	}

	public void selecteItemInDefaultDropdown(WebDriver dirver, By locator, String itemValue) {
		new Select(getWebElement(dirver, locator)).selectByValue(itemValue);
	}

	public String getFirstSelectedTextInDefaultDropdowns(WebDriver driver, By locator) {
		return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
	}

	public boolean isDefaultDropdownMultiple(WebDriver driver, By locator) {
		return new Select(getWebElement(driver, locator)).isMultiple();
	}

	public void selectItemInDropdown(WebDriver driver, By parentLocator, By childLocator, String expectedTextItem) {
		getWebElement(driver, parentLocator).click();
		sleepInSeconds(1);

		List<WebElement> speedDropdownItems = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(childLocator));
		for (WebElement tempItem : speedDropdownItems) {
			if (tempItem.getText().trim().equals(expectedTextItem))
				;
			sleepInSeconds(1);
			tempItem.click();
			break;
		}
	}

	public String convertRGBAToHexaColor(WebDriver driver, By locator) {
		return Color.fromString(getElementCssValue(driver, locator, "background-color")).asHex();
	}

	public int getElementsSize(WebDriver driver, By locator) {
		return getListWebElement(driver, locator).size();
	}

	/**
	 * Apply for checkbox and radio button
	 * 
	 * @param driver
	 * @param locator
	 */
	public void checkToElement(WebDriver driver, By locator) {
		if (!getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}

	/**
	 * Only apply for checkbox
	 * 
	 * @param driver
	 * @param locator
	 */
	public void uncheckToElement(WebDriver driver, By locator) {
		if (getWebElement(driver, locator).isSelected()) {
			getWebElement(driver, locator).click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, By locator) {
		return getWebElement(driver, locator).isDisplayed();
	}
	
	public boolean isElementDisplayed(WebDriver driver, String locator, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).isDisplayed();
	}


	public boolean isElementSelected(WebDriver driver, By locator) {
		return getWebElement(driver, locator).isSelected();
	}

	public boolean isElementEnabled(WebDriver driver, By locator) {
		return getWebElement(driver, locator).isEnabled();
	}
	
	public boolean isElementEnabled(WebDriver driver, String locator, String... restParams) {
		return getWebElement(driver, getDynamicLocator(locator, restParams)).isEnabled();
	}

	public void switchToIframe(WebDriver driver, By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
		driver.switchTo().frame(getWebElement(driver, locator));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverToElement(WebDriver driver, By locator) {
		new Actions(driver).moveToElement(getWebElement(driver, locator)).perform();
	}

	public void doubleClickToElement(WebDriver driver, By locator) {
		new Actions(driver).doubleClick(getWebElement(driver, locator)).perform();
	}

	public void rightClickToElement(WebDriver driver, By locator) {
		new Actions(driver).contextClick(getWebElement(driver, locator)).perform();
	}

	public void dragAndDropElemet(WebDriver driver, By sourceLocator, By targetLocator) {
		new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator))
				.perform();
	}

	public void sendKeyBoardToElement(WebDriver driver, By locator, Keys key) {
		new Actions(driver).sendKeys(getWebElement(driver, locator), key).perform();
	}

	/* JavaScript Executor */
	public Object executeForBrowser(WebDriver driver, String javaScripts) {
		return ((JavascriptExecutor) driver).executeScript(javaScripts);
	}

	public String getInnerText(WebDriver driver) {
		return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
	}

	public boolean areExpectedTextInInnerText(WebDriver driver, String textExpected) {
		String textActual = (String) ((JavascriptExecutor) driver)
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void scroolToBottomPage(WebDriver driver) {
		((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void naviagateToUrlByJS(WebDriver driver, String url) {
		((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
	}

	public void highlightElement(WebDriver driver, By locator) {
		WebElement element = getWebElement(driver, locator);
		String originalStyle = element.getAttribute("style");
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				"border: 2px solid red; border-style: dashed;");
		sleepInSeconds(1);
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element,
				originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
	}

	public void scrollToElementOnTop(WebDriver driver, By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);",
				getWebElement(driver, locator));
	}

	public void scrollToElementOnDown(WebDriver driver, By locator) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);",
				getWebElement(driver, locator));
	}

	public void sendKeysToElementByJS(WebDriver driver, By locator, String value) {
		((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "');",
				getWebElement(driver, locator));
	}

	public void removeAttributeInDom(WebDriver driver, By locator, String attributeRemove) {
		((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('value', '" + attributeRemove + "');",
				getWebElement(driver, locator));
	}

	public String getElementValidationMessage(WebDriver driver, By locator) {
		return (String) ((JavascriptExecutor) driver).executeScript("arguments[0].validationMessage;",
				getWebElement(driver, locator));
	}

	public boolean isImageLoaded(WebDriver driver, By locator) {
		return (boolean) ((JavascriptExecutor) driver).executeScript(
				"return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0",
				getWebElement(driver, locator));
	}

	public boolean isPageLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, Duration.ofSeconds(longTimeOut));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return (Boolean) jsExecutor.executeScript("return (window.jQuery != null) && (jQuery.active === 0);");
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};
		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	/* Wait and Conditions */
	public void waitForElementVisible(WebDriver driver, By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.visibilityOfElementLocated(locator));
	}
	
	public void waitForElementVisible(WebDriver driver, String locator, String...restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
	}

	public void waitForListElementVisible(WebDriver driver, By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.visibilityOfAllElements(getListWebElement(driver, locator)));
	}

	public void waitForElementInvisible(WebDriver driver, By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.invisibilityOfElementLocated(locator));
	}
	
	public void waitForElementInvisible(WebDriver driver, String locator, String...restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicLocator(locator, restParams))));
	}

	public void waitForListElementInvisible(WebDriver driver, By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
	}

	public void waitForElementClickable(WebDriver driver, By locator) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void waitForElementClickable(WebDriver driver, String locator, String...restParams) {
		new WebDriverWait(driver, Duration.ofSeconds(longTimeOut))
				.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicLocator(locator, restParams))));
	}

}
