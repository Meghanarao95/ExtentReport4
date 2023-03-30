package TestCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.AssertJUnit;
import java.util.Arrays;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class TestCase1 {

	public ExtentSparkReporter htmlReporter;
	public ExtentReports extent;
	public ExtentTest test;

	ITestResult result;

	@BeforeTest
	public void setReport() {

		htmlReporter = new ExtentSparkReporter("./reports/extent.html");

		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("W2A Automation Reports");
		htmlReporter.config().setReportName("Automation Test Results");
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Automation Test", "Meghana Dixit");
		extent.setSystemInfo("Organization", "W2A");
		extent.setSystemInfo("Build No", "1234");
	}

	@AfterTest
	public void endReportI() {

		extent.flush();

	}
	/*
	 * pass, fail, skip
	 */

	@Test
	public void doLogin() {

		test = extent.createTest("Login Test");
		System.out.println("Executing the Login Test");

	}

	@Test
	public void UserReg() {

		test = extent.createTest("UserRegTest");
		AssertJUnit.fail("Failing the UserReg test");

	}

	@Test
	public void isSkip() {

		test = extent.createTest("Skip Test");
		throw new SkipException("Skipping the Test Case");

	}


	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.FAILURE) {

			String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
			Assert.fail("<details>" + "<summary>" + "<b>" + "<font color=" + "red>" + "Exception Occured:Click to see"
					+ "</font>" + "</b >" + "</summary>" + exceptionMessage.replaceAll(",", "<br>") + "</details>"
					+ " \n");

			/*
			 * try {
			 * 
			 * ExtentManager.captureScreenshot(); testReport.get().fail("<b>" +
			 * "<font color=" + "red>" + "Screenshot of failure" + "</font>" + "</b>",
			 * MediaEntityBuilder.createScreenCaptureFromPath(ExtentManager.screenshotName)
			 * .build()); } catch (IOException e) {
			 * 
			 * }
			 */

			String methodName = result.getMethod().getMethodName();

			String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "  FAILED" + "</b>";
			Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
		    test.fail(m);

		} else if (result.getStatus() == ITestResult.SKIP) {

			String methodName = result.getMethod().getMethodName();

			String logText = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "  SKIPPED" + "</b>";

			Markup m = MarkupHelper.createLabel(logText, ExtentColor.ORANGE);
			test.skip(m);

		} else if (result.getStatus() == ITestResult.SUCCESS) {

			String methodName = result.getMethod().getMethodName();

			String logTest = "<b>" + "TEST CASE: - " + methodName.toUpperCase() + "   PASSED" + "</b>";

			Markup m = MarkupHelper.createLabel(logTest, ExtentColor.GREEN);
			test.pass(m);

		}

	}

}
