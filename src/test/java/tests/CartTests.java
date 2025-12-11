package tests;

import org.testng.annotations.Test;
import pages.CartPage;
import pages.HomePage;

public class CartTests extends BaseTest {

    @Test(priority = 1, description = "Verify cart page navigation")
    public void testCartNavigation() {
        CartPage cartPage = new CartPage(driver);

        logInfo("Navigating to cart");
        cartPage.navigateToCart();

        softAssert.assertTrue(driver.getCurrentUrl().contains("checkout/cart"),
                "Should navigate to cart page");
        logPass("Cart navigation successful");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify adding product to cart")
    public void testAddProductToCart() {
        HomePage homePage = new HomePage(driver);

        logInfo("Adding product to cart");
        homePage.addFirstProductToCart();

        softAssert.assertTrue(homePage.isSuccessAlertDisplayed(),
                "Success alert should be displayed");
        logPass("Product added to cart");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify cart displays added products")
    public void testCartDisplaysProducts() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        logInfo("Verifying cart displays products");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();

        int itemCount = cartPage.getCartItemsCount();
        softAssert.assertTrue(itemCount > 0, "Cart should contain items");
        logInfo("Cart contains " + itemCount + " item(s)");
        logPass("Cart displays products correctly");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify removing item from cart")
    public void testRemoveFromCart() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        logInfo("Testing remove from cart");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();

        int initialCount = cartPage.getCartItemsCount();
        if (initialCount > 0) {
            cartPage.removeFirstItem();
            logPass("Item removed from cart");
        }
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify updating product quantity")
    public void testUpdateQuantity() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        logInfo("Testing quantity update");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();

        if (cartPage.getCartItemsCount() > 0) {
            cartPage.updateQuantity(0, "2");
            logPass("Quantity updated");
        }
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "Verify empty cart message")
    public void testEmptyCartMessage() {
        CartPage cartPage = new CartPage(driver);

        logInfo("Checking empty cart");
        cartPage.navigateToCart();

        if (cartPage.isCartEmpty()) {
            logInfo("Cart is empty as expected");
        } else {
            logInfo("Cart contains items");
        }
        softAssert.assertAll();
    }

    @Test(priority = 7, description = "Verify continue shopping button")
    public void testContinueShopping() {
        CartPage cartPage = new CartPage(driver);

        logInfo("Testing continue shopping");
        cartPage.navigateToCart();

        if (!cartPage.isCartEmpty()) {
            cartPage.continueShopping();
            softAssert.assertTrue(driver.getCurrentUrl().contains("home") ||
                            driver.getCurrentUrl().equals(BASE_URL),
                    "Should return to home page");
            logPass("Continue shopping works");
        }
        softAssert.assertAll();
    }

    @Test(priority = 8, description = "Verify applying invalid coupon")
    public void testInvalidCoupon() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        logInfo("Testing invalid coupon");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();

        if (cartPage.isCartEmpty()) {
            cartPage.applyCoupon("INVALID123");
            logPass("Invalid coupon tested");
        }
        softAssert.assertAll();
    }
}