package webdriver;

import java.util.concurrent.TimeUnit;

import javax.sound.midi.Soundbank;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise_WebElement {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
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

	@Test
	public void TC_02_CheckEnabled() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

		// Step 02
		String textEnabled =  "is enabled";
		String textDisabled = "is disabled";

		WebElement mail = driver.findElement(By.id("mail"));
		WebElement age = driver.findElement(By.id("under_18"));
		WebElement edu = driver.findElement(By.id("edu"));
		WebElement job1 = driver.findElement(By.id("job1"));
		WebElement job2 = driver.findElement(By.name("user_job2"));
		WebElement interestDev = driver.findElement(By.id("development"));
		WebElement slider1 = driver.findElement(By.name("slider-1"));
		WebElement password = driver.findElement(By.id("password"));
		WebElement ageDisable = driver.findElement(By.id("radio-disabled"));
		WebElement bio = driver.findElement(By.id("bio"));
		WebElement job3 = driver.findElement(By.name("user_job3"));
		WebElement slider2 = driver.findElement(By.name("slider-2"));
		WebElement interestDisable = driver.findElement(By.id("check-disbaled"));
		
		
		if (mail.isEnabled()) {
			System.out.println("Mail " + textEnabled);
		} else
			System.out.println("Mail " + textDisabled);

		if (edu.isEnabled()) {
			System.out.println("Education " + textEnabled);
		} else
			System.out.println("Education " + textDisabled);

		if (age.isEnabled()) {
			System.out.println("Age Under 18 " + textEnabled);
		} else
			System.out.println("Age Under 18 "+ textDisabled);
		
		if (job1.isEnabled()) {
			System.out.println("Job 1 " + textEnabled);
		} else
			System.out.println("Job 1 " + textDisabled);

		if (job2.isEnabled()) {
			System.out.println("Job 2 " + textEnabled);
		} else
			System.out.println("Job 2 " + textDisabled);
		
		if (interestDev.isEnabled()) {
			System.out.println("Development " +textEnabled);
		} else
			System.out.println("Development " + textDisabled);

		if (slider1.isEnabled()) {
			System.out.println("Slider 1 " + textEnabled);
		} else
			System.out.println("Slider 1 " +textDisabled);
		
		if (password.isEnabled()) {
			System.out.println("Password " + textEnabled);
		} else
			System.out.println("Password "+ textDisabled);

		if (ageDisable.isEnabled()) {
			System.out.println("Age Disable " + textEnabled);
		} else
			System.out.println("Age Disable " + textDisabled);

		if (bio.isEnabled()) {
			System.out.println("Biography " + textEnabled);
		} else
			System.out.println("Biography " +textDisabled);

		if (job3.isEnabled()) {
			System.out.println("Job 3 " + textEnabled);
		} else
			System.out.println("Job 3 " + textDisabled);

		if (interestDisable.isEnabled()) {
			System.out.println("Interest Disabled "+ textEnabled);
		} else
			System.out.println("Interest Disabled " + textDisabled);

		if (slider2.isEnabled()) {
			System.out.println("Slider 2 "+ textEnabled);
		} else
			System.out.println("Slider 2 "+ textDisabled);
	}

	@Test
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
		WebElement chkb = driver.findElement(By.id("marketing_newsletter"));

		// Step 02
		email.sendKeys("qc001hcm@gmail.com");
		Thread.sleep(2000);
		username.sendKeys("ngocanhqc99");
		Thread.sleep(2000);

		// Step 03 + 04
		password.sendKeys("na");
		if (driver.findElement(By.xpath(".//*[@class='lowercase-char completed']")).isDisplayed()) {
			System.out.println("Parameter Lower Case is valid");
			password.clear();
		}
		Thread.sleep(3000);

		password.sendKeys("QC");
		if (driver.findElement(By.xpath(".//*[@class='uppercase-char completed']")).isDisplayed()) {
			System.out.println("Parameter Upper Case is valid");
			password.clear();
		}
		Thread.sleep(3000);

		password.sendKeys("00");
		if (driver.findElement(By.xpath(".//*[@class='number-char completed']")).isDisplayed()) {
			System.out.println("Parameter Number is valid");
			password.clear();
		}
		Thread.sleep(3000);

		password.sendKeys("@");
		if (driver.findElement(By.xpath(".//*[@class='special-char completed']")).isDisplayed()) {
			System.out.println("Parameter Character is valid");
			password.clear();
		}
		Thread.sleep(3000);

		password.sendKeys("ngocanhhh");
		if (driver.findElement(By.xpath(".//*[@class='8-char completed']")).isDisplayed()) {
			System.out.println("Number of letter is valid");
			password.clear();
		}
		Thread.sleep(3000);

		password.sendKeys("Ng0c@nh99");
		if (btnCreate.isEnabled()) {
			System.out.println("Password is valid");
		}
		else {
			System.out.println("Password is invalid");
		}

		// Step 05 kiểm tra checkbox được chọn sau khi click thành công
		Thread.sleep(2000);
		chkb.click();
		if (chkb.isSelected()) {
			System.out.println("Checkbox is checked");
			Thread.sleep(2000);
			btnCreate.click();
		}
		else {
			System.out.println("Checkbox is not checked");
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}
}
