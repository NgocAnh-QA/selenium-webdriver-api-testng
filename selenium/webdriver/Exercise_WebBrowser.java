package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise_WebBrowser {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_VerifyUrl() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Step 03
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		// Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Step 05
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_VerifyTitle() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Step 03
		assertEquals(driver.getTitle(), "Customer Login");

		// Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Step 05
		assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_03_NavigateFunction() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		// Step 03
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Step 04
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/create/");

		// Step 05
		driver.navigate().back();

		// Step 06
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");

		// Step 07
		driver.navigate().forward();

		// Step 08
		assertEquals(driver.getTitle(), "Create New Customer Account");

	}

	@Test
	public void TC_04_GetPageSourceCode() throws InterruptedException {
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);

		// Step 02
		driver.findElement(By.xpath("//div[@class='footer']//a[@title='My Account']")).click();
		String sourceCode = driver.getPageSource();
		// Step 03
		assertTrue(sourceCode.contains("Login or Create an Account"));

		// Step 04
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		// Step 05
		assertTrue(sourceCode.contains("Create an Account"));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
