package DummyAutomationWebsite.tests;

import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

import DummyAutomationWebsite.components.ProductSort;
import DummyAutomationWebsite.pageobjects.ProductPage;

public class ProductTest extends BaseTest {
	
	@Test
	public void addProductToCart() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		ProductPage productPage = loginPage.login();
		this.logger.debug("Attempting to login");
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		String productName = "Sauce Labs Backpack";
		try {
			productPage.addProductToCart(productName);
			this.logger.debug("Adding " + productName + " to cart");
		} catch (NoSuchElementException exception) {
			this.logger.debug("Product: " + productName + " is already added to cart.");
		}
		Thread.sleep(5000);
		productPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void productNameSortAtoZ() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		ProductPage productPage = loginPage.login();
		this.logger.debug("Attempting to login");
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		ProductSort sortOption = ProductSort.AtoZ;
		productPage.sortProducts(sortOption);
		this.logger.debug("Sorting products by " + sortOption.getValue());
		List<String> productNamesInActualOrder = productPage.getProductNames();
		List<String> productNamesInExpectedOrder = productPage.getProductNames();
		productNamesInExpectedOrder.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o1.compareTo(o2);
			}
		});
		this.logger.debug("Asserting sorting occurred properly");
		Assert.assertEquals(productNamesInActualOrder, productNamesInExpectedOrder);
		Thread.sleep(3000);
		productPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void productNameSortZtoA() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		ProductPage productPage = loginPage.login();
		this.logger.debug("Attempting to login");
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		ProductSort sortOption = ProductSort.ZtoA;
		productPage.sortProducts(sortOption);
		this.logger.debug("Sorting products by " + sortOption.getValue());
		List<String> productNamesInActualOrder = productPage.getProductNames();
		List<String> productNamesInExpectedOrder = productPage.getProductNames();
		productNamesInExpectedOrder.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				return o2.compareTo(o1);
			}
		});
		this.logger.debug("Asserting sorting occurred properly");
		Assert.assertEquals(productNamesInActualOrder, productNamesInExpectedOrder);
		Thread.sleep(3000);
		productPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void productPricesSortLowtoHigh() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		ProductPage productPage = loginPage.login();
		this.logger.debug("Attempting to login");
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		ProductSort sortOption = ProductSort.LowtoHigh;
		productPage.sortProducts(sortOption);
		this.logger.debug("Sorting products by " + sortOption.getValue());
		List<String> productPricesInActualOrder = productPage.getProductPrices();
		List<String> productPricesInExpectedOrder = productPage.getProductPrices();
		productPricesInExpectedOrder.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				double o1Price = Double.parseDouble(o1.substring(1));
				double o2Price = Double.parseDouble(o2.substring(1));
				return Double.compare(o1Price, o2Price);
			}
		});
		this.logger.debug("Asserting sorting occurred properly");
		Assert.assertEquals(productPricesInActualOrder, productPricesInExpectedOrder);
		Thread.sleep(3000);
		productPage.logout();
		this.logger.debug("Logging out...");
	}
	
	@Test
	public void productPricesSortHightoLow() throws InterruptedException {
		String username = "standard_user";
		this.loginPage.enterUsername(username);
		this.logger.debug("Entering username: " + username);
		this.loginPage.enterPassword("secret_sauce");
		this.logger.debug("Entering password");
		ProductPage productPage = loginPage.login();
		this.logger.debug("Attempting to login");
		this.logger.debug("Navigating to URL: " + productPage.getCurrentUrl());
		ProductSort sortOption = ProductSort.HightoLow;
		productPage.sortProducts(sortOption);
		this.logger.debug("Sorting products by " + sortOption.getValue());
		List<String> productPricesInActualOrder = productPage.getProductPrices();
		List<String> productPricesInExpectedOrder = productPage.getProductPrices();
		productPricesInExpectedOrder.sort(new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				double o1Price = Double.parseDouble(o1.substring(1));
				double o2Price = Double.parseDouble(o2.substring(1));
				return Double.compare(o2Price, o1Price);
			}
		});
		this.logger.debug("Assserting sorting occurred properly");
		Assert.assertEquals(productPricesInActualOrder, productPricesInExpectedOrder);
		Thread.sleep(3000);
		productPage.logout();
		this.logger.debug("Logging out...");
	}
}
