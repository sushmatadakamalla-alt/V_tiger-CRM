package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import GenericUtilities.WebDriverUtility;

public class HOME_POMPage {

	// Declaration

	@FindBy(linkText = "Organizations")
	private WebElement Org_tab;

	@FindBy(linkText = "Contacts")
	private WebElement cont_tab;

	@FindBy(xpath = "//span[text()=' Administrator']/../../descendant::img")
	private WebElement admin;

	@FindBy(linkText = "Sign Out")
	private WebElement signout;

	@FindBy(partialLinkText = "Home")
	private WebElement header;

	// Initialization

	public HOME_POMPage(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// Utilization

	public void getOrg_tab() {
		Org_tab.click();
	}

	public void getCont_tab() {
		cont_tab.click();
	}

	public WebElement getAdmin() {
		return admin;
	}

	public void getSignout() {
		signout.click();
	}

	public String getHeader() {
		return header.getText();
	}

	// Business logic for logout
	public void logout(WebDriver driver) {
		WebDriverUtility wutil = new WebDriverUtility();
		wutil.mousehoveronAnElement(driver, admin);
		signout.click();

	}

}
