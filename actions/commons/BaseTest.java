package commons;

import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BaseTest {
	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	
	protected WebDriver getBrowserDriver(String browserName, String url) {	
		
		BrowserList browser = BrowserList.valueOf(browserName.toUpperCase());
		
		switch (browser) {
		case FIREFOX:
			//Selenium version 5.x
			//driver = WebDriverManager.firefoxdriver().create();
			driver = new FirefoxDriver();
			break;
		case CHROME:
			driver = new ChromeDriver();
			break;
		case EDGE:
			driver = new EdgeDriver();
			break;
		case IE:
			driver = new InternetExplorerDriver();
			break;	
		default:
			throw new RuntimeException("Browser name is invalid.");
		}
		
		//driver.manage().window().maximize();
		driver.manage().window().setPosition(new Point(0, 0)); //Top left
		driver.manage().window().setSize(new Dimension(1900, 1080));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		driver.get(url);
		return driver;
	}
	
	protected String getEmailRandom() {
		Random rand = new Random();
		return "join" + rand.nextInt(99999) + "@kennedy.us";
	}
	
	protected void sleepInMilSeconds(int second) {
		try {
			Thread.sleep(second);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	protected void closeBrowser() {
		if(null != driver){
			driver.quit();
		}else {
			System.out.println("Driver is null");
		}
	}
}
