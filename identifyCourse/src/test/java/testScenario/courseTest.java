package testScenario;

import java.util.Hashtable;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Hackathon.Utilities.ReadTestData;
import Hackathon.identifyCourse.baseUI;

public class courseTest extends baseUI {
	
WebDriver driver=null;

@DataProvider
public Object[][] getDataTestOne(){
	return ReadTestData.getTestData("courseTestData.xlsx", "Data2", "TestOne");
}

String nodeUrl;
	
@Test(dataProvider="getDataTestOne",priority=1)
public void testOne(Hashtable<String, String> dataTable) throws Exception {
		
		logger=report.createTest("Course Test");
		
		driver=invokeBrowser(dataTable.get("Browser"));
		//driver=invokeBrowser2();
		
		openUrl("websiteUrl");
		
		//Search for course
		clickElement("searchBox_xpath",null);
		enterText("searchBox_xpath",dataTable.get("Course Name")+Keys.ENTER);
		Thread.sleep(5000);
		
		scrollView("levelFilter_xpath");
		Thread.sleep(1000); 
		
		//Apply level filter as Beginner
		//String a="Beginner";
		//clickElement("levelFilter_xpath",null);
		clickElement("//label[text()='"+dataTable.get("Level")+"']",dataTable.get("Level"));
		Thread.sleep(5000);
		clickElement("levelFilter_xpath",null);
		
		//Apply language filter as English
		//String b="English";
		clickElement("languageFilter_xpath",null);
		clickElement("seeMore_xpath",null);
		clickElement("//label[text()='"+dataTable.get("Language")+"']",dataTable.get("Language"));
		Thread.sleep(5000);
		clickElement("languageFilter_xpath",null);
		Thread.sleep(3000);
		
		
		scrollView("languageLearningHeading_xpath");
		Thread.sleep(5000);
		
		//Extract the data of total course
	
		String result=getText("totalCount_xpath");
		print("Total results for applied filter i.e "+dataTable.get("Level")+" and "+dataTable.get("Language")+" : "+result);
		//System.out.println("Total results for applied filter i.e "+dataTable.get("Level")+" and "+dataTable.get("Language")+" : "+result);

		
		quitBrowser();
	}
	
	@AfterTest
	public void endReport() {
		report.flush();
	}

}
