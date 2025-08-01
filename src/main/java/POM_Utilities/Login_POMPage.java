package POM_Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Login_POMPage {

	// Declaration

	@FindBy(linkText = "vtiger")
	private WebElement header;

	@FindBy(name = "user_name")
	private WebElement username;

	@FindBy(name = "user_password")
	private WebElement password;

	@FindBy(id = "submitButton")
	private WebElement login_btn;

	// Initialization

	public Login_POMPage(WebDriver driver) {

		PageFactory.initElements(driver, this);

	}

	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getUsernameTF(String user) {
		username.sendKeys(user);
	}

	public void getPasswordTF(String Password) {
		 password.sendKeys(Password);
	}

	public void getLogin_btn() {
		login_btn.click();
	}
	//Business logic for login
	
	public void login(String user,String Password) {
		username.sendKeys(user);
		password.sendKeys(Password);
		login_btn.click();
		
	}
	
}
