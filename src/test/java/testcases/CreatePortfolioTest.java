package testcases;

import org.testng.annotations.Test;

import keywords.ApplicationKeywords;

//Frenchstreet
public class CreatePortfolioTest {
	
	
	@Test
	public void createPortfolio() {
		
		ApplicationKeywords app_keywords = new ApplicationKeywords();
		app_keywords.openBrowser("Chrome");
		app_keywords.navigate("url");
		//app_keywords.click("useremail_id");
		app_keywords.type("useremail_id", "lalitha517.sudireddy@gmail.com");
		app_keywords.type("password_css", "Frenchstreet");
		app_keywords.click("remember_checkbox_xpath");
		app_keywords.click("login_submit_id");

		
		
	}

}
