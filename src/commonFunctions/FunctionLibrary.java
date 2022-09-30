package commonFunctions;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Reporter;
import constant.AppUtil;
// here login method will written , locators are store properties file then these properties are called in 
// app util class and no need to write again these classes so using extends oops concet can use these variable,methods here
public class FunctionLibrary extends AppUtil {
	
	public static boolean Verifylogin(String username,String password) throws Throwable  {
		// if extends was not declare then need to declare again driver and config
		
		driver.get(config.getProperty("Url"));
		Thread.sleep(5000);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		
		/*	driver.findElement(By.cssSelector(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.cssSelector(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.cssSelector(config.getProperty("ObjLoginBtn"))).click();
		*/
		driver.findElement(By.cssSelector(config.getProperty("ObjUser"))).sendKeys(username);
		driver.findElement(By.cssSelector(config.getProperty("ObjPass"))).sendKeys(password);
		driver.findElement(By.cssSelector(config.getProperty("ObjLoginBtn"))).click();
				
		String expected="dashboard";
		String actual=driver.getCurrentUrl();
		if(actual.contains(expected)) {
			Reporter.log("login sucess::"+expected + " " + actual,true);
			return true;		
		}
		else {	
			String errormessage=driver.findElement(By.cssSelector(config.getProperty("ObjErrormessage"))).getText();
			Reporter.log(errormessage + expected + " " + actual,true);
			return false;
		}
	}
}
