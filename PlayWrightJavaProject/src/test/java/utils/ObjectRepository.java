package utils;

public class ObjectRepository {
	
	public final String baseURL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
	
	// Locators for Login Page
	public final String usernameInput = "//input[@name='username']";
	public final String passwordInput = "//input[@name='password']";
	public final String loginButton = "//button[text()=' Login ']";
	
	// Locators for Side Menu
	public final String menuItemsObject = "//*[@class='oxd-text oxd-text--span oxd-main-menu-item--name']";


}
