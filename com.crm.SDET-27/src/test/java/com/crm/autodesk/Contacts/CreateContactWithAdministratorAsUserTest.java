package com.crm.autodesk.Contacts;

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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.genericutility.BaseClass;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;

public class CreateContactWithAdministratorAsUserTest extends BaseClass{

		@Test
		public void createCntctWithAdminAsUserTest() throws Throwable{
			int randomNum=jLib.getRandomNum();
			//reading data from excel
			String cnt_name = eLib.getDataFromExcel("Sheet1", 2, 5)+randomNum;
			
			wLib.waitForPageToGetLoad(driver);
			
			HomePage hp=new HomePage(driver);
			hp.clickOnContactsLink();
			
			//navigate to Contacts Page
			ContactsPage cp=new ContactsPage(driver);
			cp.clickOnCreateContactLookUpImg();
			
			//create new contact
			CreateContactPage newContact=new CreateContactPage(driver);
			newContact.createContact(driver, cnt_name, cnt_name);
			
		}
	}


