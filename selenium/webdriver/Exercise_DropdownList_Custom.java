package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Exercise_DropdownList_Custom {

	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver",
				"D:\\QA\\02 - Selenium API\\selenium-webdriver-api-testng\\browserDriver\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver, 30);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		jsExecutor = (JavascriptExecutor) driver;
	}

	public void Custome_JQuery() {
		driver.get("http://jqueryui.com/resources/demos/selectmenu/default.html");
		selectElementInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='19']")).isDisplayed());

		selectElementInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "3");
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='3']")).isDisplayed());

		selectElementInDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "15");
		Assert.assertTrue(
				driver.findElement(By.xpath("//span[@class='ui-selectmenu-text' and text()='15']")).isDisplayed());

	}

	@Test
	public void Custome_Angular() {
		driver.get("https://bit.ly/2UV2vYi");
		selectElementInDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(getHiddentText("#games_hidden option"), "Football");

		selectElementInDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getHiddentText("#games_hidden option"), "Golf");
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void selectElementInDropdown(String xpathParent, String xpatAllItem, String textExpected) {
		sleepInSecond(2);
		// 1. Click vào thẻ bất kỳ để xổ hết tất cả các item trong dropdown ra
		driver.findElement(By.xpath(xpathParent)).click();

		// 2. Chờ cho tất cả item được xuất hiện / có trong HTML DOM
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpatAllItem)));

		// 3. Lấy tất cả item này đưa vào 1 list WebElement
		List<WebElement> listAllElement = driver.findElements(By.xpath(xpatAllItem));

		// 4. Duyệt qua từng cái Item
		for (WebElement itemElement : listAllElement) {
			// 5. Mỗi lần duyệt kiểm tra xem text của item đó có bằng với items mình cần
			// click hay hông
			if (itemElement.getText().equals(textExpected)) {
				// Trước khi Click thì scroll xuống chính nó
				jsExecutor.executeScript("arguments[0].scrollIntoView(true)", itemElement);

				explicitWait.until(ExpectedConditions.elementToBeClickable(itemElement));
				// 6. Nếu như = thì click vào và thoát khỏi vòng lặp
				// 7.Nếu như hông = thì lại duyệt tiếp cho đến hết tất cả item
				itemElement.click();
				break;
			}
		}

	}

	public String getHiddentText(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").text");

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
