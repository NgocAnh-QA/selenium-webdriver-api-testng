package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_Windows_Tab {
	WebDriver driver;
	String IdWinDow;
	Actions action;
	JavascriptExecutor jsExecutor;
	Alert alert;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecutor = (JavascriptExecutor) driver;
		
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	public void TC_05() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		String parentID = driver.getWindowHandle();
		System.out.println("Parent ID: " + parentID);
		sleepInSecond(4);

		clickToElement("//a[text()='GOOGLE']");
		switchToWindow(parentID);
		Assert.assertEquals(driver.getTitle(), "Google");

		driver.switchTo().window(parentID);
		sleepInSecond(4);

		clickToElement("//a[text()='FACEBOOK']");
		switchToWindow(parentID);
		Assert.assertEquals(driver.getTitle(), "Facebook - Đăng nhập hoặc đăng ký");

		driver.switchTo().window(parentID);
		sleepInSecond(4);

		clickToElement("//a[text()='TIKI']");
		switchToWindow(parentID);
		Assert.assertEquals(driver.getTitle(), "Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");

		closeAllWindowsWitoutParent(parentID);

		sleepInSecond(4);
		driver.switchTo().window(parentID);
		Assert.assertEquals(driver.getTitle(), "SELENIUM WEBDRIVER FORM DEMO");

	}

	public void TC_06() {
		driver.get("https://kyna.vn/");
		String parentID = driver.getWindowHandle();

		sleepInSecond(5);

		if (driver.findElement(By.cssSelector(".fancybox-outer")).isDisplayed()) {
			sleepInSecond(5);
			System.out.println("<================== IF  ==================>");
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			sleepInSecond(10);
		}
		// Click vào FB, Youtube, Google Play và Fanpage

		clickToElementByJS("//div[@class='icon-app']/a");
		sleepInSecond(2);

		clickToElementByJS("//div[@class='social']//img[@alt='facebook']");
		sleepInSecond(2);

		clickToElementByJS("//div[@class='social']//img[@alt='youtube']");
		sleepInSecond(2);

		switchToByTitle("Kyna.vn Official – Học Online cùng chuyên gia - Ứng dụng trên Google Play");
		Assert.assertEquals(jsExecutor.executeScript("return document.title"),
				"Kyna.vn Official – Học Online cùng chuyên gia - Apps on Google Play");

		switchToByTitle("Kyna.vn - Trang chủ | Facebook"); // facebook
		Assert.assertEquals(jsExecutor.executeScript("return document.title"), "Kyna.vn - Trang chủ | Facebook");

		switchToByTitle("Kyna.vn - YouTube"); // youtube
		Assert.assertEquals(jsExecutor.executeScript("return document.title"), "Kyna.vn - YouTube");

		driver.switchTo().window(parentID);

		sleepInSecond(3);
		closeAllWindowsWitoutParent(parentID);

	}

	@Test
	public void TC_07() {
		driver.get("http://live.demoguru99.com/index.php/");
		sleepInSecond(3);
		String parentID = driver.getWindowHandle();

		clickToElement("//a[text()='Mobile']");

		// WebElement samsung = driver.findElement(By.xpath("//a[@title='Samsung
		// Galaxy']/following-sibling::div//a[@class='link-compare']"));
		clickToElement("//a[@title='Samsung Galaxy']/following-sibling::div//a[@class='link-compare']");
		Assert.assertTrue(driver.findElement(By.cssSelector(".success-msg span")).isDisplayed());
		sleepInSecond(3);

		clickToElement("//a[@title='Xperia']/following-sibling::div//a[@class='link-compare']");
		Assert.assertTrue(driver.findElement(By.cssSelector(".success-msg span")).isDisplayed());
		sleepInSecond(3);

		clickToElement("//button[@title='Compare']"); 
	
		switchToWindow(parentID);
		Assert.assertEquals(jsExecutor.executeScript("return document.title"), "Products Comparison List - Magento Commerce");
		driver.close();
		
		driver.switchTo().window(parentID);
		sleepInSecond(2);
		
		clickToElement("//div[@class='col-right sidebar']//a[text()='Clear All']");
		sleepInSecond(2);
		
		alert = driver.switchTo().alert();
		alert.accept();
		
		
		Assert.assertTrue(driver.findElement(By.cssSelector(".success-msg span")).isDisplayed());
		
	}

	@AfterClass
	public void afterClass() {
	}

	public void clickToElement(String locator) {
		driver.findElement(By.xpath(locator)).click();
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void switchToWindow(String parentID) {
		Set<String> IdWinDow = driver.getWindowHandles();
		for (String a : IdWinDow) {
			if (!a.equals(parentID)) {
				driver.switchTo().window(a);
			}
		}
	}

	public void closeAllWindowsWitoutParent(String parentID) {
		Set<String> IdWinDow = driver.getWindowHandles();
		for (String a : IdWinDow) {
			if (!a.equals(parentID)) {
				driver.switchTo().window(a);
				sleepInSecond(2);
				driver.close();
			}
		}

	}

	public void clickToElementByJS(String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click()", element);
	}

	public void switchToByTitle(String expectedTitle) {
		Set<String> windows = driver.getWindowHandles();

		for (String a : windows) {
			driver.switchTo().window(a);

			if (driver.getTitle().equals(expectedTitle)) {

				break;
			}
		}
		sleepInSecond(4);

	}
}
