package testcases.rediff;

import org.testng.ITestContext;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import testbase.BaseTest;

public class StockManagement extends BaseTest{
	
	
	@Test
	public void selectPortfolio() {
		String portfolioName = "Lalitha";
		app.selectByVisibleText("dropdown_xpath", portfolioName);
		app.wait(2);
		
	}
	
	@Test
	public void addFreshStock(ITestContext context) {
		app.log("---- Adding Stock ------- ");
		String company_Name = "Birla Corporation Ltd";
		String select_Date = "12-10-2021";
		String stockQuantity = "10";
		String stockPrice = "200";
		
		app.log("Adding "+stockQuantity+" stocks of  "+ company_Name);
		// find quantity
		int quatityBeforeModification = app.findCurrentStockQuantity(company_Name);		
		context.setAttribute("quatityBeforeModification", quatityBeforeModification);
		
		app.click("addstock_xpath");
		app.type("addstockname_id", company_Name);
		app.wait(1);
		app.clickEnterButton("addstockname_id");
		app.click("stockPurchaseDate_id");
		app.selectDateFromCalendar(select_Date);
		app.type("addstockqty_id", stockQuantity);
		app.type("addstockprice_id", stockPrice);
		app.click("addStockButton_id");
		app.waitForPageToLoad();
		app.log("Stock added Successfully");

		
		
		
	}
	
	
	@Parameters ({"action"})
	@Test
	public void modifyStock(String action,ITestContext context) {
		String companyName = "Birla Corporation Ltd";
		String selectionDate="12-14-2020";
		String stockQuantity="100";
		String stockPrice="200";
		
		app.log("Selling "+stockQuantity +" of company "+ companyName);
		
		int quatityBeforeModification = app.findCurrentStockQuantity(companyName);
		context.setAttribute("quatityBeforeModification", quatityBeforeModification);
		
		app.goToBuySell(companyName);
		if(action.equals("sellstock"))
		   app.selectByVisibleText("equityaction_id", "Sell");
		else
			app.selectByVisibleText("equityaction_id", "Buy");
		
		app.click("buySellCalendar_id");
		app.log("Selecting Date "+ selectionDate);
		app.selectDateFromCalendar(selectionDate);
		app.type("buysellqty_id", stockQuantity);
		app.type("buysellprice_id", stockPrice);
		app.click("buySellStockButton_id");
		app.waitForPageToLoad();
		app.log("Stock Sold ");
		
	}
	
	
	@Test
	public void verifyStockPresent() {
		String companyName = "Birla Corporation Ltd";
		int row = app.getRowNumWithCellData("stocktable_css", companyName);
		if(row ==-1)
			app.reportFailure("Stock Not present "+companyName, true);
		
		app.log("Stock Found in list "+companyName );
		
	}
	
	// checks the stock quantity
	@Parameters ({"action"})
	@Test
	public void verifyStockQuantity(String action, ITestContext context) {
		String companyName = "Birla Corporation Ltd";
		String selectionDate="12-14-2020";
		String stockQuantity="100";
		String stockPrice="200";
		
		
		app.log("Verifying stock quantity after action - "+ action);
		// quantity after adding/selling stocks
		int quatityAfterModification = app.findCurrentStockQuantity(companyName);
		int modifiedquantity=Integer.parseInt(stockQuantity);
		int expectedModifiedQuantity=0;
		
		// quantity before adding/selling stocks
		int quatityBeforeModification = (Integer)context.getAttribute("quatityBeforeModification");
		if(action.equals("addstock"))
			expectedModifiedQuantity = quatityAfterModification-quatityBeforeModification;
		else if(action.equals("sellstock"))
			expectedModifiedQuantity = quatityBeforeModification-quatityAfterModification;
		
		app.log("Old Stock Quantity "+quatityBeforeModification);
		app.log("New Stock Quantity "+quatityAfterModification);
		
		if(modifiedquantity != expectedModifiedQuantity)
		    app.reportFailure("Quantity did not match", true);
		
		app.log("Stock Quantity Changed as per expected "+ modifiedquantity);
	}
	
	@Test
	public void verifyStockAvgBuyPrice() {
		
	}
	
	// verifies the transaction history
	@Parameters ({"action"})
	@Test
	public void verifyTransactionHistory(String action) {
		String companyName = "Birla Corporation Ltd";
		String selectionDate="12-14-2020";
		String stockQuantity="100";
		String stockPrice="200";
		
		app.log("Verifying transaction History for "+action+"for quantity "+stockQuantity);
		app.goToTransactionHistory(companyName);
		String changedQuantityDisplayed  = app.getText("latestShareChangeQuantity_xpath");
		app.log("Got Changed Quantity "+ changedQuantityDisplayed);
		
		if(action.equals("sellstock"))
			stockQuantity="-"+stockQuantity;
		
		if(!changedQuantityDisplayed.equals(stockQuantity))
		   app.reportFailure("Got changed quantity in transaction history as "+ changedQuantityDisplayed, true);	
		
		app.log("Transaction History OK");
	}

}
