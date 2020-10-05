package webdriver;

import org.testng.annotations.Test;

import com.sun.javafx.tk.Toolkit;

import org.testng.annotations.BeforeClass;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;

public class Exercise_UploadFiles_AutoIT_Robot {
	WebDriver driver;
	String source_folder = System.getProperty("user.dir");

	String image_01 = "image_01.jpg";
	String image_02 = "image_02.jpg";
	String image_03 = "image_03.jpg";

	String image_01_path = source_folder + "\\UploadFiles\\" + image_01;
	String image_02_path = source_folder + "\\UploadFiles\\" + image_02;
	String image_03_path = source_folder + "\\UploadFiles\\" + image_03;

	String chrome_auto_it = source_folder + "\\autoITScripts\\chromeUploadOneTime.exe";
	String firefox_auto_it = source_folder + "\\autoITScripts\\firefoxUploadOneTime.exe";
	String chrome_auto_it_multiple = source_folder + "\\autoITScripts\\chromeUploadMultiple.exe";
	String firefox_auto_it_multiple = source_folder + "\\autoITScripts\\firefoxUploadMultiple.exe";

	
	public void TC_01_Chrome_Upload_1File() throws IOException {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();
		sleepInSecond(3);

		Runtime.getRuntime().exec(new String[] {chrome_auto_it, image_01_path });
	}

	@Test
	public void TC_02_Firefox_Upload_1File() throws IOException {
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();
		sleepInSecond(3);
		Runtime.getRuntime().exec(new String[] {firefox_auto_it, image_01_path});
		sleepInSecond(5);
		
		
	}

	
	public void TC_03_Chrome_Upload_Multiple() throws IOException {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();
		sleepInSecond(3);

		Runtime.getRuntime().exec(new String[] {chrome_auto_it_multiple, image_01_path, image_02_path, image_03_path });
	}

	
	
	public void TC_04_Firefox_Upload_Multiple() throws IOException {
		System.setProperty("webdriver.gecko.driver", ".\\browserDriver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();
		sleepInSecond(3);

		Runtime.getRuntime().exec(new String[] {firefox_auto_it_multiple, image_01_path, image_02_path, image_03_path });
	}

	
	public void TC_05_Robot() throws AWTException {
		System.setProperty("webdriver.chrome.driver", ".\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		driver.findElement(By.cssSelector(".btn-success")).click();
		sleepInSecond(3);
		
		// Specify the file location extension
		StringSelection select = new StringSelection(image_01_path); 
		
		
		// Copy to clipboard
		java.awt.Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select,null);
		
		Robot robot = new Robot();
		sleepInSecond(2);
		
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
	
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);
	
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSecond(5);
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
}
