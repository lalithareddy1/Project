package testcases.rediff;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

public class Dummy {
	WebDriver driver;

	@AfterTest
	public void quit() {
		driver.quit();
	}

	@Test
	public void test() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "D:\\Softwares\\drivers\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

		driver.get("https://portfolio.rediff.com/portfolio");
		driver.findElement(By.id("useremail")).sendKeys("lalitha517.sudireddy@gmail.com");
		driver.findElement(By.cssSelector("#userpass")).sendKeys("Frenchstreet");
		driver.findElement(By.id("loginsubmit")).click();

//		Thread.sleep(1000);
//		driver.findElement(By.id("addStock")).click();
//
//		driver.findElement(By.id("addstockname")).sendKeys("Birla Corporation Ltd");
//		Thread.sleep(1000);
//
//		driver.findElement(By.id("addstockname")).sendKeys(Keys.ENTER);
//		String text = driver.findElement(By.xpath("//div[@class=\"optionDivSelected\"]")).getText();
//		System.out.println(text);
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("table#stock > tbody "));
		List<WebElement> rows = driver.findElements(By.cssSelector("table#stock > tbody >tr"));
		System.out.println("Number of rows ::::::::::" + rows.size());
		for (int rNum = 0; rNum < rows.size(); rNum++) {
			WebElement row = rows.get(rNum);
			List<WebElement> columns = driver.findElements(By.cssSelector("table#stock > tbody >tr >td"));
			System.out.println("Number of columns ::::::::::" + columns.size());

			for (int cNum = 0; cNum < columns.size(); cNum++) {
				WebElement cell = columns.get(cNum);
			    System.out.println("Column Names ::::::::::: " + cell.getText());
			    if(cell.getText().startsWith("Dolda Dairy")) {
			    	int rowNum = rNum + 1;
			    	System.out.println("row Num" + rowNum);
			    }
				


			}

		}


	}

}
