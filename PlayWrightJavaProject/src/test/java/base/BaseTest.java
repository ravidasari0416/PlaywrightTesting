package base;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

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

	@Parameters("browser")
	@BeforeMethod
	public void setUp(String browserName, Method method) {
		// Initialize ExtentReports
		extent = ExtentManager.getInstance();
		test = extent.createTest(method.getName());
		test.info("Starting test: " + method.getName());

		playwright = Playwright.create();
		BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false);

		switch (browserName.toLowerCase()) {
		case "chrome":
			log.info("Launching Chrome browser");
			test.info("Launching Chrome browser");
			browser = playwright.chromium().launch(options.setChannel("chrome").setArgs(List.of("--start-maximized")));
			log.info("Chrome browser launched");
			test.info("Chrome browser launched");
			break;

		case "edge":
			log.info("Launching Edge browser");
			test.info("Launching Edge browser");
			browser = playwright.chromium().launch(options.setChannel("msedge").setArgs(List.of("--start-maximized")));
			log.info("Edge browser launched");
			test.info("Edge browser launched");
			break;

		case "firefox":
			log.info("Launching Firefox browser");
			test.info("Launching Firefox browser");
			browser = playwright.firefox().launch(options.setArgs(List.of("--start-maximized")));
			log.info("Firefox browser launched");
			test.info("Firefox browser launched");
			break;

		default:
			log.error("Unsupported browser: " + browserName + ". Launching Chrome as default.");
			browser = playwright.chromium().launch(options.setChannel("chrome").setArgs(List.of("--start-maximized")));
			break;
		}

		// Create a new browser context and page
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

		if (result.getStatus() == ITestResult.FAILURE) {
			test.fail("Test Failed: " + result.getThrowable());
			ScreenshotUtil.takeScreenshot(page, test);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		} else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Skipped: " + result.getThrowable());
		}

		extent.flush();

		if (playwright != null)
			browser.close();

	}

}