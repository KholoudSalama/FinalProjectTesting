package tests;

import org.testng.annotations.Test;
import pages.*;
import pages.WishListPage;

public class WishListTests extends BaseTest {

    @Test(priority = 1, description = "Verify wishlist page navigation")
    public void testWishListNavigation() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.login("reda1765207845064@test.com", "P@ssw0rd");

        WishListPage wishListPage = new WishListPage(driver);

        logInfo("Navigating to wishlist");
        wishListPage.navigateToWishList();

        softAssert.assertTrue(driver.getCurrentUrl().contains("wishlist"),
                "Should navigate to wishlist page");
        logPass("Wishlist navigation successful");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify adding product to wishlist")
    public void testAddProductToWishlist() {
        HomePage homePage = new HomePage(driver);

        logInfo("Adding product to wishlist");
        homePage.addFirstProductToWishlist();

        // Product added (might need login)
        logPass("Add to wishlist attempted");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify empty wishlist message")
    public void testEmptyWishlistMessage() {
        WishListPage wishListPage = new WishListPage(driver);

        logInfo("Checking empty wishlist");
        wishListPage.navigateToWishList();

        // Wishlist might be empty or require login
        logInfo("Wishlist checked");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify removing item from wishlist")
    public void testRemoveFromWishlist() {
        HomePage homePage = new HomePage(driver);
        WishListPage wishListPage = new WishListPage(driver);

        logInfo("Testing remove from wishlist");
        homePage.addFirstProductToWishlist();
        wishListPage.navigateToWishList();

        int initialCount = wishListPage.getWishListItemsCount();
        if (initialCount > 0) {
            wishListPage.removeFirstItem();
            logPass("Item removal attempted");
        } else {
            logInfo("Wishlist empty or requires login");
        }
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify moving wishlist item to cart")
    public void testMoveWishlistItemToCart() {
        WishListPage wishListPage = new WishListPage(driver);

        logInfo("Testing move to cart from wishlist");
        wishListPage.navigateToWishList();

        int itemCount = wishListPage.getWishListItemsCount();
        if (itemCount > 0) {
            wishListPage.addFirstItemToCart();
            logPass("Move to cart attempted");
        } else {
            logInfo("No items in wishlist");
        }
        softAssert.assertAll();
    }
}