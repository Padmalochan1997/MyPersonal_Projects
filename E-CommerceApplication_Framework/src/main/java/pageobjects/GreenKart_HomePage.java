package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class GreenKart_HomePage {
	static WebDriver driver;
	
	public GreenKart_HomePage(WebDriver d) {
		driver= d;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how= How.CSS,using= "input[type='search']") WebElement searchBar;
	@FindBy(how= How.XPATH,using= "//button[@type='submit']") WebElement searchBtn;
	@FindBy(how= How.CSS,using="a.increment") WebElement incrementBtn;
	@FindBy(how= How.XPATH,using= "//div[@class='product-action']/button") WebElement addToCartBtn;
	@FindBy(how= How.XPATH,using= "//a/img[@alt='Cart']") WebElement cartIcon;
	@FindBy(how= How.XPATH,using= "//div/button[text()='PROCEED TO CHECKOUT']") WebElement checkOutBtn;
	
	public void searchProduct(String product) {
		this.searchBar.sendKeys(product);
	}
	
	public void clickSearchBtn() {
		this.searchBtn.click();
	}
	
	public void increaseItems() {
		this.incrementBtn.click();
	}
	
	public void clickAddToCart() {
		this.addToCartBtn.click();
	}
	
	public void clickCartIcon() {
		this.cartIcon.click();
	}
	
	public void proceedToCheckout() {
		this.checkOutBtn.click();
	}
}
