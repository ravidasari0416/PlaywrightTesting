package pages;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import base.BaseTest;
import utils.ObjectRepository;
import utils.ScreenshotUtil;

public class LandingPage extends BaseTest {

//	ScreenshotUtil screenshotUtil = new ScreenshotUtil();

	private Page page;

	ObjectRepository objRepo = new ObjectRepository();

	public LandingPage(Page page) {
		this.page = page;
	}

	public void verifyMenuItems(String menuLocator, List<String> expectedItems, ExtentTest test) {
		try {
			List<String> actualItems = page.locator(menuLocator).allTextContents();
			List<String> missingItems = new ArrayList<>();

			page.waitForSelector(menuLocator, new Page.WaitForSelectorOptions().setTimeout(15000));
			log.info("Total menu items found: " + page.locator(objRepo.menuItemsObject).count());

			// System.out.println(page.locator(objRepo.menuItemsObject).count());

			List<String> actualMenus = page.locator(menuLocator).allInnerTexts();

			for (String m : actualMenus) {
				System.out.println("[" + m + "]");
			}
			
			System.out.println("Actual Menu Items: " + actualItems);

			for (String expected : expectedItems) {
				if (actualMenus.contains(expected)) {
					test.pass(expected + " is present in the menu.");
					log.info(expected + " is present in the menu.");
				} else {
					test.fail(expected + " is NOT present in the menu.");
					log.error(expected + " is NOT present in the menu.");
					ScreenshotUtil.takeScreenshot(page, test);
					missingItems.add(expected);
				}

			}

			if (!missingItems.isEmpty()) {
				Assert.fail("Missing menu items: " + missingItems);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Assert.fail("Exception during menu verification: " + e.getMessage());
		}
	}

}
