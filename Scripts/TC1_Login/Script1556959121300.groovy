import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver.Options
import java.net.HttpURLConnection;
import java.util.concurrent.TimeUnit;

WebUI.openBrowser('')
WebUI.navigateToUrl('http://newtours.demoaut.com/')
WebUI.delay(4)
String title = WebUI.getWindowTitle()
WebUI.verifyEqual(title, title, FailureHandling.CONTINUE_ON_FAILURE)



// Code to verify all links of web Page

WebDriver driver = DriverFactory.getWebDriver()

String homePage = "http://newtours.demoaut.com/";
String url = "";
HttpURLConnection huc = null;
int respCode = 200;


WebUI.navigateToUrl(homePage)

List<WebElement> links = driver.findElements(By.tagName("a"));

Iterator<WebElement> it = links.iterator();

while(it.hasNext()){
	
	url = it.next().getAttribute("href");
	
	System.out.println(url);

	if(url == null || url.isEmpty()){
System.out.println("URL is either not configured for anchor tag or it is empty");
		continue;
	}
	
	if(!url.startsWith(homePage)){
		System.out.println("URL belongs to another domain, skipping it.");
		continue;
	}
	
	try {
		huc = (HttpURLConnection)(new URL(url).openConnection());
		
		huc.setRequestMethod("HEAD");
		
		huc.connect();
		
		respCode = huc.getResponseCode();
		
		if(respCode >= 400){
			System.out.println(url+" is a broken link");
		}
		else{
			System.out.println(url+" is a valid link");
		}
			
	} catch (MalformedURLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

driver.quit();


