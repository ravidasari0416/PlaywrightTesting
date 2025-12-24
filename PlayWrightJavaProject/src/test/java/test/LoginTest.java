package test;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import base.BaseTest;

public class LoginTest extends BaseTest{

	@Test
	public void test() {
		// Navigate to demo site
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

		// Fill username and password using role selectors
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
		page.waitForTimeout(10000);

		/*
		 * // Wait for profile/menu to appear and perform logout Locator profile =
		 * page.getByRole(AriaRole.LISTITEM).filter(new
		 * Locator.FilterOptions().setHasText("Paul Collings")); // If the exact text
		 * differs across environments, try a broader selector if (!profile.isVisible())
		 * { profile =
		 * page.locator("//header//button[contains(@class,'oxd-userdropdown')]"); } //
		 * Click profile/menu and then logout if (profile.isVisible()) {
		 * profile.locator("i").click(); page.getByRole(AriaRole.MENUITEM, new
		 * Page.GetByRoleOptions().setName("Logout")).click(); } else { // if
		 * profile/menu not visible, fail the test with page content for debugging
		 * String body = page.content();
		 * Assert.fail("Profile/menu not found after login. Page snapshot length=" +
		 * body.length()); }
		 * 
		 * // Verify we are back on login page by checking the Login button is visible
		 * boolean loginVisible = page.getByRole(AriaRole.BUTTON, new
		 * Page.GetByRoleOptions().setName("Login")).isVisible();
		 * Assert.assertTrue(loginVisible,
		 * "Expected Login button to be visible after logout");
		 */
	}

}