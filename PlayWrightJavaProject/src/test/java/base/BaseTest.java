package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class BaseTest {

	protected Playwright playwright;
	protected Browser browser;
	protected Page page;

	@BeforeMethod
	public void setUp() {
		playwright = Playwright.create();
		browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)
				.setArgs(java.util.Arrays.asList("--start-maximized")));
		page = browser.newPage();
		// Ensure the viewport matches a typical maximized window
		page.setViewportSize(1920, 1080);

	}

	@AfterMethod
	public void tearDown() {
		if (browser != null)
			browser.close();
		if (playwright != null)
			playwright.close();

	}

}