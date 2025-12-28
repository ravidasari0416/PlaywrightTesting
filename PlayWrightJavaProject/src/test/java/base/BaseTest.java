package base;

import java.lang.reflect.Method;
import java.util.List;

import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.microsoft.playwright.*;

import utils.ExtentManager;
import utils.LoggerUtil;
import utils.ObjectRepository;
import utils.ScreenshotUtil;

public class BaseTest {

	private ThreadLocal<Playwright> playwright = new ThreadLocal<>();
	private ThreadLocal<Browser> browser = new ThreadLocal<>();
	private ThreadLocal<BrowserContext> context = new ThreadLocal<>();
	protected ThreadLocal<Page> page = new ThreadLocal<>();
	protected ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

	protected ExtentReports extent = ExtentManager.getInstance();
	protected Logger log = LoggerUtil.getLogger(getClass());

	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(@Optional("chrome") String browserName, Method method) {

		ExtentTest test = extent.createTest(method.getName());
		extentTest.set(test);

		Playwright pw = Playwright.create();
		playwright.set(pw);

		//BrowserType.LaunchOptions options = new BrowserType.LaunchOptions().setHeadless(false); // Jenkins best practice

		Browser br;

		switch (browserName.toLowerCase()) {
		case "chrome":{
			BrowserType.LaunchOptions chromeOptions = new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome").setChromiumSandbox(false);
		
			test.info("Launching Chrome browser");
			br = pw.chromium().launch(chromeOptions.setArgs(List.of("--start-maximized")));
			break;
		}
		case "edge":{
			BrowserType.LaunchOptions edgeOptions = new BrowserType.LaunchOptions().setHeadless(false).setChannel("chrome").setChromiumSandbox(false);
			
			test.info("Launching Edge browser");
			br = pw.chromium().launch(edgeOptions.setArgs(List.of("--start-maximized")));
			break;
		}
		

		case "firefox":{
			BrowserType.LaunchOptions firefoxOptions = new BrowserType.LaunchOptions().setHeadless(false);
			
			test.info("Launching Firefox browser");
			br = pw.firefox().launch(firefoxOptions);
			break;

		}
		
		default:
			throw new IllegalArgumentException("Unsupported browser: " + browserName);
		}

		browser.set(br);
		context.set(br.newContext(new Browser.NewContextOptions().setViewportSize(null)));
		page.set(context.get().newPage());

		ObjectRepository objRepo = new ObjectRepository();
		page.get().navigate(objRepo.baseURL);

		test.info("Navigated to URL: " + objRepo.baseURL);
		log.info("Browser launched successfully: " + browserName);
	}

	protected Page getPage() {
		return page.get();
	}
	
	protected ExtentTest getTest() {
		return extentTest.get();
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown(ITestResult result) {

		ExtentTest test = extentTest.get();
		Page currentPage = page.get();

		try {
			if (result.getStatus() == ITestResult.FAILURE) {
				test.fail("Test Failed");
				test.fail(result.getThrowable());

				if (currentPage != null) {
					ScreenshotUtil.takeScreenshot(currentPage, test);
				}

			} else if (result.getStatus() == ITestResult.SUCCESS) {
				test.pass("Test Passed");
			} else if (result.getStatus() == ITestResult.SKIP) {
				test.skip("Test Skipped");
				test.skip(result.getThrowable());
			}
		} finally {

			if (context.get() != null)
				context.get().close();

			if (browser.get() != null)
				browser.get().close();

			if (playwright.get() != null)
				playwright.get().close();

			context.remove();
			browser.remove();
			playwright.remove();
			page.remove();
			extentTest.remove();

			log.info("Browser closed safely");
		}
	}

	@AfterSuite(alwaysRun = true)
	public void afterSuite() {
		extent.flush();
	}
}
