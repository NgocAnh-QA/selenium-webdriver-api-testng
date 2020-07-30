package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise_XPath_Css {
	WebDriver driver;

	@BeforeMethod
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);
		
		// Step 02
		driver.findElement(By.xpath("//a[.='My Account']"));
		Thread.sleep(2000);
	}
	
	@Test
	public void LoginEmptyEmailAndPassword() {
		// Step 03 + 04
		driver.findElement(By.xpath("//button[@id='send2']")).click();
		
		// Step 05
		driver.findElement(By.xpath("//div[@id='advice-required-entry-email']']")).isDisplayed();
		driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).isDisplayed();
	}
	@AfterMethod
	public void afterClass() {
		driver.quit();
	}
}
