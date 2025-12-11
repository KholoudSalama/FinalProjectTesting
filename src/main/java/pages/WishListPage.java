package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import java.util.List;

public class WishListPage extends BasePage {

    // Locators
    private By wishListLink = By.id("wishlist-total");
    private By wishListItems = By.cssSelector("table.table tbody tr");
    private By removeButtons = By.cssSelector("a[data-original-title='Remove']");
    private By addToCartButtons = By.cssSelector("button[data-original-title='Add to Cart']");
    private By emptyWishListMessage = By.xpath("//p[contains(text(),'Your wish list is empty')]");
    private By productNames = By.cssSelector("table.table tbody tr td:nth-child(2) a");
    private By successMessage = By.cssSelector(".alert-success");

    public WishListPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToWishList() {
        click(wishListLink);
    }

    public int getWishListItemsCount() {
        if (isElementDisplayed(emptyWishListMessage)) {
            return 0;
        }
        return driver.findElements(wishListItems).size();
    }

    public boolean isWishListEmpty() {
        return isElementDisplayed(emptyWishListMessage);
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

    public void addFirstItemToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public List<String> getWishListProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        return elements.stream().map(WebElement::getText).toList();
    }

    public boolean isSuccessMessageDisplayed() {
        return isElementDisplayed(successMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}