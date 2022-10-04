package com.firebase.test.base;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import com.firebase.test.utility.CommonUtilities;
import com.firebase.test.utility.Constants;
import com.firebase.test.utility.GenerateReports;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public static WebDriver driver = null;
	public static WebDriverWait wait = null;
	public static GenerateReports report=null;
	@BeforeTest
	public static void setupBeforeTest() {
		report=GenerateReports.getInstance();
		report.startExtentReport();
	}
	@Parameters({ "browsername" })
	@BeforeMethod
	public static void setUp(String browsername,Method m ) {
		System.out.println("beofre method execution started");
		report.startSingleTestReport(m.getName());
		setDriver(browsername);
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String url = CU.getApplicationProperty("url", applicationPropertiesFile);
		gotoUrl(url);
		waitUntilPageLoads();
	}
	@AfterMethod
	public static void tearDown() {
		
		report.logTestInfo("after method execution is started");
		closeBrowser();
	}
	
	@AfterTest
	public static void teardownAfterTest() {
		report.endReport();
	}

	public static void setDriver(String browser) {

		switch (browser) {
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "chrome":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		default:
			break;

		}

	}
	public static void gotoUrl(String url) {
		driver.get(url);
	}
	public static void waitUntilPageLoads() {
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
	}
	
	public static void closeBrowser() {
		driver.close();
	}

	public static void closeAllbrowser() {
		driver.quit();
	}

	
}
