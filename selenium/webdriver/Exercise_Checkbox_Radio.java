package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_Checkbox_Radio {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}


	public void TC_02_Default() throws InterruptedException  {
		// Step 01
		driver.get("http://demos.telerik.com/kendo-ui/styling/checkboxes");
		Thread.sleep(2000);

		// Step 02
		WebElement chk = driver
				.findElement(By.xpath("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		chk.click();
		Thread.sleep(2000);

		// Step 03
		Assert.assertTrue(chk.isSelected());
		Thread.sleep(2000);

		// Step 04
		if (chk.isSelected()) {
			chk.click();
		}
		Assert.assertTrue(!chk.isSelected());
		Thread.sleep(2000);

		// Step 05
		driver.get("http://demos.telerik.com/kendo-ui/styling/radios");
		Thread.sleep(2000);

		// Step 06
		WebElement rdo = driver.findElement(By.xpath("//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input"));
		rdo.click();
		Thread.sleep(2000);

		// Step 07
		Assert.assertTrue(rdo.isSelected());
		Thread.sleep(2000);
		if (!rdo.isSelected()) {
			rdo.click();
			Thread.sleep(2000);
		}

	}

	@Test
	public void TC_03_Custom() {
		// Step 01
		driver.get("https://material.angular.io/components/radio/examples");
		
		// Step 02
		WebElement summer = driver.findElement(By.xpath("//div[@class='mat-radio-label-content' and contains(.,'Summer')]/preceding-sibling::div/input"));
		summer.click();;
		
		// Step 03
		if (!summer.isSelected()) {
			summer.click();
		}
		
		// Step 04
		driver.get("https://material.angular.io/components/checkbox/examples");
		
		// Step 05
		WebElement checked = driver.findElement(By.xpath("//span[@class='mat-checkbox-label' and contains(.,'Checked')]/preceding-sibling::div/input")); 
		checked.click();
		
		WebElement intermediate = driver.findElement(By.xpath("//mat-card[contains(.,'Checkbox configuration')]//span[contains(.,'Indeterminate')]/preceding-sibling::div/input"));
		intermediate.click();
		
		// Step 06
		Assert.assertTrue(checked.isSelected());
		Assert.assertTrue(intermediate.isSelected());
		
		// Step 07
		checked.click();
		intermediate.click();
		
		Assert.assertFalse(checked.isSelected());
		Assert.assertFalse(intermediate.isSelected());
	
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
