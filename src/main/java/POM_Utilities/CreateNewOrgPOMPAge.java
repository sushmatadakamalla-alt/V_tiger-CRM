package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CreateNewOrgPOMPAge {
	
	
	@FindBy(xpath="//span[text()='Creating New Organization']")
	private WebElement header;
	
	@FindBy(xpath ="//input[@title=\"Save [Alt+S]\"]")
	private WebElement savebtn;
	
	
	@FindBy(name="accountname")
	private WebElement OrgnameTF;
	
	
	@FindBy(name="industry")
	private WebElement Ind_dd ;
	
	
	@FindBy(name="accounttype")
	private WebElement Type_dd ;
	
	
	@FindBy(id="phone")
	private WebElement Phno ;
	
	
	//Initialization
		public CreateNewOrgPOMPAge(WebDriver driver) {
		PageFactory.initElements(driver, this);
		}
	//Utilization

	public String getHeader() {
		return header.getText();
	}


	public void getSavebtn() {
		savebtn.click();
	}


	public void getOrgnameTF(String orgname) {
		 OrgnameTF.sendKeys(orgname);
	}


	public WebElement getInd_dd() {
		return Ind_dd;
	}


	public WebElement getType_dd() {
		return Type_dd;
	}


	public void getPhnoTF(String phno) {
		Phno.sendKeys(phno);
	}
	
	
}


