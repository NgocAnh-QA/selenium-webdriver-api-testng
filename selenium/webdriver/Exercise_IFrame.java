package webdriver;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_IFrame {

	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fixed_Popup() {
		driver.get("https://kyna.vn/");

		sleepInSecond(5);
		driver.findElement(By.xpath("//a[@title='Close']")).click();

		// Switch to Facebook iframe
		driver.switchTo().frame(driver.findElement(By.cssSelector(".face-content>iframe")));
		sleepInSecond(3);

		// Verify 169 likes
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='lfloat']//div[text()='169K likes']")).getText(),
				"169K likes");

		// Switch to default content
		sleepInSecond(3);
		driver.switchTo().defaultContent();

		// Check Search textbox displayed
		sleepInSecond(2);
		Assert.assertTrue(driver.findElement(By.cssSelector("#live-search-bar")).isDisplayed());

		// Switch to webchat iframe
		sleepInSecond(3);
		driver.switchTo().frame(driver.findElement(By.cssSelector("#cs_chat_iframe")));

		// Input text to textarea
		sleepInSecond(2);
		driver.findElement(By.xpath("//textarea")).sendKeys("automation");
		action.sendKeys(Keys.ENTER).perform();
		sleepInSecond(5);
		
		// Verify form display
		Assert.assertTrue(driver.findElement(By.xpath("//form")).isDisplayed());
		

		// Switch to default content
		driver.switchTo().defaultContent();
		
		// Tìm kiếm khóa học Java 
		sleepInSecond(5);
		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("Java");
		driver.findElement(By.cssSelector(".search-button")).click();
		
		// Verify kết quả tìm kiếm có chứa từ khóa Java
		sleepInSecond(5);
		Assert.assertEquals(driver.findElement(By.cssSelector(".menu-heading h1")).getText(), "'Java'");
		
	}

	@AfterClass
	public void afterClass() {
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
