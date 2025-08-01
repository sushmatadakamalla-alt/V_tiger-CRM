package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Cont_Info_POMPage {

	@FindBy(xpath = "//td[text()='Contact Information']")
	private WebElement Verifyheader;

	@FindBy(id = "dtlview_Last Name")
	private WebElement VerifylastnameTF;

	@FindBy(id = "mouseArea_Organization Name")
	private WebElement VerifyOrgnameTF;
	
	//td[@id=\"mouseArea_Organization Name\"]/a[contains(@href,'module=Accounts')]

	@FindBy(id = "dtlview_Support Start Date")
	private WebElement VerifyStartDateTF;

	@FindBy(id = "dtlview_Support End Date")
	private WebElement VerifyEndDateTF;

	public Cont_Info_POMPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	public String getHeader() {
		return Verifyheader.getText();
	}

	public String getVerifylastnameTF() {
		return VerifylastnameTF.getText();
	}

	public String getVerifyOrgnameTF() {
		return VerifyOrgnameTF.getText();
	}

	public String getVerifyStartDateTF() {
		return VerifyStartDateTF.getText();
	}

	public String getVerifyEndDateTF() {
		return VerifyEndDateTF.getText();
	}

}
