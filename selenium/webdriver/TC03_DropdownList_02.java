package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class TC03_DropdownList_02 {
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
		driver.get("https://demo.nopcommerce.com/register");
	}
	
	@Test
	public void Step_02() {
		driver.findElement(By.xpath("//a[text()='Register']")).click();
	}
	
	@Test
	public void Step_03() throws InterruptedException {
		driver.findElement(By.id("gender-female")).click(); 
		Thread.sleep(2000);
		
		driver.findElement(By.id("FirstName")).sendKeys("Automation");
		Thread.sleep(2000);
		
		driver.findElement(By.id("LastName")).sendKeys("Testing");
		Thread.sleep(2000);
		
		Select selectDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
		selectDay.selectByValue("1");
		Assert.assertEquals(selectDay.getOptions().size(), 32);
		Thread.sleep(2000);
		
		Select selectMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
		selectMonth.selectByVisibleText("May");
		Assert.assertEquals(selectMonth.getOptions().size(), 13);
		Thread.sleep(2000);
		
		Select selectYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
		selectYear.selectByValue("1980");
		Assert.assertEquals(selectYear.getOptions().size(), 112);
		Thread.sleep(2000);
		
		driver.findElement(By.id("Email")).sendKeys("qa_auto123@gmail.com");
		Thread.sleep(2000);
		
		driver.findElement(By.id("Password")).sendKeys("qa123456");
		Thread.sleep(2000);

		driver.findElement(By.id("ConfirmPassword")).sendKeys("qa123456");
		Thread.sleep(2000);
		
	}
	
	@Test
	public void Step_04() throws InterruptedException {
		driver.findElement(By.id("register-button")).click();
		Thread.sleep(2000);
	}
	
	@Test
	public void Step_05() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	}
	
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
