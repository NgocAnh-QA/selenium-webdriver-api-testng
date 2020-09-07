package webdriver;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
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
		// driver.manage().window().maximize();

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

	public void Custom_Angular() {
		driver.get("https://bit.ly/2UV2vYi");
		selectElementInDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Football");
		Assert.assertEquals(getHiddentText("#games_hidden option"), "Football");

		selectElementInDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Golf");
		Assert.assertEquals(getHiddentText("#games_hidden option"), "Golf");
	}

	public void Custom_ReactJS() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");
		selectElementInDropdown("//div[@role='listbox']", "//div[@role='listbox']//div[last()]/div/span", "Matt");
		Assert.assertEquals(getHiddentText(".selected"), "Matt");

		selectElementInDropdown("//div[@role='listbox']", "//div[@role='listbox']//div[last()]/div/span", "Jenny Hess");
		Assert.assertEquals(getHiddentText(".selected"), "Jenny Hess");

	}

	public void Custom_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectElementInDropdown("//li[contains(.,\"Please select an item\")]", "//ul[@class='dropdown-menu']/li/a",
				"Second Option");
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");
	}

	public void Custom_Editable() {
		driver.get("http://indrimuska.github.io/jquery-editable-select/");

		sendKeyToEditableDropdown("//div[@id='slide-place']/input", "Mini");
		Assert.assertEquals(getHiddentText("div[id='slide-place'] li.selected"), "Mini");

		sendKeyToEditableDropdown("//div[@id='slide-place']/input", "Smart");
		Assert.assertEquals(getHiddentText("div[id='slide-place'] li.selected"), "Smart");
	}

	@Test
	public void Custom_Multiple() {
		driver.get("http://multiple-select.wenzhixin.net.cn/examples#basic.html");
		driver.switchTo().frame(driver.findElement(By.tagName("iframe")));

		String[] a = { "April", "August", "September" };
		multipleSelect("//option/parent::select/following-sibling::div",
				"//option/parent::select/following-sibling::div//li//span",
				"//option/parent::select/following-sibling::div//li[@class='selected']",
				"//option/parent::select/following-sibling::div/button/span", a);

		driver.navigate().refresh();
		
		String[] b = { "May", "January", "October", "September" };
		multipleSelect("//option/parent::select/following-sibling::div",
				"//option/parent::select/following-sibling::div//li//span",
				"//option/parent::select/following-sibling::div//li[@class='selected']",
				"//option/parent::select/following-sibling::div/button/span", b);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sendKeyToEditableDropdown(String locator, String text) {
		driver.findElement(By.xpath(locator)).clear();
		driver.findElement(By.xpath(locator)).sendKeys(text);
		sleepInSecond(2);
		driver.findElement(By.xpath(locator)).sendKeys(Keys.TAB);
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
				sleepInSecond(2);

				itemElement.click();
				sleepInSecond(2);
				break;
			}
		}

	}

	public String getHiddentText(String cssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + cssLocator + "\").textContent");

	}

	public void sleepInSecond(long time) {
		try {
			Thread.sleep(time * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void multipleSelect(String xpathParent, String xpathAllItem, String xpathSelected, String xpathResult,
			String[] expectedValue) {

		// 1. Click vào dropdown cho nó xổ hết tất cả các item ra
		driver.findElement(By.xpath(xpathParent)).click();

		// 2. Chờ cho tất cả các item được load thành công
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpathAllItem)));

		// 3. Đưa các item vào 1 list
		List<WebElement> allItems = driver.findElements(By.xpath(xpathAllItem));

		// 4. Duyêt tất cả phần từ trong list cho đến khi thỏa mản điều kiện (Tức là tìm
		// được cái đã chọn)
		for (WebElement items : allItems) {
			for (String item : expectedValue) {
				if (items.getText().equals(item)) {
					// 5. Scroll đến item cần chọn
					jsExecutor.executeScript("arguments[0].scrollIntoView(true)", items);
					sleepInSecond(1);
					// 6. Click vào item cần chọn
					items.click();
					break;
				}

			}
		}

		checkSelectedItem(xpathParent, xpathSelected, xpathResult, expectedValue);
	}

	// 7. Sau khi chọn thành công thì kiểm tra
	public void checkSelectedItem(String parentLocator, String selectedLocator, String resultLocator,
			String[] expectedValue) {
		List<WebElement> AllItems = driver.findElements(By.xpath(parentLocator));
		List<WebElement> itemsSelected = driver.findElements(By.xpath(selectedLocator));

		int numberSelected = itemsSelected.size();
		Assert.assertEquals(numberSelected, expectedValue.length);

		String selectedText = driver.findElement(By.xpath(resultLocator)).getText();
		if (numberSelected <= 3 && numberSelected >= 0) {
			// 8.Nếu số lượng đã chọn nhỏ hơn hoặc = 3 thì hiển thị text của từng cái đã
			// chọn
			for (String items : expectedValue) {
				if (selectedText.contains(items)) {
					System.out.println(driver.findElement(By.xpath(resultLocator)).getText());
					break;
				}
			}

		} else {
			// 9. Nếu không thì hiển thị số cái đã chọn / tổng số
			Assert.assertEquals(driver.findElement(By.xpath(resultLocator)).getText(),
					numberSelected + " of " + (AllItems.size() - 1) + " selected");
			System.out.println(driver.findElement(By.xpath(resultLocator)).getText());
		}

	}
}
