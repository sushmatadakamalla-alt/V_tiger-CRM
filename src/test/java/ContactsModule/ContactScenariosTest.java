package ContactsModule;

import org.openqa.selenium.By;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.Status;

import Base_Class_Utility.Baseclass;
import GenericUtilities.ExcelUtilities;
import GenericUtilities.JavaUtility;
import GenericUtilities.WebDriverUtility;
import POM_Utilities.Cont_Info_POMPage;
import POM_Utilities.Cont_POMPAge;
import POM_Utilities.CreateNewContPOM;
import POM_Utilities.CreateNewOrgPOMPAge;
import POM_Utilities.HOME_POMPage;
import POM_Utilities.OrgInfoPOMPage;
import POM_Utilities.Org_POMPage;

@Listeners(Listners_Utilities.Listeners.class)
public class ContactScenariosTest extends Baseclass {
	
	@Test(groups="reg" )
//	, retryAnalyzer = Listeners_Utility.IRetryAnalyser.class)
	public void Create_Orgwith_Cont() throws Exception {

		// Fetch random number
	
		JavaUtility jutil = new JavaUtility();
		int randonNum = jutil.Randomnum();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Fetch random number");

		// Fetch data from Excel
		ExcelUtilities ex=new ExcelUtilities();
		String contactname =ex.FetchDataFromExcelFile("Cont_Data", 8, 3)+randonNum;
		String orgname =ex.FetchDataFromExcelFile("Cont_Data", 8, 2)+randonNum;
		Listners_Utilities.Listeners.test.log(Status.INFO,"Fetch data from excel");


		WebDriverUtility wutil = new WebDriverUtility();
//		// Maximize the window
//
//		wutil.maximizethewindow(driver);
//
//		wutil.waitUntilElementFound(driver, time);
//		// Navigate to appln
//
//		wutil.navigateToAnApplication(driver, url);
//
//		Login_POMPage login = new Login_POMPage(driver);
//
//		login.login(un, pw);

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
		Listners_Utilities.Listeners.test.log(Status.INFO,"Navigate to home page");
		
		home.getOrg_tab();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on Organization link");

		new SoftAssert();
		
		Org_POMPage OPOM = new Org_POMPage(driver);

		
		// Identify create plus icon n click

		//driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		OPOM.getOrg_PlusIcon();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on Create Contact Plus Icon ");
		
		// click on org name textfield enter data
		
		CreateNewOrgPOMPAge neworg = new CreateNewOrgPOMPAge(driver);

		neworg.getOrgnameTF(orgname);
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"click on org name textfield enter data");

		//driver.findElement(By.name("accountname")).sendKeys(orgname);

		
		
		// identify save button and click

		//driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		neworg.getSavebtn();
		Listners_Utilities.Listeners.test.log(Status.INFO,"click on Save button");
		

		// identify header in org info page and validate

		OrgInfoPOMPage orginfo = new OrgInfoPOMPage(driver);

		String actualOrgname_orginfo = orginfo.getVerifyOrgnameTF();
		System.out.println(actualOrgname_orginfo+orgname);
		
		Listners_Utilities.Listeners.test.log(Status.PASS,"Validate header on org info page");

//		Assert.assertEquals(actualOrgname_orginfo, " "+orgname);
		System.out.println(actualOrgname_orginfo+orgname);
		
//		if (actualOrgname.contains(orgname)) {
//			System.out.println("create Org test pass");
//
//		} else {
//
//			System.out.println("create Org test fail");
//
//		}

		// Identify Contacts link and click

		//driver.findElement(By.linkText("Contacts")).click();
		home.getCont_tab();
		Listners_Utilities.Listeners.test.log(Status.INFO,"click on Contacts link");
		

		// Identify create plus icon n click

//		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		Cont_POMPAge cont = new Cont_POMPAge(driver);

		cont.getContPlusIcon();
		Listners_Utilities.Listeners.test.log(Status.INFO,"click on Plus icon on contacs page");

		// Identify contactname tf and enter contact name

//		driver.findElement(By.name("lastname")).sendKeys(contactname);

		CreateNewContPOM newcont = new CreateNewContPOM(driver);
		newcont.getLastnameTF(contactname);
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"Identify contactname tf and enter contact name");

		newcont.getOrgPlusICon();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on OrgTF Plus Icon ");

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
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"Windowhandles");

//		driver.switchTo().window(pwid);

		// identify save button and click

//		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		newcont.getSavebtn();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on Save Button ");

		// identify header in contact info page and validateQ3

//		WebElement header1 = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));

		Cont_Info_POMPage coninfo = new Cont_Info_POMPage(driver);
		String actuallastname = coninfo.getVerifylastnameTF();
		System.out.println(actuallastname+contactname);
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"Validate header in contact info page ");
//		Assert.assertEquals(actuallastname, contactname);
//		if (actuallastname.contains(contactname)) {
//			System.out.println("create Contact with org verified last name test pass");
//
//		} else {
//
//			System.out.println("create Contact with org verified last name  test fail");
//
//		}

		// identify orgname in contact page

//		String Verifyorgname = driver.findElement(By.id("mouseArea_Organization Name")).getText();

		String actualorgname = coninfo.getVerifyOrgnameTF();
		System.out.println(actualorgname+orgname);
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"Validate Org name in contact info page ");
//		Assert.assertEquals(actualorgname, orgname);

//		if (actualorgname.contains(orgname)) {
//			System.out.println("create contact with Org verified orgname test pass");
//
//		} else {
//
//			System.out.println("create contact with Org verified orgname test fail");
//
//		}

		// Identify Contact tab and click

//				driver.findElement(By.linkText("Contacts")).click();
		home.getCont_tab();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on contacts link ");

		// identify and click on del link

		driver.findElement(By.xpath(
				"//a[text()='"+contactname+"']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on delete link ");

		Thread.sleep(3000);
		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"Handle Alert pop up");

		Thread.sleep(3000);
		// Identify organization link and click

//			driver.findElement(By.linkText("Organizations")).click();
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on Organization link ");
		home.getOrg_tab();

		// identify and click on del link

//		driver.findElement(
//				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
//				.click();
		
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"Click on delete link ");

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);
		Listners_Utilities.Listeners.test.log(Status.INFO,"Handle Alert pop up");

		Thread.sleep(3000);
//		// Mousehover on admin and signout
//
//		WebElement acts1 = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
//		wutil.mousehoveronAnElement(driver, acts1);
//
//		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
//
//		Thread.sleep(2000);

//		// click on signout
//		home.logout(driver);
//		// close the browser
//		wutil.quitTheBrowser(driver);
//		soft.assertAll();
		
		Listners_Utilities.Listeners.test.log(Status.INFO,"Mousehover on admin and signout");


	}
	
	@Test(groups="smoke" , retryAnalyzer = Listeners_Utility.IRetryAnalyser.class)

	public void CreateNewContTest() throws Exception {

//		// Fetch the data from Property file
//
//		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
//
//		Properties p = new Properties();
//
//		p.load(pfis);
//
//		String Browser = p.getProperty("Browser");
//
//		String url = p.getProperty("Url");
//		String un = p.getProperty("Username");
//		String pw = p.getProperty("Password");
//		String time = p.getProperty("Timeouts");

		// Fetch random number

		JavaUtility jutil = new JavaUtility();
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel
		
		ExcelUtilities ex=new ExcelUtilities();
		String contactname =ex.FetchDataFromExcelFile("Cont_Data", 1, 2)+randonNum;


//		FileInputStream efis = new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");
//
//		Workbook wb = WorkbookFactory.create(efis);
//
//		String contactname = wb.getSheet("Cont_Data").getRow(1).getCell(2).toString() + randonNum;

		// Launch the browser

//		WebDriver driver = new ChromeDriver();
		
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
		// Maximize the window

//		wutil.maximizethewindow(driver);
//
//		wutil.waitUntilElementFound(driver, time);
//		// Navigate to appln
//
//		wutil.navigateToAnApplication(driver, url);
//
//		Login_POMPage login = new Login_POMPage(driver);
//
//		login.login(un, pw);

//					//Identify un textfield and pass the username
//					
//					driver.findElement(By.name("user_name")).sendKeys(un);
//					
//					driver.findElement(By.name("user_password")).sendKeys(pw);
//					
//					//Identify login button
//					
//					driver.findElement(By.id("submitButton")).click();

		HOME_POMPage home = new HOME_POMPage(driver);
		
//		SoftAssert soft=new SoftAssert();
//		soft.assertEquals(home.getHeader(),"Home");
		// Identify Contacts link and click

//					driver.findElement(By.linkText("Contacts")).click();
		home.getCont_tab();

		// Identify create plus icon n click

//					driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		Cont_POMPAge cont = new Cont_POMPAge(driver);
		cont.getContPlusIcon();

		// Identify contact name and enter data

//					driver.findElement(By.name("lastname")).sendKeys(contactname);

		CreateNewContPOM newcont = new CreateNewContPOM(driver);
		newcont.getLastnameTF(contactname);

		// identify save button and click

//					driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		newcont.getSavebtn();

		// identify header in contact info page and validate

//					WebElement  header=driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]"));

		Cont_Info_POMPage coninfo = new Cont_Info_POMPage(driver);
		String actuallastname = coninfo.getVerifylastnameTF();
		
//		Assert.assertEquals(actuallastname, contactname);

		if (actuallastname.contains(contactname)) {
			System.out.println("create Contact test pass");

		} else {

			System.out.println("create Contact test fail");

		}

		// Identify Contacts link and click

//							driver.findElement(By.linkText("Contacts")).click();
		home.getCont_tab();

		// identify and click on del link

		driver.findElement(By.xpath(
				"//a[text()='" + contactname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

		Thread.sleep(3000);
//		// click on signout
//		home.logout(driver);
//
//		wutil.quitTheBrowser(driver);
//		soft.assertAll();

	}
	
	@Test(groups="reg" , retryAnalyzer = Listeners_Utility.IRetryAnalyser.class)

	public void ContactWithSupportDate() throws Exception {

		JavaUtility jutil = new JavaUtility();

//		// Fetch the data from Property file
//
//		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");
//
//		Properties p = new Properties();
//
//		p.load(pfis);
//
//		String Browser = p.getProperty("Browser");
//
//		String url = p.getProperty("Url");
//		String un = p.getProperty("Username");
//		String pw = p.getProperty("Password");
//		String time = p.getProperty("Timeouts");

		// Fatch random number
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel

		ExcelUtilities ex=new ExcelUtilities();
		String contactname =ex.FetchDataFromExcelFile("Cont_Data", 5, 2)+randonNum;
		ex.FetchDataFromExcelFile("Cont_Data", 8, 2);
		String startdate =ex.FetchDataFromExcelFile("Cont_Data", 5, 3)+randonNum;
		String enddate =ex.FetchDataFromExcelFile("Cont_Data", 5, 4)+randonNum;
		

		// Launch the browser

//		WebDriver driver = new ChromeDriver();
		
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
//		wutil.waitUntilElementFound(driver, time);
//		// Navigate to appln
//
//		wutil.navigateToAnApplication(driver, url);
//
//		Login_POMPage login = new Login_POMPage(driver);
//
//		login.login(un, pw);

//			//Identify un textfield and pass the username
//			
//			driver.findElement(By.name("user_name")).sendKeys(un);
//			
//			driver.findElement(By.name("user_password")).sendKeys(pw);
//			
//			//Identify login button
//			
//			driver.findElement(By.id("submitButton")).click();

		HOME_POMPage home = new HOME_POMPage(driver);
//
//		SoftAssert soft=new SoftAssert();
//		soft.assertEquals(home.getHeader(),"Home");
		// Identify Contacts link and click

//			driver.findElement(By.linkText("Contacts")).click();

		home.getCont_tab();

		// Identify create plus icon n click

//		driver.findElement(By.xpath("//img[@title=\"Create Contact...\"]")).click();

		Cont_POMPAge cont = new Cont_POMPAge(driver);
		cont.getContPlusIcon();

		// Identify last name and enter data

//		driver.findElement(By.name("lastname")).sendKeys(contactname);

		CreateNewContPOM newcont = new CreateNewContPOM(driver);
		newcont.getLastnameTF(contactname);

		// Identify Supportstart date

//		WebElement startdate_TF = driver.findElement(By.name("support_start_date"));
		newcont.getStartDateTF(startdate);

		// Identidy Supportenddate text field

//		WebElement enddate_TF = driver.findElement(By.name("support_end_date"));
//
//		String endDate = jutil.getDateAfterSpecificDays(30);
//		enddate_TF.sendKeys(endDate);
//		System.out.println(endDate);

		newcont.getEndDateTF(enddate);

		//// identify save button and click

//		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		newcont.getSavebtn();

		// identify header in contact info page and validate

//		WebElement header = driver.findElement(By.xpath("//span[@class=\"dvHeaderText\"]"));
		Cont_Info_POMPage coninfo = new Cont_Info_POMPage(driver);
		String actualcontactname = coninfo.getVerifylastnameTF();
		
//		Assert.assertEquals(actualcontactname, contactname);

		if (actualcontactname.contains(contactname)) {
			System.out.println("create Contact test pass");

		} else {

			System.out.println("create Contact test fail");

		}

		// Validate Support start date

//		WebElement Verify_StartDate = driver.findElement(By.id("dtlview_Support Start Date"));

		String actualstartdate = coninfo.getVerifyStartDateTF();
		
//		Assert.assertEquals(actualstartdate, startdate);

		if (actualstartdate.contains(startdate)) {
			System.out.println("create Contactwithsupport date verified start date test pass");

		} else {

			System.out.println("create Contactwithsupport date verified start date test fail");

		}
		// validate support end date

//		WebElement Verify_EndDate = driver.findElement(By.id("dtlview_Support End Date"));

		String actualenddate = coninfo.getVerifyEndDateTF();
		
//		Assert.assertEquals( actualenddate, enddate);
		if (actualenddate.contains(enddate)) {
			System.out.println("create Contactwithsupport date verified end date test pass");

		} else {

			System.out.println("\"create Contactwithsupport date verified end date test fail");

		}

		// Identify Contacts link and click

		home.getCont_tab();
//					driver.findElement(By.linkText("Contacts")).click();

		// identify and click on del link

		driver.findElement(By.xpath(
				"//a[text()='" + contactname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		Thread.sleep(3000);

		// Handle delete pop up

		wutil.HandleAlertpopupAndClickOK(driver);

//		Thread.sleep(3000);
//		// click on signout
//		home.logout(driver);
//
//		wutil.quitTheBrowser(driver);
		
//		soft.assertAll();
////
}



}
