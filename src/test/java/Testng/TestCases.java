package Testng;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import Base.BaseClass;
import Utility.ExtentReportManager;
import Pages.*;


public class TestCases extends BaseClass {
	 HomePage homePage;
	 LoginPage loginPage;
	 SignupPage signuppage;
	 ProductPage productpage;
	 CartPage cartpage;
	 CheckoutPage checkoutpage;
	 WebDriverWait wait;
	 JavascriptExecutor js;
	 ExtentReports extent;
	 ExtentTest test;
 
@BeforeMethod
public void intialize() {
	// Initialize Extent Reports
    ExtentReportManager.initReport();
    extent = ExtentReportManager.getExtent();
	
    invokeBrowser();
    homePage = new HomePage(driver);
	loginPage = new LoginPage(driver);
	signuppage = new SignupPage(driver);
	productpage = new ProductPage(driver);
	cartpage = new CartPage(driver);
	checkoutpage = new CheckoutPage(driver);
	wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	js = (JavascriptExecutor) driver;
}
	 
@Test(priority=1)
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
	 
@Test(priority=2)
public void testLoginAndPurchaseProduct() throws IOException, InterruptedException {
	 test = ExtentReportManager.createTest("Login and Purchase Product Test");
	 try {
	   homePage.clickLogin();
	   String loginname = prop.getProperty("loginname");
	   String loginpassword = prop.getProperty("loginpassword");
	   wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameField())).sendKeys(loginname);
	   wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordField())).sendKeys(loginpassword);
	   wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();
	        
	   // Wait for the "Welcome [username]" element to confirm successful login
	   By welcomeMessage = By.xpath("//a[text()='Welcome wiprouser']") ;
	   wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
	   screenshot();
	   test.log(Status.PASS, "User logged in successfully.");
	      
	   // Select category dynamically (Laptop, Mobile, Monitor)
	   productpage.selectCategory("Laptops");
			 
	   // Scroll down before selecting product
	   js.executeScript("window.scrollBy(0,300);"); 
			  
	   // Select a specific product dynamically 
	   productpage.selectProduct("MacBook air");
	   Thread.sleep(2000);
			 
		//Add to cart
		productpage.addToCart(); 
		screenshot();
		test.log(Status.PASS, "Product added to cart successfully.");
			 
		//navigate to add to cart	 
		cartpage.goToCart();
	    Assert.assertTrue(cartpage.isCartNotEmpty(), "Cart is empty!");
	    screenshot();
	    test.log(Status.PASS, "Cart is not empty. Proceeding with checkout.");

	    //  Place Order
		cartpage.clickPlaceOrder();
		
		// Perform Checkout
        performCheckout();

	 }
	 catch(Exception e) {
		 test.log(Status.FAIL, "Login and purchase test failed: " + e.getMessage());
	 }
}
public void performCheckout() throws IOException {
    System.out.println("Filling out checkout details...");

    // Entering checkout details
    checkoutpage.enterName("John Doe");
    checkoutpage.enterCountry("USA");
    checkoutpage.enterCity("New York");
    checkoutpage.enterCardDetails("4111 1111 1111 1111");
    checkoutpage.enterMonth("12");
    checkoutpage.enterYear("2025");

    // Click purchase button
    checkoutpage.clickPurchase();

    // Wait for the confirmation  to appear 
    By confirmation = By.xpath("//div[contains(@class,'sweet-alert')]");
    wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation));

 // Capture the confirmation message 
    String confirmationMessage = driver.findElement(By.xpath("//div[contains(@class,'sweet-alert')]//h2")).getText();
    System.out.println("Order Confirmation Message: " + confirmationMessage);
    
    // Log the confirmation message in the Extent Report
    test.log(Status.PASS, "Order placed successfully. Confirmation Message: " + confirmationMessage);
    
    // Screenshot for proof of purchase
    screenshot();
    
 // Click 'OK' to close the confirmation
    driver.findElement(By.xpath("//button[text()='OK']")).click();
}

	    @AfterMethod
	    public void close(){
	    	//save the extent report
	    	ExtentReportManager.flushReport();
	        closeBrowser();
	    }
}
