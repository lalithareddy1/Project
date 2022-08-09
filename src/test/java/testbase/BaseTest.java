package testbase;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.SkipException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import keywords.ApplicationKeywords;
import reports.ExtentManager;

public class BaseTest {
	
	public ApplicationKeywords app ;
	public ExtentReports reports;
	public ExtentTest test;
	
	@BeforeTest(alwaysRun = true)
	public void beforeTest(ITestContext context) {
		System.out.println("I will execute Before Test starts");
		app = new ApplicationKeywords();
		context.setAttribute("app", app);
		
		// initializing the reporting 
		reports = ExtentManager.getReports();
		test = reports.createTest(context.getCurrentXmlTest().getName());
		app.setReports(test);
		test.log(Status.INFO, "Stating Test ::::" + context.getCurrentXmlTest().getName());
		context.setAttribute("reports", reports);
		context.setAttribute("test", test);
		
	}

	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethod(ITestContext context) {
		System.out.println("I will execute Before each and every Method");
		app = (ApplicationKeywords) context.getAttribute("app");		
		test = (ExtentTest) context.getAttribute("test");
		
		String criticalFailure = (String) context.getAttribute("criticalFailure");
		System.out.println("Critical Failure Value -------" + criticalFailure);
		if(criticalFailure != null && criticalFailure.equals("Y"))
		{
			test.log(Status.SKIP, "Critical Failure in Previous test");
			throw new SkipException("Critical Failure in Previous test");
		}

		
		reports = (ExtentReports) context.getAttribute("reports");

	}
	
	
	@AfterTest
	public void quit() {
		if(reports != null) {
			reports.flush();
		}
		if(app.driver != null) {
			app.driver.quit();
		}
	
	}
	
}
