package OrganizationModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.checkerframework.framework.qual.DefaultQualifier.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Base_Class_Utility.Baseclass;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFile_Utilities;
import GenericUtilities.WebDriverUtility;
import POM_Utilities.CreateNewOrgPOMPAge;
import POM_Utilities.HOME_POMPage;
import POM_Utilities.Login_POMPage;
import POM_Utilities.OrgInfoPOMPage;
import POM_Utilities.Org_POMPage;


@SuppressWarnings("unused")
@Listeners(Listeners_Utility.Listeners.class)
public class OrgScenariosTest extends Baseclass {
	
	@Test(groups="smoke" , retryAnalyzer = Listeners_Utility.IRetryAnalyser.class)

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

//		PropertyFile_Utilities prop = new PropertyFile_Utilities();
//
//		String browser = prop.FetchingDataFromPropertyFile("Browser");
//		String url = prop.FetchingDataFromPropertyFile("Url");
//		String un = prop.FetchingDataFromPropertyFile("Username");
//		String pw = prop.FetchingDataFromPropertyFile("Password");
//		String timeouts = prop.FetchingDataFromPropertyFile("Timeouts");

		// Fetch random number

		JavaUtility jutil = new JavaUtility();
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel

		FileInputStream efis = new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");

		Workbook wb = WorkbookFactory.create(efis);

		String Orgname = wb.getSheet("Org_Data").getRow(1).getCell(2).toString() + randonNum;

//		// Launch the browser
//
////		WebDriver driver = new ChromeDriver();
//		WebDriver driver=null;
//		if(BROWSER.equals("chrome")) {
//			
//			 driver=new ChromeDriver();
//		}
//		else if(BROWSER.equals("edge")) {
//			driver=new EdgeDriver();
//		}
//		else {
//			driver=new ChromeDriver();
//		}

		WebDriverUtility wutil = new WebDriverUtility();
//		// Maximize the window
//
//		wutil.maximizethewindow(driver);
//
//		wutil.waitUntilElementFound(driver, timeouts);
//		// Navigate to appln
//
//		wutil.navigateToAnApplication(driver, url);
//
//		// Login to application
//		Login_POMPage login = new Login_POMPage(driver);
//
//		login.login(un, pw);

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

		driver.findElement(By.xpath("//a[text()='" + Orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

//		Thread.sleep(3000);
//
//		// click on signout
//		home.logout(driver);
//		// close the browser
//
//		wutil.quitTheBrowser(driver);

	}
	
	@Test(groups="reg" , retryAnalyzer = Listeners_Utility.IRetryAnalyser.class)

	public void CreateOrg_IndType() throws Exception {

		// Fetch data from Properties File

//		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
//		Properties p = new Properties();
//		p.load(pfis);
//		String browser = p.getProperty("Browser");
//		String url = p.getProperty("Url");
//		String un = p.getProperty("Username");
//		String pwd = p.getProperty("Password");
//		String TIME = p.getProperty("Timeouts");

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

//		// Launch the browser
//
//		WebDriver driver=null;
//		if(BROWSER.equals("chrome")) {
//			
//			 driver=new ChromeDriver();
//		}
//		else if(BROWSER.equals("edge")) {
//			driver=new EdgeDriver();
//		}
//		else {
//			driver=new ChromeDriver();
//		}

		WebDriverUtility wutil = new WebDriverUtility();
//		// Maximize the window
//
//		wutil.maximizethewindow(driver);
//
//		wutil.waitUntilElementFound(driver, TIME);
//		// Navigate to appln
//
//		wutil.navigateToAnApplication(driver, url);
//
//		Login_POMPage login = new Login_POMPage(driver);
//
//		login.login(un, pwd);

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
//		// click on signout
//		home.logout(driver);
//
//		Thread.sleep(2000);
//
//		wutil.quitTheBrowser(driver);

	}
	
	@Test(groups="reg" , retryAnalyzer = Listeners_Utility.IRetryAnalyser.class)

	public void creteOrg_Phno_Test() throws IOException, InterruptedException {

//		// Fetch data from Properties File
//
//		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
//		Properties p = new Properties();
//		p.load(pfis);
//		String browser = p.getProperty("Browser");
//		String url = p.getProperty("Url");
//		String un = p.getProperty("Username");
//		String pwd = p.getProperty("Password");
//		String TIME = p.getProperty("Timeouts");

		// Fetch random number

		JavaUtility jutil = new JavaUtility();
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel file

		FileInputStream efis = new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String orgname = wb.getSheet("Org_Data").getRow(5).getCell(2).toString() + randonNum;
		String phno = wb.getSheet("Org_Data").getRow(5).getCell(3).toString();

		// Launch the browser

//		WebDriver driver=null;
//		if(BROWSER.equals("chrome")) {
//			
//			 driver=new ChromeDriver();
//		}
//		else if(BROWSER.equals("edge")) {
//			driver=new EdgeDriver();
//		}
//		else {
//			driver=new ChromeDriver();
//		}

		WebDriverUtility wutil = new WebDriverUtility();
//		// Maximize the window
//
//		wutil.navigateToAnApplication(driver, url);
//		wutil.maximizethewindow(driver);
//
//		wutil.waitUntilElementFound(driver, TIME);
//		// Navigate to appln
//
//		Login_POMPage login = new Login_POMPage(driver);
//
//		login.login(un, pwd);

//		wutil.navigateToAnApplication(driver, url);
//		//Identify un textfield and pass the username
//		
//			driver.findElement(By.name("user_name")).sendKeys(un);
//			
//			driver.findElement(By.name("user_password")).sendKeys(pwd);
//			
//			//Identify login button
//			
//			driver.findElement(By.id("submitButton")).click();
//			
		HOME_POMPage home = new HOME_POMPage(driver);

		// Identify organization link and click

//			driver.findElement(By.linkText("Organizations")).click();
		home.getOrg_tab();

		// Identify create plus icon n click

//		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		Org_POMPage OPOM = new Org_POMPage(driver);
		OPOM.getOrg_PlusIcon();

		// click on org name textfield enter data

//		driver.findElement(By.name("accountname")).sendKeys(Orgname);

		// click on org name textfield enter data

//		driver.findElement(By.name("accountname")).sendKeys(orgname);

		CreateNewOrgPOMPAge neworg = new CreateNewOrgPOMPAge(driver);

		neworg.getOrgnameTF(orgname);
		neworg.getPhnoTF(phno);

//		driver.findElement(By.id("phone")).sendKeys(phno);

		// identify save button and click

//		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		neworg.getSavebtn();
		// identify header in org info page and validate

//		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		OrgInfoPOMPage orginfo = new OrgInfoPOMPage(driver);

		String actualOrgname = orginfo.getVerifyOrgnameTF();

		if (actualOrgname.contains(orgname)) {
			System.out.println("create Org with phno verified orgname test pass");

		} else {

			System.out.println("create Org with phno verified orgname  test fail");

		}
		// Validate phno

//		WebElement VerifyPhno = driver.findElement(By.id("mouseArea_Phone"));

		String actualphno = orginfo.getVerifyPhno();

		if (actualphno.contains(phno)) {

			System.out.println("Create org with phno Verified phno Test pass");

		}

		else {
			System.out.println("Create org with phno Verified phno Test fail");

		}

		// Identify organization link and click

		home.getOrg_tab();
		// identify and click on del link

		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

		Thread.sleep(3000);
//		// click on signout
//		home.logout(driver);
//
//		Thread.sleep(2000);
//
//		wutil.quitTheBrowser(driver);

	}


}
