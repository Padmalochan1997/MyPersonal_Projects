package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample_program {

	public static WebDriver driver;
	static String path= System.getProperty("user.dir");
	static FileInputStream input, locator;
	static Properties prop= new Properties();
	public static void main(String[] args) throws IOException {

		if(driver== null) {

			input= new FileInputStream(path+"/src/main/java/com/packages/configurations/config.properties");
			prop.load(input);

			if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				driver= new ChromeDriver();
			}
			driver.get("https://rahulshettyacademy.com/seleniumPractise/#/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(30000, TimeUnit.SECONDS);
			String items= prop.getProperty("MyProducts");
			String myItems[]= items.split(",");
			
			for(String veggies: myItems) {
				System.out.print(veggies);
			}
//			String items[]= {"Brocolli","Banana","Cauliflower","Mango"};

//			System.out.println("Total items in arrays are "+myItems.length);
			addItems(driver,myItems);
		}
	}

	public static void addItems(WebDriver driver, String myItems[]) {

		int j=0;
		List<WebElement> productNames= driver.findElements(By.cssSelector("h4.product-name"));
		System.out.println("Total products are "+productNames.size());

		for(int i=0;i<productNames.size();i++) {
			String productName[]= productNames.get(i).getText().split("-");
			String formattedName= productName[0].trim();
			List myProducts= Arrays.asList(myItems);

			if(myProducts.contains(formattedName)) {
				j++;
				driver.findElements(By.xpath("//div[@class= 'product-action']/button")).get(i).click();

				if(j==myItems.length)
					break;
			}
		}

		driver.findElement(By.xpath("//a[@class='cart-icon']/img")).click();
	}
}
