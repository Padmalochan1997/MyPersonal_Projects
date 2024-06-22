package baseClass;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.markuputils.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Const;

public class BaseClass {
	
	public static WebDriver driver;
	public static String path= System.getProperty("user.dir");
	public static FileInputStream file1, file2;
	public static Properties prop= new Properties();
	public static Properties loc= new Properties();
	public static ExtentSparkReporter sparkReporter;
	public static ExtentReports extent;
	public static ExtentTest logger;

	@BeforeTest(description="Creating Methods for Extent reports generation")
	public void createReports() {
		
		sparkReporter= new ExtentSparkReporter(path+"/E-CommerceApplication_Framework/Reports/TestExecutionReports");
		extent= new ExtentReports();
		extent.attachReporter(sparkReporter);
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent.setSystemInfo("OS", "Windows 11");
		extent.setSystemInfo("Browser", "Chrome,Edge and Firefox");
			
	}
	
	@BeforeMethod(description="Setting up browser")
	@Parameters("browser")
	public void setupBrowser(String browser, Method testMethod) throws IOException {
		
		logger= extent.createTest(testMethod.getName());
		setupdriver(browser);
		driver.get(Const.url);
		driver.manage().window().maximize();
		
	}
	
	@AfterMethod(description="get the test report")
	public void getReports(ITestResult result) {
		
		if(result.getStatus()== ITestResult.FAILURE) {
			logger.log(Status.FAIL, MarkupHelper.createLabel(result.getName()+"-- Test Failed --",ExtentColor.RED));
		}
		
		else if(result.getStatus()== ITestResult.SKIP) {
			logger.log(Status.SKIP, MarkupHelper.createLabel(result.getName()+"-- Test Skipped --",ExtentColor.YELLOW));
		}
		
		else if(result.getStatus()== ITestResult.SUCCESS) {
			logger.log(Status.PASS, MarkupHelper.createLabel(result.getName()+"-- Test Passed --",ExtentColor.GREEN));
		}
		
	}
	@AfterTest(description="Close Test execution")
	public void teardown() {
		
		extent.flush();
		driver.quit();
		
	}

	public void setupdriver(String browser) throws IOException {
		
		file1= new FileInputStream(path+"/src/main/java/configurations/config.properties");
		file2= new FileInputStream(path+"/src/main/java/configurations/locators.properties");
		prop.load(file1);
		loc.load(file2);
		
		if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver= new ChromeDriver();
		}

		else if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
		}

		else if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver= new EdgeDriver();
		}
		
	}
}
