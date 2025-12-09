package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage {

    private WebDriver driver;

    private By cartRows = By.cssSelector("#content form table tbody tr");
    private By checkoutButton = By.linkText("Checkout");

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    public int getCartItemsCount() {
        return driver.findElements(cartRows).size();
    }

    public void clickCheckout() {
        driver.findElement(checkoutButton).click();
    }
}

