package Pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage {
    WebDriver driver;
    WebDriverWait wait;

    // Locators using Page Factory
    @FindBy(id = "cartur")
    private WebElement cartLink; // Cart navigation link

    @FindBy(xpath = "//tbody[@id='tbodyid']/tr")
    private List<WebElement> cartItems; // Items in the cart

    @FindBy(id = "totalp")
    private WebElement totalAmount; // Total price

    @FindBy(xpath = "//button[text()='Place Order']")
    private WebElement placeOrderButton; // Place Order button

    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this); // Initialize elements
    }

    // Navigate to the Cart page
    public void goToCart() {
        cartLink.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(cartItems)); // Ensure cart items are loaded
        System.out.println("Navigated to cart page.");
    }

    // Verify if the cart has items
    public boolean isCartNotEmpty() {
        return !cartItems.isEmpty();
    }

    // Get total amount
    public String getTotalAmount() {
        return wait.until(ExpectedConditions.visibilityOf(totalAmount)).getText();
    }

    // Click on 'Place Order' button
    public void clickPlaceOrder() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton)).click();
    }
}
