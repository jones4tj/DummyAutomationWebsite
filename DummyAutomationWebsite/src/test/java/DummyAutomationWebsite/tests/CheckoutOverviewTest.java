package DummyAutomationWebsite.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import DummyAutomationWebsite.pageobjects.CartPage;
import DummyAutomationWebsite.pageobjects.CheckoutInfoPage;
import DummyAutomationWebsite.pageobjects.CheckoutOverviewPage;
import DummyAutomationWebsite.pageobjects.ProductPage;

public class CheckoutOverviewTest extends BaseTest {

	@Test
	public void calculateTotal() throws InterruptedException {
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
		CheckoutOverviewPage checkoutOverviewPage = checkoutInfoPage.checkout();
		this.logger.debug("Continue Checkout");
		this.logger.debug("Navigating to URL: " + checkoutOverviewPage.getCurrentUrl());
		double expectedSubtotal = checkoutOverviewPage.calculateSubtotal();
		double actualSubtotal = checkoutOverviewPage.getActualSubtotal();
		this.logger.debug("Asserting subtotal is calculated correctly");
		Assert.assertEquals(actualSubtotal, expectedSubtotal);
		double tax = checkoutOverviewPage.getTax();
		double expectedTotal = expectedSubtotal + tax;
		double actualTotal = checkoutOverviewPage.getTotal();
		this.logger.debug("Asserting total is calculated correctly");
		Assert.assertEquals(actualTotal, expectedTotal);
		Thread.sleep(2000);
		checkoutInfoPage.logout();
		this.logger.debug("Logging out...");
	}
}
