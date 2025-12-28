package test;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import base.BaseTest;
import base.GlobalVariables;
import pages.LoginPage;
import utils.DatabaseUtil;

public class LoginTest extends BaseTest{
	
	
	@Test
	public void login(){
		// Navigate to demo site
	
		getTest().info("Navigating to the application URL");
		getPage().navigate(GlobalVariables.baseURL);
		
		// Fill username and password using role selectors
		getTest().info("Performing login operation");
		LoginPage loginpage = new LoginPage(getPage());
		loginpage.login(GlobalVariables.testuserName, GlobalVariables.testpassword);
		getTest().pass("Login successful");
		getPage().waitForTimeout(5000);

	}
	


}