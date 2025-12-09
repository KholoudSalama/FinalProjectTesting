package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class Header {

    private WebDriver driver;

    private By myAccountDropdown = By.xpath("//span[text()='My Account']");
    private By registerLink = By.linkText("Register");
    private By loginLink = By.linkText("Login");
    private By wishlistLink = By.id("wishlist-total");
    private By shoppingCartLink = By.linkText("Shopping Cart");
    private By checkoutLink = By.linkText("Checkout");

    public Header(WebDriver driver) {
        this.driver = driver;
    }

    private void openMyAccount() {
        driver.findElement(myAccountDropdown).click();
    }

    public void goToRegister() {
        openMyAccount();
        driver.findElement(registerLink).click();
    }

    public void goToLogin() {
        openMyAccount();
        driver.findElement(loginLink).click();
    }

    public void goToWishlist() {
        driver.findElement(wishlistLink).click();
    }

    public void goToCart() {
        driver.findElement(shoppingCartLink).click();
    }

    public void goToCheckout() {
        driver.findElement(checkoutLink).click();
    }
}

