package ContactsModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

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
import GenericUtilities.WebDriverUtility;
import OrganizationModule.cretaeOrg_PhnoTest;
import POM_Utilities.Cont_Info_POMPage;
import POM_Utilities.Cont_POMPAge;
import POM_Utilities.CreateNewContPOM;
import POM_Utilities.CreateNewOrgPOMPAge;
import POM_Utilities.HOME_POMPage;
import POM_Utilities.Login_POMPage;
import POM_Utilities.OrgInfoPOMPage;
import POM_Utilities.Org_POMPage;

public class Create_OrgWith_Cont {
	@Test
	public void Create_Orgwith_Cont() throws Exception {

		// Fetch the data from Property file

		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");

		Properties p = new Properties();

		p.load(pfis);

		String Browser = p.getProperty("Browser");

		String url = p.getProperty("Url");
		String un = p.getProperty("Username");
		String pw = p.getProperty("Password");
		String time = p.getProperty("Timeouts");

		// Fetch random number

		JavaUtility jutil = new JavaUtility();
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel

		FileInputStream efis = new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");

		Workbook wb = WorkbookFactory.create(efis);

		String contactname = wb.getSheet("Cont_Data").getRow(8).getCell(3).toString() + randonNum;
		String orgname = wb.getSheet("Cont_Data").getRow(8).getCell(2).toString() + randonNum;

		// Launch the browser

		WebDriver driver = new ChromeDriver();

		WebDriverUtility wutil = new WebDriverUtility();
		// Maximize the window

		wutil.maximizethewindow(driver);

		wutil.waitUntilElementFound(driver, time);
		// Navigate to appln

		wutil.navigateToAnApplication(driver, url);

		Login_POMPage login = new Login_POMPage(driver);

		login.login(un, pw);

//			//Identify un textfield and pass the username
//			
//			driver.findElement(By.name("user_name")).sendKeys(un);
//			
//			driver.findElement(By.name("user_password")).sendKeys(pw);
//			
//			//Identify login button
//			
//			driver.findElement(By.id("submitButton")).click();

		// Identify organization link and click

//			driver.findElement(By.linkText("Organizations")).click();
		HOME_POMPage home = new HOME_POMPage(driver);
		home.getOrg_tab();

		Org_POMPage OPOM = new Org_POMPage(driver);

		// Identify create plus icon n click

//		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		OPOM.getOrg_PlusIcon();

		CreateNewOrgPOMPAge neworg = new CreateNewOrgPOMPAge(driver);

		neworg.getOrgnameTF(orgname);

		// click on org name textfield enter data

//		driver.findElement(By.name("accountname")).sendKeys(orgname);

		// identify save button and click

//		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		neworg.getSavebtn();

		// identify header in org info page and validate

		OrgInfoPOMPage orginfo = new OrgInfoPOMPage(driver);

		String actualOrgname = orginfo.getVerifyOrgnameTF();

		if (actualOrgname.contains(orgname)) {
			System.out.println("create Org test pass");

		} else {

			System.out.println("create Org test fail");

		}

		// Identify Contacts link and click

//				driver.findElement(By.linkText("Contacts")).click();
		home.getCont_tab();

		// Identify create plus icon n click

//		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		Cont_POMPAge cont = new Cont_POMPAge(driver);

		cont.getContPlusIcon();

		// Identify contactname tf and enter contact name

//		driver.findElement(By.name("lastname")).sendKeys(contactname);

		CreateNewContPOM newcont = new CreateNewContPOM(driver);
		newcont.getLastnameTF(contactname);

		newcont.getOrgPlusICon();

		// Fetch parent window id

//		String pwid = driver.getWindowHandle();
		String pwid = wutil.fetchParentWindowID(driver);
//		driver.findElement(By.xpath("//img[contains(@onclick,'specific_contact_account_address')]")).click();

		// Fetch all the window handles

//		Set<String> wids = driver.getWindowHandles();
//
//		for (String s : wids) {
//
//			driver.switchTo().window(s);

//			if (driver.getCurrentUrl().contains("module=Accounts&action=Popup")) {

		wutil.SwitchToChildWindowUsingUrl(driver, "module=Accounts&action=Popup");
//				driver.findElement(By.id("search_txt")).sendKeys(orgname);
//				driver.findElement(By.name("search")).click();
		newcont.getOrgSearchTF(orgname);
		newcont.getOrgSearchbtn();
		driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();

		wutil.switchToParentwindow(driver, pwid);

//		driver.switchTo().window(pwid);

		// identify save button and click

//		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		newcont.getSavebtn();

		// identify header in contact info page and validateQ3

//		WebElement header1 = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));

		Cont_Info_POMPage coninfo = new Cont_Info_POMPage(driver);
		String actuallastname = coninfo.getVerifylastnameTF();

		if (actuallastname.contains(contactname)) {
			System.out.println("create Contact with org verified last name test pass");

		} else {

			System.out.println("create Contact with org verified last name  test fail");

		}

		// identify orgname in contact page

//		String Verifyorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();

		String actualorgname = coninfo.getVerifyOrgnameTF();

		if (actualorgname.contains(orgname)) {
			System.out.println("create contact with Org verified orgname test pass");

		} else {

			System.out.println("create contact with Org verified orgname test fail");

		}

		// Identify Contact tab and click

//				driver.findElement(By.linkText("Contacts")).click();
		home.getCont_tab();

		// identify and click on del link

		driver.findElement(By.xpath(
				"//a[text()='" + contactname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);
		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

		Thread.sleep(3000);
		// Identify organization link and click

//			driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();

		// identify and click on del link

		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

		Thread.sleep(3000);
//		// Mousehover on admin and signout
//
//		WebElement acts1 = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
//		wutil.mousehoveronAnElement(driver, acts1);
//
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//
//		Thread.sleep(2000);

		// click on signout
		home.logout(driver);
		// close the browser
		wutil.quitTheBrowser(driver);
	}

}
