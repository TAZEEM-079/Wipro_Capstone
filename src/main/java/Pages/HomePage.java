package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
	 WebDriver driver;
	 
	 //locators
	 private By loginButton = By.id("login2");
	 private By signupButton = By.id("signin2");
	 private By cartButton = By.id("cartur");
	 private By mobilesCategory = By.xpath("(//a[@id='itemc'])[1]");
	 private By laptopsCategory = By.xpath("(//a[@id='itemc'])[2]");
	 private By monitorsCategory = By.xpath("(//a[@id='itemc'])[3]");
	 
	 //constructor
	 public HomePage(WebDriver driver) {
		 this.driver=driver;
	 }
	 
	 //Methods
	 public void clickLogin() {
		 driver.findElement(loginButton).click();
	 }
	 
	 public void clickSignup() {
		 driver.findElement(signupButton).click();
	 }
	 public void clickCart() {
		 driver.findElement(cartButton).click();
	 }
	 
	 public void selectPhones() {
		 driver.findElement(mobilesCategory).click();
	 }
	 
	 public void selectLaptop() {
		 driver.findElement(laptopsCategory).click();
	 }
	 
	 public void selectMonitors() {
		 driver.findElement(monitorsCategory).click();
	 }
}
