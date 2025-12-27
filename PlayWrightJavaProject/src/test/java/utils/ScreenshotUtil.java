package utils;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentTest;
import com.microsoft.playwright.Page;

import base.BaseTest;

public class ScreenshotUtil {

    public static void takeScreenshot(Page page, ExtentTest test) {
        try {
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String dir = System.getProperty("user.dir") + "/test-output/screenshots";

            Files.createDirectories(Paths.get(dir));

            String path = dir + "/Failure_" + timestamp + ".png";

            page.screenshot(new Page.ScreenshotOptions()
                    .setPath(Paths.get(path))
                    .setFullPage(true));

            test.addScreenCaptureFromPath(path);

        } catch (Exception e) {
            test.warning("Screenshot capture failed: " + e.getMessage());
        }
    }
}
