package webdriver;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Nhap {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test
	public void TC_name() {
		driver.get("https://automationfc.github.io/basic-form/");
		String name = driver.findElement(By.xpath("//h5[@id='hacker']")).getText();
		
		Assert.assertTrue(name.contains("I'm a Hacker"));
		
	}
	@Test
	public void TC_age() {
		driver.get("https://automationfc.github.io/basic-form/");
		String age = driver.findElement(By.xpath("//h5[@id='hacker']//span[@class='year']")).getText();
	
		Assert.assertTrue(age.contains("18 years old"));
	
	}
	@Test
	public void TC_address() {
		driver.get("https://automationfc.github.io/basic-form/");
		String address = driver.findElement(By.xpath("//h5[@id='hacker']")).getText();
		
		Assert.assertTrue(address.contains("living in Viet Nam"));
	}
	@Test
	public void TC_date() {
		driver.get("https://automationfc.github.io/basic-form/");
		String date = driver.findElement(By.xpath("//h5[@id='hacker']//span[@class='time']")).getText();
		
		Assert.assertTrue(date.contains("15/03/2020"));
		
	}
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
