package com.lexnod.genericLib;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ListnerImplimentation implements ITestListener{
	
	ExtentSparkReporter spark;
	ExtentReports report;
	ExtentTest test;
	

	public void onTestStart(ITestResult result) {
		//Reporter.log(result.getName()+" method STARTED", true);
		test=report.createTest(result.getName()+" method STARTED");
		
	}

	public void onTestSuccess(ITestResult result) {
		//Reporter.log(result.getName()+" method PASSED", true);
		test=report.createTest(result.getName());
		test.pass(MarkupHelper.createLabel(result.getName()+" method PASSED", ExtentColor.GREEN));
		
	}

	public void onTestFailure(ITestResult result) {
		//Reporter.log(result.getName()+" method FAILED", true);
		test=report.createTest(result.getName());
		test.fail(MarkupHelper.createLabel(result.getName()+" method FAILED", ExtentColor.RED));
		WebDriverCommonLibrary wlib = new WebDriverCommonLibrary();
		wlib.takesScreenShot(BaseClass.sdriver, "./screenShots/"+result.getName()+".PNG");
		
	}

	public void onTestSkipped(ITestResult result) {
		//Reporter.log(result.getName()+" method SKIPPED");
		test=report.createTest(result.getName());
		test.skip(MarkupHelper.createLabel(result.getName()+" method SKIPPED", ExtentColor.YELLOW));
		
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		
	}

	public void onStart(ITestContext context) {
		//Reporter.log(context.getName()+" STARTED", true);
		spark = new ExtentSparkReporter(IAutoConstants.REPORT_PATH);
		spark.config().setTheme(Theme.DARK);
		spark.config().setDocumentTitle("Lexnod Reports");
		spark.config().setReportName("Arun Reports");
		
		report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("PLATFORM", "Windown 11");
		report.setSystemInfo("Created By", "Arun K");
		
	}

	public void onFinish(ITestContext context) {
		//Reporter.log(context.getName()+" ENDED", true);
		report.flush();
		
	}
	
	

}
