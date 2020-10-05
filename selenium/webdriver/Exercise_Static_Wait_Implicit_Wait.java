package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_Static_Wait_Implicit_Wait {
	WebDriver driver;
	/*
	 * Các điều kiện để check trạng thái Element: 1) Visible: điều kiện bắt buộc :
	 * có trong DOM + hiển thị trên UI 2) Invisible: điều kiện bắt buộc : không hiển
	 * thị trên UI 3) Presence: điều kiện bắt buộc : có trong DOM 4) Staleness: điều
	 * kiện bắt buộc : không có trong DOM
	 */

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
	}

	
	public void TC_01_Static_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button")).click();
		System.out.println("Start: " + getDate());
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed());
		System.out.println("Found: " + getDate());
		sleepInSecond(10);
		System.out.println("End: " + getDate());
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("#finish h4")).getText(), "Hello World!");
	}

	
	
	@Test
	public void TC_02_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		driver.findElement(By.xpath("//button")).click();
		
		System.out.println("Start: " + getDate());
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed());
		
		System.out.println("End: " + getDate());
		Assert.assertTrue(driver.findElement(By.cssSelector("#finish h4")).isDisplayed());
		Assert.assertEquals(driver.findElement(By.cssSelector("#finish h4")).getText(), "Hello World!");
		
	}
	@AfterClass
	public void afterClass() {
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getDate() {
		return java.time.LocalTime.now().toString();
	}
}
