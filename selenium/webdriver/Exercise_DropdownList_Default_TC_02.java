package webdriver;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_DropdownList_Default_TC_02 {
	WebDriver driver;

	By job1Dropdown = By.id("job1");
	By job2Dropdown = By.id("job2");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_02() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(3000);
		
		Select job1 = new Select(driver.findElement(job1Dropdown));
		Assert.assertFalse(job1.isMultiple());
		
		job1.selectByVisibleText("Mobile Testing");
		Assert.assertEquals(job1.getFirstSelectedOption().getText(), "Mobile Testing");
		
		job1.selectByValue("manual");
		Assert.assertEquals(job1.getFirstSelectedOption().getText(),"Manual Testing");
		
		job1.selectByIndex(9);
		Assert.assertEquals(job1.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		Assert.assertEquals(job1.getOptions().size(), 10);
		
		Select job2 = new Select(driver.findElement(job2Dropdown));
		Assert.assertTrue(job2.isMultiple());
		
		job2.selectByValue("automation");
		job2.selectByValue("mobile");
		job2.selectByValue("desktop");

		int optionSelected = job2.getAllSelectedOptions().size();
		System.out.println(optionSelected);
		Assert.assertEquals(optionSelected, 3);
		List<WebElement> listSelected = job2.getAllSelectedOptions();
		for (WebElement options : listSelected) {
			System.out.println("Selected: "+options.getText());
		}
				
		job2.deselectAll();
		optionSelected = job2.getAllSelectedOptions().size();
		Assert.assertEquals(optionSelected, 0);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
