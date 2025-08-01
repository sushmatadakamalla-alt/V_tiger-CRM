package GenericUtilities;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {

	public void maximizethewindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void waitUntilElementFound(WebDriver driver, String time) {
		long TIME = Long.parseLong(time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME));

	}

	public void navigateToAnApplication(WebDriver driver, String url) {

		driver.get(url);
	}

	public void HandleAlertpopupAndClickOK(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void mousehoveronAnElement(WebDriver driver, WebElement element) {

		Actions act = new Actions(driver);
		act.moveToElement(element).perform();

	}

	public void quitTheBrowser(WebDriver driver) {

		driver.quit();

	}

	public void selectDDUsingValue(WebDriver driver, WebElement ddEle, String value) {
		Select s = new Select(ddEle);
		s.selectByValue(value);
	}

	public String fetchParentWindowID(WebDriver driver) {
		String pwid = driver.getWindowHandle();
		return pwid;
	}

	public Set<String> fetchMultiplewindowIDs(WebDriver driver) {

		Set<String> wids = driver.getWindowHandles();
		return wids;

	}

	public void SwitchToChildWindowUsingtitle(WebDriver driver, String title) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getTitle().contains(title)) {
				break;

			}

		}

	}

	public void SwitchToChildWindowUsingUrl(WebDriver driver, String url) {
		Set<String> wids = driver.getWindowHandles();
		for (String s : wids) {
			driver.switchTo().window(s);
			if (driver.getCurrentUrl().contains(url)) {
				break;

			}
		}
	}

	public void switchToParentwindow(WebDriver driver, String pwid) {
		driver.switchTo().window(pwid);
	}

	public void selectDD_UsingIndex(WebDriver driver, WebElement ddEle, int index) {
		Select s = new Select(ddEle);
		s.selectByIndex(index);
	}

	public void selectDD_UsingVisibleText(WebDriver driver, WebElement ddEle, String text) {
		Select s = new Select(ddEle);
		s.selectByValue(text);
	}

	public void HandlePopupAndClickCancel(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public String HandleAlertPopUpAndFetchText(WebDriver driver) {
		String text = driver.switchTo().alert().getText();
		return text;
	}

	/**
	 * This method is used to Handle Alert pop up and pass the text
	 * 
	 * @param driver
	 * @param text
	 */
	public void HandleAlertPopupAndPAssTheText(WebDriver driver, String text) {
		driver.switchTo().alert().sendKeys(text);
	}

	/**
	 * 
	 * @param driver
	 * @param index
	 */
	public void switchToFrameByindex(WebDriver driver, int index) {
		driver.switchTo().frame(index);
	}

	public void switchToFrameByID_Name(WebDriver driver, String id_name) {
		driver.switchTo().frame(id_name);
	}

	public void switchToFrameBywebelement(WebDriver driver, WebElement frameele) {
		driver.switchTo().frame(frameele);
	}

	public void switchToMainWebpageFromFrame(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void waitTillElementisVisible(WebDriver driver, WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public void waitTillElementisClickable(WebDriver driver, WebElement ele) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(ele));
	}

	public void waitTillTitleVisible(WebDriver driver, String title) {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.titleContains(title));
	}

}