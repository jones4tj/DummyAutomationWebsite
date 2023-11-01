package DummyAutomationWebsite.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import DummyAutomationWebsite.pageobjects.CartPage;
import DummyAutomationWebsite.pageobjects.CheckoutCompletePage;
import DummyAutomationWebsite.pageobjects.CheckoutInfoPage;
import DummyAutomationWebsite.pageobjects.CheckoutOverviewPage;
import DummyAutomationWebsite.pageobjects.ProductPage;

public class CheckoutCompleteTest extends BaseTest {

	@Test
	public void checkingHeader() throws InterruptedException {
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
		this.logger.debug("Clicking finish");
		CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finish();
		this.logger.debug("Navigating to URL: " + checkoutCompletePage.getCurrentUrl());
		String actualHeader = checkoutCompletePage.getHeader();
		String expectedHeader = "Thank you for your order!";
		this.logger.debug("Asserting header is " + expectedHeader);
		Assert.assertEquals(actualHeader, expectedHeader);
		Thread.sleep(2000);
		checkoutInfoPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void clickBackHome() throws InterruptedException {
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
		this.logger.debug("Clicking finish");
		CheckoutCompletePage checkoutCompletePage = checkoutOverviewPage.finish();
		this.logger.debug("Navigating to URL: " + checkoutCompletePage.getCurrentUrl());
		this.logger.debug("Clicking back home button");
		checkoutCompletePage.clickHomeButton();
		this.logger.debug("Navigating to URL: " + checkoutCompletePage.getCurrentUrl());
		String expectedURL = "https://www.saucedemo.com/inventory.html";
		this.logger.debug("Asserting URL is " + expectedURL);
		Assert.assertEquals(checkoutCompletePage.getCurrentUrl(), expectedURL);
		Thread.sleep(2000);
		checkoutInfoPage.logout();
		this.logger.debug("Logging out...");
	}
}
