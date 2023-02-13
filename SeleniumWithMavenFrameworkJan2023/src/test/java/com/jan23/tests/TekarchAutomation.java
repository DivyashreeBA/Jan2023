package com.jan23.tests;
//logging--  .log  sljf4 log4j--1 2
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.Alert;
// options--
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.jan23.base.BaseTest;
import com.jan23.utility.PropertiesUtility;
@Listeners(com.jan23.utility.TestEventListenersUtility.class)
public class TekarchAutomation extends BaseTest {
	@Test
	public void loginToFirebase() throws IOException {
		
		logger.info("inside loginToFirebase method");
		extentreport.logTestInfo("inside loginToFirebase method");
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		String username=propertiesUtility.getPropertyValue("login.valid.userid");
		String password=propertiesUtility.getPropertyValue("login.valid.password");
					String expected = "Student Registration Form";
		By idLoc = By.id("email_field");
		WebElement userName = driver.findElement(idLoc);
		WaitUntilElementIsVisible(userName,"username element");
		enterText(userName,username, "usernameEle");

		WebElement passwrd = driver.findElement(By.id("password_field"));
		enterText(passwrd, password, "passwordEle");

		driver.findElement(By.tagName("button")).click();
	
		WebElement textElement = driver.findElement(By.xpath("/html/body/div[2]/div[2]/h1"));
		WaitUntilElementIsVisible(textElement,"password element");
		
		String actaul = getTextFromWebElement(textElement, "student registartion form");
		Assert.assertEquals(actaul,expected);
		extentreport.logTestInfo("method ended");
	/*	if (actaul.equalsIgnoreCase(expected)) {
			logger.info("testcase passed");
			
		} else {
			logger.error("testcase failed");
			
		}*/
		
	}

	@Test
	
	public void error_loginToFirebase() throws InterruptedException, IOException {
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
		
		String username=propertiesUtility.getPropertyValue("login.invalid.userid");
		String password=propertiesUtility.getPropertyValue("login.invalid.password");
		
		String expected = "Error : There is no user record corresponding to this identifier. The user may have been deleted.";
		
		By idLoc = By.id("email_field");
		WebElement userName = driver.findElement(idLoc);
		WaitUntilElementIsVisible(userName,"username element");
		enterText(userName,username, "usernameEle");

		WebElement passwrd = driver.findElement(By.id("password_field"));
		enterText(passwrd,password, "passwordEle");

		driver.findElement(By.tagName("button")).click();
		Thread.sleep(5000);
		 Alert alert=driver.switchTo().alert();
		String actaul= alert.getText();
		alert.accept();
		Assert.assertEquals(actaul,expected);
		
	
	}
	@Test
	
	public void Student_registration_Form() throws InterruptedException {
		
		PropertiesUtility propertiesUtility =new PropertiesUtility();
		propertiesUtility.loadFile("applicationDataProperties");
	
		String username=propertiesUtility.getPropertyValue("login.valid.userid");
		String password=propertiesUtility.getPropertyValue("login.valid.password");
		
		
		String expected = "Student Registration Form";
		
		By idLoc = By.id("email_field");
		WebElement userName = driver.findElement(idLoc);
		WaitUntilElementIsVisible(userName,"username element");
		enterText(userName,username, "usernameEle");

		WebElement passwrd = driver.findElement(By.id("password_field"));
		enterText(passwrd,password, "passwordEle");

		driver.findElement(By.tagName("button")).click();
		Thread.sleep(5000);
		
		Properties propertyFile1=propertiesUtility.loadFile("studentRegistrationProperties");
		String name=propertiesUtility.getPropertyValue("name");
		String fatherName=propertiesUtility.getPropertyValue("fathername");
		String city=propertiesUtility.getPropertyValue("city");
		
		WebElement nameEle=driver.findElement(By.id("name"));
		enterText(nameEle,name,"name field");
		WebElement femaleRadioButton=driver.findElement(By.xpath("//input[@id='radiobut' and @value='female']"));
		femaleRadioButton.click();
		WebElement cityDropdown=driver.findElement(By.id("city"));
		selectByTextData(cityDropdown,city,"city select element");
		Assert.assertFalse(true);

	}
	
	
	
}
