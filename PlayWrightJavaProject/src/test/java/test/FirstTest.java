package test;

import org.testng.annotations.Test;

import base.BaseTest;

public class FirstTest extends BaseTest{
	
	@Test
	public void verifyTest() {
		page.navigate("https://www.google.com");
		String title = page.title();
		System.out.println("Title is - " + title);
	}
	
	
	
	/*
	 * public static void main(String[] args) { try(Playwright playwright =
	 * Playwright.create()){ Browser browser = playwright.chromium().launch(new
	 * BrowserType.LaunchOptions().setHeadless(false)); Page page =
	 * browser.newPage(); page.navigate("https://www.google.com"); String title =
	 * page.title(); System.out.println("Title is - " + title); } }
	 */

}
