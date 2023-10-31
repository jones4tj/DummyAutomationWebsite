package DummyAutomationWebsite.tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import DummyAutomationWebsite.loggers.ILogger;
import DummyAutomationWebsite.loggers.Log4jLogger;
import DummyAutomationWebsite.pageobjects.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	private WebDriver driver;
	protected ILogger logger;
	protected LoginPage loginPage;
	
	public WebDriver initializeDriver() throws FileNotFoundException, IOException {
		WebDriverManager.chromedriver().setup();
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		this.driver.manage().window().maximize();
		return this.driver;
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws FileNotFoundException, IOException {
		this.logger = new Log4jLogger(this.getClass());
		this.logger.debug("Initializing driver");
		this.driver = initializeDriver();
		this.loginPage = new LoginPage(driver);
		this.loginPage.goTo();
		this.logger.debug("Navigating to URL: " + this.loginPage.getCurrentUrl());
	}
	
	@AfterMethod(alwaysRun=true)
	public void teardown() {
		this.driver.quit();
		this.logger.debug("Quitting driver");
	}
	
	public String getScreenshot(String testCaseName, WebDriver driver) throws IOException {
		TakesScreenshot takeScreenshot = (TakesScreenshot)driver;
		File source = takeScreenshot.getScreenshotAs(OutputType.FILE);
		File destination = new File(System.getProperty("user.dir") + "/reports/" + testCaseName + ".png");
		FileUtils.copyFile(source, destination);
		this.logger.debug("Taking screenshot of testcase: " + testCaseName);
		return destination.getPath();
	}
}
