package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Nhap {
	WebDriver driver;

	@Test
	public void TC_name() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com/");
		Thread.sleep(3000);
		driver.quit();

	}
}
