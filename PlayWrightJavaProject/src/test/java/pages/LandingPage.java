package pages;

import java.util.List;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import base.BaseTest;
import utils.ObjectRepository;

public class LandingPage extends BaseTest {

	private Page page;

	public LandingPage(Page page) {
		this.page = page;
	}

	
	public void verifyMenuItems(String menuLocator, List<String> expectedItems,  ExtentTest test) {
		List<String> actualItems = page.locator(menuLocator).allTextContents();
		
		for(String expected : expectedItems) {
			if(actualItems.contains(expected)) {
				test.pass(expected + " is present in the menu.");
				log.info(expected + " is present in the menu.");
			} else {
				test.fail(expected + " is NOT present in the menu.");
				log.error(expected + " is NOT present in the menu.");
			}
		}
	}

}
