package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Topic_01_Check_Environment_Console {

	static WebDriver driver;

	public static void main(String[] args) {
		System.out.println("Selenium Automation Testing");
		driver = new FirefoxDriver();
  
		driver.get("https://www.facebook.com/");
	}

}
