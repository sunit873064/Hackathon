package testScenario;

import java.util.Hashtable;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hackathon.Utilities.ReadTestData;
import Hackathon.identifyCourse.baseUI;

public class FormTest extends baseUI{
	WebDriver driver=null;
	String nodeUrl;
	@DataProvider
	public Object[][] getDataTestOne(){
		return ReadTestData.getTestData("courseTestData.xlsx", "Data3", "TestOne");
	}
	
	@Test(dataProvider="getDataTestOne",priority=1)
	public void TestOne(Hashtable<String, String> dataTable) throws Exception {
		
		logger=report.createTest("Business Form Test");
		driver=invokeBrowser(dataTable.get("Browser"));
		//driver=invokeBrowser2();
		openUrl("websiteUrl");
		Thread.sleep(2000);
		//driver.get("https://www.udemy.com/");
		
		clickElement("UdemyForBuisness_xpath",null);
		switchTab();
		Thread.sleep(5000);
		
		enterText("firstName_xpath",dataTable.get("First Name"));
		Thread.sleep(1000);
		enterText("lastName_xpath",dataTable.get("Last Name"));
		Thread.sleep(1000);
		enterText("workEmail_xpath",dataTable.get("Work Email"));
		Thread.sleep(1000);
		enterText("phone_xpath",dataTable.get("Phone"));
		Thread.sleep(1000);
		enterText("companyName_xpath",dataTable.get("Company Name"));
		Thread.sleep(1000);
		enterText("jobTitle_xpath",dataTable.get("Job Title"));
		Thread.sleep(2000);
		dropSelect("companySize_xpath",dataTable.get("Company size"));
		Thread.sleep(2000);
		dropSelect("peopleSize_xpath",dataTable.get("people"));
		Thread.sleep(2000);
		clickElement("submitBtn_xpath",null);
		Thread.sleep(3000);
		valid(dataTable.get("Work Email"),dataTable.get("Phone"),dataTable.get("First Name"),dataTable.get("Last Name"));
		//print(dataTable.get("Work Email"));
		//print(getText("validEmailErrorMsg_xpath"));
		//System.out.println(getText("validEmailErrorMsg_xpath"));
		Thread.sleep(3000);
		tearDown();
		switchToParentTab();
		
		Thread.sleep(2000);
		
		quitBrowser();
		
	}
	
	@AfterTest
	public void endReport() {
		report.flush();
	}

}
