package webdriver;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Exercise_WebElement {
	WebDriver driver;

	By mail = By.id("mail");
	By education = By.id("edu");
	By ageUnder18 = By.id("under_18");
	By job1 = By.id("job1");
	By job2 = By.name("user_job2");
	By interestDev = By.id("development");
	By slider1 = By.name("slider-1");
	By password = By.id("password");
	By ageDisable = By.id("radio-disabled");
	By bio = By.id("bio");
	By job3 = By.name("user_job3");
	By slider2 = By.name("slider-2");
	By interestDisable = By.id("check-disbaled");
	By java = By.id("java");

	@BeforeClass
	public void beforeClass() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_CheckDisplayed() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

		// Step 02 + 03
		if (isElementDisplayed(mail)) {
			senKeyToElement(mail, "Automation Testing");
			Thread.sleep(2000);
		}

		if (isElementDisplayed(education)) {
			senKeyToElement(education, "Automation Testing");
			Thread.sleep(2000);
		}

		if (isElementDisplayed(ageUnder18)) {
			clickOnElement(ageUnder18);
			Thread.sleep(2000);
		}
	}

	@Test
	public void TC_02_CheckEnabled() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

		// Step 02
		Assert.assertTrue(isElementEnabled(mail));
		Assert.assertTrue(isElementEnabled(ageUnder18));
		Assert.assertTrue(isElementEnabled(education));
		Assert.assertTrue(isElementEnabled(job1));
		Assert.assertTrue(isElementEnabled(job2));
		Assert.assertTrue(isElementEnabled(interestDev));
		Assert.assertTrue(isElementEnabled(slider1));
		
		// Step 03
		Assert.assertFalse(isElementEnabled(password));
		Assert.assertFalse(isElementEnabled(ageDisable));
		Assert.assertFalse(isElementEnabled(bio));
		Assert.assertFalse(isElementEnabled(job3));
		Assert.assertFalse(isElementEnabled(interestDisable));
		Assert.assertFalse(isElementEnabled(slider2));
	}

	@Test
	public void TC_03_CheckSelected() throws InterruptedException {
		// Step 01
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(2000);

		// Step 02
		clickOnElement(ageUnder18);
		Thread.sleep(2000);

		clickOnElement(java);
		Thread.sleep(2000);

		// Step 03
		Assert.assertTrue(isElementSelected(ageUnder18));
		Assert.assertTrue(isElementSelected(java));

		// Step 04
		clickOnElement(java);
		Thread.sleep(4000);

		// Step 05
		Assert.assertFalse(isElementSelected(java));
		Thread.sleep(4000);
	}

	
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
		password.sendKeys("00");
		if (driver.findElement(By.xpath(".//*[@class='number-char completed']")).isDisplayed()) {
			System.out.println("Parameter Number is valid");
			password.clear();
		}
		Thread.sleep(3000);

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

		password.sendKeys("@#$");
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
		Assert.assertTrue(!btnCreate.isEnabled());

		password.sendKeys("Ng0c@nh99");
		if (btnCreate.isEnabled()) {
			System.out.println("Password is valid");
		} else {
			System.out.println("Password is invalid");
		}

		// Step 05 kiểm tra checkbox được chọn sau khi click thành công
		Thread.sleep(2000);
		chkb.click();
		if (chkb.isSelected()) {
			System.out.println("Checkbox is checked");
			Thread.sleep(2000);
			btnCreate.click();
		} else {
			System.out.println("Checkbox is not checked");
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public boolean isElementDisplayed(By by) {
		if (driver.findElement(by).isDisplayed()) {
			System.out.println("Element is displayed: " + by);
			return true;
		} else {
			System.out.println("Element is not displayed: " + by);
			return false;
		}
	}

	public void senKeyToElement(By by, String text) {
		driver.findElement(by).sendKeys(text);
	}

	public void clickOnElement(By by) {
		driver.findElement(by).click();
	}
	
	public boolean isElementEnabled(By by) {
		if(driver.findElement(by).isEnabled()) {
			System.out.println("Element is enabled: "+ by);
			return true;
		}else {
			System.out.println("Element is not enabled: "+ by);
			return false;
		}
		
	}
	
	public boolean isElementSelected(By by) {
		if(driver.findElement(by).isSelected()) {
			System.out.println("Element is selected: "+ by);
			return true;
		}else {
			System.out.println("Element is not selected: "+ by);
			return false;
		}
		
	}

}
