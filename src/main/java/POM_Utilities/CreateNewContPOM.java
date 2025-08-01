package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewContPOM {

	// Declaration

	@FindBy(name = "lastname")
	private WebElement lastnameTF;

	@FindBy(xpath = "//img[contains(@onclick,'specific_contact_account_address')]")
	private WebElement OrgPlusICon;

	@FindBy(id = "search_txt")
	private WebElement OrgSearchTF;

	@FindBy(name = "search")
	private WebElement OrgSearchbtn;

	@FindBy(xpath = "//span[text()='Creating New Contact']")
	private WebElement header;

	@FindBy(name = "support_start_date")
	private WebElement StartDateTF;

	@FindBy(name = "support_end_date")
	private WebElement EndDateTF;

	@FindBy(name = "button")
	private WebElement savebtn;

	// Initialization
	public CreateNewContPOM(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	// Utilization

	public void getLastnameTF(String contactname) {
		lastnameTF.sendKeys(contactname);
	}

	public void getOrgPlusICon() {
		OrgPlusICon.click();
	}

	public void getOrgSearchTF(String Orgname) {
		OrgSearchTF.sendKeys(Orgname);
	}

	public void getOrgSearchbtn() {
		OrgSearchbtn.click();
	}

	public String getHeader() {
		return header.getText();
	}

	public void getStartDateTF(String Startdate) {
		StartDateTF.sendKeys(Startdate);
	}

	public void getEndDateTF(String Enddate) {
		EndDateTF.sendKeys(Enddate);
	}

	public void getSavebtn() {
		savebtn.click();
	}

}
