package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_Textbox_TextArea {
	WebDriver driver;

	String timeNow = java.time.LocalTime.now().toString().replace(".", ":").replace(":", "");

	String userID, passWord;
	String homePageUrl;

	String infor_name = "Selenium Online";
	String infor_birth = "20082020";
	String infor_address = "123 Address";
	String infor_city = "Ho Chi Minh";
	String infor_state = "Thu Duc";
	String infor_pin = "123456";
	String infor_phone = "0123456789";
	String infor_email = "simon" + timeNow + "@gmail.com";
	String infor_passwordForm = "123456";

	String ID;

	String new_address = "234 Edit Address";
	String new_city = "Edit Ho Chi Minh";
	String new_state = "Edit Thu Duc";
	String new_pin = "654321";
	String new_email = "EditMail000@gmail.com";
	String new_phone = "0987654321";

	By usernameLogin = By.name("uid");
	By passwordLoign = By.name("password");

	By name = By.name("name");
	By gender = By.xpath("//input[@value='f']");
	By birth = By.id("dob");
	By address = By.name("addr");
	By city = By.name("city");
	By state = By.name("state");
	By pin = By.name("pinno");
	By phone = By.name("telephoneno");
	By email = By.name("emailid");
	By passwordForm = By.name("password");
	By btnSubmit = By.name("sub");

	By idView = By.xpath("//td[text()='Customer ID']/following-sibling::td");
	By nameView = By.xpath("//td[text()='Customer Name']/following-sibling::td");
	By birthView = By.xpath("//td[text()='Birthdate']/following-sibling::td");
	// infor_birth.substring(4,8)+"-"+infor_birth.substring(2,4)+"-"+infor_birth.substring(0,2));
	By addressView = By.xpath("//td[text()='Address']/following-sibling::td");
	By cityView = By.xpath("//td[text()='City']/following-sibling::td");
	By stateView = By.xpath("//td[text()='State']/following-sibling::td");
	By pinView = By.xpath("//td[text()='Pin']/following-sibling::td");
	By mobileView = By.xpath("//td[text()='Mobile No.']/following-sibling::td");
	By emailView = By.xpath("//td[text()='Email']/following-sibling::td");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4");
	}

	@Test
	public void Step_01() throws InterruptedException {
		homePageUrl = driver.getCurrentUrl();
		Thread.sleep(3000);
		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys(infor_email);
		driver.findElement(By.name("btnLogin")).click();

		userID = driver.findElement(By.xpath("//td[contains(.,'User ID')]/following-sibling::*")).getText();
		passWord = driver.findElement(By.xpath("//td[contains(.,'Password')]/following-sibling::*")).getText();

	}

	@Test
	public void Step_02() throws InterruptedException {
		driver.get(homePageUrl);

		sendKeyToElement(usernameLogin, userID);
		sendKeyToElement(passwordLoign, passWord);
		Thread.sleep(3000);

		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(3000);

		Assert.assertTrue(driver.findElement(By.xpath("//marquee")).isDisplayed());
		Thread.sleep(3000);
	}

	@Test
	public void Step_03() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Thread.sleep(3000);
	}

	@Test
	public void Step_04() throws InterruptedException {
		sendKeyToElement(name, infor_name);
		clickOnElement(gender);
		sendKeyToElement(birth, infor_birth.substring(0, 4) + Keys.TAB + infor_birth.substring(4, 8));
		sendKeyToElement(address, infor_address);
		sendKeyToElement(city, infor_city);
		sendKeyToElement(state, infor_state);
		sendKeyToElement(pin, infor_pin);
		sendKeyToElement(phone, infor_phone);
		sendKeyToElement(email, infor_email);
		sendKeyToElement(passwordForm, infor_passwordForm);
		clickOnElement(btnSubmit);
	}

	@Test
	public void Step_05() {
		ID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();

	}

	@Test
	public void Step_06() throws InterruptedException {
		Assert.assertEquals(getTextElement(idView), ID);
		Assert.assertEquals(getTextElement(nameView), infor_name);

		Assert.assertEquals(getTextElement(birthView),
				infor_birth.substring(4, 8) + "-" + infor_birth.substring(2, 4) + "-" + infor_birth.substring(0, 2));

		Assert.assertEquals(getTextElement(addressView), infor_address);
		Assert.assertEquals(getTextElement(cityView), infor_city);
		Assert.assertEquals(getTextElement(stateView), infor_state);
		Assert.assertEquals(getTextElement(pinView), infor_pin);
		Assert.assertEquals(getTextElement(mobileView), infor_phone);
		Assert.assertEquals(getTextElement(emailView), infor_email);
	}

	@Test
	public void Step_07() throws InterruptedException {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		Thread.sleep(3000);
		driver.findElement(By.name("cusid")).sendKeys(ID);
		Thread.sleep(3000);
		driver.findElement(By.name("AccSubmit")).click();
		Thread.sleep(3000);

	}

	@Test
	public void Step_08() {
		WebElement name = driver.findElement(By.name("name"));
		WebElement address = driver.findElement(By.name("addr"));
		Assert.assertEquals(name.getAttribute("value"), infor_name);
		Assert.assertEquals(address.getText(), infor_address);
	}

	@Test
	public void Step_09() throws InterruptedException {
		clearText(address);
		sendKeyToElement(address, new_address);

		clearText(city);
		sendKeyToElement(city, new_city);

		clearText(state);
		sendKeyToElement(state, new_state);

		clearText(pin);
		sendKeyToElement(pin, new_pin);

		clearText(email);
		sendKeyToElement(email, new_email);

		clearText(phone);
		sendKeyToElement(phone, new_phone);

		driver.findElement(By.xpath("//input[@value='Submit']")).click();
	}

	@Test
	public void Step_10() throws InterruptedException {
		Assert.assertEquals(getTextElement(addressView), new_address);
		Assert.assertEquals(getTextElement(cityView), new_city);
		Assert.assertEquals(getTextElement(stateView), new_state);
		Assert.assertEquals(getTextElement(pinView), new_pin);
		Assert.assertEquals(getTextElement(mobileView), new_phone);
		Assert.assertEquals(getTextElement(emailView), new_email);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sendKeyToElement(By by, String text) throws InterruptedException {
		driver.findElement(by).sendKeys(text);
		Thread.sleep(1000);
	}

	public void clickOnElement(By by) throws InterruptedException {
		driver.findElement(by).click();
		Thread.sleep(1000);
	}

	public String getTextElement(By by) throws InterruptedException {
		return driver.findElement(by).getText();

	}

	public void clearText(By by) {
		driver.findElement(by).clear();
	}

}
