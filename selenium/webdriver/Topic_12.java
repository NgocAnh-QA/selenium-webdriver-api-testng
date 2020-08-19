package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_12 {
	WebDriver driver;
  
    @Test
	public void Cross_Browser_Firefox_Lastest() throws InterruptedException{
		//System.setProperty("webdriver.gecko.driver", "D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		Thread.sleep(3000);
		driver.quit();
	}
	
	/*
	@Test
	public void Cross_Browser_Chrome() {

		System.setProperty("webdriver.chrome.driver", "D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}
	@Test
	public void Cross_Browser_Edge() {
		System.setProperty("webdriver.edge.driver", "D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\msedgedriver.exe");
		driver = new EdgeDriver();
		driver.get("https://www.facebook.com/");
		driver.quit();
	}
	*/	
}
