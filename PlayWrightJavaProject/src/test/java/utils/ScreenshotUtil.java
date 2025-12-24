package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.microsoft.playwright.Page;

public class ScreenshotUtil {
	public static String takeScreenshotPath(Page page, String testCaseName) {
		String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots";

		try {
			Files.createDirectories(Paths.get(screenshotDir));
		} catch (Exception e) {
			e.printStackTrace();
		}

		String screenshotPath = screenshotDir + "/" + testCaseName + "_" + timestamp + ".png";

		//String path = "test-output/screenshots/" + testCaseName + "_" + timestamp + ".png";
		page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(false));
		return screenshotPath;
	}

}
