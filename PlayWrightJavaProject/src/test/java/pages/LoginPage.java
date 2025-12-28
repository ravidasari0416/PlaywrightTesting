package pages;

import java.util.Map;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import base.BaseTest;
import utils.DatabaseUtil;
import utils.ExcelUtils;
import utils.ObjectRepository;
import utils.ScreenshotUtil;

public class LoginPage extends BaseTest {
	
	private Page page;
	ObjectRepository objRepo = new ObjectRepository();
	
	private static String filePath = "src/test/resources/testdata.xlsx";
	private static String sheetName = "creds";

	
	public LoginPage(Page page) {
		this.page = page;
	}
	
	public void loginAsRole(String role) {
		try {
			Map<String, String> credentials = ExcelUtils.getCredentialsByRole(filePath, sheetName, role);
			
			// Extract username and password from the map
			String username = credentials.get("username");
			String password = credentials.get("password");
			
			System.out.println("Logging in as role: " + role + " with username: " + username + " with password: " + password);
			
			// Call the login method with extracted credentials
			login(username, password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			
		}
	}
	
	public void loginUsingDataBaseRole(String roleName, ExtentTest test) {
		//String role = GlobalVariables.role;
		String role = roleName.toUpperCase();
		
		try {
			String[] creds = DatabaseUtil.getCredentialByRole(role);
			
			if (creds == null || creds.length < 2 || creds[0] == null || creds[1] == null) {
			    Assert.fail("No credentials found in DB for role: " + role);
			}
			
			String username = creds[0];
			String passeord = creds[1];
			
			LoginPage loginpage = new LoginPage(page);
			loginpage.login(username, passeord);
			page.waitForTimeout(3000);
			log.info("Logged in successfully for role from DataBase: " + role);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			Assert.fail("Login failed for role: " + role + " due to exception: " + e.getMessage());
			ScreenshotUtil.takeScreenshot(page, test);
			e.printStackTrace();
		}
		
	}

	public void login(String username, String password) {
		try {
			// Fill in username and password fields and click login
			page.fill(objRepo.usernameInput, username);
			page.fill(objRepo.passwordInput, password);
			page.click(objRepo.loginButton);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			page.screenshot();

			e.printStackTrace();
		}
	}
	

}
