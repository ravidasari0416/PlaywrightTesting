package pages;

import com.microsoft.playwright.Page;

import utils.ObjectRepository;

public class LoginPage {
	
	private Page page;
	ObjectRepository objRepo = new ObjectRepository();

	
	public LoginPage(Page page) {
		this.page = page;
	}

	public void login(String username, String password) {
		page.fill(objRepo.usernameInput, username);
		page.fill(objRepo.passwordInput, password);
		page.click(objRepo.loginButton);
		page.screenshot();
	}
}
