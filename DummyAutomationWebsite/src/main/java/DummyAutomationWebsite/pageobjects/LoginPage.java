package DummyAutomationWebsite.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DummyAutomationWebsite.components.Component;

public class LoginPage extends Component {

	@FindBy(id="user-name")
	private WebElement username;
	@FindBy(id="password")
	private WebElement password;
	@FindBy(id="login-button")
	private WebElement loginButton;
	@FindBy(xpath="//div[@class='error-message-container error']/h3")
	private WebElement errorMessage;
	private final String url = "https://www.saucedemo.com/";
	
	public LoginPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void goTo() {
		this.driver.get("https://saucedemo.com/");
	}
	
	public void enterUsername(String username) {
		this.username.sendKeys(username);
	}
	
	public void enterPassword(String password) {
		this.password.sendKeys(password);
	}
	
	public ProductPage login() {
		this.loginButton.click();
		return new ProductPage(this.driver);
	}
	
	public String getErrorMessage() throws NoSuchElementException {
		return this.errorMessage.getText();
	}

	public String getUrl() {
		return this.url;
	}

}
