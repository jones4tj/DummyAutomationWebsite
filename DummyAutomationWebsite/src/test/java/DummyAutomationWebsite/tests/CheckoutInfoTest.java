package DummyAutomationWebsite.tests;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import DummyAutomationWebsite.pageobjects.CartPage;
import DummyAutomationWebsite.pageobjects.CheckoutInfoPage;
import DummyAutomationWebsite.pageobjects.ProductPage;

public class CheckoutInfoTest extends BaseTest {

	@Test
	public void testRequiredFields() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		this.logger.debug("Attempting to login");
		ProductPage productPage = this.loginPage.login();
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		String productName = "Sauce Labs Backpack";
		productPage.addProductToCart(productName);
		this.logger.debug("Adding " + productName + " to cart");
		this.logger.debug("Clicking shopping cart");
		CartPage cartPage = productPage.clickShoppingCart();
		this.logger.debug("Navigating to URL: " + cartPage.getCurrentUrl());
		this.logger.debug("Checking out");
		CheckoutInfoPage checkoutInfoPage = cartPage.checkout();
		this.logger.debug("Navigating to URL: " + checkoutInfoPage.getCurrentUrl());
		checkoutInfoPage.checkout();
		this.logger.debug("Continue checkout");
		String errorMessage = getErrorMessage(checkoutInfoPage);
		this.logger.debug(errorMessage);
		String expectedErrorMessage = "Error: First Name is required";
		this.logger.debug("Asserting error message is " + expectedErrorMessage);
		Assert.assertEquals(errorMessage, expectedErrorMessage);
		String firstName = "First Name";
		checkoutInfoPage.enterFirstName(firstName);
		this.logger.debug("Entering First Name: " + firstName);
		checkoutInfoPage.checkout();
		this.logger.debug("Continue checkout");
		errorMessage = getErrorMessage(checkoutInfoPage);
		this.logger.debug(errorMessage);
		expectedErrorMessage = "Error: Last Name is required";
		this.logger.debug("Asserting error message is " + expectedErrorMessage);
		Assert.assertEquals(errorMessage, expectedErrorMessage);
		String lastName = "Last Name";
		checkoutInfoPage.enterLastName(lastName);
		this.logger.debug("Entering Last Namee: " + lastName);
		checkoutInfoPage.checkout();
		this.logger.debug("Continue checkout");
		errorMessage = getErrorMessage(checkoutInfoPage);
		this.logger.debug(errorMessage);
		expectedErrorMessage = "Error: Postal Code is required";
		this.logger.debug("Asserting error message is " + expectedErrorMessage);
		Assert.assertEquals(errorMessage, expectedErrorMessage);
		String postalCode = "Postal Code";
		checkoutInfoPage.enterPostalCode(postalCode);
		this.logger.debug("Entering Postal Code: " + postalCode);
		checkoutInfoPage.checkout();
		this.logger.debug("Continue checkout");
		Thread.sleep(2000);
		checkoutInfoPage.logout();
		this.logger.debug("Logging out...");
		
	}
	
	private String getErrorMessage(CheckoutInfoPage checkoutInfoPage) {
		try {
			return checkoutInfoPage.getErrorMessage();
		} catch (NoSuchElementException exception) {
			return "No error message";
		}
	}
	
	@Test
	public void cancel() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		this.logger.debug("Attempting to login");
		ProductPage productPage = this.loginPage.login();
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		String productName = "Sauce Labs Backpack";
		productPage.addProductToCart(productName);
		this.logger.debug("Adding " + productName + " to cart");
		this.logger.debug("Clicking shopping cart");
		CartPage cartPage = productPage.clickShoppingCart();
		this.logger.debug("Navigating to URL: " + cartPage.getCurrentUrl());
		this.logger.debug("Checking out");
		CheckoutInfoPage checkoutInfoPage = cartPage.checkout();
		this.logger.debug("Navigating to URL: " + checkoutInfoPage.getCurrentUrl());
		checkoutInfoPage.cancel();
		this.logger.debug("Cancelling");
		this.logger.debug("Navigating to URL: " + checkoutInfoPage.getCurrentUrl());
		String expectedURL = "https://www.saucedemo.com/cart.html";
		this.logger.debug("Asserting URL is " + expectedURL);
		Assert.assertEquals(checkoutInfoPage.getCurrentUrl(), expectedURL);
		Thread.sleep(2000);
		checkoutInfoPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void continueCheckout() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		this.logger.debug("Attempting to login");
		ProductPage productPage = this.loginPage.login();
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		String productName = "Sauce Labs Backpack";
		productPage.addProductToCart(productName);
		this.logger.debug("Adding " + productName + " to cart");
		this.logger.debug("Clicking shopping cart");
		CartPage cartPage = productPage.clickShoppingCart();
		this.logger.debug("Navigating to URL: " + cartPage.getCurrentUrl());
		this.logger.debug("Checking out");
		CheckoutInfoPage checkoutInfoPage = cartPage.checkout();
		this.logger.debug("Navigating to URL: " + checkoutInfoPage.getCurrentUrl());
		String firstName = "First Name";
		checkoutInfoPage.enterFirstName(firstName);
		this.logger.debug("Entering First Name: " + firstName);
		String lastName = "Last Name";
		checkoutInfoPage.enterLastName(lastName);
		this.logger.debug("Entering Last Namee: " + lastName);
		String postalCode = "Postal Code";
		checkoutInfoPage.enterPostalCode(postalCode);
		this.logger.debug("Entering Postal Code: " + postalCode);
		checkoutInfoPage.checkout();
		this.logger.debug("Continue Checkout");
		this.logger.debug("Navigating to URL: " + checkoutInfoPage.getCurrentUrl());
		String expectedURL = "https://www.saucedemo.com/checkout-step-two.html";
		this.logger.debug("Asserting URL is " + expectedURL);
		Assert.assertEquals(checkoutInfoPage.getCurrentUrl(), expectedURL);
		Thread.sleep(2000);
		checkoutInfoPage.logout();
		this.logger.debug("Logging out...");
	}
}
