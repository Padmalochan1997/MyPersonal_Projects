package testcases;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import baseClass.BaseClass;
import pageobjects.Google_Page;
import pageobjects.GreenKart_HomePage;
import pageobjects.Product_Details;
import pageobjects.SelectCountry;

public class MultiProduct extends BaseClass{

	@Test(priority=1, description="Search from Google")
	public void searchFromGoogle() {
		driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
		Google_Page tc1= new Google_Page(driver);
		tc1.sendQuerytoGoogle(prop.getProperty("MyApp"));
		List<WebElement> auto= driver.findElements(By.xpath(loc.getProperty("autoSuggestions")));

		for(WebElement options: auto) {
			if(options.getText().equalsIgnoreCase(prop.getProperty("MyApp"))) {
				options.click();
				break;
			}
		}

		tc1.clickSearchResult();
	}

	@Test(priority= 2, description="Verify the page and adding products to cart")
	public void homePage() throws InterruptedException {
		GreenKart_HomePage tc2= new GreenKart_HomePage(driver);
		WebElement logo= driver.findElement(By.cssSelector(loc.getProperty("appLogo")));
		Assert.assertTrue(logo.isDisplayed(), "Checking if Logo is displayed");

		String itemsNeeded= prop.getProperty("MyProducts");
		String items[]= itemsNeeded.split(",");
		System.out.print("My items are "+items+ "and the length of the array is "+items.length);

		int j=0;
		List<WebElement> allProducts= driver.findElements(By.cssSelector(loc.getProperty("productNames")));
		for(int i=0;i<allProducts.size();i++)
		{
			String productNames[]= allProducts.get(i).getText().split("-");
			String names= productNames[0].trim();
			List myItems= Arrays.asList(items);
			if(myItems.contains(names)) {
				j++;
				driver.findElements(By.xpath(loc.getProperty("AddToCartBtns"))).get(i).click();
				Thread.sleep(2000);

				if(j==itemsNeeded.length()) {
					break;
				}
			}
		}

		tc2.clickCartIcon();
		tc2.proceedToCheckout();

	}

	@Test(priority= 3, description="Verify product, Applying promocode and proceed")
	public void checkoutPage(){
		Product_Details tc3= new Product_Details(driver);
		tc3.enterPromocode(prop.getProperty("Promocode"));
		WebElement successMsg= driver.findElement(By.xpath(loc.getProperty("successAlert")));

		if(successMsg.isDisplayed()) {
			System.out.println("Promocode Applied Successfully");
		}

		else {
			System.out.println("Check Your code");
		}

		tc3.clickPlaceOrder();

	}

	@Test(priority= 4, description="Select the country")
	public void selectCountry() {

		SelectCountry tc4= new SelectCountry(driver);
		WebElement selectDropdown= driver.findElement(By.cssSelector(loc.getProperty("dropdown")));
		Select select= new Select(selectDropdown);
		select.selectByVisibleText(prop.getProperty("myCountry"));
		tc4.clickCheckBox();
		tc4.clickProceedBtn();

	}

}

