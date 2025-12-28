package test;

import java.util.List;

import org.testng.annotations.Test;

import base.BaseTest;
import base.GlobalVariables;
import pages.LandingPage;
import pages.LoginPage;
import utils.ObjectRepository;

public class LandingPageTest extends BaseTest {
	
	ObjectRepository objRepo = new ObjectRepository();

	
	@Test
	public void verifySideMenuItems() {
		try {
			// Navigate to demo site
			LoginPage loginpage = new LoginPage(page);
			//login from Global variables
//			loginpage.login(GlobalVariables.testuserName, GlobalVariables.testpassword);
//			log.info("Login successful, verifying side menu items");
			
			
			log.info("logging as role: " + GlobalVariables.role);
			//Login using Excel
			//loginpage.loginAsRole("preparer");
			
			//Login using Database
			loginpage.loginUsingDataBaseRole(GlobalVariables.role, test);
			log.info("Login successful as preparer, verifying side menu items");
			test.info("Login successful as preparer, verifying side menu items");

			
			// Verify side menu items
			List<String> expectedItems = List.of("Admin", "PIM", "Leave", "Time", "Recruitment", "My Info", "Performance", "Dashboard", "Directory", "Maintenance", "Claim", "Buzz");
			
			// Create LandingPage object and verify menu items
			LandingPage landingPage = new LandingPage(page);
			landingPage.verifyMenuItems(objRepo.menuItemsObject,expectedItems, test);
			log.info("Side menu items verification completed");
			
		} catch (Exception e) {

			log.error("Error in verifying side menu items: " + e.getMessage());
			test.fail("Test failed due to exception: " + e.getMessage());
		}

	}

}
