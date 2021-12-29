package com.crm.autodesk.Organisation;

import org.testng.annotations.Test;

import com.crm.autodesk.ObjectRepository.CreateOrganizationPage;
import com.crm.autodesk.ObjectRepository.HomePage;
import com.crm.autodesk.ObjectRepository.OrganizationInfoPage;
import com.crm.autodesk.ObjectRepository.OrganizationsPage;
import com.crm.autodesk.genericutility.BaseClass;

public class CreateOrgTest extends BaseClass{

	@Test(groups="RegressionSuite")
	public void createOrgTest() throws Throwable {
		int randomNum=jLib.getRandomNum();
		String orgName = eLib.getDataFromExcel("Sheet1", 1, 2) + randomNum;
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOnOrganisationLink();
		
		OrganizationsPage orgPage = new OrganizationsPage(driver);
        orgPage.clickOnCreateOrganization();
        
        CreateOrganizationPage createNewOrg = new CreateOrganizationPage(driver);
        createNewOrg.createOrg(orgName);
        
        OrganizationInfoPage orginfoPage = new OrganizationInfoPage(driver);
        String  actSuccesfullMg =  orginfoPage.getOrgInfo();
        if(actSuccesfullMg.contains(orgName)) {
        	System.out.println(orgName + "==>created successfully");
        }else {
        	System.out.println(orgName + "==> not created successfully");

        }



		
		
	}
}
