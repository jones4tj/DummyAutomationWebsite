package DummyAutomationWebsite.tests;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

	@Test
	public void loginStandardUser() throws InterruptedException {
		login("standard_user", "secret_sauce");
	}
	
	@Test
	public void loginLockedOutUser() throws InterruptedException {
		login("locked_out_user", "secret_sauce");
	}
	
	@Test
	public void loginProblemUser() throws InterruptedException {
		login("problem_user", "secret_sauce");
	}
	
	@Test
	public void loginPerformanceGlitchUser() throws InterruptedException {
		login("performance_glitch_user", "secret_sauce");
	}
	
	@Test
	public void loginErrorUser() throws InterruptedException {
		login("error_user", "secret_sauce");
	}
	
	@Test
	public void loginVisualUser() throws InterruptedException {
		login("visual_user", "secret_sauce");
	}
	
	private void login(String username, String password) throws InterruptedException {
		String loginMessage = "Attempting to login";
		this.loginPage.login();
		this.logger.debug(loginMessage);
		this.printErrorMessageIfExists();
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.login();
		this.logger.debug(loginMessage);
		this.printErrorMessageIfExists();
		this.loginPage.enterPassword(password);
		this.logger.debug("Entering password");
		this.loginPage.login();
		this.logger.debug(loginMessage);
		this.printErrorMessageIfExists();
		Thread.sleep(2000);
	}
	
	private void printErrorMessageIfExists() {
		try {
			this.logger.debug("Error Message: " + loginPage.getErrorMessage());
		} catch (NoSuchElementException exception) {
			this.logger.debug("No error message");
		}
	}
}
