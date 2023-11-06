package DummyAutomationWebsite.stepdefinitions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import DummyAutomationWebsite.components.Comparators;
import DummyAutomationWebsite.components.ProductSort;
import DummyAutomationWebsite.pageobjects.CartPage;
import DummyAutomationWebsite.pageobjects.CheckoutCompletePage;
import DummyAutomationWebsite.pageobjects.CheckoutInfoPage;
import DummyAutomationWebsite.pageobjects.CheckoutOverviewPage;
import DummyAutomationWebsite.pageobjects.LoginPage;
import DummyAutomationWebsite.pageobjects.ProductPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FunctionalTestingStepDefinition {
	
	private Logger logger;
	private WebDriver driver;
	private LoginPage loginPage;
	private ProductPage productPage;
	private CartPage cartPage;
	private CheckoutInfoPage checkoutInfoPage;
	private CheckoutOverviewPage checkoutOverviewPage;
	private CheckoutCompletePage checkoutCompletePage;
	
	@Before
	public void setup() {
		this.logger = LogManager.getLogger(this.getClass());
		this.logger.debug("Initializing driver");
		WebDriverManager.chromedriver().setup();
		this.driver = new ChromeDriver();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		this.driver.manage().window().maximize();
		this.loginPage = new LoginPage(driver);
	}
	
	@When("I navigate to the login page")
	public void i_navigate_to_the_login_page() {
	    this.loginPage.goTo();
	    this.logger.debug("Navigating to URL: " + this.loginPage.getCurrentUrl());
	}

	@When("I attempt to login")
	public void i_attempt_to_login() {
		this.logger.debug("Attempting to login");
	    this.productPage = loginPage.login();
	    try {
	    	String errorMessage = loginPage.getErrorMessage();
	    	this.logger.debug(errorMessage);
	    } catch (NoSuchElementException exception) {
	    	Assert.assertEquals(productPage.getCurrentUrl(), productPage.getUrl());
	    	this.logger.debug("Navigating to URL: " + productPage.getUrl());
	    }
	}
	
	@When("I enter my username {string}")
	public void i_enter_my_username(String username) {
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterUsername(username);
	}
	
	@When("I enter my password {string}")
	public void i_enter_my_password(String password) {
		this.logger.debug("Entering password");
		this.loginPage.enterPassword(password);
	}
	
	@When("I enter my username {string} and password {string}")
	public void i_enter_my_username_and_password(String username, String password) {
		this.logger.debug("Entering username: " + username);
	    this.loginPage.enterUsername(username);
	    this.logger.debug("Entering password");
	    this.loginPage.enterPassword(password);
	}
	
	@When("I add product {string} to cart")
	public void i_add_product_to_cart(String productName) {
		this.logger.debug("Adding product: " + productName + " to cart");
		try {
			this.productPage.addProductToCart(productName);
		} catch (NoSuchElementException exception) {
			this.logger.debug("Product: " + productName + " has already been added to cart");
			Assert.assertTrue(false);
		}
	}
	
	@When("I sort products {string}")
	public void i_sort_products(String sortOption) {
		List<String> productsInActualOrder = new ArrayList<String>();
		List<String> productsInExpectedOrder = new ArrayList<String>();
		if (sortOption.equals("AtoZ")) {
			ProductSort productSort = ProductSort.AtoZ;
			this.logger.debug("Sorting products by " + sortOption);
			this.productPage.sortProducts(productSort);
			productsInActualOrder = this.productPage.getProductNames();
			productsInExpectedOrder = this.productPage.getProductNames();
			productsInExpectedOrder.sort(Comparators.AtoZ);
		} else if (sortOption.equals("ZtoA")) {
			ProductSort productSort = ProductSort.ZtoA;
			this.logger.debug("Sorting products by " + sortOption);
			this.productPage.sortProducts(productSort);
			productsInActualOrder = this.productPage.getProductNames();
			productsInExpectedOrder = this.productPage.getProductNames();
			productsInExpectedOrder.sort(Comparators.ZtoA);
		} else if (sortOption.equals("LowtoHigh")) {
			ProductSort productSort = ProductSort.LowtoHigh;
			this.productPage.sortProducts(productSort);
			this.logger.debug("Sorting products by " + sortOption);
			productsInActualOrder = this.productPage.getProductPrices();
			productsInExpectedOrder = this.productPage.getProductPrices();
			productsInExpectedOrder.sort(Comparators.LowtoHigh);
		} else if (sortOption.equals("HightoLow")) {
			ProductSort productSort = ProductSort.HightoLow;
			this.logger.debug("Sorting products by " + sortOption);
			this.productPage.sortProducts(productSort);
			productsInActualOrder = this.productPage.getProductPrices();
			productsInExpectedOrder = this.productPage.getProductPrices();
			productsInExpectedOrder.sort(Comparators.HightoLow);
		}
		this.logger.debug("Asserting sorting occurred properly");
		Assert.assertEquals(productsInActualOrder, productsInExpectedOrder);
	}
	
	@Then("I navigate to cart page")
	public void i_navigate_to_cart_page() {
		this.cartPage = this.productPage.clickShoppingCart();
		this.logger.debug("Navigating to URL: " + this.cartPage.getCurrentUrl());
	}
	
	@Then("I remove product {string} from cart")
	public void i_remove_product_from_cart(String productName) {
		this.logger.debug("Removing " + productName + " from cart");
		this.cartPage.removeProductFromCart(productName);
		this.logger.debug("Asserting " + productName + " is no longer in the cart");
		Assert.assertFalse(this.cartPage.isProductInCart(productName));
	}
	
	@Then("I continue shopping")
	public void i_continue_shopping() {
		this.logger.debug("Continue shopping");
		this.cartPage.continueShopping();
		this.logger.debug("Navigating to URL: " + this.cartPage.getCurrentUrl());
		this.logger.debug("Asserting URL is " + this.productPage.getUrl());
		Assert.assertEquals(this.cartPage.getCurrentUrl(), this.productPage.getUrl());
	}
	
	@Then("I checkout")
	public void i_checkout() {
		this.logger.debug("Checking out");
		this.checkoutInfoPage = this.cartPage.checkout();
		this.logger.debug("Navigating to URL: " + this.checkoutInfoPage.getCurrentUrl());
		this.logger.debug("Asserting URL is " + this.checkoutInfoPage.getUrl());
		Assert.assertEquals(this.checkoutInfoPage.getCurrentUrl(), this.checkoutInfoPage.getUrl());
	}
	
	@Then("I test required checkout info fields {string} {string} {string}")
	public void i_test_required_checkout_info_fields(String firstName, String lastName, String postalCode) {
		this.logger.debug("Continue Checkout");
		this.checkoutInfoPage.checkout();
		this.logger.debug(this.checkoutInfoPage.getErrorMessage());
		this.logger.debug("Entering first name: " + firstName);
		this.checkoutInfoPage.enterFirstName(firstName);
		this.logger.debug("Continue Checkout");
		this.checkoutInfoPage.checkout();
		this.logger.debug(this.checkoutInfoPage.getErrorMessage());
		this.logger.debug("Entering last name: " + lastName);
		this.checkoutInfoPage.enterLastName(lastName);
		this.logger.debug("Continue Checkout");
		this.checkoutInfoPage.checkout();
		this.logger.debug(this.checkoutInfoPage.getErrorMessage());
		this.logger.debug("Entering postal code: " + postalCode);
		this.checkoutInfoPage.enterPostalCode(postalCode);
		this.logger.debug("Continue checkout");
		this.checkoutOverviewPage = this.checkoutInfoPage.checkout();
		this.logger.debug("Navigating to URL: " + this.checkoutOverviewPage.getCurrentUrl());
		this.logger.debug("Asserting URL is " + this.checkoutOverviewPage.getUrl());
		Assert.assertEquals(this.checkoutOverviewPage.getCurrentUrl(), this.checkoutOverviewPage.getUrl());
	}
	
	@Then("I cancel checkout")
	public void i_cancel_checkout() {
		this.logger.debug("Cancelling checkout");
		this.checkoutInfoPage.cancel();
		this.logger.debug("Navigating to URL: " + this.checkoutInfoPage.getCurrentUrl());
		this.logger.debug("Asserting URL is " + this.cartPage.getUrl());
		Assert.assertEquals(this.checkoutInfoPage.getCurrentUrl(), this.cartPage.getUrl());
	}
	
	@Then("I continue checkout {string} {string} {string}")
	public void i_continue_checkout(String firstName, String lastName, String postalCode) {
		this.logger.debug("Entering first name: " + firstName);
		this.checkoutInfoPage.enterFirstName(firstName);
		this.logger.debug("Entering last name: " + lastName);
		this.checkoutInfoPage.enterLastName(lastName);
		this.logger.debug("Entering postal code: " + postalCode);
		this.checkoutInfoPage.enterPostalCode(postalCode);
		this.logger.debug("Continuing checkout");
		this.checkoutOverviewPage = this.checkoutInfoPage.checkout();
		this.logger.debug("Navigating to URL: " + this.checkoutOverviewPage.getCurrentUrl());
		this.logger.debug("Asserting URL is " + this.checkoutOverviewPage.getUrl());
		Assert.assertEquals(this.checkoutOverviewPage.getCurrentUrl(), this.checkoutOverviewPage.getUrl());
	}
	
	@Then("I verify total in checkout overview page")
	public void i_verify_total_in_checkout_overview_page() {
		double expectedSubtotal = this.checkoutOverviewPage.calculateSubtotal();
		double actualSubtotal = this.checkoutOverviewPage.getActualSubtotal();
		this.logger.debug("Asserting subtotal is calculated correctly");
		Assert.assertEquals(actualSubtotal, expectedSubtotal);
		double tax = this.checkoutOverviewPage.getTax();
		double expectedTotal = expectedSubtotal + tax;
		double actualTotal = this.checkoutOverviewPage.getTotal();
		this.logger.debug("Asserting total is calculated correctly");
		Assert.assertEquals(actualTotal, expectedTotal);
	}
	
	@Then("I finish checkout")
	public void i_finish_checkout() {
		this.logger.debug("Finishing checkout");
		this.checkoutCompletePage = this.checkoutOverviewPage.finish();
		this.logger.debug("Navigating to URL: " + this.checkoutCompletePage.getCurrentUrl());
		this.logger.debug("Asserting URL is " + this.checkoutCompletePage.getUrl());
		Assert.assertEquals(this.checkoutCompletePage.getCurrentUrl(), this.checkoutCompletePage.getUrl());
	}
	
	@Then("I verify header {string}")
	public void i_verify_header(String header) {
		this.logger.debug("Asserting header is " + header);
		Assert.assertEquals(this.checkoutCompletePage.getHeader(), header);
	}
	
	@Then("I click back home button")
	public void i_click_back_home_button() {
		this.logger.debug("Clicking back home button");
		this.checkoutCompletePage.clickHomeButton();
		this.logger.debug("Navigating to URL: " + this.checkoutCompletePage.getCurrentUrl());
		this.logger.debug("Asserting URL is " + this.productPage.getUrl());
		Assert.assertEquals(this.checkoutCompletePage.getCurrentUrl(), this.productPage.getUrl());
	}
	
	@Then("I logout")
	public void i_logout() {
		if (!this.loginPage.getUrl().equals(this.loginPage.getCurrentUrl())) {
			this.logger.debug("Logging out");
			this.loginPage.logout();
		}
	}

	@After
	public void teardown() {
		this.logger.debug("Quitting driver");
		this.driver.quit();
	}
}
