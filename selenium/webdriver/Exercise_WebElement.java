package webdriver;

import java.util.concurrent.TimeUnit;

import javax.sound.midi.Soundbank;

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

	@Test(enabled = false)
	public void TC_01_CheckDisplayed() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

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

	@Test(enabled = false)
	public void TC_02_CheckEnabled() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

		// Step 02
		String textEnabled = "Element is enabled";
		String textDisabled = "Element is disabled";

		if (driver.findElement(By.id("mail")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("edu")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("under_18")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("job1")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.name("user_job2")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("development")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.name("slider-1")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("password")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("radio-disabled")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("bio")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.name("user_job3")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.id("check-disbaled")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);

		if (driver.findElement(By.name("slider-2")).isEnabled()) {
			System.out.println(textEnabled);
		} else
			System.out.println(textDisabled);
	}

	@Test(enabled = false)
	public void TC_03_CheckSelected() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

		WebElement age = driver.findElement(By.id("under_18"));
		WebElement develop = driver.findElement(By.id("development"));

		// Step 02
		age.click();
		Thread.sleep(4000);

		develop.click();
		Thread.sleep(4000);

		// Step 03
		if (age.isSelected()) {
			System.out.println("Age is selected");
		}

		// Step 04
		develop.click();
		Thread.sleep(4000);

		// Step 05
		if (!develop.isSelected()) {
			System.out.println("Development is unselected");
		}
		Thread.sleep(4000);
	}

	@Test
	public void TC_04_RegisterFunction() throws InterruptedException {
		// Step 01
		driver.get("https://login.mailchimp.com/signup/");
		Thread.sleep(2000);

		WebElement email = driver.findElement(By.id("email"));
		WebElement username = driver.findElement(By.id("new_username"));
		WebElement password = driver.findElement(By.id("new_password"));
		WebElement btnCreate = driver.findElement(By.id("create-account"));
		WebElement chkb	= driver.findElement(By.id("marketing_newsletter"));

		// Step 02
		email.sendKeys("qc001hcm@gmail.com");
		Thread.sleep(2000);
		username.sendKeys("ngocanh02");
		Thread.sleep(2000);

		// Step 03 + 04
		password.sendKeys("01");
		Thread.sleep(3000);
		password.sendKeys("na");
		Thread.sleep(3000);
		password.sendKeys("QC");
		Thread.sleep(3000);
		password.sendKeys(".");
		Thread.sleep(3000);
		if (!btnCreate.isEnabled()) {
			System.out.println("Password invalid");
		}
		password.sendKeys("hcm");
		Thread.sleep(3000);
		
		// Step 05  kiểm tra checkbox được chọn sau khi click thành công
		btnCreate.click();
		if (chkb.isSelected()) {
			System.out.println("Checkbox is selected");
		}
		 
		

	}

	@AfterMethod
	public void afterClass() {
		driver.quit();
	}
}
