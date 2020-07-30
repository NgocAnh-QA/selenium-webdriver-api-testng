package webdriver;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Exercise_WebBrowser {
	WebDriver driver;

	@BeforeMethod
	public void beforeClass() throws InterruptedException {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		
		// Step 01
		driver.get("http://live.demoguru99.com/");
		Thread.sleep(2000);
		
		// Step 02
		driver.findElement(By.linkText("MY ACCOUNT")).click();;

	}
	@Test
	public void TC_01_VerifyUrl() { 
		// Step 03
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
	
		// Step 04
		driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
		
		// Step 05
		assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		
	}
	
	@Test
	public void TC_02_VerifyTitle() {
		// Step 03
		assertEquals(driver.getTitle(), "Customer Login");
		
		// Step 04
		driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
		
		// Step 05
		assertEquals(driver.getTitle(), "Create New Customer Account");
		
	}
	
	@Test
	public void TC_03_NavigateFunction() {
		// Step 03
		driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();
		
		// Step 04 
		assertEquals(driver.getCurrentUrl(),"http://live.demoguru99.com/index.php/customer/account/create/");
		
		// Step 05 
		driver.navigate().back();
		
		// Step 06
		assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/customer/account/login/");
		
		//Step 07 
		driver.navigate().forward();
		
		// Step 08 
		assertEquals(driver.getTitle(), "Create New Customer Account");

	}
	
	
	@Test
	public void TC_04_GetPageSourceCode() {
		// Step 03 
		assertTrue(driver.getPageSource().contains("Login or Create an Account"));
		
		
		// Step 04 
		driver.findElement(By.linkText("CREATE AN ACCOUNT")).click();;
		
		// Step 05 
		assertTrue(driver.getPageSource().contains("Create an Account"));
	}
	
	
	@AfterMethod
	public void afterClass() {
		driver.quit();
	}
}