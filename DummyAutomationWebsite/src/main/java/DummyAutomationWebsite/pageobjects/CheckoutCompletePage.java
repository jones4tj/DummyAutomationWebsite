package DummyAutomationWebsite.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DummyAutomationWebsite.components.Component;

public class CheckoutCompletePage extends Component {

	@FindBy(css="h2.complete-header")
	private WebElement header;
	@FindBy(id="back-to-products")
	private WebElement backHomeButton;
	
	public CheckoutCompletePage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public String getHeader() {
		return this.header.getText();
	}
	
	public void clickHomeButton() {
		this.backHomeButton.click();
	}
}
