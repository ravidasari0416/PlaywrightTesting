package pages;

import java.util.Map;

import com.microsoft.playwright.Page;

import utils.ExcelUtils;
import utils.ObjectRepository;

public class LoginPage {
	
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
