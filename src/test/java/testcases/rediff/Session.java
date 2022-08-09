package testcases.rediff;

import org.testng.ITestContext;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import keywords.ApplicationKeywords;
import testbase.BaseTest;

public class Session extends BaseTest{
	
	@Test
	public void doLogin(ITestContext context) {
		System.out.println("Logging into the application");
		app.log("Logging into the application");
		app.openBrowser("Chrome");
		app.navigate("url");
		app.type("useremail_id", "lalitha517.sudireddy@gmail.com");
		app.type("password_css", "Frenchstreet");
		app.click("remember_checkbox_xpath");
		app.click("login_submit_id");
		
	}
	
	
	@Test
	public void doLogout() {
		
	}

}
