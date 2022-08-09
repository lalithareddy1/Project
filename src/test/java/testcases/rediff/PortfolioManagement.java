package testcases.rediff;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import testbase.BaseTest;

public class PortfolioManagement extends BaseTest{
	
	@Test
	public void createPortfolio() {
		System.out.println("Creating PortFolio");
		app.log("Creating Portfolio");
		app.click("create_xpath");
		app.clear("name_xpath");
		app.type("name_xpath", "Nitish");
		app.click("createPortfolio_xpath");
		app.waitForPageToLoad();
		app.validateValuePresentInDropDown("dropdown_xpath", "Nitish");
		
	}
	
	@Test
	public void deletePortfolio() {
		app.log("Deleting Portfolio");
		String portfolio_name = "Nitish";
		app.selectByVisibleText("dropdown_xpath", portfolio_name);
		app.waitForPageToLoad();
		app.click("delete_id");
		app.validateSelectedValueNotInDropDown("dropdown_xpath", portfolio_name);
		

		
		
		
	}

}
