package Test;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;

import PageObject.HomePage;
import PageObject.Login;
import PageObject.NewCustomer;
import io.github.bonigarcia.wdm.WebDriverManager;
import extentreports.ExtentReport;

public class Test {
		WebDriver driver;
		Login objLogin;
	    HomePage objHomePage;
	    NewCustomer objNewCustomer;
	    ExtentReport extRpt = new ExtentReport();
	    @BeforeTest

	    public void setup()
	    {
	    	 extRpt.setUpReport();
	       // System.setProperty("webdriver.chrome.driver","C:\\chromedrv\\chromedriver.exe");
	    	WebDriverManager.chromedriver().setup();
	      // driver = new ChromeDriver();
	        
	    	//Maximize Window Opt 01
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--start-maximized");
	        driver = new ChromeDriver(options);
	        
	        //Maximize Window Opt 02
	        // driver.manage().window().maximize();

	        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

	        driver.get("http://demo.guru99.com/V4/");

	    }

	    /**

	     * This test go to http://demo.guru99.com/V4/

	     * Verify login page title as guru99 bank

	     * Login to application

	     * Verify the home page using Dashboard message

	     */

	    @org.testng.annotations.Test(priority=0)

	    public void test_Home_Page_Appear_Correct(){

	        //Create Login Page object
	    	  extRpt.startTestCase("User Login");
	    objLogin = new Login(driver);

	    //Verify login page title

	    String loginPageTitle = objLogin.getLoginTitle();

	    Assert.assertTrue(loginPageTitle.toLowerCase().contains("guru99 bank"));
	    extRpt.logEventsPass("Successs");
	    
	    extRpt.logEventsFail("Not Successs");
	    //login to application

	    objLogin.loginToGuru99("mgr123", "mgr!23");

	    // go the next page

	    objHomePage = new HomePage(driver);

	    //Verify home page
	   	    
	    
	    objNewCustomer = new NewCustomer (driver);
	    objNewCustomer.navigateNewCustomer(driver);
	    objNewCustomer.createNewCustomer();
	  //  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	    
	 //   Assert.assertTrue(objHomePage.getHomePageDashboardUserName().toLowerCase().contains("manger id : mgr123"));
	    
	    
	  
	   
	  
	    extRpt.createFinalReport();
	    
	    }
}
