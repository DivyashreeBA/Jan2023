package com.jan23.base;

import java.io.File;



import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.jan23.utility.Constants;
import com.jan23.utility.ExtentReportsUtility;
import com.jan23.utility.PropertiesUtility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	protected static WebDriver driver = null;
	protected static WebDriverWait wait=null;
	protected Logger logger=null;
	protected ExtentReportsUtility extentreport=ExtentReportsUtility.getInstance();
	
	@BeforeTest
	public void setUpBeforeTest() {
		//extentreport.logTestInfo("beforeTest method has started");
		System.out.println(" inside @BeforeTest method");
		logger=LogManager.getLogger(BaseTest.class.getName());
	}
	
	
	@BeforeMethod
	@Parameters("browsername")
	public void setUpBeforeTestMethod(@Optional("chrome") String browserName,Method method) {
	logger.info("started testscript name"+method.getName());
	//extentreport.logTestInfo("started testscript name"+method.getName());
	PropertiesUtility propertiesUtility =new PropertiesUtility();
	propertiesUtility.loadFile("applicationDataProperties");
	String url=propertiesUtility.getPropertyValue("url");
	GetDriverInstance(browserName);
	goToUrl(url);
	}
	@AfterMethod
	public void teardownAfterTestMethod() {
		driver.close();
	}

	public void enterText(WebElement element, String text, String name) {
		if (element.isDisplayed()) {
			clearElement(element, name);
			element.sendKeys(text);
			
			logger.info("text entered in " + name + "field");
			extentreport.logTestInfo("text entered in " + name + "field");
		} else {
			logger.info("fail: " + name + " element not displayed");
		}
		driver.getTitle();
	}
	
	
	

	public void clearElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.clear();
			logger.info("pass:" + objName + "  element cleared");
			extentreport.logTestInfo("pass:" + objName + "  element cleared");

		} else {
			logger.info("fail:" + objName + " element not displayed");
		}
	}

	public void clickElement(WebElement element, String objName) {
		if (element.isDisplayed()) {
			element.click();
			logger.info("pass:" + objName + " element clicked");
			extentreport.logTestInfo("pass:" + objName + " element clicked");
		} else {
			logger.info("fail:" + objName + "  element not displayed");

		}
	}

	public void goToUrl(String url) {
		logger.info("going to the url="+url);
		driver.get(url);
	}

	public void closeBrowser() {
		logger.info("closing the browser");
		driver.close();
	}

	public String getPageTitle() {
		return driver.getTitle();
	}

	public void refreshPage() {
		driver.navigate().refresh();

	}
	
	public void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(30,TimeUnit.SECONDS);
	}
	public WebDriver returnDriverInstance() {
		return driver;
	}

	public void GetDriverInstance(String browserName) {

		switch (browserName) {
		case "firefox":
			System.setProperty("webdriver.gecko.driver","D:\\New folder\\documents\\drivers\\geckodriver0.32.0.exe");
			driver=new FirefoxDriver();
			driver.manage().window().maximize();
			break;

		case "chrome":
			System.out.println("inside chrome driver creation switch method");
			WebDriverManager.chromedriver().setup();
			//ChromeOptions option=new ChromeOptions();
			//option.addArguments("--headless");
			//option.addArguments("--incognito");
			//option.setPageLoadStrategy(PageLoadStrategy.NORMAL);
			driver = new ChromeDriver();
			driver.manage().window().maximize();
			break;

		default:
			System.out.println("not entered proper browsername");
		}

	}

	public String getTextFromWebElement(WebElement element, String name) {
		if (element.isDisplayed()) {
			return element.getText();
		} else {
			System.out.println(name + " web element is not displayed");
			return null;
		}

	}
	
	public static void moveTOElementAction(WebElement ele, String objName) {
		Actions action=new Actions(driver);
		action.moveToElement(ele).build().perform();
		System.out.println(" cursor moved to web element "+objName);
	}
	public void ContextClickAction(WebElement ele, String objName) {
		Actions action=new Actions(driver);
		action.contextClick(ele).build().perform();
		System.out.println("right click persormed on web element "+objName);
	}
	
	public void WaitUntilElementIsVisible(WebElement ele, String objName) {
		System.out.println("waiting for an web element "+objName+" for its visibility");
		wait=new WebDriverWait(driver,30);
		 wait.until(ExpectedConditions.visibilityOf(ele));
		 
		 
	}
	
	public void waitUntilAlertIsPresent() {
		System.out.println("waiting for aleet to be present");
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.alertIsPresent());
	}

	public void waitUntilElementToBeClickable(By locator, String objName) {
		System.out.println("waiting for an web element "+objName+" to be clickable");
		wait = new WebDriverWait(driver,30);
		wait.until(ExpectedConditions.elementToBeClickable(locator));
	}
	
	public void waitFluentForVisibility(WebElement ele, String objName) {
		 Wait<WebDriver> wait=new FluentWait<WebDriver>(driver)
				 					.withTimeout(Duration.ofSeconds(30))
				 					.pollingEvery(2,TimeUnit.SECONDS) 
				 					.ignoring(NoSuchElementException.class);				
				wait.until(ExpectedConditions.visibilityOf(ele));
	}

	public  Alert switchToAlert() {
		// TODO Auto-generated method stub
		waitUntilAlertIsPresent();
		 Alert alert=driver.switchTo().alert();
		System.out.println("switched to alert");
		return alert;
	}

	public  void AcceptAlert(Alert alert) {

		System.out.println("Alert accepted");
		alert.accept();

	}

	public  String getAlertText(Alert alert) {
		System.out.println("etracting text in the alert");
		return alert.getText();

	}

	public  void dismisAlert() {
		waitUntilAlertIsPresent();
		Alert alert = switchToAlert();
		alert.dismiss();
		System.out.println("Alert dismissed");

	}

	public  void selectByTextData(WebElement element, String text, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByVisibleText(text);
		System.out.println(objName + " selected " + text);

	}

	public  void selectByIndexData(WebElement element, int index, String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByIndex(index);
		System.out.println(objName + " selected ");
	}

	public  void selectByValueData(WebElement element, String text,String objName) {
		Select selectCity = new Select(element);
		selectCity.selectByValue(text);
		System.out.println(objName + " selected ");
	}

	public  void switchToWindowOpned(String mainWindowHandle) {
		Set<String> allWindowHandles = driver.getWindowHandles();
		for (String handle : allWindowHandles) {
			if (!mainWindowHandle.equalsIgnoreCase(handle))
				driver.switchTo().window(handle);
		}
		System.out.println("switched to new window");
	}
	public WebElement selectFromList(List<WebElement> list,String text) {
		WebElement country=null;
		for (WebElement i : list) {
			if (i.getText().equalsIgnoreCase(text)) {
				System.out.println("selected=" +i.getText());
				country=i;
				break;
			}
			
		}
		return country;
		
	}
	
	
	public void getScreenshotOfThePage() {
		// radom value +  date()+testcasen name-->filename
		 String date = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String curDir=System.getProperty("user.dir");
		TakesScreenshot screenShot=(TakesScreenshot)driver;
		File imgFile= screenShot.getScreenshotAs(OutputType.FILE);
		File destFile=new File(Constants.SCREENSHOTS_DIRECTORY_PATH+date+".png");
		try {
			FileHandler.copy(imgFile,destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getScreenshotBase64(WebDriver driver) {
		// radom value +  date()+testcasen name-->filename
		 String date = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss").format(new Date());
		String curDir=System.getProperty("user.dir");
		TakesScreenshot screenShot=(TakesScreenshot)driver;
		String img= screenShot.getScreenshotAs(OutputType.BASE64);
		
		return img;
	}

}
