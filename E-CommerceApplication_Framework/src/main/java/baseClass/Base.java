package baseClass;

import java.io.*;
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

public class Base {

	public static WebDriver driver;
	public static String path= System.getProperty("user.dir");
	public static FileInputStream file1, file2;
	public static Properties prop= new Properties();
	public static Properties loc= new Properties();
	public static ExtentSparkReporter htmlReporter;
	public static ExtentReports reports;
	public static ExtentTest test;

	@BeforeClass(description= "Project Setup and Invoke Browser")
	public void setProject() throws IOException {

		if(driver== null) {
			file1= new FileInputStream(path+"/src/main/java/configurations/config.properties");
			file2= new FileInputStream(path+"/src/main/java/configurations/locators.properties");
			prop.load(file1);
			loc.load(file2);
		}

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

		driver.get(prop.getProperty("url"));
		driver.manage().window().maximize();

		//Importing Extent Report methods
		htmlReporter = new ExtentSparkReporter("HTMLReport.html");
		reports= new ExtentReports();
		reports.attachReporter(htmlReporter);

		//Configuring System info
		reports.setSystemInfo("OS", "Windows 10");
		reports.setSystemInfo("Browser", "Google Chrome");

		//Setting Up Look and Feel
		htmlReporter.config().setDocumentTitle("W3Schools Automation Test Report");
		htmlReporter.config().setReportName("Test Execution Report");
		htmlReporter.config().setTheme(Theme.DARK);
		htmlReporter.config().setTimeStampFormat("MM/DD/YYYY HH:MM:SS");

	}

	@BeforeMethod(description= "Generate HTML reports")
	public void tearDown(ITestResult result) {

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

	@AfterClass(description= "Complete the test cycle")
	public void tearDown() {

		reports.flush();
//		driver.quit();

	}
}
