package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {



        private WebDriver driver;

        private By featuredProducts = By.cssSelector(".product-layout"); // كروت المنتجات
        private By firstProductAddToCartBtn =
                By.cssSelector(".product-layout:first-of-type button[onclick*='cart.add']");
        private By firstProductAddToWishlistBtn =
                By.cssSelector(".product-layout:first-of-type button[onclick*='wishlist.add']");
        private By successAlert = By.cssSelector(".alert.alert-success");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public int getFeaturedProductCount() {
            List<WebElement> products = driver.findElements(featuredProducts);
            return products.size();
        }

        public void addFirstProductToCart() {
            driver.findElement(firstProductAddToCartBtn).click();
        }

        public void addFirstProductToWishlist() {
            driver.findElement(firstProductAddToWishlistBtn).click();
        }

        public String getSuccessMessage() {
            return driver.findElement(successAlert).getText();
        }
}

