package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise_WebElement {
	WebDriver driver;

	@BeforeMethod
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

	}

	@Test
	public void TC_01_CheckDisplayed() throws InterruptedException {
		// Step 02
		WebElement mail = driver.findElement(By.id("mail"));
		WebElement education = driver.findElement(By.id("edu"));
		WebElement age = driver.findElement(By.id("under_18"));

		if (mail.isDisplayed()) {
			mail.sendKeys("Automation Testing");
			Thread.sleep(2000);
		}

		if (education.isDisplayed()) {
			education.sendKeys("Automation Testing");
			Thread.sleep(2000);
		}

		if (age.isDisplayed()) {
			age.click();
			Thread.sleep(2000);
		}

	}

	@Test
	public void TC_02_CheckEnabled() {
		// Step 02
		String textEnabled = "Element is enabled";
		String textDisabled = "Element is disabled";
		if (driver.findElement(By.id("mail")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("edu")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("under_18")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.name("user_job2")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.name("slider-1")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("password")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("bio")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.name("user_job3")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

		if (driver.findElement(By.name("slider-2")).isEnabled()) {
			System.out.println(textEnabled);
		} else System.out.println(textDisabled);

	}

	@AfterMethod
	public void afterClass() {
		driver.quit();
	}
}
