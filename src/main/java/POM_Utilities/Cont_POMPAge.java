package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cont_POMPAge {

	// Declaration

	@FindBy(linkText = "Contacts")
	private WebElement Cont_header;

	@FindBy(xpath = "//img[@title=\"Create Contact...\"]")
	private WebElement ContPlusIcon;

	// Initialization
	public Cont_POMPAge(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization

	public String getCont_header(String header) {
		return Cont_header.getText();
	}

	public void getContPlusIcon() {
		ContPlusIcon.click();
	}

}
