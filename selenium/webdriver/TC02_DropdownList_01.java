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

public class TC02_DropdownList_01 {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void Step_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		Thread.sleep(3000);

	}

	@Test
	public void Step_02() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertFalse(job1.isMultiple());
	}

	@Test
	public void Step_03() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		job1.selectByVisibleText("Mobile Testing");
	}

	@Test
	public void Step_04() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertEquals("Mobile Testing", job1.getFirstSelectedOption().getText());
	}

	@Test
	public void Step_05() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		job1.selectByValue("manual");
	}

	@Test
	public void Step_06() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertEquals("Manual Testing", job1.getFirstSelectedOption().getText());
	}

	@Test
	public void Step_07() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		job1.selectByIndex(job1.getOptions().size() - 1);
	}

	@Test
	public void Step_08() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertEquals("Functional UI Testing", job1.getFirstSelectedOption().getText());
	}

	@Test
	public void Step_09() {
		Select job1 = new Select(driver.findElement(By.id("job1")));
		Assert.assertEquals(10, job1.getOptions().size());
	}

	@Test
	public void Step_10() {
		Select job2 = new Select(driver.findElement(By.id("job2")));
		Assert.assertTrue(job2.isMultiple());
	}

	@Test
	public void Step_11_12() throws InterruptedException {
		String[] itemArray = new String[] { "Mobile", "Manual"};
		selectMultiItemInDropDown("//select[@id='job2']", "//select[@id='job2']/*", itemArray);
	}

	@Test
	public void Step_13_14() throws InterruptedException {
		deSelectedItemInDropdown("//select[@id='job2']/*");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	List<String> itemSelected = new ArrayList<String>();
	public void selectMultiItemInDropDown(String parentXpath, String allItemXpath, String[] expectedValueItem)
			throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> allItems = driver.findElements(By.xpath(allItemXpath));

		int a = 0;
		for (WebElement childElement : allItems) {
			for (String item : expectedValueItem) {
				if (childElement.getText().equals(item)) {
					js.executeScript("arguments[0].click();", childElement);
					Thread.sleep(1000);
					a++;
					itemSelected.add(item);
					if (a == expectedValueItem.length) {
						break;
					}
				}
			}
		}
		for (String index : itemSelected) {
			System.out.println(index);
		}
		System.out.println("======> Total: " + itemSelected.size());

	}

	
	public void deSelectedItemInDropdown(String allItemsXpath) throws InterruptedException {
		int b = 0;
		JavascriptExecutor js = (JavascriptExecutor) driver;
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));
		for (WebElement childElement : allItems) {
			for (String item : itemSelected) {
				if (childElement.getText().equals(item)) {
					js.executeScript("arguments[0].click();", childElement);
					Thread.sleep(3000);
					b++;
					if ((itemSelected.size() - b) == 0) {
						System.out.println("DESELECT SUCCESSFULLY");
						break;
					}
				}
			}
		}
	}

}
