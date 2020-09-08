package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_User_Interactions {

	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	public void TC01_Hover_To_Element() {
		driver.get("http://www.myntra.com/");

		Actions action = new Actions(driver);
		action.moveToElement(locatorElement("//a[@data-group=\"kids\"]")).perform();
		;
		sleepInSecond(3);

		driver.findElement(By.xpath("//a[text()=\"Home & Bath\"]")).click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.cssSelector(".title-title")).isDisplayed());

	}

	public void TC02_Click_And_Hold_Element() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		clickAndHoldFromTo(0, 3, "//ol[@id='selectable']/li", "//ol[@id='selectable']/li[contains(@class,'selected')]");

	}

	public void TC03_Click_And_Select_Element() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		int[] a = { 2, 4, 6, 8 };
		clickAndSelect("//ol[@id='selectable']/li", "//li[@class='ui-state-default ui-selectee ui-selected']", a);

		driver.navigate().refresh();
		int[] b = { 1, 3, 5, 7, 9, 11 };
		clickAndSelect("//ol[@id='selectable']/li", "//li[@class='ui-state-default ui-selectee ui-selected']", b);

	}

	public void TC04_Double_Click_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement element = driver.findElement(By.xpath("//button[text()='Double click me']"));

		Actions action = new Actions(driver);

		action.doubleClick(element).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("#demo")).getText(), "Hello Automation Guys!");
	}

	public void TC05_Right_Click_To_Element() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
	
		Actions action = new Actions(driver);
		action.contextClick(locatorElement("//span[text()='right click me']")).perform();
		sleepInSecond(2);
		action.moveToElement(locatorElement("//span[text()='Quit']")).perform();
		
		WebElement btnQuit = driver.findElement(By.xpath("//li[contains(@class,'context-menu-visible')]/span[text()='Quit']"));
		sleepInSecond(2);
		btnQuit.click();
	}

	public void TC06_Drag_And_Drop_Element() {
	driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");
	
	WebElement sourceBtn = driver.findElement(By.cssSelector("#draggable"));
	WebElement targetBtn = driver.findElement(By.cssSelector("#droptarget"));
	
	Actions action = new Actions(driver);
	action.dragAndDrop(sourceBtn, targetBtn).build().perform();
	sleepInSecond(3);
	
	Assert.assertEquals(targetBtn.getText(), "You did great!");
	
	}

	@Test
	public void TC07_Drag_And_Drop_HTML() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public WebElement locatorElement(String xpathElement) {
		WebElement element = driver.findElement(By.xpath(xpathElement));
		return element;
	}

	public void clickAndHoldFromTo(int from, int to, String xpathLocator, String xpathSelected) {
		List<WebElement> listItems = driver.findElements(By.xpath(xpathLocator));
		Actions action = new Actions(driver);
		action.clickAndHold(listItems.get(from)).moveToElement(listItems.get(to)).release().perform();
		sleepInSecond(3);

		List<WebElement> itemsActual = new ArrayList<WebElement>();
		for (int i = from; i <= to; i++) {
			itemsActual.add(listItems.get(i));
		}
		sleepInSecond(3);
		List<WebElement> listItemSelected = driver.findElements(By.xpath(xpathSelected));

		Assert.assertEquals(itemsActual.size(), listItemSelected.size());

	}

	public void clickAndSelect(String xpathElement, String xpathSelected, int[] value) {

		List<WebElement> elements = driver.findElements(By.xpath(xpathElement));

		Actions action = new Actions(driver);

		action.keyDown(Keys.CONTROL).perform();

		for (int i = 0; i < value.length; i++) {
			action.click(elements.get(value[i] - 1)).perform();
		}
		action.keyUp(Keys.CONTROL).perform();

		Assert.assertEquals(driver.findElements(By.xpath(xpathSelected)).size(), value.length);

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
