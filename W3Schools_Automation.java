package myWorkSpace;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class W3Schools_Automation {

	static WebDriver driver;
	ExtentSparkReporter htmlReporter;
	ExtentReports reports;
	ExtentTest test;
	
	Properties prop= new Properties();
	String path= System.getProperty("user.dir");
	FileInputStream inp;
	
	@BeforeTest
	public void setUp() throws IOException {
		
		if(driver== null) {
			try {
				inp= new FileInputStream(path+"/src/test/java/configFile/config.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			prop.load(inp);
		}
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			  WebDriverManager.chromedriver().setup();
			  driver= new ChromeDriver();
		  }
		  
		  else if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
			  WebDriverManager.edgedriver().setup();
		      driver= new EdgeDriver();
		  }
		  
		  else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				driver= new FirefoxDriver();
		  }	
			   //Importing Extent Report methods
				htmlReporter = new ExtentSparkReporter("HTMLReport.html");
				reports= new ExtentReports();
				reports.attachReporter(htmlReporter);
				
				//Configuring System info
				reports.setSystemInfo("OS", "Windows 10");
				reports.setSystemInfo("Browser", "Google Chrome");
				reports.setSystemInfo("OS", "Windows 10");
				reports.setSystemInfo("Browser", "Microsoft Edge");
				
				//Setting Up Look and Feel
				htmlReporter.config().setDocumentTitle("W3Schools Automation Test Report");
				htmlReporter.config().setReportName("Test Execution Report");
				htmlReporter.config().setTheme(Theme.DARK);
				htmlReporter.config().setTimeStampFormat("MM/DD/YYYY HH:MM:SS");
		
	}

	@Test
	public void test1() {
		
		test= reports.createTest("Launching and Verfiying Google Chrome");
		driver.manage().window().maximize();
		driver.navigate().to(prop.getProperty("url"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		System.out.println("We'll Use this method to Verify whether google.com is launched or not");
		System.out.println("=============================");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("=============================");
		
		WebElement logo1= driver.findElement(By.cssSelector("img.lnXdpd"));
		if(logo1.isDisplayed()) {
			System.out.println("Congrats, Welcome to Google");
			System.out.println("=============================");
		}
		else {
			System.out.println("Please Check Your Source code");
			System.out.println("=============================");
		}
	}

	@Test
	public void test2() throws InterruptedException {
		
		test= reports.createTest("Search and click W3Schools from the Google Auto-suggestion");
		System.out.println("We'll Use this method to Search W3 Schools from the Google Auto Suggestion");
		System.out.println("=============================");
		
		driver.findElement(By.name("q")).sendKeys(prop.getProperty("input1"));
		Thread.sleep(3000);
		List<WebElement> auto= driver.findElements(By.xpath("//ul[@class='G43f7e']/li"));
		
		int count= auto.size();
		System.out.println(count);
		
		String selText= prop.getProperty("text");
		for(WebElement option: auto) {
			//System.out.println(option);
			
			if(option.getText().equalsIgnoreCase(selText)){
				option.click();
				break;
			}		
		  }
		driver.findElement(By.cssSelector("h3.LC20lb.MBeuO.DKV0Md")).click();
	}
	
	@Test
	public void test3() {
		
		test= reports.createTest("Verifying W3Schools Website");
		System.out.println("We'll Use this method to Check Whether W3Schools website is launched or not");
		System.out.println("=============================");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("=============================");
		
		WebElement logo2= driver.findElement(By.cssSelector("i.fa.fa-logo"));
		if(logo2.isDisplayed()) {
			System.out.println("Welcome to W3Schools");
			System.out.println("=============================");
		}
		else {
			System.out.println("Please check your Source code");
			System.out.println("=============================");
		}
		
		driver.findElement(By.id("search2")).sendKeys(prop.getProperty("input2")+Keys.ENTER);
	}
	
	
	@Test
	public void test4() throws InterruptedException {
		
		test= reports.createTest("Verifying W3Schools SQL page");
		System.out.println("We'll Use this method to Check Whether W3 Schools SQL page "
				+ " is opened or not and Open SQL Query Editor");
		System.out.println("=============================");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("=============================");
		
		WebElement logo3= driver.findElement
				(By.cssSelector("i.fa.fa-logo.ws-text-green.ws-hover-text-green"));
		//fa fa-logo ws-text-green ws-hover-text-green
		if(logo3.isDisplayed()) {
			System.out.println("Welcome to W3Schools SQL Tutorial");
			System.out.println("=============================");
		}
		else {
			System.out.println("Please Check Your Source Code");
			System.out.println("=============================");
		}
		
		Actions action1= new Actions (driver);
		action1.moveToElement(driver.findElement(By.linkText("Try it Yourself »")
				)).click().build().perform();
		
		Thread.sleep(3000);
		Set<String> Win= driver.getWindowHandles();
		Iterator<String> it= Win.iterator();
		String Win1= it.next();
		String Win2= it.next();
		
		driver.switchTo().window(Win2);
		
	}
	
	@Test
	public void test5() throws InterruptedException {
		
		test= reports.createTest("Run SQL Query");
		System.out.println("We'll Use this method to Clear the Text in the Query box,"
				+ " Re-Enter our query and Run our Query");
		System.out.println("=============================");
		
		System.out.println(driver.getCurrentUrl());
		System.out.println("=============================");
		
		Actions action2= new Actions(driver);
		String query= prop.getProperty("query");
		Thread.sleep(3000);
		
		action2.moveToElement(driver.findElement(By.className("CodeMirror-lines")))
		.doubleClick().click().sendKeys(Keys.BACK_SPACE)
		.sendKeys(query).build().perform();
		
		driver.findElement(By.className("ws-btn")).click();
		
		JavascriptExecutor js= (JavascriptExecutor)driver;
		js.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(3000);
	}
	
	@Test
	public void test6() throws InterruptedException {
		
		test= reports.createTest("Checking Broken links");
		//finding total hyperlinks in a WebPage
		List<WebElement> links= driver.findElements(By.tagName("a"));
		System.out.println("Total links are: " +links.size());
		
		int resCode= 200;
		int brokenLinks= 0;
		
		for(WebElement element: links) {
			
			//captured entire urls of the hyperlinks 
			String url= element.getAttribute("href");
			
			try {
				URL urlLink= new URL(url);
				
				//opened connections for the Captured URLs
				HttpURLConnection h= (HttpURLConnection)urlLink.openConnection();
				
				//sent requests to the links
				h.setRequestMethod("HEAD");
				
				//connected the Links
				h.connect();
				
				//get their response codes
				resCode= h.getResponseCode();
				
				if(resCode >= 400) {
					System.out.println(url + " is broken");
					brokenLinks++;
				}
				else {
					System.out.println("No broken links are found");
				}
			}
		catch(MalformedURLException e) {
			
		}
			catch(Exception e) {
			}
		}
		System.out.println("Total broken links are: " + brokenLinks);
	}
		
		
		@AfterMethod
		public void getResult(ITestResult result) {
			
			if(result.getStatus()== ITestResult.SUCCESS) {
				test.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"Passed", ExtentColor.GREEN));
			}
			else if(result.getStatus()== ITestResult.FAILURE) {
				test.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"Failed", ExtentColor.RED));
			}
			else if(result.getStatus()== ITestResult.SKIP) {
				test.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"Skipped", ExtentColor.PURPLE));
			}
		}
		
		@AfterTest
		public void tearDown() {
			
			reports.flush();
			driver.quit();
		}
}
