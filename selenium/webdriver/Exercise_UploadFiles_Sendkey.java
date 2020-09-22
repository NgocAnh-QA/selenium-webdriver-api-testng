package webdriver;

import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_UploadFiles_Sendkey {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	String source_folder = System.getProperty("user.dir");

	String image_01 = "image_01.jpg";
	String image_02 = "image_02.jpg";
	String image_03 = "image_03.jpg";

	String image_01_path = source_folder + "\\UploadFiles\\" + image_01;
	String image_02_path = source_folder + "\\UploadFiles\\" + image_02;
	String image_03_path = source_folder + "\\UploadFiles\\" + image_03;

	public void TC_01_Chrome() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("input[type='file']"))
				.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_01 + "']")).getText(),
				image_01);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_02 + "']")).getText(),
				image_02);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_03 + "']")).getText(),
				image_03);

		List<WebElement> btnStarts = driver.findElements(By.cssSelector("td .start"));
		for (WebElement start : btnStarts) {
			start.click();
			sleepInSecond(1);
		}

		sleepInSecond(5);

		Assert.assertTrue(checkImageDisplayed("//img[contains(@src,'" + image_01 + "')]"));
		Assert.assertTrue(checkImageDisplayed("//img[contains(@src,'" + image_02 + "')]"));
		Assert.assertTrue(checkImageDisplayed("//img[contains(@src,'" + image_03 + "')]"));

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_03 + "']")).isDisplayed());

	}

	public void TC_02_Firefox() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");

		driver.findElement(By.cssSelector("input[type='file']"))
				.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);

		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_01 + "']")).getText(),
				image_01);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_02 + "']")).getText(),
				image_02);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@class='name' and text()='" + image_03 + "']")).getText(),
				image_03);

		List<WebElement> btnStarts = driver.findElements(By.cssSelector("td .start"));
		for (WebElement start : btnStarts) {
			start.click();
			sleepInSecond(1);
		}

		sleepInSecond(5);

		Assert.assertTrue(checkImageDisplayed("//img[contains(@src,'" + image_01 + "')]"));
		Assert.assertTrue(checkImageDisplayed("//img[contains(@src,'" + image_02 + "')]"));
		Assert.assertTrue(checkImageDisplayed("//img[contains(@src,'" + image_03 + "')]"));

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_01 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_02 + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + image_03 + "']")).isDisplayed());
	}

	@Test
	public void TC_03_GoFile() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("https://gofile.io/?t=uploadFiles");
		driver.findElement(By.cssSelector("input[type='file']"))
				.sendKeys(image_01_path + "\n" + image_02_path + "\n" + image_03_path);
		
		sleepInSecond(5);
		
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_01+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_02+"']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='"+image_03+"']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[text()='Upload']")).click();
		
		// -- To be continue
		
	}

	@AfterClass
	public void afterClass() {
	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean checkImageDisplayed(String xpathLocator) {
		boolean result = (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && "
				+ "typeof arguments[0].naturalWidth != \"undefined\" && " + "arguments[0].naturalWidth > 0",
				driver.findElement(By.xpath(xpathLocator)));
		return result;
	}
}
