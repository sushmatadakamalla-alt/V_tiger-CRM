package Base_Class_Utility;

import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import GenericUtilities.DatabaseUtility;
import GenericUtilities.PropertyFile_Utilities;
import GenericUtilities.WebDriverUtility;
import POM_Utilities.HOME_POMPage;
import POM_Utilities.Login_POMPage;

public class Baseclass  {

	DatabaseUtility dutil = new DatabaseUtility();
	PropertyFile_Utilities prop = new PropertyFile_Utilities();
	WebDriverUtility wutil = new WebDriverUtility();
	public WebDriver driver = null;
	public static WebDriver sdriver=null;

	@BeforeSuite
	public void getConnectionToDB() throws Exception {
		dutil.getDatabaseConnection();

	}

	@BeforeTest
	public void conParallelExe() {

		Reporter.log("Configure parallel execution", true);
	}

	@BeforeClass
	public void launchTheBrowser() throws Exception {
		String BROWSER = prop.FetchingDataFromPropertyFile("Browser");
		if (BROWSER.equals("chrome")) {

			driver = new ChromeDriver();
		} else if (BROWSER.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
		sdriver=driver;

	}

	@BeforeMethod
	public void login() throws Exception {

		String url = prop.FetchingDataFromPropertyFile("Url");
		String un = prop.FetchingDataFromPropertyFile("Username");
		String pw = prop.FetchingDataFromPropertyFile("Password");
		String timeouts = prop.FetchingDataFromPropertyFile("Timeouts");

		wutil.maximizethewindow(driver);
		wutil.waitUntilElementFound(driver, timeouts);
		wutil.navigateToAnApplication(driver, url);
		Login_POMPage login = new Login_POMPage(driver);
		login.login(un, pw);
	}

	@AfterMethod
	public void logout() {

		HOME_POMPage home = new HOME_POMPage(driver);
		home.logout(driver);

	}

	@AfterClass
	public void closeTheBrowser() {
		wutil.quitTheBrowser(driver);

	}

	@AfterTest
	public void closeParallelExe() {

		Reporter.log("Close the Configuring parallel execution", true);

	}

	@AfterSuite
	public void closeConnectionwithDB() throws SQLException {

		dutil.ClosetheDatabaseConnection();

	}
}
