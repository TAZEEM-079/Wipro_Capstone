package StepDef;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.cucumber.datatable.DataTable;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;

import Base.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import Pages.*;
import Utility.ExtentReportManager;

public class LoginSteps extends BaseClass {
    WebDriver driver;
    HomePage homePage;
    LoginPage loginPage;
    WebDriverWait wait;
    ExtentTest test;

    @Given("User is on the Login page")
    public void user_is_on_the_login_page() {
        driver = BaseClass.driver; // Assign WebDriver from BaseClass
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        homePage.clickLogin(); 
        test = ExtentReportManager.createTest("User Login Test"); 
        test.info("Navigated to Login Page"); 
    }

    @When("User enters the following credentials:")
    public void user_enters_the_following_credentials(DataTable dataTable) {
        List<Map<String, String>> credentials = dataTable.asMaps(String.class, String.class);
        String username = credentials.get(0).get("Username");
        String password = credentials.get(0).get("Password");

        wait.until(ExpectedConditions.visibilityOf(loginPage.getUsernameField())).sendKeys(username);
        wait.until(ExpectedConditions.visibilityOf(loginPage.getPasswordField())).sendKeys(password);
        test.info("Entered username: " + username);
    }

    @When("Clicks on the Login button")
    public void clicks_on_the_login_button() {
        loginPage.getLoginButton().click();
        test.info("Clicked on Login button"); 
    }

    @Then("User should be logged in successfully")
    public void user_should_be_logged_in_successfully() {
        By welcomeMessage = By.xpath("//a[contains(text(),'Welcome ')]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(welcomeMessage));
        Assert.assertTrue(driver.findElement(welcomeMessage).isDisplayed(), "User login failed!");
        System.out.println("User logged in successfully!");
        test.pass("User logged in successfully");
    }

    @Then("User should see an error alert")
    public void user_should_see_an_error_alert() {
        try {
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertMessage = alert.getText();
            System.out.println("Login error message: " + alertMessage);

            // Check if the alert message matches expected error messages
            if (alertMessage.contains("Wrong password.") || alertMessage.contains("User does not exist.")) {
                test.pass("User login failed as expected with error: " + alertMessage);
            } else {
                test.fail("Unexpected error message: " + alertMessage);
                Assert.fail("Unexpected error message: " + alertMessage);
            }

            alert.accept(); // Accept alert to proceed

        } catch (Exception e) {
            test.fail("No error alert appeared for invalid login!");
            Assert.fail("No error alert appeared for invalid login!");
        }
    }

}
