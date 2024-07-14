package assignment.E_CommercePages;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

import assignment.Reports.ExtentReportUtils;

public class BaseClass {
	protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeMethod
	public void setupBrowser() {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ambuj\\eclipse-workspace\\ambujproject\\Utils\\chromedriver126.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}

    @BeforeClass
    public void setupExtent() {
        extent = ExtentReportUtils.getInstance();
        Path screenshotDir = Paths.get("test-output/screenshots");
        if (!Files.exists(screenshotDir)) {
            try {
                Files.createDirectories(screenshotDir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @AfterClass
    public void tearDownExtent() {
        extent.flush();
    }
    
    @AfterMethod
    public void teardown() {
    	 if (driver != null) {
    driver.quit();
    	 	}
    }

    @AfterMethod
    public void captureScreenshot(ITestResult result) throws IOException {
        if (driver != null) {
            if (result.getStatus() == ITestResult.FAILURE) {
                test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
                String screenshotPath = takeScreenshot(result.getName());
                test.fail("Screenshot",
				        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else if (result.getStatus() == ITestResult.SUCCESS) {
                test.log(Status.PASS, "Test Passed");
                String screenshotPath = takeScreenshot(result.getName());
                test.pass("Screenshot",
				        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
            } else if (result.getStatus() == ITestResult.SKIP) {
                test.log(Status.SKIP, "Test Skipped: " + result.getThrowable());
            }
            
        }
    }

    protected String takeScreenshot(String stepName) {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String dest = "C:/Users/Ambuj/eclipse-workspace/ambujproject/test-output/screenshots/" + stepName + "_" + timestamp + ".png";
        try {
            Files.copy(src.toPath(), Paths.get(dest));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dest;
    }

    protected void ScreenshotIntoLog(String stepDescription) throws IOException {
        test.log(Status.INFO, stepDescription);
        String screenshotPath = takeScreenshot(stepDescription);
        test.info(stepDescription,
		        MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
    }
    
}
