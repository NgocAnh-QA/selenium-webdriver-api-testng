package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_DropdownList_Default_TC_03 {
	WebDriver driver;
	String timeNow = java.time.LocalTime.now().toString().replace(".", ":").replace(":", "");
	
	By FemaleBtn = By.id("gender-female");
	By firstNameTxt = By.id("FirstName");
	By lastNameTxt = By.id("LastName");
	By dateOfBirthDrd = By.name("DateOfBirthDay");
	By monthOfBirthDrd = By.name("DateOfBirthMonth");
	By yearOfBirthDrd = By.name("DateOfBirthYear");
	By emaildTxt = By.id("Email");
	By passwordTxt = By.id("Password");
	By confirmPasswordTxt = By.id("ConfirmPassword");
	
	String dateBirth = "3";
	String monthBirth = "September";
	String yearBirth = "2020";
	
	

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
		clickOnElement(FemaleBtn);
		
		sentkeyToElement(firstNameTxt, "Automation");

		sentkeyToElement(lastNameTxt, "Testing");
		
		selectElementByText(dateOfBirthDrd, dateBirth);
		assertSizeSelect(dateOfBirthDrd, 32);

		selectElementByText(monthOfBirthDrd, monthBirth);
		assertSizeSelect(monthOfBirthDrd, 13);

		selectElementByText(yearOfBirthDrd, yearBirth);
		assertSizeSelect(yearOfBirthDrd, 112);

		sentkeyToElement(emaildTxt, "qa_auto"+ timeNow + "@gmail.com");
		sentkeyToElement(passwordTxt, "qa123456");
		sentkeyToElement(confirmPasswordTxt, "qa123456");
	}

	@Test
	public void Step_04() throws InterruptedException {
		driver.findElement(By.id("register-button")).click();
		Thread.sleep(2000);
	}

	@Test
	public void Step_05() throws InterruptedException {
		Thread.sleep(2000);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(),
				"Your registration completed");
	}
	
	@Test
	public void Step_06() {
		driver.findElement(By.xpath("//div[@class='header']//a[text()='My account']")).click();
		Assert.assertEquals(getTextToSelect(dateOfBirthDrd), dateBirth);
		Assert.assertEquals(getTextToSelect(monthOfBirthDrd), monthBirth);
		Assert.assertEquals(getTextToSelect(yearOfBirthDrd), yearBirth);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sentkeyToElement(By by, String text) throws InterruptedException {
		WebElement element = driver.findElement(by);
		element.sendKeys(text);
		Thread.sleep(500);
	}

	public void clickOnElement(By by) throws InterruptedException {
		WebElement element = driver.findElement(by);
		element.click();
		Thread.sleep(500);
	}
	
	public void selectElementByText(By by, String text) throws InterruptedException {
		Select select = new Select(driver.findElement(by));
		select.selectByVisibleText(text);
		Thread.sleep(100);
	}
	
	public void assertSizeSelect(By by, int size) throws InterruptedException {
		Select select = new Select(driver.findElement(by));
		Assert.assertEquals(select.getOptions().size(), size);
		Thread.sleep(100);
	}
	
	public String getTextToSelect(By by) {
		Select select = new Select(driver.findElement(by));
		return select.getFirstSelectedOption().getText();
		
	}

}
