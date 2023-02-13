package com.jan23.utility;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.jan23.base.BaseTest;

public class TestEventListenersUtility implements ITestListener{
	protected static ExtentReportsUtility extentreport=null;
	protected WebDriver driver;
	@Override
	public void onTestStart(ITestResult result) {
		
		extentreport.startSingleTestReport(result.getMethod().getMethodName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestpassed(result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		extentreport.logTestFailed(result.getMethod().getMethodName());
		BaseTest ob=new BaseTest();
		driver=ob.returnDriverInstance();
		String path=ob.getScreenshotBase64(driver);
		extentreport.logTestFailedWithException(result.getThrowable());
		extentreport.logTestScreenshotBase64(path);
		
		
		
	}

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport=ExtentReportsUtility.getInstance();
		extentreport.startExtentReport();
		
	}

	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport.endReport();
	}

}
