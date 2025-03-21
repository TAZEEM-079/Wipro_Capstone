package Testng;

import java.io.IOException;
import java.time.Duration;
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
public class AddToCartTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;
    ProductPage productpage;
    CartPage cartPage;
    WebDriverWait wait;
    JavascriptExecutor js;
    ExtentReports extent;
    ExtentTest test;

    @BeforeMethod
    public void setUp() {
        invokeBrowser();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productpage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
        ExtentReportManager.initReport();
        extent = ExtentReportManager.getExtent();
    }

    @Test
    public void testAddSingleProductToCart() throws IOException {
        test = ExtentReportManager.createTest("Add Single Product to Cart Test");
        try {
            // **Login first**
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

            // **Select a single product**
            String product = "MacBook air";  
            String category = "Laptops";  

            System.out.println("Selecting category: " + category);
            productpage.selectCategory(category);
            Thread.sleep(2000); // Small delay for category to load

            // **Scroll to make sure the product is visible**
            js.executeScript("window.scrollBy(0,300);");
            Thread.sleep(2000); // Ensure scrolling happens before clicking

            System.out.println("Selecting product: " + product);
            productpage.selectProduct(product);
            Thread.sleep(2000); // Allow product page to load before clicking "Add to Cart"

            System.out.println("Adding to cart: " + product);
            productpage.addToCart();

            test.log(Status.PASS, product + " added to cart successfully.");

            // **Verify cart contains product**
            cartPage.goToCart();
            Assert.assertTrue(cartPage.isCartNotEmpty(), "Cart is empty!");
            test.log(Status.PASS, "Cart contains the product.");
        } catch (Exception e) {
            test.log(Status.FAIL, "Add to Cart Test failed: " + e.getMessage());
            screenshot();
        }
    }

    @AfterMethod
    public void close() {
        ExtentReportManager.flushReport();
        closeBrowser();
    }
}
