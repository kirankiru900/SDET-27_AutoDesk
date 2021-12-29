package com.crm.autodesk.Contacts;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactInfoPage;
import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.genericutility.BaseClass;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;



public class CreateContactWithMarketingGroup extends BaseClass{

		@Test
		public void createCntctWithMarketingGrp() throws Throwable {
		String contact_Name = eLib.getDataFromExcel("Sheet1", 2, 5);
		String group_Name = eLib.getDataFromExcel("Sheet1", 2, 6);
		
	//	WebDriver driver=new ChromeDriver();
		wLib.waitForPageToGetLoad(driver);
		
		HomePage hP=new HomePage(driver);
		hP.clickOnContactsLink();
		
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();
		
		CreateContactPage cCP=new CreateContactPage(driver);
		cCP.createContact(contact_Name, driver, group_Name);
		
		WebElement ele=driver.findElement(By.xpath("//select[@name='assigned_group_id']"));
		wLib.select(ele, group_Name);
		
		cCP.clickOnSaveBtn();
		
		ContactInfoPage cIP=new ContactInfoPage(driver);
		String actual_Msg = cIP.getContHeaderText();
		
		Assert.assertTrue(actual_Msg.contains(contact_Name));
		
		EventFiringWebDriver edriver=new EventFiringWebDriver(driver);
		File srcFile=edriver.getScreenshotAs(OutputType.FILE);
		//File dstFile=new File("./screenshot"+)
		}
		
}


