package webdriver;

import org.testng.annotations.Test;



import org.testng.annotations.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_Explicit_Wait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String dateNow = "Monday, October 05, 2020";

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 10);
	}

	
	public void TC_01() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("1: " + getDate());
		
		driver.findElement(By.xpath("//button")).click();
		System.out.println("2: " + getDate());
		
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#loading img")));
		System.out.println("3: " + getDate());
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
		Assert.assertEquals(driver.findElement(By.cssSelector("#finish h4")).getText(), "Hello World!");
		System.out.println("4: " + getDate());
	}
	
	
	public void TC_02() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");
		System.out.println("1: " + getDate());
		
		driver.findElement(By.xpath("//button")).click();
		System.out.println("2: " + getDate());
		
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#finish h4")));
		System.out.println("3: " + getDate());
		
		Assert.assertEquals(driver.findElement(By.cssSelector("#finish h4")).getText(), "Hello World!");
		System.out.println("4: " + getDate());
	}


	@Test
	public void TC_03() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='calendarContainer']")));
		Assert.assertTrue(driver.findElement(By.cssSelector("#ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel")).isDisplayed());
		driver.findElement(By.xpath("//td[@title='"+dateNow+"']")).click();
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_RadAjaxLoadingPanel1ctl00_ContentPlaceholder1_RadCalendar1']//div[@class='raDiv']")));
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']"))).isDisplayed();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='ctl00_ContentPlaceholder1_ctl00_ContentPlaceholder1_Label1Panel']")).getText(), "Monday, October 5, 2020");
		
	}
	
	
	@AfterClass
	public void afterClass() {
	}
	
	public String getDate() {
		return java.time.LocalTime.now().toString();
	}

}
