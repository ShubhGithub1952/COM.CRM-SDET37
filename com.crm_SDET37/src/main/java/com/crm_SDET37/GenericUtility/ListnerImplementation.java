package com.crm_SDET37.GenericUtility;

import org.testng.ITestContext;


import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class ListnerImplementation implements ITestListener {
	ExtentReports report;
	ExtentTest test;

	public void onTestStart(ITestResult result) {

		test=report.createTest(result.getName());
	}

	public void onTestSuccess(ITestResult result) {
		test.log(Status.PASS, result.getMethod().getMethodName());
		test.log(Status.PASS, result.getThrowable());
	}

	public void onTestFailure(ITestResult result) {
		test.log(Status.FAIL, result.getMethod().getMethodName());
		test.log(Status.FAIL, result.getThrowable());
		try {
			String screenshot = WebDriverUtility.takeScreenshot(BaseClass.sdriver, result.getMethod().getMethodName());
			test.addScreenCaptureFromPath(screenshot);
		} catch (Exception e) {
			// TODO: handle exception
		}


		/*String testName = result.getMethod().getMethodName();
	System.out.println("<----Checking Listner InterFace----->");
	TakesScreenshot screenShot = (TakesScreenshot)BaseClass.sdriver;
	File srcFile = screenShot.getScreenshotAs(OutputType.FILE);
	LocalDateTime localDateTime = LocalDateTime.now();
	String dataTime = localDateTime.toString().replace(" ", "_").replace(":", "_");
	File destFile = new File("Screenshot/"+"_"+testName+"_"+dataTime+".PNG");
	try {
		FileUtils.copyFile(srcFile, destFile);
	} catch (Exception e) {
		e.printStackTrace();
		System.out.println("Listner Saving ScreenShot"+e.getMessage());
	}*/
	}

	public void onTestSkipped(ITestResult result) {
		test.log(Status.SKIP, result.getMethod().getMethodName());
		test.log(Status.SKIP, result.getThrowable());
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

	}

	public void onTestFailedWithTimeout(ITestResult result) {

	}

	public void onStart(ITestContext context) {
		ExtentSparkReporter spark = new ExtentSparkReporter("./ExtentReport/report1.html");
		spark.config().setTheme(Theme.DARK);
		spark.config().setReportName("Shubham");
		spark.config().setDocumentTitle("extent report 1");

		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("platformName", "windows10");
		report.setSystemInfo("report Verified By", "Sanjay");
	}

	public void onFinish(ITestContext context) {
		report.flush();
	}

}
