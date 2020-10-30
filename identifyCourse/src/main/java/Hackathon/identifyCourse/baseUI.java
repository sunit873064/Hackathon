package Hackathon.identifyCourse;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Hackathon.Utilities.DateUtils;
import Hackathon.Utilities.ExtendReportManager;

public class baseUI {
	
	WebDriver driver=null;
	public Properties prop; 
	public ExtentReports report =ExtendReportManager.getReportInstance();
	public ExtentTest logger;
	static String parentHandle,nodeUrl;
	String value;
	
	SoftAssert softAssert = new SoftAssert();
	
	//**********************Invoke Browser*****************************//
	
	public WebDriver invokeBrowser(String browserName){
		
	try {
		if(browserName.equalsIgnoreCase("chrome")) {
			
			nodeUrl="http://192.168.29.20:5566/wd/hub";
		 	DesiredCapabilities capabilities=DesiredCapabilities.chrome();
		 	driver=new RemoteWebDriver(new URL(nodeUrl),capabilities);
			/*ChromeOptions co=new ChromeOptions();
			co.addArguments("--disable-infobars");
			co.addArguments("--disable-notifications");
			co.addArguments("--start-maximized");
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\chromedriver.exe");
			driver=new ChromeDriver(co);*/
			reportPass(browserName + " launched");
			
		}else if(browserName.equalsIgnoreCase("firefox")) {
			
			nodeUrl="http://192.168.29.20:5566/wd/hub";
		 	DesiredCapabilities capabilities=DesiredCapabilities.firefox();
		 	driver=new RemoteWebDriver(new URL(nodeUrl),capabilities);
			/*OperaOptions op=new OperaOptions();
			op.addArguments("--disable-infobars");
			op.addArguments("--disable-notifications");
			op.addArguments("--start-maximized");
			System.setProperty("webdriver.opera.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\operadriver.exe");
			driver=new OperaDriver(op);*/
			reportPass(browserName + " launched");
		}
		else if(browserName.equalsIgnoreCase("opera")) {
			nodeUrl="http://192.168.29.20:5566/wd/hub";
		 	DesiredCapabilities capabilities=DesiredCapabilities.opera();
		 	driver=new RemoteWebDriver(new URL(nodeUrl),capabilities);
			/*FirefoxOptions ff=new FirefoxOptions();
			ff.addArguments("--disable-infobars");
			ff.addArguments("--disable-notifications");
			ff.addArguments("--start-maximized");
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"\\src\\test\\resources\\driver\\geckodriver.exe");
			driver=new FirefoxDriver(ff);
			driver.manage().window().fullscreen();*/
			reportPass(browserName + " launched");
			
		}
	}catch(Exception e) {
		System.out.println("Wrong Browser name");
		reportFail(e.getMessage());
	}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		
		if(prop==null) {
			prop=new Properties();
			
			try {
			FileInputStream file=new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\objectRepository\\projectConfig.properties");
			prop.load(file);
			}catch(Exception e) {
				reportFail(e.getMessage());
			}
			
		}
	
		
		return driver;
	}		
	//**********************Open website*****************************//
	public void openUrl(String urlKey) {
		try {
		driver.get(prop.getProperty(urlKey));
		reportPass(urlKey + " Identified Successfully");
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	//**********************Close browser*****************************//
	public void tearDown() {
		driver.close();
	}
	
	//**********************Quit*****************************//
	public void quitBrowser() {
		driver.quit();
	}
	
	//**********************Enter Text*****************************//
	public void enterText(String xpathKey,String data) {
		try {
		getElement(xpathKey).sendKeys(data);
		reportPass(data + " - Entered successfully in WebElement : " +xpathKey);
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	//**********************Click element*****************************//
	public void clickElement(String xpathKey,String data) {
		try {
			if(data==null) {
		getElement(xpathKey).click();
		reportPass(xpathKey + " : Clicked Successfully");
			}else {
				getElement(xpathKey).click();
				reportPass(data + " : Clicked Successfully");
			}
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	//**********************Validation*****************************//
	public void valid(String email,String phone,String firstName,String lastName) throws InterruptedException {
		
		
		if(email=="") {
			String a=getText("validEmailErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);	
		}else if(phone =="") {
			String a=getText("phoneErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);	
		}else  if(firstName =="") {
			String a=getText("firstErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);	
		}else  if(lastName =="") {
			String a=getText("lastErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);	
		}else {
			reportPass("No error");
		}
		
		/*if(getElement("firstErrorMsg_id").isDisplayed()) {
			String a=getText("firstErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);
		}else if(getElement("lastErrorMsg_id").isDisplayed()) {
			String a=getText("lastErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);
		}else if(getElement("workEmail_xpath").isDisplayed()) {
			String a=getText("validEmailErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);
		}else if(getElement("phoneErrorMsg_id").isDisplayed()) {
			String a=getText("phoneErrorMsg_id");
			reportPass("Error Message : "+a);
			Thread.sleep(2000);
		}else {
			reportPass("No error");
		}*/
	}
	
	//**********************Verify course searched*****************************//
	public void verifyCourseSearched(String locatorKey,String data) {
		String actual=getText(locatorKey);
		if(actual.equalsIgnoreCase(data)) {
			reportPass(data + " : Valid data searched");
		}
		else {
			reportPass(actual+ " : Invalid data searched");
			//logger.log(Status.INFO,"Invalid data searched.So,closing the browser");	
		}
	}
	
	//**********************Verify Element*****************************//
	public boolean isElementPresent(String locatorKey) {
		try {
			if(getElement(locatorKey).isDisplayed()) {
				reportPass(locatorKey+" : Element is Displayed");
				return true;
			}
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}
	
	public boolean isElementSelected(String locatorKey) {
		try {
			if(getElement(locatorKey).isSelected()) {
				reportPass(locatorKey+" : Element is Selected");
				return true;
			}
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}
	
	public boolean isElementEnabled(String locatorKey) {
		try {
			if(getElement(locatorKey).isEnabled()) {
				reportPass(locatorKey+" : Element is Enabled");
				return true;
			}
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
		return false;
	}
	
	//**********************Switch tab*****************************//
	public void switchTab() {
		parentHandle=driver.getWindowHandle();
		Set<String> allWindows = driver.getWindowHandles();
		ArrayList<String> tabs= new ArrayList<String>(allWindows);   
		driver.switchTo().window(tabs.get(1));	
	}
	
	//**********************Switch to parent tab*****************************//
	public void switchToParentTab() {
		driver.switchTo().window(parentHandle);
	}
	
	
	//**********************Scroll*****************************//
	public void scrollView(String xpathKey) {
		WebElement filterElement = getElement(xpathKey);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", filterElement); 
	}
	
	//**********************Get Text*****************************//
	public String getText(String xpathKey) {
		logger.log(Status.INFO,"Locator Identified : "+xpathKey);
		try {
		value=getElement(xpathKey).getText();
		//reportPass(xpathKey+" : "+value);
		logger.log(Status.INFO,"value is : "+value);
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
		return value;
	}
	//**********************Value from dropdown*****************************//
	public void dropSelect(String locatorKey,String value) {
		try {
		logger.log(Status.INFO,"Locator Identified : "+value);
		Select drpCountry = new Select(getElement(locatorKey));
		drpCountry.selectByVisibleText(value);
		reportPass(value+" : Selected");
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	//**********************Store WebElements in List*****************************//
	public List<WebElement> storelist(String xpathKey) {
		List<WebElement> element= (List<WebElement>) driver.findElements(By.xpath(prop.getProperty(xpathKey)));
		return  element;
	}
	
	//**********************Get webElements*****************************//
	public WebElement getElement(String locatorKey) {
		WebElement ele=null;
		
		try {
		if(locatorKey.endsWith("_id")) {
			ele=driver.findElement(By.id(prop.getProperty(locatorKey)));
			logger.log(Status.INFO,"Locator Identified : "+locatorKey);
		}
		else if(locatorKey.endsWith("_xpath")) {
			ele=driver.findElement(By.xpath(prop.getProperty(locatorKey)));
			logger.log(Status.INFO,"Locator Identified : "+locatorKey);
		}
		else if(locatorKey.endsWith("_css")) {
			ele=driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
			logger.log(Status.INFO,"Locator Identified : "+locatorKey);
		}
		else if(locatorKey.endsWith("_linkText")) {
			ele=driver.findElement(By.linkText(prop.getProperty(locatorKey)));
			logger.log(Status.INFO,"Locator Identified : "+locatorKey);
		}
		else if(locatorKey.endsWith("_partialLinkText")) {
			ele=driver.findElement(By.partialLinkText(prop.getProperty(locatorKey)));
			logger.log(Status.INFO,"Locator Identified : "+locatorKey);
		}
		else if(locatorKey.endsWith("_name")) {
			ele=driver.findElement(By.name(prop.getProperty(locatorKey)));
			logger.log(Status.INFO,"Locator Identified : "+locatorKey);
		}
		else if(locatorKey.startsWith("//")){
			ele=driver.findElement(By.xpath(locatorKey));
			logger.log(Status.INFO,"Locator Identified : "+locatorKey);
		}
		else{
			reportFail("Failing the TestCase, Invalid Locator "+locatorKey);
			//Assert.fail("Failing the TestCase, Invalid Locator "+locatorKey);
		}
		}catch(Exception e) {
			reportFail(e.getMessage());
			e.printStackTrace();
			//Assert.fail("Failing the test Case: "+e.getMessage());
		}
		return ele;				
	}
	//**********************To print data*****************************//
	public void printData(String prefix1,String xpathKey) {
		
		try {
			logger.log(Status.INFO,"Locator Identified : "+xpathKey);
		List<WebElement> elementHeading= storelist(xpathKey);
		int i=1;
		for(WebElement e:elementHeading)
		{
			
			String result=e.getText();
			System.out.println( prefix1+" "+i+":"+result);
			logger.log(Status.INFO,prefix1+" "+i+":"+result);
			i++;
			if(i>2)
				break;
		}
		reportPass(xpathKey+" : Data Extracted");
		}catch(Exception e) {
			reportFail(e.getMessage());
		}
	}
	
	//**********************To print data*****************************//
	public void print(String data) {
		System.out.println(data);
		logger.log(Status.INFO,data);
	}
	
	//**********************Assertion Functions*****************************//
	public void assertTrue(boolean flag) {
		softAssert.assertTrue(flag);
	}
	
	public void assertFalse(boolean flag) {
		softAssert.assertTrue(flag);
	}
	
	public void assertequals(String actual,String expected) {
		softAssert.assertEquals(actual, expected);;
	}
	
	//**********************Reporting Functions*****************************//
	
	public void reportFail(String reportString) {
		logger.log(Status.FAIL, reportString);
		takeScreenShotOnFailure();
		Assert.fail(reportString);	
		quitBrowser();
	}
	
	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);	
	}
	
	@AfterMethod
	public void afterTest() {
		softAssert.assertAll();
	}
	
	//**********************Screenshot*****************************//
	public void takeScreenShotOnFailure() {
		TakesScreenshot takeScreenShot=(TakesScreenshot) driver;
		File sourceFile=takeScreenShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(System.getProperty("user.dir") + "//ScreenShot//" + DateUtils.getTimeStamp() + ".png");
		
		
		try {
			FileUtils.copyFile(sourceFile,destFile);
			logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "//ScreenShot//" + DateUtils.getTimeStamp() + ".png");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
