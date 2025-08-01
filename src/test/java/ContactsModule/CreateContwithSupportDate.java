package ContactsModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import GenericUtilities.WebDriverUtility;
import POM_Utilities.Cont_Info_POMPage;
import POM_Utilities.Cont_POMPAge;
import POM_Utilities.CreateNewContPOM;
import POM_Utilities.HOME_POMPage;
import POM_Utilities.Login_POMPage;

public class CreateContwithSupportDate {

	@Test

	public void ContactWithSupportDate() throws Exception {

		JavaUtility jutil = new JavaUtility();

		// Fetch the data from Property file

		FileInputStream pfis = new FileInputStream("./src/test/resources/VtigerCommonData.properties");

		Properties p = new Properties();

		p.load(pfis);

		String Browser = p.getProperty("Browser");

		String url = p.getProperty("Url");
		String un = p.getProperty("Username");
		String pw = p.getProperty("Password");
		String time = p.getProperty("Timeouts");

		// Fatch random number
		int randonNum = jutil.Randomnum();

		// Fetch data from Excel

		FileInputStream efis = new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");

		Workbook wb = WorkbookFactory.create(efis);

		String contactname = wb.getSheet("Cont_Data").getRow(5).getCell(2).toString() + randonNum;
		String startdate = wb.getSheet("Cont_Data").getRow(5).getCell(3).toString();
		String enddate = wb.getSheet("Cont_Data").getRow(5).getCell(4).toString();

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

		HOME_POMPage home = new HOME_POMPage(driver);

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

		if (actualcontactname.contains(contactname)) {
			System.out.println("create Contact test pass");

		} else {

			System.out.println("create Contact test fail");

		}

		// Validate Support start date

//		WebElement Verify_StartDate = driver.findElement(By.id("dtlview_Support Start Date"));

		String actualstartdate = coninfo.getVerifyStartDateTF();

		if (actualstartdate.contains(startdate)) {
			System.out.println("create Contactwithsupport date verified start date test pass");

		} else {

			System.out.println("\"create Contactwithsupport date verified start date test fail");

		}
		// validate support end date

//		WebElement Verify_EndDate = driver.findElement(By.id("dtlview_Support End Date"));

		String actualenddate = coninfo.getVerifyEndDateTF();

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

		Thread.sleep(3000);
		// click on signout
		home.logout(driver);

		wutil.quitTheBrowser(driver);

	}

}
