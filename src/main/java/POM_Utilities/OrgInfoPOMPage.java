package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrgInfoPOMPage {
	
	@FindBy(xpath="//td[text()='Organization Information']")
	private WebElement header;
	
	
	
	@FindBy(id="mouseArea_Organization Name")
	private WebElement VerifyOrgnameTF;
	
	
	@FindBy(id="mouseArea_Industry")
	private WebElement VerifyInd_dd ;
	
	
	@FindBy(id="mouseArea_Type")
	private WebElement VerifyType_dd ;
	
	
	@FindBy(id="mouseArea_Phone")
	private WebElement VerifyPhno ;


	public OrgInfoPOMPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		
	}


	public String getHeader() {
		return header.getText();
	}


	public String getVerifyOrgnameTF() {
		return VerifyOrgnameTF.getText();
	}


	public String getVerifyInd_dd() {
		return VerifyInd_dd.getText();
	}


	public String getVerifyType_dd() {
		return VerifyType_dd.getText();
	}


	public String getVerifyPhno() {
		return VerifyPhno.getText();
	}
	
}
