package test;

import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;

import base.BaseTest;

public class LoginTest extends BaseTest{

	@Test
	public void test() {
		page.navigate("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Username")).fill("Admin");
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).click();
		page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("admin123");
		page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login")).click();
		page.getByRole(AriaRole.LISTITEM).filter(new Locator.FilterOptions().setHasText("yog user")).locator("i")
				.click();
		page.getByRole(AriaRole.MENUITEM, new Page.GetByRoleOptions().setName("Logout")).click();
//		assertThat(page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Login"))).isVisible();
	}

}
