package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class Google_Page {
	
static WebDriver driver;
	
	public Google_Page(WebDriver d) {
		driver= d;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(name="q") WebElement globalSearch;
	@FindBy(name= "btnk") WebElement searchBtn;
	@FindBy(how= How.XPATH,using= "//*[contains(text(), 'GreenKart - veg and fruits kart')]") WebElement searchResult;
	
	public void sendQuerytoGoogle(String query) {
		this.globalSearch.sendKeys(query);
	}
	
	public void clickSearchBtn() {
		this.searchBtn.click();
	}
	
	public void clickSearchResult() {
		this.searchResult.click();
	}
}
