package test;

import java.util.List;

import org.testng.annotations.Test;

import base.BaseTest;
import pages.LandingPage;
import utils.ObjectRepository;
import pages.LoginPage;
import base.GlobalVariables;

public class LandingPageTest extends BaseTest {
	
	ObjectRepository objRepo = new ObjectRepository();
	//GlobalVariables global = new GlobalVariables();

	
	@Test
	public void verifySideMenuItems() {
		// Navigate to demo site
		LoginPage loginpage = new LoginPage(page);
		loginpage.login(GlobalVariables.testuserName, GlobalVariables.testpassword);
		log.info("Login successful, verifying side menu items");
		
		// Verify side menu items
		List<String> expectedItems = List.of("Admin", "PIM", "Leave", "Time", "Recruitment", "My Info", "Performance", "Dashboard", "Directory", "Maintenance", "Claim", "Buzz");
		
		// Create LandingPage object and verify menu items
		LandingPage landingPage = new LandingPage(page);
		landingPage.verifyMenuItems(objRepo.menuItems,expectedItems, test);
		log.info("Side menu items verification completed");
	}

}
