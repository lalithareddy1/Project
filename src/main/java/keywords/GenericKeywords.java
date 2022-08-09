package keywords;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import reports.ExtentManager;

public class GenericKeywords {

	public WebDriver driver = null;
	public Properties prop;
	public ExtentTest test;
	public SoftAssert softAssert;
	
	public GenericKeywords() {
		
		System.out.println("Inside Generic Keywords");
	}

	public void openBrowser(String browserName) {
		log("Opening Browser : " + browserName);
		if (browserName.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"D:\\Softwares\\drivers\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();

		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	}
	

	public void navigate(String urlKey) {
		log("Navigating to URL :" + prop.getProperty(urlKey));
		driver.get(prop.getProperty(urlKey));

	}

	public void click(String locatorKey) {
		getElement(locatorKey).click();

	}

	public void type(String locatorKey, String data) {
		getElement(locatorKey).sendKeys(data);

	}
	
	public String getText(String locatorKey) {
		System.out.println("Get Text -------------" + getElement(locatorKey).getText());
		return getElement(locatorKey).getText();
		
	}
	public void clear(String locatorKey) {
		
		getElement(locatorKey).clear();
	}
	
	public void clickEnterButton(String locatorKey) {
		log("Clinking enter button");
		getElement(locatorKey).sendKeys(Keys.ENTER);
	}
	
	
	
	public void selectByVisibleText(String locatorKey, String data) {
		
		Select s = new Select(getElement(locatorKey));
		s.selectByVisibleText(data);
		
	}
	
	public WebElement getElement(String locatorKey) {
		
		if(!isElementPresent(locatorKey)) {
			System.out.println("Element not present");
		}
		
		if(!isElementVisible(locatorKey)) {
			System.out.println("Element not visible");
		}

		WebElement e = driver.findElement(getLocator(locatorKey));

		return e;

		/*
		 * if(locatorKey.endsWith("xpath")) { e =
		 * driver.findElement(By.xpath(prop.getProperty(locatorKey)));
		 * 
		 * } else if(locatorKey.endsWith("id")) { e =
		 * driver.findElement(By.id(prop.getProperty(locatorKey)));
		 * 
		 * }
		 * 
		 * else if(locatorKey.endsWith("class")) { e =
		 * driver.findElement(By.className(prop.getProperty(locatorKey)));
		 * 
		 * } else if(locatorKey.endsWith("css")) { e =
		 * driver.findElement(By.cssSelector(prop.getProperty(locatorKey)));
		 * 
		 * }
		 */

	}

	public boolean isElementPresent(String locatorKey) {
		WebDriverWait wait = new WebDriverWait(driver, 10);

		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(getLocator(locatorKey)));
		} catch (Exception e) {
			return false;
		}
		return true;

		/*
		 * try { if(locatorKey.endsWith("xpath")) {
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(prop.
		 * getProperty(locatorKey))));
		 * 
		 * } else if(locatorKey.endsWith("id")) {
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.id(prop.getProperty
		 * (locatorKey))));
		 * 
		 * }
		 * 
		 * else if(locatorKey.endsWith("class")) {
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.className(prop.
		 * getProperty(locatorKey))));
		 * 
		 * } else if(locatorKey.endsWith("css")) {
		 * wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(prop.
		 * getProperty(locatorKey))));
		 * 
		 * } }catch(Exception e) { return false; }
		 */
	}

	public boolean isElementVisible(String locator) {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(getLocator(locator)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public By getLocator(String locatorKey) {

		By by = null;

		if (locatorKey.endsWith("xpath")) {
			by = By.xpath(prop.getProperty(locatorKey));

		} else if (locatorKey.endsWith("id")) {
			by = By.id(prop.getProperty(locatorKey));

		}

		else if (locatorKey.endsWith("class")) {
			by = By.className(prop.getProperty(locatorKey));

		} else if (locatorKey.endsWith("css")) {
			by = By.cssSelector(prop.getProperty(locatorKey));

		}
		else if (locatorKey.endsWith("name")) {
			by = By.name(prop.getProperty(locatorKey));

		}

		return by;

	}
	
	public void log(String msg) {
		test.log(Status.INFO, msg);
	}

	
	public void reportFailure(String failureMsg, boolean stopOnFailure) {
		test.log(Status.FAIL, failureMsg); // fail in extent reports
		softAssert.fail(failureMsg); // fail in testNG reports
		if(stopOnFailure) {
			Reporter.getCurrentTestResult().getTestContext().setAttribute("criticalFailure", "Y");
			assertAll();
		}
		
	}
	
	
	public void assertAll() {
		softAssert.assertAll();
	}
	
	
	public void takeScreenShot(){
		// fileName of the screenshot
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		// take screenshot
		File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File desFile = new File(ExtentManager.screenshotFolderPath+"//"+screenshotFile);

		try {
			// get the dynamic folder name
			FileUtils.copyFile(srcFile, desFile);
			//put screenshot file in reports
			test.log(Status.INFO,"Screenshot-> "+ test.addScreenCaptureFromPath(ExtentManager.screenshotFolderPath+"//"+screenshotFile));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void waitForPageToLoad(){
		JavascriptExecutor js = (JavascriptExecutor)driver;
		int i=0;
		// ajax status
		while(i!=10){
		String state = (String)js.executeScript("return document.readyState;");
		System.out.println(state);

		if(state.equals("complete"))
			break;
		else
			wait(2);

		i++;
		}
		// check for jquery status
		i=0;
		while(i!=10){
	
			Long d= (Long) js.executeScript("return jQuery.active;");
			System.out.println(d);
			if(d.longValue() == 0 )
			 	break;
			else
				 wait(2);
			 i++;
				
			}
		
		}
	
	public void wait(int time) {
		try {
			Thread.sleep(time*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	// finds the row number of the data
		public int getRowNumWithCellData(String tableLocator, String data) {
			
			WebElement table = getElement(tableLocator);
			List<WebElement> rows = table.findElements(By.tagName("tr"));
			for(int rNum=0;rNum<rows.size();rNum++) {
				WebElement row = rows.get(rNum);
				List<WebElement> cells = row.findElements(By.tagName("td"));
				for(int cNum=0;cNum<cells.size();cNum++) {
					WebElement cell = cells.get(cNum);
					System.out.println("Text "+ cell.getText());
					if(!cell.getText().trim().equals(""))
						if(data.startsWith(cell.getText()))
							return(rNum+1);
				}
			}
			
			return -1; // data is not found
		}
}
