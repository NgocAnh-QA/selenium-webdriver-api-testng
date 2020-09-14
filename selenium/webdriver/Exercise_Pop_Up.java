package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

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

public class Exercise_Pop_Up {

	WebDriver driver;
	Actions action;

	boolean status;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		// driver.manage().window().maximize();
	}

	public void TC_01_Fixed_Popup() {
		driver.get("https://www.zingpoll.com/");
		sleppInSecond(10);

		// 1. Click vào popup
		driver.findElement(By.cssSelector("#Loginform")).click();
		sleppInSecond(5);

		Assert.assertTrue(driver.findElement(By.id("Login")).isDisplayed());
		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Popup displays: " + status);

		// 2. Đóng popup
		driver.findElement(By.cssSelector("#Login .close")).click();
		sleppInSecond(5);

		status = driver.findElement(By.id("Login")).isDisplayed();
		System.out.println("Popup displays: " + status);
		Assert.assertFalse(driver.findElement(By.id("Login")).isDisplayed());

		// 3. Mở popup lần nữa
		driver.findElement(By.cssSelector("#Loginform")).click();
		sleppInSecond(5);

		// 4. Đăng nhập
		driver.findElement(By.id("loginEmail")).sendKeys("automationfc.vn@gmail.com");
		driver.findElement(By.id("loginPassword")).sendKeys("automationfc");

		sleppInSecond(5);
		driver.findElement(By.id("button-login")).click();

		// 5. Xác nhận đăng nhập không thành công
		Assert.assertTrue(driver.findElement(By.className("username")).getText().contains("AUTOMATION TESTING"));

	}

	@Test
	public void TC_02_Random_Popup() {
		driver.get("https://blog.testproject.io/");
		sleppInSecond(15);

		if (driver.findElement(By.cssSelector("#mailch-bg .rocket-lazyload")).isDisplayed()) {
			sleppInSecond(5);
			System.out.println("<================== IF ==================>");
			driver.findElement(By.id("close-mailch")).click();
			sleppInSecond(10);
		}

		driver.findElement(By.cssSelector("#search-2 .search-field ")).sendKeys("Selenium");
		sleppInSecond(5);
		action.sendKeys(Keys.ENTER).perform();;

		List<WebElement> seletecd = driver.findElements(By.cssSelector(".post-title a"));
		sleppInSecond(5);
		
		for (WebElement a : seletecd) {
			System.out.println("Ket qua tim kiem: " + a.getText().trim());
			Assert.assertTrue(a.getText().trim().contains("Selenium"));
		}
	}

	@AfterClass
	public void afterClass() {
	}

	public void sleppInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
