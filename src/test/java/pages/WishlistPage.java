package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

    public class WishlistPage {

        private WebDriver driver;

        private By wishlistTableRows = By.cssSelector("#content table tbody tr");

        public WishlistPage(WebDriver driver) {
            this.driver = driver;
        }

        public int getWishlistItemsCount() {
            return driver.findElements(wishlistTableRows).size();
        }
    }


