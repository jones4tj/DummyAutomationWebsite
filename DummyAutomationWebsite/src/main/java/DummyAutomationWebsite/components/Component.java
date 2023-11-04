package DummyAutomationWebsite.components;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import DummyAutomationWebsite.pageobjects.CartPage;

public abstract class Component {
	
	protected WebDriver driver;
	
	@FindBy(id="react-burger-menu-btn")
	private WebElement menu;
	@FindBy(id="inventory_sidebar_link")
	private WebElement allItems;
	@FindBy(id="about_sidebar_link")
	private WebElement about;
	@FindBy(id="logout_sidebar_link")
	private WebElement logout;
	@FindBy(id="reset_sidebar_link")
	private WebElement resetAppState;
	@FindBy(css="a.shopping_cart_link")
	private WebElement shoppingCart;
	
	public Component(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void clickAllItemsInMenu() {
		this.menu.click();
		this.allItems.click();
	}
	
	public void about() {
		this.menu.click();
		this.about.click();
	}
	
	public void logout() {
		this.resetAppState();
		this.logout.click();
	}
	
	public void resetAppState() {
		this.menu.click();
		this.resetAppState.click();
	}
	
	public CartPage clickShoppingCart() {
		this.shoppingCart.click();
		return new CartPage(this.driver);
	}
	
	public String getCurrentUrl() {
		return this.driver.getCurrentUrl();
	}
	
	public abstract String getUrl();
}
