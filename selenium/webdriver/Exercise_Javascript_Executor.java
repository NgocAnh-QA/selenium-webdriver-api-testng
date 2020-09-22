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
		navigateToUrlByJS("http://live.demoguru99.com/");
		sleepInSecond(2);

		String domain = (String) getDomainByJS();
		Assert.assertEquals(domain, "live.demoguru99.com");
		sleepInSecond(2);

		String url = (String) getUrlByJS();
		Assert.assertEquals(url, "http://live.demoguru99.com/");
		sleepInSecond(2);

		clickElementByJS("//a[text()='Mobile']");
		sleepInSecond(2);

		clickElementByJS(
				"//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']//button/span");
		sleepInSecond(2);

		String status = (String) getInnerTextByJS();
		Assert.assertTrue(status.contains("Samsung Galaxy was added to your shopping cart."));
		sleepInSecond(2);

		navigateToUrlByJS("http://live.demoguru99.com/index.php/customer-service/");

		status = (String) getTitleByJS();
		Assert.assertEquals(status, "Customer Service");
		sleepInSecond(2);

		scrollToBottomPageByJS();
		sleepInSecond(3);

		status = (String) getInnerTextByJS();
		Assert.assertTrue(status.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));
		sleepInSecond(3);

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		sleepInSecond(2);

		status = (String) getDomainByJS();
		Assert.assertEquals(status, "demo.guru99.com");
		sleepInSecond(2);

	}

	@Test
	public void TC_02_Remove_Attribute() {
		String username = "mngr282369";
		String password = "naqemaj";

		navigateToUrlByJS("http://demo.guru99.com/v4");

		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		clickElementByJS("//a[text()='New Customer']");

		driver.findElement(By.name("name")).sendKeys("Automation Testing");
		driver.findElement(By.name("rad1")).click();

		removeAttributeByJS("//input[@name='dob']", "type");
		sleepInSecond(3);
		driver.findElement(By.name("dob")).sendKeys("09/21/2020");

		driver.findElement(By.name("addr")).sendKeys("Sai Gon");
		driver.findElement(By.name("city")).sendKeys("Viet Nam");
		driver.findElement(By.name("state")).sendKeys("Quan nam");
		driver.findElement(By.name("pinno")).sendKeys("123456");
		driver.findElement(By.name("telephoneno")).sendKeys("0123456789");
		driver.findElement(By.name("emailid")).sendKeys("autotest" + new Random().nextInt(999) + "@gmail.com");
		driver.findElement(By.name("password")).sendKeys("654321");

		driver.findElement(By.name("sub")).click();

		sleepInSecond(5);
		String status = driver.findElement(By.cssSelector(".heading3")).getText();
		Assert.assertTrue(status.contains("Successfully"));

	}

	@Test
	public void TC_03_Create_An_Account() {
		navigateToUrlByJS("http://live.demoguru99.com/");
		sleepInSecond(2);
		
		clickElementByJS("//div[@id='header-account']//a[text()='My Account']");
		clickElementByJS("//a[@title='Create an Account']");
		
		sendKeyToElementByJS("//input[@id='firstname']", "Automation");
		sendKeyToElementByJS("//input[@id='lastname']", "Testing");
		sendKeyToElementByJS("//input[@id='email_address']", "qaauto" + new Random().nextInt(9999) + "@gmai.com");
		sendKeyToElementByJS("//input[@id='password']", "123456");
		sendKeyToElementByJS("//input[@id='confirmation']", "123456");

		clickElementByJS("//button[@title='Register']");

		
		String status = (String)getInnerTextByJS();
		Assert.assertTrue(status.contains("Thank you for registering with Main Website Store."));

		clickElementByJS("//div[@id='header-account']//a[text()='Log Out']");

		Assert.assertTrue(driver
				.findElement(By.xpath(
						"//p[contains(.,'You have logged out and will be redirected to our homepage in 5 seconds.')]"))
				.isDisplayed());
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

	public void navigateToUrlByJS(String Url) {
		jsExecutor.executeScript("window.location='" + Url + "'");
	}

	public Object getDomainByJS() {
		return jsExecutor.executeScript("return document.domain");
	}

	public Object getTitleByJS() {
		return jsExecutor.executeScript("return document.title");
	}

	public Object getUrlByJS() {
		return jsExecutor.executeScript("return document.URL");
	}

	public Object getInnerTextByJS() {
		return jsExecutor.executeScript("return document.documentElement.innerText");

	}

	public Object scrollToElementByJS(String xpath) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return jsExecutor.executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public Object scrollToBottomPageByJS() {
		return jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object removeAttributeByJS(String xpath, String attribute) {
		WebElement element = driver.findElement(By.xpath(xpath));
		return jsExecutor.executeScript("arguments[0].removeAttribute('" + attribute + "')", element);
	}
}
