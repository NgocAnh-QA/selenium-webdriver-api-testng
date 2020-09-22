package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_User_Interactions {

	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	@Test
	public void TC01_Hover_To_Element() {
		driver.get("http://www.myntra.com/");

		action.moveToElement(locatorElement("//a[@data-group='kids']")).perform();

		sleepInSecond(3);

		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepInSecond(2);

		Assert.assertTrue(driver.findElement(By.cssSelector(".title-title")).isDisplayed());

	}

	@Test
	public void TC02_Click_And_Hold_Element() {
		driver.get("http://jqueryui.com/resources/demos/selectable/display-grid.html");

		clickAndHoldFromTo(0, 3, "//ol[@id='selectable']/li", "//ol[@id='selectable']/li[contains(@class,'selected')]");

	}

	@Test
	public void TC03_Click_And_Select_Element() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");

		int[] a = { 2, 4, 6, 8 };
		clickAndSelect("//ol[@id='selectable']/li", "//li[@class='ui-state-default ui-selectee ui-selected']", a);

		driver.navigate().refresh();
		int[] b = { 1, 3, 5, 7, 9, 11 };
		clickAndSelect("//ol[@id='selectable']/li", "//li[@class='ui-state-default ui-selectee ui-selected']", b);

	}

	@Test
	public void TC04_Double_Click_Element() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		WebElement element = driver.findElement(By.xpath("//button[text()='Double click me']"));

		action.doubleClick(element).perform();
		sleepInSecond(3);

		Assert.assertEquals(driver.findElement(By.cssSelector("#demo")).getText(), "Hello Automation Guys!");
	}

	@Test
	public void TC05_Right_Click_To_Element() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");

		action.contextClick(locatorElement("//span[text()='right click me']")).perform();
		sleepInSecond(2);
		action.moveToElement(locatorElement("//span[text()='Quit']")).perform();

		WebElement btnQuit = driver
				.findElement(By.xpath("//li[contains(@class,'context-menu-visible')]/span[text()='Quit']"));
		sleepInSecond(2);
		btnQuit.click();
	}
	
	@Test
	public void TC06_Drag_And_Drop_Element() {
		driver.get("http://demos.telerik.com/kendo-ui/dragdrop/angular");

		WebElement sourceBtn = driver.findElement(By.cssSelector("#draggable"));
		WebElement targetBtn = driver.findElement(By.cssSelector("#droptarget"));

		action.dragAndDrop(sourceBtn, targetBtn).build().perform();
		sleepInSecond(3);

		Assert.assertEquals(targetBtn.getText(), "You did great!");

	}

	@Test
	public void TC07_Drag_And_Drop_HTML() throws AWTException, InterruptedException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
		
		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		Thread.sleep(3000);
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

		action.clickAndHold(listItems.get(from)).moveToElement(listItems.get(to)).release().perform();
		sleepInSecond(3);

		ArrayList<WebElement> itemsActual = new ArrayList<WebElement>();
		for (int i = from; i <= to; i++) {
			itemsActual.add(listItems.get(i));
		}
		sleepInSecond(3);
		List<WebElement> listItemSelected = driver.findElements(By.xpath(xpathSelected));

		Assert.assertEquals(itemsActual.size(), listItemSelected.size());

	}

	public void clickAndSelect(String xpathElement, String xpathSelected, int[] value) {

		List<WebElement> elements = driver.findElements(By.xpath(xpathElement));

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
	
	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x, ((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

}
