package webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_01_Check_Environment_Console {

	static WebDriver driver;

	@BeforeMethod
	public void setUp() {
		System.out.println("Step Set Up");
	}
	
	@Test
	public void TC_01() {
		System.out.println("This is Test Case 01");
	}
	
	@Test
	public void TC_02() {
		System.out.println("This is Test Case 02");
		
	}
	
	@Test
	public void TC_03() {
		System.out.println("This is Test Case 03");
		
	}
	
	@AfterMethod
	public void finish() {
		System.out.println("Step Ends");
	}

}
