package com.employeeapi.utilities;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Listeners extends TestListenerAdapter {
	public ExtentHtmlReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;
	
	public void onStart(ITestContext testContext) {
		htmlReporter = new ExtentHtmlReporter(System.getProperty("user.dir")+"/Reports/myReport.html");
		htmlReporter.config().setDocumentTitle("rest automation report");//title of the report
		htmlReporter.config().setReportName("rest api testing report");
		htmlReporter.config().setTheme(Theme.DARK);
		
		extent=new ExtentReports();
		extent.attachReporter(htmlReporter);
		extent.setSystemInfo("Project Name", "employee database API");
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environment", "QA");
		extent.setSystemInfo("user", "chiran");
	}
	
	public void onTestSuccess(ITestResult result) {
		test= extent.createTest(result.getName());
		test.log(Status.PASS, "Test case Passed is --------"+ result.getName());
	}
	
	public void onTestFailure(ITestResult result) {
		test= extent.createTest(result.getName());//create new entry int he report
		
		test.log(Status.FAIL, "TEST case FAILED is ========"+ result.getName());//to add name int he extent report
		test.log(Status.FAIL, "test case failed is=========="+ result.getThrowable());// to add error/exception in extent reprot
		
	}

	public void onTestSkipped(ITestResult result) {
		test = extent.createTest(result.getName());//create a new entry in the report
		test.log(Status.SKIP, "test case skipped is======="+ result.getName());
		
	}
	
	public void onFinish(ITestContext testContext) {
		extent.flush();
	}
}
