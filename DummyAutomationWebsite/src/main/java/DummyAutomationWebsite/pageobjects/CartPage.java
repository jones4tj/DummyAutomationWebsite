package DummyAutomationWebsite.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DummyAutomationWebsite.components.Component;

public class CartPage extends Component {

	@FindBy(css="div.cart_item")
	private List<WebElement> productsInCart;
	@FindBy(id="continue-shopping")
	private WebElement continueShoppingButton;
	@FindBy(id="checkout")
	private WebElement checkoutButton;
	
	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void removeProductFromCart(String productName) {
		for (WebElement product : this.productsInCart) {
			if (product.findElement(By.cssSelector("div.inventory_item_name")).getText().equals(productName)) {
				product.findElement(By.cssSelector("button.btn_secondary")).click();
				break;
			}
		}
	}
	
	public boolean isProductInCart(String productName) {
		for (WebElement product : this.productsInCart) {
			if (product.findElement(By.cssSelector("div.inventory_item_name")).getText().equals(productName)) {
				return true;
			}
		}
		return false;
	}
	
	public ProductPage continueShopping() {
		this.continueShoppingButton.click();
		return new ProductPage(this.driver);
	}
	
	public CheckoutInfoPage checkout() {
		this.checkoutButton.click();
		return new CheckoutInfoPage(this.driver);
	}
}
