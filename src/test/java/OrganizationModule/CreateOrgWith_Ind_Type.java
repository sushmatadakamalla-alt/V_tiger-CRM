package OrganizationModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import POM_Utilities.CreateNewOrgPOMPAge;
import POM_Utilities.HOME_POMPage;
import POM_Utilities.Login_POMPage;
import POM_Utilities.OrgInfoPOMPage;
import POM_Utilities.Org_POMPage;

public class CreateOrgWith_Ind_Type {

	private WebElement type_dd;

	@Test

	public void CreateOrg_IndType() throws Exception {

		// Fetch data from Properties File

		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
		Properties p = new Properties();
		p.load(pfis);
		String browser = p.getProperty("Browser");
		String url = p.getProperty("Url");
		String un = p.getProperty("Username");
		String pwd = p.getProperty("Password");
		String TIME = p.getProperty("Timeouts");

		// Fetch random number

		JavaUtility jutil = new JavaUtility();
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel file

		FileInputStream efis = new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String orgname = wb.getSheet("Org_Data").getRow(9).getCell(2).toString() + randonNum;
		// String phno=wb.getSheet("Org_Data").getRow(5).getCell(3).toString();
		String industry = wb.getSheet("Org_Data").getRow(9).getCell(3).toString();
		String type = wb.getSheet("Org_Data").getRow(9).getCell(4).toString();

		// Launch the browser

		WebDriver driver = new ChromeDriver();

		WebDriverUtility wutil = new WebDriverUtility();
		// Maximize the window

		wutil.maximizethewindow(driver);

		wutil.waitUntilElementFound(driver, TIME);
		// Navigate to appln

		wutil.navigateToAnApplication(driver, url);

		Login_POMPage login = new Login_POMPage(driver);

		login.login(un, pwd);

//				//Identify un and pw enter data
//				driver.findElement(By.name("user_name")).sendKeys(un);
//				
//				driver.findElement(By.name("user_password")).sendKeys(pwd);
//				
//				//Identify login button
//				
//				driver.findElement(By.id("submitButton")).click();

		// Identify organization link and click

		HOME_POMPage home = new HOME_POMPage(driver);
//				driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();

		// Identify create plus icon n click

		Org_POMPage OPOM = new Org_POMPage(driver);
		OPOM.getOrg_PlusIcon();
//		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		// click on org name textfield enter data

		CreateNewOrgPOMPAge neworg = new CreateNewOrgPOMPAge(driver);

		neworg.getOrgnameTF(orgname);
		// Handle Indutry dd

		WebElement ind_dd = driver.findElement(By.name("industry"));

		wutil.selectDDUsingValue(driver, ind_dd, industry);

		// Handle type dd
		WebElement type_dd = driver.findElement(By.name("accounttype"));

		wutil.selectDDUsingValue(driver, type_dd, type);

		// identify click on save

		neworg.getSavebtn();
		// driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
		// identify header in org info page and validate

//		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));

		OrgInfoPOMPage orginfo = new OrgInfoPOMPage(driver);

		String actualOrgname = orginfo.getVerifyOrgnameTF();

		if (actualOrgname.contains(orgname)) {
			System.out.println("create Org with industry type verified orgname test pass");

		} else {

			System.out.println("create Org with industry type verified orgname test pass");

		}
		// validate industry and type

//		WebElement Verify_ind = driver.findElement(By.id("dtlview_Industry"));
		String actualInd = orginfo.getVerifyInd_dd();
		if (actualInd.contains(industry)) {
			System.out.println("create Org with industry type verified Industry test pass");

		} else {

			System.out.println("create Org with industry type verified Industry test fail");

		}

//		WebElement Verify_type = driver.findElement(By.id("dtlview_Type"));
		String actualtype = orginfo.getVerifyType_dd();

		if (actualtype.contains(type)) {
			System.out.println("create Org with industry type verified type test pass");

		} else {

			System.out.println("create Org with industry type verified type test fail");

		}

		// Identify organization link and click

//						driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();

		// identify and click on del link

		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

		Thread.sleep(3000);
		// click on signout
		home.logout(driver);

		Thread.sleep(2000);

		wutil.quitTheBrowser(driver);

	}

}
