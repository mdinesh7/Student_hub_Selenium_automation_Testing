

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import org.apache.commons.*;

public class Utils {
	 private static ExtentReports extent;
	 private static ExtentSparkReporter reporter;

	public static void takeScreenShot(WebDriver driver, String fileName) {

		File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

		try {
			Files.copy(srcFile, new File("./screenshots/" + fileName + "_screenshot.png"));
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
	
	
	 public static ExtentReports getInstance() {
	        if (extent == null) {
	        	reporter = new ExtentSparkReporter("./test-report/selenium_test_report.html");
	            extent = new ExtentReports();
	            extent.attachReporter(reporter);

				reporter.config().setDocumentTitle("Selenium Test Report - Group 1");
				reporter.config().setReportName("Test Report");
				reporter.config().setTheme(Theme.STANDARD);
				reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
	        }
	        return extent;
	    }
}
