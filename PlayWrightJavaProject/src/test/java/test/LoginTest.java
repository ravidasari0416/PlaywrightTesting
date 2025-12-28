package test;

import org.testng.annotations.Test;

import base.BaseTest;
import base.GlobalVariables;
import pages.LoginPage;
import utils.DatabaseUtil;

public class LoginTest extends BaseTest{
	
	GlobalVariables global = new GlobalVariables();
	
	@Test
	public void login() {
		// Navigate to demo site
		test.info("Navigating to the application URL");
		page.navigate(global.baseURL);
		
		// Fill username and password using role selectors
		test.info("Performing login operation");
		LoginPage loginpage = new LoginPage(page);
		loginpage.login(global.testuserName, global.testpassword);
		test.pass("Login successful");
		page.waitForTimeout(5000);

	}
	


}