package pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

public class SelectCountry {

	static WebDriver driver;

	public SelectCountry(WebDriver d) {
		driver= d;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how= How.CSS, using="div[class='wrapperTwo'] div select") WebElement dropDown;
	@FindBy(className= "chkAgree") WebElement Checkbox;
	@FindBy(how= How.XPATH,using="//button[normalize-space()='Proceed']") WebElement proceedBtn;
	
	public void clickDropdown() {
		this.dropDown.click();
	}
	
	public void clickCheckBox() {
		this.Checkbox.click();
	}
	
	public void clickProceedBtn() {
		this.proceedBtn.click();
	}
}
