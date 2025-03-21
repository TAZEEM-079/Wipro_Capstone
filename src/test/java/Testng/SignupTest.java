package Testng;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import Base.BaseClass;
import Utility.ExtentReportManager;
import Pages.*;

public class SignupTest extends BaseClass {
    HomePage homePage;
    SignupPage signuppage;
    ExtentReports extent;
    ExtentTest test;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        invokeBrowser();
        homePage = new HomePage(driver);
        signuppage = new SignupPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentReportManager.initReport();
        extent = ExtentReportManager.getExtent();
    }

    @Test
    public void testSignup() throws IOException {
        test = ExtentReportManager.createTest("User Signup Test");
        try { homePage.clickSignup();
 	   String username = prop.getProperty("username");
 	   String password = prop.getProperty("password");
 	   wait.until(ExpectedConditions.visibilityOf(signuppage.getUsernameField())).sendKeys(username);
 	   wait.until(ExpectedConditions.visibilityOf(signuppage.getPasswordField())).sendKeys(password);
 	   wait.until(ExpectedConditions.elementToBeClickable(signuppage.getSignupButton())).click();
 	        
 	   //wait for pop-up appear
 	   wait.until(ExpectedConditions.alertIsPresent());
 	        
 	   //sign-up alert message
 	   String msg=driver.switchTo().alert().getText();
 	   System.out.println("Signup Alert Message: " + msg);
 	        
 	   //Accept the alert
 	   driver.switchTo().alert().accept();
 	   screenshot();
 	   
 	   test.log(Status.PASS, "User signed up successfully. Alert message: " + msg);
 	    }
 	  catch(Exception e) {
 		  test.log(Status.FAIL, "Signup test failed: " + e.getMessage());
 	  }
    }

    @AfterMethod
    public void tearDown() {
        ExtentReportManager.flushReport();
        closeBrowser();
    }
}
