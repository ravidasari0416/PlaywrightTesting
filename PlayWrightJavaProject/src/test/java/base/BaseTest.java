package base;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import utils.ExtentManager;
import utils.LoggerUtil;
import utils.ObjectRepository;
import utils.ScreenshotUtil;

public class BaseTest {

	protected Playwright playwright;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;
	protected Logger log = LoggerUtil.getLogger(getClass());
	protected ExtentReports extent;
	public ExtentTest test;
	
	
	ScreenshotUtil screenshotUtil = new ScreenshotUtil();

	@BeforeMethod
	public void setUp(Method method) {
		// Initialize ExtentReports
		extent = ExtentManager.getInstance();
		test = extent.createTest(method.getName());
		test.info("Starting test: " + method.getName());
		
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false).setArgs(List.of("--start-maximized")));
		context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
		page = context.newPage();
		log.info("Browser launched successfully");
		
		ObjectRepository objRepo = new ObjectRepository();
		// Navigate to base URL
		page.navigate(objRepo.baseURL);
		log.info("Navigated to URL: " + objRepo.baseURL);
	}

	@AfterMethod
	public void tearDowon(ITestResult result) {
		log.info("Closing the browser");
		
		if(result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed: " + result.getThrowable());
			screenshotUtil.takeScreenshot(page, test);
		} else if(result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		} else if(result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Skipped: " + result.getThrowable());
		}
		
		extent.flush();

		if (playwright != null)
			browser.close();

	}

}