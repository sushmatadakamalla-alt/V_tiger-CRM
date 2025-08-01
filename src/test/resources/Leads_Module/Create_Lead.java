package Leads_Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class Create_Lead {
	@Test
	
	public void Create_LeadTest() throws Exception, IOException
	{
		
		//Fetch the data from Property file
		
				FileInputStream pfis=new FileInputStream("./src/test/resources/VtigerCommonData.properties");
				
				Properties p=new Properties();
				
				p.load(pfis);
				
				String Browser=p.getProperty("Browser");
				
				String url=p.getProperty("Url");
				String un=p.getProperty("Username");
				String pw=p.getProperty("Password");
				String time=p.getProperty("Timeouts");
				
				//Fetch data from Excel
				
				FileInputStream efis=new FileInputStream("./src/test/resources/Vtiger_TestData.xlsx");
				
				Workbook wb=WorkbookFactory.create(efis);
				
				String Lastname=wb.getSheet("Lead_Data").getRow(1).getCell(2).toString();
				String Companyname=wb.getSheet("Lead_Data").getRow(1).getCell(3).toString();
				
				
				//Launch the browser
				
				WebDriver driver=new ChromeDriver();
				
				//Maximize the window
				
				driver.manage().window().maximize();
				
				//Implicit wait
				long TIME=Long.parseLong(time);
				
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TIME));
				
				//Navigate to appln
				
				driver.get(url);
				
				//Identify un textfield and pass the username
				
				driver.findElement(By.name("user_name")).sendKeys(un);
				
				driver.findElement(By.name("user_password")).sendKeys(pw);
				
				//Identify login button
				
				driver.findElement(By.id("submitButton")).click();
				//Identify leads link and click
				driver.findElement(By.linkText("Leads")).click();
				
				//Identify create plus icon n click
				
				driver.findElement(By.xpath("//img[@title=\"Create Lead...\"]")).click();
				
				//Identify last name textfield and enter data
				
				driver.findElement(By.name("lastname")).sendKeys(Lastname);
				
				////Identify company name textfield and enter data
				
				driver.findElement(By.name("company")).sendKeys(Companyname);
				
				//Identify n click on save button
				

				driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();
				
				// and validate header
				
				
			WebElement	header=driver.findElement(By.xpath("//span[contains(text(),'[ L')]"));
			
			if(header.getText().contains(Lastname))
			{
				
				System.out.println("Create Lead test pass");
			}
			
			else {
				
				System.out.println("Create Lead test fail");
			}
				
			//Identify leads link and click
			driver.findElement(By.linkText("Leads")).click();
			
			//identify and click  on del link 
			
			driver.findElement(By.xpath("//a[text()='"+Lastname+"']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']")).click();
			
			Thread.sleep(3000);
			
			//Handle delete pop up
			
			Alert al=driver.switchTo().alert();
			
			
			al.accept();
			
			Thread.sleep(3000);
			
			WebElement acts  =driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
			
			Actions act=new Actions(driver);
			
			act.moveToElement(acts).perform();
			
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			
			Thread.sleep(2000);
			
			driver.quit();		
				
				
	}

}
