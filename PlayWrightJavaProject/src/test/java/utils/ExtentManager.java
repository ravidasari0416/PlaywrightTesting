package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
	public static ExtentReports extent;

	public static ExtentReports getInstance() {
		if (extent == null) {
			// Create ExtentReports and attach reporter
			ExtentSparkReporter reporter = new ExtentSparkReporter("test-output/ExtentReport.html");
			extent = new ExtentReports();
			extent.attachReporter(reporter);
			
			// Adding system information
			extent.setSystemInfo("Framework", "Playwright + TestNG");
			extent.setSystemInfo("Browser", "Chromium");
			extent.setSystemInfo("Tester", "Ravi Kuma Dasari");
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java", System.getProperty("java.version"));
		}
		return extent;

	}

}
