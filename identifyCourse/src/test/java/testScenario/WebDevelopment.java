package testScenario;

import java.util.Hashtable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hackathon.Utilities.ReadTestData;
import Hackathon.identifyCourse.baseUI;


public class WebDevelopment extends baseUI{
	
	WebDriver driver=null;
	String nodeUrl;
	
	@DataProvider
	public Object[][] getDataTestOne(){
		return ReadTestData.getTestData("courseTestData.xlsx", "Data1", "TestOne");
	}
	
	@Test(dataProvider="getDataTestOne",priority=1)
	//@Test
public void testOne(Hashtable<String, String> dataTable) throws Exception {
		
		logger=report.createTest("WebDevelopment Course Test");
		
		driver=invokeBrowser(dataTable.get("Browser"));
		//driver=invokeBrowser1();
		openUrl("websiteUrl");
		Thread.sleep(2000);
		//driver.get("https://www.udemy.com/");
		//Search for course
		clickElement("searchBox_xpath",null);
		enterText("searchBox_xpath",dataTable.get("Course Name")+Keys.ENTER);
		//enterText("searchBox_xpath","Web development"+Keys.ENTER);
		Thread.sleep(5000);
		
		verifyCourseSearched("Heading_xpath","Web Development Courses");
		
		scrollView("levelFilter_xpath");
		Thread.sleep(1000); 
		
		//Apply level filter as Beginner
		clickElement("levelFilter_xpath",null);
		clickElement("beginnerFilter_xpath",null);
		Thread.sleep(5000);
		clickElement("levelFilter_xpath",null);
		
		//Apply language filter as English
		clickElement("languageFilter_xpath",null);
		clickElement("englishFilter_xpath",null);
		Thread.sleep(5000);
		clickElement("languageFilter_xpath",null);
		Thread.sleep(3000);
		
		
		scrollView("webdevelopmentHeading_xpath");
		Thread.sleep(5000);
		
		//Extract the data of first 2 courses
		printData("Topic","coursesTitle_xpath");
		printData("Total hrs for Topic","courseHrs_xpath");
		printData("Topic","courseRating_xpath");
	
		//String result=getText("totalCount_xpath");
		//System.out.println("Total results for applied filter:"+result);

		
		quitBrowser();
	}
	
	@AfterTest
	public void endReport() {
		report.flush();
	}


}
