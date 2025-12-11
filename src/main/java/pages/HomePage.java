package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage extends BasePage {

    // Locators
    private By logo = By.id("logo");
    private By searchBox = By.name("search");
    private By searchButton = By.cssSelector(".btn-default.btn-lg");
    private By shoppingCartLink = By.linkText("Shopping Cart");
    private By wishListLink = By.id("wishlist-total");
    private By featuredProducts = By.cssSelector(".product-layout");
    private By productNames = By.cssSelector(".product-layout h4 a");
    private By productPrices = By.cssSelector(".product-layout .price");
    private By addToCartButtons = By.xpath("//*[@id=\"content\"]/div[2]/div[1]/div/div[3]/button[1]");
    private By addToWishlistButtons = By.xpath("//button[@data-original-title='Add to Wish List']");
    private By successAlert = By.cssSelector(".alert-success");
    private By navbar = By.cssSelector(".navbar-nav");

    // Category Locators
    private By desktopsMenu = By.linkText("Desktops");
    private By desktopsMac = By.linkText("Mac (1)");
    private By laptopsMenu = By.linkText("Laptops & Notebooks");
    private By allLaptops = By.linkText("Show All Laptops & Notebooks");
    private By componentsMenu = By.linkText("Components");
    private By tabletsMenu = By.linkText("Tablets");
    private By softwareMenu = By.linkText("Software");
    private By phonesMenu = By.linkText("Phones & PDAs");
    private By camerasMenu = By.linkText("Cameras");
    private By mp3PlayersMenu = By.linkText("MP3 Players");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigateToHomePage(String url) {
        driver.get(url);
    }

    public boolean isLogoDisplayed() {
        return isElementDisplayed(logo);
    }

    public void searchForProduct(String productName) {
        sendKeys(searchBox, productName);
        click(searchButton);
    }

    public int getFeaturedProductsCount() {
        return driver.findElements(featuredProducts).size();
    }

    public List<WebElement> getFeaturedProducts() {
        return driver.findElements(featuredProducts);
    }

    public void clickShoppingCart() {
        click(shoppingCartLink);
    }

    public void clickWishList() {
        click(wishListLink);
    }

    public void addFirstProductToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public void addFirstProductToWishlist() {
        List<WebElement> buttons = driver.findElements(addToWishlistButtons);
        if (!buttons.isEmpty()) {
            buttons.get(0).click();
        }
    }

    public boolean isSuccessAlertDisplayed() {
        return isElementDisplayed(successAlert);
    }

    public String getSuccessAlertText() {
        return getText(successAlert);
    }

    public void navigateToCategory(String category) {
        switch (category.toLowerCase()) {
            case "desktops":
                click(desktopsMenu);
                click(desktopsMac);
                break;
            case "laptops":
                click(laptopsMenu);
                click(allLaptops);
                break;
            case "components":
                click(componentsMenu);
                break;
            case "tablets":
                click(tabletsMenu);
                break;
            case "software":
                click(softwareMenu);
                break;
            case "phones":
                click(phonesMenu);
                break;
            case "cameras":
                click(camerasMenu);
                break;
            case "mp3players":
                click(mp3PlayersMenu);
                break;
        }
    }

    public boolean isNavbarDisplayed() {
        return isElementDisplayed(navbar);
    }
}