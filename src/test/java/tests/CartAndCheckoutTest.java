package tests;

import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CartAndCheckoutTest extends BaseTest {

    @Test
    public void viewCartAndCheckoutAsGuest() throws Exception {
        HomePage homePage = new HomePage(driver);
        Header header = new Header(driver);

        // Add product to cart
        homePage.addFirstProductToCart();

        // View shopping cart
        header.goToCart();
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.getCartItemsCount() > 0,
                "Cart should contain at least one item");

        // Checkout
        cartPage.clickCheckout();
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        checkoutPage.chooseGuestCheckout();
        checkoutPage.fillBillingDetails(
                "Reda",
                "Guest",
                "guest+" + System.currentTimeMillis() + "@test.com",
                "01234567890",
                "Test Address 1",
                "Cairo",
                "12345",
                "Egypt",
                "Al Qahirah"
        );

        checkoutPage.completeCheckoutFlow();
        String successHeading = checkoutPage.getSuccessHeading();

        Assert.assertTrue(successHeading.toLowerCase().contains("your order has been placed")
                        || successHeading.toLowerCase().contains("success"),
                "Order not placed successfully");
    }
}

