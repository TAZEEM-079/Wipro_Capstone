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
import Pages.*;
import Utility.ExtentReportManager;
public class CheckoutTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productPage;
    CartPage cartPage;
    CheckoutPage checkoutPage;
    WebDriverWait wait;
    JavascriptExecutor js;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
        invokeBrowser();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        checkoutPage = new CheckoutPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        ExtentReportManager.initReport();
        extent = ExtentReportManager.getExtent();
    }

    @Test
    public void testLoginAddToCartAndCheckout() throws IOException {
        test = ExtentReportManager.createTest("Login, Add to Cart, and Checkout Test");
        try {
            // **Step 1: Login to Demoblaze**
            homePage.clickLogin();
            String loginname = prop.getProperty("loginname");
            String loginpassword = prop.getProperty("loginpassword");
            wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameField())).sendKeys(loginname);
            wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordField())).sendKeys(loginpassword);
            wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();
            test.log(Status.PASS, "User logged in successfully.");
            
            // **Refresh the page before selecting category to avoid stale elements**
            driver.navigate().refresh();
            Thread.sleep(3000); // Allow page to reload properly

            // **Step 2: Select and Add Product to Cart**
            String product = "MacBook air";  
            String category = "Laptops";  

            System.out.println("Selecting category: " + category);
            productPage.selectCategory(category);
            Thread.sleep(2000); // Allow category to load

            // Scroll before selecting product
            js.executeScript("window.scrollBy(0,300);");
            Thread.sleep(2000); // Ensure UI updates

            System.out.println("Selecting product: " + product);
            productPage.selectProduct(product);
            Thread.sleep(2000); // Allow product page to load

            System.out.println("Adding to cart: " + product);
            productPage.addToCart();
            test.log(Status.PASS, product + " added to cart successfully.");

            // **Step 3: Navigate to Cart**
            cartPage.goToCart();
            Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty! Checkout cannot proceed.");
            test.log(Status.PASS, "Cart contains products. Proceeding to checkout.");
            screenshot();

            // **Step 4: Click Place Order**
            cartPage.clickPlaceOrder();

            // **Step 5: Enter Checkout Details**
            checkoutPage.enterName("John Doe");
            checkoutPage.enterCountry("USA");
            checkoutPage.enterCity("New York");
            checkoutPage.enterCardDetails("4111 1111 1111 1111");
            checkoutPage.enterMonth("12");
            checkoutPage.enterYear("2025");

            // **Step 6: Submit Order**
            checkoutPage.clickPurchase();
            test.log(Status.PASS, "Checkout details entered and order placed.");

            // **Step 7: Verify Confirmation Message**
            By confirmation = By.xpath("//div[contains(@class,'sweet-alert')]//h2");
            wait.until(ExpectedConditions.visibilityOfElementLocated(confirmation));
            String confirmationMessage = driver.findElement(confirmation).getText();
            Assert.assertTrue(confirmationMessage.contains("Thank you for your purchase!"), "Checkout failed!");

            // **Step 8: Log Success and Take Screenshot**
            test.log(Status.PASS, "Order placed successfully. Confirmation: " + confirmationMessage);
            screenshot();

            // **Step 9: Close Confirmation**
            driver.findElement(By.xpath("//button[text()='OK']")).click();
        } catch (Exception e) {
            test.log(Status.FAIL, "Checkout test failed: " + e.getMessage());
            screenshot();
        }
    }

    @AfterMethod
    public void close() {
        ExtentReportManager.flushReport();
        closeBrowser();
    }
}
