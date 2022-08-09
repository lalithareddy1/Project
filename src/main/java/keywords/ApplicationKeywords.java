package keywords;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.openqa.selenium.By;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;

public class ApplicationKeywords extends ValidationKeywords {

	public ApplicationKeywords() {
		System.out.println("Inside Application Keywords");

		String path = System.getProperty("user.dir") + "//src//test//resources//prod.properties";
		prop = new Properties();
		try {
			FileInputStream fs = new FileInputStream(path);
			prop.load(fs);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		softAssert = new SoftAssert();
	}

	public void setReports(ExtentTest test) {
		this.test = test;

	}

	public void selectDateFromCalendar(String date) {
		log("Selecting Date ");

		try {
			Date dateToSel = new SimpleDateFormat("MM-d-yyyy").parse(date);
			String day = new SimpleDateFormat("d").format(dateToSel);
			String month = new SimpleDateFormat("MMMM").format(dateToSel);
			String year = new SimpleDateFormat("yyyy").format(dateToSel);
			String monthYearToBeSelected = month + " " + year;

			String monthYearDisplayed = driver.findElement(By.cssSelector(prop.getProperty("monthyear_css"))).getText();
			System.out.println("MonthYearDisplayed" + monthYearDisplayed);

			while (!monthYearToBeSelected.equals(monthYearDisplayed)) {
				click("datebackButton_xpath");
				monthYearDisplayed = driver.findElement(By.cssSelector(prop.getProperty("monthyear_css"))).getText();
			}
			driver.findElement(By.xpath("//td[text()='" + day + "']")).click();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int findCurrentStockQuantity(String companyName) {
		log("Finding current stock quantity for " + companyName);
		int row = getRowNumWithCellData("stocktable_css", companyName);
		if (row == -1) {
			log("Current Stock Quantity is 0 as Stock not present in list");
			return 0;
		}
		// table#stock > tbody > tr:nth-child(2) >td:nth-child(4)
		String quantity = driver
				.findElement(By.cssSelector(
						prop.getProperty("stocktable_css") + " > tr:nth-child(" + row + ") >td:nth-child(4)"))
				.getText();
		log("Current stock Quantity " + quantity);
		return Integer.parseInt(quantity);
	}

	public void goToBuySell(String companyName) {
		log("Selecting the company row " + companyName);
		int row = getRowNumWithCellData("stocktable_css", companyName);
		if (row == -1) {
			log("Stock not present in list");
		}
		driver.findElement(
				By.cssSelector(prop.getProperty("stocktable_css") + " > tr:nth-child(" + row + ") >td:nth-child(1)"))
				.click();
		driver.findElement(
				By.cssSelector(prop.getProperty("stocktable_css") + "  tr:nth-child(" + row + ") input.buySell"))
				.click();

	}

	public void goToTransactionHistory(String companyName) {
		log("Selecting the company row " + companyName);
		int row = getRowNumWithCellData("stocktable_css", companyName);
		if (row == -1) {
			log("Stock not present in list");
			// report failure
		}
		driver.findElement(
				By.cssSelector(prop.getProperty("stocktable_css") + " > tr:nth-child(" + row + ") >td:nth-child(1)"))
				.click();
		driver.findElement(By.cssSelector(
				prop.getProperty("stocktable_css") + "  tr:nth-child(" + row + ") input.equityTransaction")).click();

	}
}
