package DummyAutomationWebsite.pageobjects;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import DummyAutomationWebsite.components.Component;

public class CheckoutInfoPage extends Component {

	@FindBy(id="first-name")
	private WebElement firstName;
	@FindBy(id="last-name")
	private WebElement lastName;
	@FindBy(id="postal-code")
	private WebElement postalCode;
	@FindBy(id="cancel")
	private WebElement cancelButton;
	@FindBy(id="continue")
	private WebElement continueButton;
	@FindBy(xpath="//div[@class='error-message-container error']/h3")
	private WebElement errorMessage;
	
	
	public CheckoutInfoPage(WebDriver driver) {
		super(driver);
	}
	
	public void enterFirstName(String firstName) {
		this.firstName.sendKeys(firstName);
	}
	
	public void enterLastName(String lastName) {
		this.lastName.sendKeys(lastName);
	}
	
	public void enterPostalCode(String postalCode) {
		this.postalCode.sendKeys(postalCode);
	}
	
	public CheckoutOverviewPage checkout() {
		this.continueButton.click();
		return new CheckoutOverviewPage(this.driver);
	}
	
	public String getErrorMessage() throws NoSuchElementException {
		return this.errorMessage.getText();
	}
	
	public CartPage cancel() {
		this.cancelButton.click();
		return new CartPage(this.driver);
	}

}
