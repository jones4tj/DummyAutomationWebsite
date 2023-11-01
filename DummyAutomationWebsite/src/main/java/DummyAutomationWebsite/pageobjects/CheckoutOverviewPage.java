package DummyAutomationWebsite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DummyAutomationWebsite.components.Component;

public class CheckoutOverviewPage extends Component {

	@FindBy(css="div.cart_item")
	private List<WebElement> products;
	@FindBy(css="div.summary_subtotal_label")
	private WebElement subtotalLabel;
	@FindBy(css="div.summary_tax_label")
	private WebElement taxLabel;
	@FindBy(css="div.summary_total_label")
	private WebElement totalLabel;
	@FindBy(id="cancel")
	private WebElement cancelButton;
	@FindBy(id="finish")
	private WebElement finishButton;
	
	public CheckoutOverviewPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public double calculateSubtotal() {
		double subtotal = 0;
		for (WebElement product : this.products) { 
			double productPrice = Double.parseDouble(product.findElement(By.cssSelector("div.inventory_item_price")).getText().substring(1));
			subtotal += productPrice;
		}
		return subtotal;
	}
	
	public double getActualSubtotal() {
		return Double.parseDouble(this.subtotalLabel.getText().substring(13));
	}
	
	public double getTax() {
		return Double.parseDouble(this.taxLabel.getText().substring(6));
	}
	
	public double getTotal() {
		return Double.parseDouble(this.totalLabel.getText().substring(8));
	}
	
	public void cancel() {
		this.cancelButton.click();
	}
	
	public CheckoutCompletePage finish() {
		this.finishButton.click();
		return new CheckoutCompletePage(this.driver);
	}
}
