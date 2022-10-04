package com.firebase.test.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.firebase.test.base.BaseTest;
import com.firebase.test.utility.CommonUtilities;
import com.firebasse.test.pages.home.HomePage;
import com.firebasse.test.pages.login.LoginPage;

// 8 locators
//driver.findElement(RelativeLocator.with(By.tagName("a")).above(By.tagName("div")));
public class AutomationScripts extends BaseTest {

	// name,id,clasname,xpath,linktext,tagname
	@Test
	public static void loginToTekarchTest() throws InterruptedException {
		String expected = "Student Registration Form1";
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile = CU.loadFile("applicationProperties");
		String usrname = CU.getApplicationProperty("username", applicationPropertiesFile);
		String passwrd = CU.getApplicationProperty("password", applicationPropertiesFile);
		LoginPage login=new LoginPage(driver);
		login.login(usrname, passwrd);
		HomePage homepage=new  HomePage(driver);
		String actual=homepage.GetTextFromStudentRegistrationElement();
		
		if(!expected.equals(actual))
			report.logTestFailed("assertion failed");
		Assert.assertEquals(actual, expected,report.logTestFailed("actual is not matching with expected in page tile"));	
		
		report.logTestInfo("testscript execution completd");
	}
	
	public static void invalidloginToTekarchTest() throws InterruptedException {
		String expected = "Student Registration Form1";
		
		LoginPage loginob=new LoginPage(driver);
		loginob.login(null, null);		
		report.logTestInfo("testscript execution completd");
	}
	

}
