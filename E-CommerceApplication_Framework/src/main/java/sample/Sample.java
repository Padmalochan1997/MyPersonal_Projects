package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample {

	static WebDriver driver;
	static String path= System.getProperty("user.dir");
	static FileInputStream fi;
	static Properties prop= new Properties();
	
	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().setup();
		driver= new ChromeDriver();
		fi= new FileInputStream(path+"/src/main/java/com/packages/configurations/config.properties");
		prop.load(fi);
		
		String items= prop.getProperty("MyProducts");
	    String itemsNeeded[]= items.split(",");
	    
	    for(String veggies: itemsNeeded) {
	    	System.out.println(veggies);
	    }
//		String itemsNeeded[]= {"Brocolli", "Cucumber", "Cauliflower"};
		
		addItems(driver, itemsNeeded);

	}

	public static void addItems(WebDriver driver, String itemsNeeded[]) {

		driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
		List<WebElement> items= driver.findElements(By.cssSelector("h4.product-name"));
		for(int i=0; i<items.size();i++) {
			String names[]= items.get(i).getText().split("-");
			String formattedName= names[0].trim();

			List myItems= Arrays.asList(itemsNeeded);
			int j=0;
			if(myItems.contains(formattedName)) {
				j++;
				driver.findElements(By.xpath("//div[@class='product-action']/button")).get(i).click();
			}
			
			if(j==itemsNeeded.length)
				break;
		}
	}
}
