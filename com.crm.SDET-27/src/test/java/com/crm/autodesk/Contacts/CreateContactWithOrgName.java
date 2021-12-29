package com.crm.autodesk.Contacts;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
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
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.ContactsPage;
import com.crm.autodesk.ObjectRepository.CreateContactPage;
import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.LoginPage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;
import com.crm.autodesk.genericutility.ExcelUtility;
import com.crm.autodesk.genericutility.FileUtility;
import com.crm.autodesk.genericutility.JavaUtility;
import com.crm.autodesk.genericutility.WebDriverUtility;


public class CreateContactWithOrgName extends BaseClass {

	@Test
	public void createContctWithOrgname() throws Throwable {
		
	/* Get Random data */
		
		int contactname = jLib.getRandomNum();
		int random = jLib.getRandomNum();
		
	//fetch contact name from excel
		String name = eLib.getDataFromExcel("Sheet1", 3, 5);
		
	// fetch organisation name from excel
		String orgname = eLib.getDataFromExcel("Sheet1", 3, 0);//c1.getStringCellValue()+random;
		
	//declare webdriver and initialize 
		
		wLib.waitForPageToGetLoad(driver);
		
	//create organization
		HomePage hp=new HomePage(driver);
		hp.clickOnOrganisationLink();
		OrganizationsPage orgPage=new OrganizationsPage(driver);
		orgPage.clickOnCreateOrganization();
		CreateOrganizationPage createOrgPage=new CreateOrganizationPage(driver);
		createOrgPage.createOrg(orgname);
		
		wLib.waitForElemnetToBeClickAble(driver, driver.findElement(By.className("dvHeaderText")));
		System.out.println("before click on contacts");
		hp.clickOnContactsLink();
		System.out.println("in contacts");
		
		ContactsPage cp=new ContactsPage(driver);
		cp.clickOnCreateContactLookUpImg();
		CreateContactPage createCnt=new CreateContactPage(driver);
		createCnt.createContact(driver, name, orgname);
		
		OrganizationInfoPage oIP=new OrganizationInfoPage(driver);
		String actualMSG = oIP.getOrgInfo();
		Assert.assertTrue(actualMSG.contains("abc"));
		
	}
}


