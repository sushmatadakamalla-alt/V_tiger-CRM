package OrganizationModule;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFile_Utilities;
import GenericUtilities.WebDriverUtility;
import POM_Utilities.CreateNewOrgPOMPAge;
import POM_Utilities.HOME_POMPage;
import POM_Utilities.Login_POMPage;
import POM_Utilities.OrgInfoPOMPage;
import POM_Utilities.Org_POMPage;

public class CreateOrgTest {

	@Test

	public void createNewOrgTest() throws Exception {
//		
//		//Fetch the data from Property file
//		
//		FileInputStream pfis=new FileInputStream("./src/test/resources/VtigerCommonData.properties");
//		
//		Properties p=new Properties();
//		
//		p.load(pfis);
//		
//		String Browser=p.getProperty("Browser");
//		
//		String url=p.getProperty("Url");
//		String un=p.getProperty("Username");
//		String pw=p.getProperty("Password");
//		String time=p.getProperty("Timeouts");

		PropertyFile_Utilities prop = new PropertyFile_Utilities();

		String browser = prop.FetchingDataFromPropertyFile("Browser");
		String url = prop.FetchingDataFromPropertyFile("Url");
		String un = prop.FetchingDataFromPropertyFile("Username");
		String pw = prop.FetchingDataFromPropertyFile("Password");
		String timeouts = prop.FetchingDataFromPropertyFile("Timeouts");

		// Fetch random number

		JavaUtility jutil = new JavaUtility();
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel

		FileInputStream efis = new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");

		Workbook wb = WorkbookFactory.create(efis);

		String Orgname = wb.getSheet("Org_Data").getRow(1).getCell(2).toString() + randonNum;

		// Launch the browser

		WebDriver driver = new ChromeDriver();

		WebDriverUtility wutil = new WebDriverUtility();
		// Maximize the window

		wutil.maximizethewindow(driver);

		wutil.waitUntilElementFound(driver, timeouts);
		// Navigate to appln

		wutil.navigateToAnApplication(driver, url);

		// Login to application
		Login_POMPage login = new Login_POMPage(driver);

		login.login(un, pw);

//		//Identify un textfield and pass the username
//		
//		driver.findElement(By.name("user_name")).sendKeys(un);
//		
//		driver.findElement(By.name("user_password")).sendKeys(pw);
//		
//		//Identify login button
//		
//		driver.findElement(By.id("submitButton")).click();

		// Identify organization link and click

//		driver.findElement(By.linkText("Organizations")).click();
		HOME_POMPage home = new HOME_POMPage(driver);
		home.getOrg_tab();

		// Identify create plus icon n click

//		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		Org_POMPage OPOM = new Org_POMPage(driver);
		OPOM.getOrg_PlusIcon();

		// click on org name textfield enter data

//		driver.findElement(By.name("accountname")).sendKeys(Orgname);

		CreateNewOrgPOMPAge neworg = new CreateNewOrgPOMPAge(driver);

		neworg.getOrgnameTF(Orgname);

		// identify save button and click

//		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		neworg.getSavebtn();
		// identify header in org info page and validate

//		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		OrgInfoPOMPage orginfo = new OrgInfoPOMPage(driver);

		String actualOrgname = orginfo.getVerifyOrgnameTF();

		if (actualOrgname.contains(Orgname)) {
			System.out.println("create Org test pass");

		} else {

			System.out.println("create Org test fail");

		}
		// Identify organization link and click

//			driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();

		// identify and click on del link

		driver.findElement(
				By.xpath("//a[text()='" + Orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

		Thread.sleep(3000);

		// click on signout
		home.logout(driver);
		// close the browser

		wutil.quitTheBrowser(driver);

	}

}
