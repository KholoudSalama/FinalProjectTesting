package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {

    // Locators
    private By cartButton = By.xpath("//*[@id=\"top-links\"]/ul/li[4]/a");
    private By viewCartLink = By.xpath("//strong[contains(text(),'View Cart')]");
    private By cartItems = By.cssSelector(".table-responsive tbody tr");
    private By removeButtons = By.cssSelector("button[data-original-title='Remove']");
    private By quantityInputs = By.cssSelector("input[name^='quantity']");
    private By updateButtons = By.cssSelector("button[data-original-title='Update']");
    private By checkoutButton = By.linkText("Checkout");
    private By continueShoppingButton = By.xpath("//*[@id=\"content\"]/div/div/a");
    private By emptyCartMessage = By.xpath("//p[contains(text(),'Your shopping cart is empty')]");
    private By productNames = By.cssSelector(".table-responsive tbody tr td:nth-child(2) a");
    private By totalPrice = By.xpath("//td[contains(text(),'Total')]/following-sibling::td");
    private By couponInput = By.id("input-coupon");
    private By applyCouponButton = By.id("button-coupon");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToCart() {
        driver.findElement(cartButton).click();
//        click(viewCartLink);
    }

    public int getCartItemsCount() {
        if (isElementDisplayed(emptyCartMessage)) {
            return 0;
        }
        return driver.findElements(cartItems).size();
    }

    public boolean isCartEmpty() {
        return isElementDisplayed(emptyCartMessage);
    }

    public void removeFirstItem() {
        List<WebElement> buttons = driver.findElements(removeButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void removeItemByIndex(int index) {
        List<WebElement> buttons = driver.findElements(removeButtons);
        if (buttons.size() > index) {
            buttons.get(index).click();
        }
    }

    public void updateQuantity(int itemIndex, String quantity) {
        List<WebElement> inputs = driver.findElements(quantityInputs);
        List<WebElement> buttons = driver.findElements(updateButtons);

        if (inputs.size() > itemIndex && buttons.size() > itemIndex) {
            WebElement input = inputs.get(itemIndex);
            input.clear();
            input.sendKeys(quantity);
            buttons.get(itemIndex).click();
        }
    }

    public void proceedToCheckout() {
        driver.findElement(checkoutButton).click();
    }

    public void continueShopping() {
        driver.findElement(continueShoppingButton).click();
    }

    public List<String> getCartProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        return elements.stream().map(WebElement::getText).toList();
    }

    public String getTotalPrice() {
        return getText(totalPrice);
    }

    public void applyCoupon(String couponCode) {
        sendKeys(couponInput, couponCode);
        click(applyCouponButton);
    }
}