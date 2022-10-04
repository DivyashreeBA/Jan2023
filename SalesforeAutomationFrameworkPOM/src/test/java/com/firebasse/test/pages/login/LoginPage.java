package com.firebasse.test.pages.login;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import com.firebasse.test.pages.base.BasePage;

public class LoginPage extends BasePage{
	
	
	@FindBy(xpath="//*[@id=\"email_field\"]") WebElement username;
	@FindBy(id = "password_field") WebElement password;
	@FindBy(tagName = "button") WebElement loginButton;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterUserName(String usrname) {
		waitUntilVisible(username,"user name field");
		enterText(username, usrname, "username field");
	
	}
	public void enterPassword(String passWord) {
		
		enterText(password, passWord, "password field");
		
	}
	
	public void clickLoginButton() {
		clickElement(loginButton,"login button");
	}
	
	public void login(String usrname,String passWrd) {
		enterUserName(usrname);
		enterPassword(passWrd);
		clickLoginButton();
	}
	
	

}
