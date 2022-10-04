package com.firebasse.test.pages.home;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.firebasse.test.pages.base.BasePage;

public class HomePage extends BasePage{
	
	@FindBy(xpath ="//h1[text()='Student Registration Form']") WebElement studentRegistration;
	
	public HomePage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
	}
	
	public String GetTextFromStudentRegistrationElement() {
		String text=readText(studentRegistration, "text form field");
		String path=captureWebElementScreenshot(studentRegistration, "studentRegistrationtextImage");
		try {
			report.attachScreeshot(path);
		} catch (IOException e) {
			report.logTestFailedWithException(e);
		}
		return text;
	}
	
	

}
