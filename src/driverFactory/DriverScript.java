package driverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import commonFunctions.FunctionLibrary;
import constant.AppUtil;
import utilities.ExcelFileUtils;

public class DriverScript extends AppUtil {
	// 2 global variables created
	String inputpath="C:\\Users\\Srikant\\Desktop\\project\\DDT_Framework\\TestInput\\LoginData.xlsx";
	String outputpath="C:\\Users\\Srikant\\Desktop\\project\\DDT_Framework\\TestOutPut\\Results.xlsx";
	ExtentReports report;
	ExtentTest test;
	
	// Already extends the before test and after test so no need to write before and after
@Test
public void startTest()throws Throwable
{
	//define path for html
	report= new ExtentReports("./Reports/DataDriven.html");
	//create object for excel file util class
	ExcelFileUtils xl = new ExcelFileUtils(inputpath);
	//count no of rows in a sheet
	int rc =xl.rowCount("Login");
	//count no of cells in row
	int cc =xl.cellCount("Login");
	Reporter.log(rc+"  row"+"       "+cc+"  coloumn",true);
	for(int i=1;i<=rc;i++)
	{
		// before every iteration extend reports
		test=report.startTest("Validate Login");
		String user =xl.getCellData("Login", i, 0);
		String pass = xl.getCellData("Login", i, 1);
		//call login method from function library
		// function library access the data from excel file and driver script which is control from driver script
		boolean res =FunctionLibrary.Verifylogin(user, pass);
		if(res)
		{
			//write as login success into results cell
			xl.setCellData("Login", i, 2, "Login Success", outputpath);
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "Pass", outputpath);
			test.log(LogStatus.PASS, "Login success");
		}
		else
		{
			//take screen shot
			File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screens/Iteration/"+"  "+i+"Loginpage.png"));
			//write as login success into results cell
			xl.setCellData("Login", i, 2, "Login Fail", outputpath);
			//write as pass into status cell
			xl.setCellData("Login", i, 3, "Fail", outputpath);
			test.log(LogStatus.FAIL, "Login success");
		}
		report.endTest(test);
		report.flush();
	}}

}
