package constant;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class AppUtil {
	// declare webdriver in static bcz it is constant and call to other class easily
	public static WebDriver driver;
	
	// declare properties class also in static bcz constant 
	
	public static Properties config;
	
	// precondition before test, pre condition 
	@BeforeTest
	// this also declare static bcz these are constant not change and use anywhere alse
	
	public static void setup() throws Throwable {
		// link the properties file		
		config=new Properties();
		// load the properties file
		// new keyword confusion
		config.load(new FileInputStream("C:\\Users\\Srikant\\Desktop\\project\\DDT_Framework\\PropertyFiles\\Environment.properties"));
		
		driver=new ChromeDriver();
		}
	
	@AfterTest
	public static void teardown() {
		driver.close();
		
	}	
}
