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

public class Exercise_Textbox_TextArea_Dropdown_CustomDropdown {
	WebDriver driver;
	/*
	 * Username: mngr279979 Password: rEjAgyh
	 */


	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01() throws InterruptedException {
		String username = "mngr279979";
		String password = "rEjAgyh";
		String timeNow = java.time.LocalTime.now().toString().replace(".", ":").replace(":", "");;
		
		// Step 01 
		driver.get("http://demo.guru99.com/v4");
		Thread.sleep(3000);
		
		//Step 02
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		Thread.sleep(3000);
		
		driver.findElement(By.name("btnLogin")).click();
		Thread.sleep(3000);
		
		// Step 03
		Assert.assertTrue(driver.findElement(By.xpath("//marquee")).isDisplayed());
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		Thread.sleep(3000);
		
		WebElement name = driver.findElement(By.name("name"));
		WebElement gender = driver.findElement(By.xpath("//input[@value='f']"));
		WebElement birth = driver.findElement(By.id("dob"));
		WebElement address = driver.findElement(By.name("addr"));
		WebElement city = driver.findElement(By.name("city"));
		WebElement state = driver.findElement(By.name("state"));
		WebElement pin = driver.findElement(By.name("pinno"));
		WebElement phone = driver.findElement(By.name("telephoneno"));
		WebElement email = driver.findElement(By.name("emailid"));
		WebElement passwordForm = driver.findElement(By.name("password"));
		WebElement btnSubmit = driver.findElement(By.name("sub"));
		
		String infor_name = "Selenium Online";
		String infor_birth = "20082020";
		String infor_address = "123 Address";
		String infor_city = "Ho Chi Minh";
		String infor_state = "Thu Duc";
		String infor_pin = "123456";
		String infor_phone = "0123456789";
		String infor_email = "simon"+timeNow+"@gmail.com";
		String infor_passwordForm = "123456";
		
		// Step 04
		name.sendKeys(infor_name);
		Thread.sleep(3000);
		
		gender.click();
		Thread.sleep(3000);
		
		birth.sendKeys(infor_birth.substring(0,4) + Keys.TAB + infor_birth.substring(4, 8));
		Thread.sleep(3000);
		
		address.sendKeys(infor_address);
		Thread.sleep(3000);
		
		city.sendKeys(infor_city);
		Thread.sleep(3000);
		
		state.sendKeys(infor_state);
		Thread.sleep(3000);
		
		pin.sendKeys(infor_pin);
		Thread.sleep(3000);
		
		phone.sendKeys(infor_phone);
		Thread.sleep(3000);
		
		email.sendKeys(infor_email);
		Thread.sleep(3000);
		
		passwordForm.sendKeys(infor_passwordForm);
		Thread.sleep(3000);
		btnSubmit.click();
		
		// Step 05
		String ID = driver.findElement(By.xpath("//td[text()='Customer ID']/following-sibling::td")).getText();
		
		// Step 06
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']/following-sibling::td")).getText(), infor_name);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']/following-sibling::td")).getText(), infor_birth.substring(4,8)+"-"+infor_birth.substring(2,4)+"-" +infor_birth.substring(0,2));
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), infor_address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), infor_city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), infor_state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), infor_pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), infor_phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), infor_email);
		
		// Step 07
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(ID);
		driver.findElement(By.name("AccSubmit")).click();
		
		// Step 08 
		Assert.assertEquals(driver.findElement(By.name("name")).getAttribute("value"), infor_name);
		Assert.assertEquals(driver.findElement(By.name("addr")).getText(), infor_address);

		String new_address = "234 Edit Address";
		String new_city = "Edit Ho Chi Minh";
		String new_state = "Edit Thu Duc";
		String new_pin = "654321";
		String new_email = "EditMail000@gmail.com";
		String new_phone = "0987654321";
		
		
		// Step 09
		driver.findElement(By.name("addr")).clear();
		driver.findElement(By.name("addr")).sendKeys(new_address);
		Thread.sleep(3000);
		
		driver.findElement(By.name("city")).clear();
		driver.findElement(By.name("city")).sendKeys(new_city);
		Thread.sleep(3000);
		
		driver.findElement(By.name("state")).clear();
		driver.findElement(By.name("state")).sendKeys(new_state);
		Thread.sleep(3000);
		
		driver.findElement(By.name("pinno")).clear();
		driver.findElement(By.name("pinno")).sendKeys(new_pin);
		Thread.sleep(3000);
		
		driver.findElement(By.name("emailid")).clear();
		driver.findElement(By.name("emailid")).sendKeys(new_email);
		Thread.sleep(3000);
		
		driver.findElement(By.name("telephoneno")).clear();
		driver.findElement(By.name("telephoneno")).sendKeys(new_phone);
		Thread.sleep(3000);
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		// Step 10
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']/following-sibling::td")).getText(), new_address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']/following-sibling::td")).getText(), new_city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']/following-sibling::td")).getText(), new_state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']/following-sibling::td")).getText(), new_pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']/following-sibling::td")).getText(), new_phone);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']/following-sibling::td")).getText(), new_email);
		
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
