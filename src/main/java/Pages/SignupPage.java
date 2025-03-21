package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SignupPage {
	WebDriver driver;
	
	public SignupPage(WebDriver driver) {
		this.driver=driver;
	}
	
	// Locators
    private By usernameField = By.xpath("//input[@id='sign-username']");
    private By passwordField = By.xpath("//input[@id='sign-password']");
    private By SignupButton = By.xpath("//button[text()='Sign up']");
    private By closeButton = By.xpath("(//button[text()='Close'])[2]");
    
    // Getter Methods
    public WebElement getUsernameField() {
        return driver.findElement(usernameField);
    }

    public WebElement getPasswordField() {
        return driver.findElement(passwordField);
    }

    public WebElement getSignupButton() {
        return driver.findElement(SignupButton);
    }

    public WebElement getCloseButton() {
        return driver.findElement(closeButton);
    }

    // Methods
    public void enterUsername(String username) {
        getUsernameField().sendKeys(username);
    }

    public void enterPassword(String password) {
        getPasswordField().sendKeys(password);
    }

    public void clickSignup() {
        getSignupButton().click();
    }

    public void closeSignup() {
        getCloseButton().click();
    }

}
