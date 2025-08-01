package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Org_POMPage {

	// Declaration

	@FindBy(linkText = "Organizations")
	private WebElement header;

	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement Org_PlusIcon;

	public Org_POMPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getHeader() {
		return header.getText();
	}

	public void getOrg_PlusIcon() {
		Org_PlusIcon.click();
	}

}
