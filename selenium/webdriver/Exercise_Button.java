package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_Button {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void Step_01() {
		driver.get("https://www.fahasa.com/customer/account/create");
	}
	
	@Test
	public void Step_02() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void Step_03() throws InterruptedException {
		Assert.assertTrue(!driver.findElement(By.xpath("//div[@class='fhs-input-box']//button[@title='Đăng nhập']")).isEnabled());
		Thread.sleep(2000);
	}
	
	@Test
	public void Step_04() throws InterruptedException {
		driver.findElement(By.id("login_username")).sendKeys("0908321654");
		Thread.sleep(2000);
		
		driver.findElement(By.id("login_password")).sendKeys("123456789");
		Thread.sleep(2000);
	}
	
	
	
	@Test
	public void Step_05() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement enable = driver.findElement(By.xpath("//div[@class='fhs-input-box']//button[@title='Đăng nhập']"));
		js.executeScript("arguments[0].enabled", enable);
	}
	
	@Test
	public void Step_06() throws InterruptedException {
		driver.navigate().refresh();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void Step_07() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement enable = driver.findElement(By.xpath("//div[@class='fhs-input-box']//button[@title='Đăng nhập']"));
		js.executeScript("arguments[0].removeAttribute('disabled')", enable);
		Thread.sleep(2000);
	}
	
	@Test
	public void Step_08() throws InterruptedException {
		driver.findElement(By.xpath("//div[@class='fhs-input-box']//button[@title='Đăng nhập']")).click();;
		Thread.sleep(2000);
	}
	
	@Test
	public void Step_09() throws InterruptedException {
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Số điện thoại/Email']/following-sibling::div[contains(.,'Thông tin')]")).getText(), "Thông tin này không thể để trống");
		Assert.assertEquals(driver.findElement(By.xpath("//label[text()='Mật khẩu']/following-sibling::div[contains(.,'Thông tin')]")).getText(), "Thông tin này không thể để trống");
		Thread.sleep(2000);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
