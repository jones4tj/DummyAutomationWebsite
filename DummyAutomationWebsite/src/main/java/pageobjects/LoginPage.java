package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import components.Component;

public class LoginPage extends Component {

	@FindBy(id="user-name")
	private WebElement username;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="login-button")
	private WebElement login;
	@FindBy(xpath="//div[@class='error-message-container']/h3")
	private WebElement errorMessage;
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void goTo() {
		this.driver.get("https://saucedemo.com/");
	}
	
	public void login(String username, String password) {
		this.enterUsername(username);
		this.enterPassword(password);
		this.login();
	}
	
	private void enterUsername(String username) {
		this.username.sendKeys(username);
	}
	
	private void enterPassword(String password) {
		this.password.sendKeys(password);
	}
	
	private void login() {
		this.login.click();	
	}
	
	public String getErrorMessage() {
		return this.errorMessage.getText();
	}

}
