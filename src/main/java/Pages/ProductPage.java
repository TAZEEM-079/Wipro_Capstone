package Pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage {
	WebDriver driver;
	WebDriverWait wait;
	
	public ProductPage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}
	
	//Methods to select a product category
	 public void selectCategory(String category) {
	        String categoryXPath = "//a[contains(text(),'" + category + "')]";
	        WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(categoryXPath)));
	        categoryElement.click();
	    }
	 
	 // Method to select a product dynamically
	    public void selectProduct(String productName) {
	        String productXPath = "//a[contains(text(),'" + productName + "')]";
	        WebElement productElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(productXPath)));
	        productElement.click();
	    }
	    
	 // Click "Add to Cart" and handle the alert
	    public void addToCart() {
	        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]")));
	        JavascriptExecutor js =(JavascriptExecutor)driver;
	        
	        js.executeScript("arguments[0].scrollIntoView(true);", addToCartButton);
	        System.out.println("Clicking 'Add to Cart'...");
	        addToCartButton.click();
	        
	        // Add a delay to allow alert to appear
	        try {
	            Thread.sleep(3000);  
	        } catch (InterruptedException e) {
	            e.printStackTrace();
	        }
	        
	        // Handle alert if present
	        try {
	            wait.until(ExpectedConditions.alertIsPresent()).accept();
	            System.out.println("Alert accepted.");
	        } catch (Exception e) {
	            System.out.println("No alert appeared after clicking 'Add to Cart'.");
	        }
	    }

	
}
