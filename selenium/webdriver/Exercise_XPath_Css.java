package webdriver;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_XPath_Css {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	 @Test
	public void TC_01_Login_Empty_Email_And_Password() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		// Step 03
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("pass")).sendKeys("");

		// Step 04
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Step 05
		String errorMessageMail = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(errorMessageMail, "This is a required field.");

		String errorMessagePass = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(errorMessagePass, "This is a required field.");
	}

	 @Test
	public void TC_02_Login_With_Invalid_Email() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		// Step 03
		driver.findElement(By.id("email")).sendKeys("123434234@12312.123123");
		driver.findElement(By.id("pass")).sendKeys("123456");

		// Step 04
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Step 05
		String errorMessage = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errorMessage, "Please enter a valid email address. For example johndoe@domain.com.");
	}

	 @Test
	public void TC_03_Login_With_Invalid_Password() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		// Step 03
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123");

		// Step 04
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Step 05
		String errorMessage = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(errorMessage, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	
	 @Test
	public void TC_04_Login_With_Incorrect_Password() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		// Step 03
		driver.findElement(By.id("email")).sendKeys("automation@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123123");

		// Step 04
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Step 05
		String errorMessage = driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]"))
				.getText();
		Assert.assertEquals(errorMessage, "Invalid login or password.");
	}

	@Test
	public void TC_05_Login_With_Valid_Email_And_Password() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);

		// Step 03
		driver.findElement(By.id("email")).sendKeys("automation_13@gmail.com");
		driver.findElement(By.id("pass")).sendKeys("123123");

		// Step 04
		driver.findElement(By.xpath("//button[@id='send2']")).click();

		// Step 05
		// Verify MY DASHBOARD
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());

		// Verify Hello, Automation Testing!
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='welcome-msg']//strong")).isDisplayed());

		// Verify Automation Testing + automation_13@gmail.com
		String fullname = driver
				.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'Automation Testing')]"))
				.getText();
		Assert.assertTrue(fullname.contains("Automation Testing"));
		Assert.assertTrue(fullname.contains("automation_13@gmail.com"));

		// Step 06
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[contains(text(), 'Account')]")).click();
		driver.findElement(By.xpath("//div[@class='links']//a[contains(text(),'Log Out')]")).click();
	}

	@Test
	public void TC_06_Create_A_New_Account() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		Thread.sleep(2000);
		
		// Step 03
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		// Step 04
		String firstName = "Automation";
		String lastName = "Testing";
		String email = "ngocanhqa"+randomMail()+"@gmail.com"; 
		String password = "123456";
		String confirmPassword = "123456";
		
		driver.findElement(By.id("firstname")).sendKeys(firstName);
		driver.findElement(By.id("lastname")).sendKeys(lastName);
		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(confirmPassword);
		
		// Step 05
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		
		// Step 06
		Assert.assertTrue(driver.findElement(By.xpath("//ul[@class='messages']//span")).isDisplayed());
		
		// Step 07
		String fullname = driver
				.findElement(By.xpath("//div[@class='box-content']//p[contains(text(),'"+ firstName + " " + lastName +"')]")).getText();
		Assert.assertTrue(fullname.contains(firstName + " " + lastName));
		Assert.assertTrue(fullname.contains(email));
		
		// Step 08
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[contains(text(), 'Account')]")).click();
		driver.findElement(By.xpath("//div[@class='links']//a[contains(text(),'Log Out')]")).click();
		
		// Step 09
		String homePage = driver.findElement(By.xpath("//p[@class='welcome-msg']")).getText();
		Assert.assertEquals(homePage, "DEFAULT WELCOME MSG!");
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}
	
	public static String randomMail() {
		String timeNow = java.time.LocalTime.now().toString();
		return timeNow.replace(".", ":").replace(":", "");
	}
}
