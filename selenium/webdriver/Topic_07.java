package webdriver;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_07 {
	public static void main(String[] args) {
		System.out.println("Your Email is: ngocanhqa"+randomMail()+"@gmail.com");
	}
	
	public static String randomMail() {
		String timeNow = java.time.LocalTime.now().toString();
		return timeNow.replace(".", ":").replace(":", "");
	}
}
