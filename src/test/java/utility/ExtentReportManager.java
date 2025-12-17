package utility;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import testBase.BaseClass;

public class ExtentReportManager implements ITestListener {
	public ExtentSparkReporter spark; // Deal with UI of the Reporter.
	public ExtentReports reports; // Deal with the common/Basic info about the report.
	public ExtentTest test;// Creating test case report and updates the status.

	@Override
	public void onStart(ITestContext result) {
		// Time stamp
		SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY.MM.DD_hh.mm.ss");
		String timeStamp = dateFormat.format(new Date());

		// Configure UI of the report
		spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/reports/" + timeStamp + ".html");
		spark.config().setDocumentTitle("TEST REPORT");
		spark.config().setReportName("Arunjay");
		spark.config().setTheme(Theme.DARK);

		// Common Basic Report
		reports = new ExtentReports();
		reports.attachReporter(spark);
		reports.setSystemInfo("Computer Name", "Localhost");
		reports.setSystemInfo("Environment", "QA");
		reports.setSystemInfo("Operating System", "Window");
		reports.setSystemInfo("Tester Name", "Arunjay");
		reports.setSystemInfo("Browser", "Chorme");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test = reports.createTest(this.getClass().getName());
		test.log(Status.PASS, "Test Pass is " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test = reports.createTest(this.getClass().getName());
		test.log(Status.FAIL, "Test Failed is " + result.getName());

		// Capture Screenshot
		try {
			BaseClass bs = new BaseClass();
			bs.captureScreen(result.getMethod().getMethodName());
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test = reports.createTest(this.getClass().getName());
		test.log(Status.SKIP, "Test get Skiped " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		reports.flush();

	}
}