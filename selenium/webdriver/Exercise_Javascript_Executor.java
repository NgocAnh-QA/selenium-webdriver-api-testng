package webdriver;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_Javascript_Executor {

	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();

		jsExecutor = (JavascriptExecutor) driver;
	}

	@Test
	public void TC_01_Javascript_Executor() {
		jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");
		sleepInSecond(2);

		String domain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.demoguru99.com");
		sleepInSecond(2);

		String url = (String) jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(url, "http://live.demoguru99.com/");
		sleepInSecond(2);

		WebElement element = driver.findElement(By.xpath("//a[text()='Mobile']"));
		jsExecutor.executeScript("arguments[0].click()", element);
		sleepInSecond(2);

		element = driver.findElement(By.xpath(
				"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button/span"));
		jsExecutor.executeScript("arguments[0].click()", element);
		sleepInSecond(2);

		String status = jsExecutor.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(status.contains("Samsung Galaxy was added to your shopping cart."));
		sleepInSecond(2);

		jsExecutor.executeScript("window.location='http://live.demoguru99.com/index.php/customer-service/'");
		status = jsExecutor.executeScript("return document.title").toString();
		Assert.assertEquals(status, "Customer Service");
		sleepInSecond(2);

		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		sleepInSecond(3);

		status = jsExecutor.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(status.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		sleepInSecond(3);

		jsExecutor.executeScript("window.location='http://demo.guru99.com/v4/'");
		sleepInSecond(2);
		status = jsExecutor.executeScript("return document.domain").toString();
		Assert.assertEquals(status, "demo.guru99.com");
		sleepInSecond(2);

	}

	@Test
	public void TC_02_Remove_Attribute() {
		String username = "mngr282369";
		String password = "naqemaj";

		jsExecutor.executeScript("window.location='http://demo.guru99.com/v4'");

		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		WebElement element = driver.findElement(By.xpath("//a[text()='New Customer']"));
		jsExecutor.executeScript("arguments[0].click()", element);

		driver.findElement(By.name("name")).sendKeys("Automation Testing");
		driver.findElement(By.name("rad1")).click();

		element = driver.findElement(By.name("dob"));
		jsExecutor.executeScript("arguments[0].removeAttribute('type')", element);
		element.sendKeys("09/09/2020");

		driver.findElement(By.name("addr")).sendKeys("Sai Gon");
		driver.findElement(By.name("city")).sendKeys("Viet Nam");
		driver.findElement(By.name("state")).sendKeys("Quan nam");
		driver.findElement(By.name("pinno")).sendKeys("123456");
		driver.findElement(By.name("telephoneno")).sendKeys("0123456789");
		driver.findElement(By.name("emailid")).sendKeys("autotest" + new Random().nextInt(999) + "@gmail.com");
		driver.findElement(By.name("password")).sendKeys("654321");

		driver.findElement(By.name("sub")).click();

		String status = driver.findElement(By.cssSelector(".heading3")).getText();
		Assert.assertTrue(status.contains("Successfully"));

	}

	@Test
	public void TC_03_Create_An_Account() {
		jsExecutor.executeScript("window.location='http://live.demoguru99.com/'");
		sleepInSecond(2);

		WebElement element = driver.findElement(By.xpath("//div[@id='header-account']//a[text()='My Account']"));
		jsExecutor.executeScript("arguments[0].click()", element);

		element = driver.findElement(By.xpath("//a[@title='Create an Account']"));
		jsExecutor.executeScript("arguments[0].click()", element);

		sendKeyToElementByJS("//input[@id='firstname']", "Automation");
		sendKeyToElementByJS("//input[@id='lastname']", "Testing");
		sendKeyToElementByJS("//input[@id='email_address']", "qaauto" + new Random().nextInt(9999) + "@gmai.com");
		sendKeyToElementByJS("//input[@id='password']", "123456");
		sendKeyToElementByJS("//input[@id='confirmation']", "123456");

		clickElementByJS("//button[@title='Register']");

		String status = jsExecutor.executeScript("return document.documentElement.innerText").toString();
		Assert.assertTrue(status.contains("Thank you for registering with Main Website Store."));

		clickElementByJS("//div[@id='header-account']//a[text()='Log Out']");

		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(.,'You have logged out and will be redirected to our homepage in 5 seconds.')]")).isDisplayed());

	}

	@AfterClass
	public void afterClass() {
		 driver.quit();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void sendKeyToElementByJS(String xpath, String text) {
		WebElement element = driver.findElement(By.xpath(xpath));
		jsExecutor.executeScript("arguments[0].setAttribute('value','" + text + "')", element);
	}

	public void clickElementByJS(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		jsExecutor.executeScript("arguments[0].click()", element);
	}
}
