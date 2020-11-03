package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_Alert {

	WebDriver driver;
  
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	@Test
	public void TC_Confirm_Alert() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Step 02
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();

		// Step 03
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Thread.sleep(2000);
		Assert.assertEquals(textOnAlert, "I am a JS Confirm");
		alert.dismiss();

		// Step 04
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You clicked: Cancel");

	}

	@Test
	public void TC_Accept_Alert() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Step 02
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();

		// Step 03
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS Alert");
		Thread.sleep(2000);
		alert.accept();

		// Step 04
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(),
				"You clicked an alert successfully");
	}

	@Test
	public void TC_Prompt_Alert() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");

		// Step 02
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();

		// Step 03
		Alert alert = driver.switchTo().alert();
		String textOnAlert = alert.getText();
		Assert.assertEquals(textOnAlert, "I am a JS prompt");

		Thread.sleep(2000);
		String inputAlert = "Automation Testing";
		Thread.sleep(2000);
		alert.sendKeys(inputAlert);
		alert.accept();

		// Step 04
		Assert.assertEquals(driver.findElement(By.cssSelector("#result")).getText(), "You entered: " + inputAlert);
	}

	@Test
	public void TC_Authentication_Alert_Link() throws InterruptedException {
		String username = "admin";
		String password = "admin";
		driver.get("https://the-internet.herokuapp.com/");

		Thread.sleep(3000);
		WebElement element = driver.findElement(By.xpath("//a[text()='Basic Auth']"));
		String url = element.getAttribute("href");
		Thread.sleep(3000);

		driver.get(getUrlUsernameAndPassword(username, password, url));
		Thread.sleep(3000);

		Assert.assertEquals(driver.findElement(By.cssSelector(".example p")).getText(),
				"Congratulations! You must have the proper credentials.");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public String getUrlUsernameAndPassword(String username, String password, String url) {
		String[] a = url.split("//");

		return a[0] + "//" + username + ":" + password + "@" + a[1];
	}

}
