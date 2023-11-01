package DummyAutomationWebsite.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import DummyAutomationWebsite.pageobjects.CartPage;
import DummyAutomationWebsite.pageobjects.ProductPage;

public class CartTest extends BaseTest {

	@Test
	public void removeProductFromCart() throws InterruptedException {
		String productName = "Sauce Labs Backpack";
		CartPage cartPage = this.addProductToCartAndNavigateToCartPage(productName);
		this.logger.debug("Removing " + productName + " from cart");
		cartPage.removeProductFromCart(productName);
		this.logger.debug("Asserting " + productName + " is no longer in the cart");
		Assert.assertFalse(cartPage.isProductInCart(productName));
		Thread.sleep(2000);
		cartPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void continueShopping() throws InterruptedException {
		String productName = "Sauce Labs Backpack";
		CartPage cartPage = this.addProductToCartAndNavigateToCartPage(productName);
		cartPage.continueShopping();
		this.logger.debug("Clicked Continue Shopping");
		this.logger.debug("Navigating to URL: " + cartPage.getCurrentUrl());
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		this.logger.debug("Asserting URL is " + expectedURL);
		Assert.assertEquals(cartPage.getCurrentUrl(), expectedURL);
		Thread.sleep(2000);
		cartPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void checkout() throws InterruptedException {
		String productName = "Sauce Labs Backpack";
		CartPage cartPage = this.addProductToCartAndNavigateToCartPage(productName);
		cartPage.checkout();
		this.logger.debug("Clicked Checkout");
		this.logger.debug("Navigating to URL: " + cartPage.getCurrentUrl());
		String expectedURL = "https://www.saucedemo.com/checkout-step-one.html";
		this.logger.debug("Asserting URL is " + expectedURL);
		Assert.assertEquals(cartPage.getCurrentUrl(), expectedURL);
		Thread.sleep(2000);
		cartPage.logout();
		this.logger.debug("Logging out...");
		
	}
	
	private CartPage addProductToCartAndNavigateToCartPage(String productName) {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		ProductPage productPage = this.loginPage.login();
		this.logger.debug("Attempting to login");
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		this.logger.debug("Adding " + productName + " to cart");
		productPage.addProductToCart(productName);
		return productPage.clickShoppingCart();
	}
}
