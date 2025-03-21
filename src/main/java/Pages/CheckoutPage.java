package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    // Locators using @FindBy annotation
    @FindBy(id = "name")
    private WebElement nameField;

    @FindBy(id = "country")
    private WebElement countryField;

    @FindBy(id = "city")
    private WebElement cityField;

    @FindBy(id = "card")
    private WebElement cardField;

    @FindBy(id = "month")
    private WebElement monthField;

    @FindBy(id = "year")
    private WebElement yearField;

    @FindBy(xpath = "//button[text()='Purchase']")
    private WebElement purchaseButton;

    @FindBy(xpath = "//button[text()='Close']")
    private WebElement closeButton;

    // Constructor to initialize elements
    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Action Methods
    public void enterName(String name) {
        nameField.sendKeys(name);
    }

    public void enterCountry(String country) {
        countryField.sendKeys(country);
    }

    public void enterCity(String city) {
        cityField.sendKeys(city);
    }

    public void enterCardDetails(String card) {
        cardField.sendKeys(card);
    }

    public void enterMonth(String month) {
        monthField.sendKeys(month);
    }

    public void enterYear(String year) {
        yearField.sendKeys(year);
    }

    public void clickPurchase() {
        purchaseButton.click();
    }

    public void clickClose() {
        closeButton.click();
    }
}
