package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class Product_Details {

	static WebDriver driver;

	public Product_Details(WebDriver d) {
		driver= d;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className= "promoCode") WebElement promocodeInput;
	@FindBy(how= How.LINK_TEXT, using="Apply") WebElement applyPromocodeBtn;
	@FindBy(how= How.XPATH, using="//*[text()='Place Order']") WebElement placeOrderBtn;

	public void enterPromocode(String promocode) {
		this.promocodeInput.sendKeys(promocode, Keys.TAB,Keys.ENTER);
	}

	public void clickOnApply() {
		this.applyPromocodeBtn.click();
	}

	public void clickPlaceOrder() {
		this.placeOrderBtn.click();
	}

}
