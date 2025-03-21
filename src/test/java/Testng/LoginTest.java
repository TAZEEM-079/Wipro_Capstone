package Testng;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
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
public class LoginTest extends BaseClass {
    HomePage homePage;
    LoginPage loginPage;
    ExtentReports extent;
    ExtentTest test;
    WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        invokeBrowser();
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        ExtentReportManager.initReport();
        extent = ExtentReportManager.getExtent();
    }

    @Test
    public void testLogin() throws IOException {
        test = ExtentReportManager.createTest("User Login Test");
        try {
            homePage.clickLogin();
            String loginname = prop.getProperty("loginname");
            String loginpassword = prop.getProperty("loginpassword");

            wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameField())).sendKeys(loginname);
            wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordField())).sendKeys(loginpassword);
            wait.until(ExpectedConditions.elementToBeClickable(loginPage.getLoginButton())).click();

            By welcomeMessage = By.xpath("//a[text()='Welcome wiprouser']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));

            Assert.assertTrue(driver.findElement(welcomeMessage).isDisplayed(), "Login Failed!");
            test.log(Status.PASS, "Login successful!");
        } catch (Exception e) {
            test.log(Status.FAIL, "Login failed: " + e.getMessage());
            screenshot();
        }
    }

    @AfterMethod
    public void close() {
        ExtentReportManager.flushReport();
        closeBrowser();
    }
}
