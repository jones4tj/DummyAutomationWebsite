package DummyAutomationWebsite.pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import DummyAutomationWebsite.components.Component;
import DummyAutomationWebsite.components.ProductSort;

public class ProductPage extends Component {

	@FindBy(css="select.product_sort_container")
	private WebElement productSort;
	@FindBy(css="div.inventory_item")
	private List<WebElement> productElements;
	
	public ProductPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
	}
	
	public void sortProducts(ProductSort sortOption) {
		Select sortDropdown = new Select(this.productSort);
		sortDropdown.selectByValue(sortOption.getValue());
	}
	
	public List<String> getProductNames() {
		List<String> products = new ArrayList<String>();
		List<WebElement> productNames = this.driver.findElements(By.cssSelector("div.inventory_item_name"));
		productNames.forEach(productName -> products.add(productName.getText()));
		return products;
	}
	
	public List<String> getProductPrices() {
		List<String> products = new ArrayList<String>();
		List<WebElement> productPrices = this.driver.findElements(By.cssSelector("div.inventory_item_price"));
		productPrices.forEach(productPrice -> products.add(productPrice.getText()));
		return products;
	}
	
	public void addProductToCart(String name) throws NoSuchElementException {
		for (WebElement product : productElements) {
			if (product.findElement(By.cssSelector("div.inventory_item_name")).getText().equals(name)) {
				product.findElement(By.cssSelector("button.btn_primary")).click();
				break;
			}
		}
	}

}
