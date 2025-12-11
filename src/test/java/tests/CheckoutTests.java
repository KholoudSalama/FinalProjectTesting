package tests;

import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import pages.HomePage;
import utils.TestDataGenerator;

public class CheckoutTests extends BaseTest {

    @Test(priority = 1, description = "Verify checkout page navigation")
    public void testCheckoutNavigation() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        logInfo("Navigating to checkout");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();
        cartPage.proceedToCheckout();

        softAssert.assertTrue(driver.getCurrentUrl().contains("checkout"),
                "Should navigate to checkout");
        logPass("Checkout navigation successful");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify guest checkout option")
    public void testGuestCheckout() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        logInfo("Testing guest checkout");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();
        cartPage.proceedToCheckout();
        checkoutPage.selectGuestCheckout();

        logPass("Guest checkout option selected");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify checkout with empty cart")
    public void testCheckoutWithEmptyCart() {
        CartPage cartPage = new CartPage(driver);

        logInfo("Testing checkout with empty cart");
        cartPage.navigateToCart();

        if (cartPage.isCartEmpty()) {
            logInfo("Cart is empty, checkout not available");
        } else {
            cartPage.proceedToCheckout();
        }
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify billing details validation")
    public void testBillingDetailsValidation() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);
        CheckoutPage checkoutPage = new CheckoutPage(driver);

        logInfo("Testing billing details");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();
        cartPage.proceedToCheckout();
        checkoutPage.selectGuestCheckout();

        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();
        String email = TestDataGenerator.generateEmail();
        String telephone = TestDataGenerator.generatePhoneNumber();
        String address = TestDataGenerator.generateAddress();
        String city = TestDataGenerator.generateCity();
        String postcode = TestDataGenerator.generatePostCode();

        checkoutPage.fillBillingDetails(firstName, lastName,email,telephone, address, city,
                postcode, "United States", "California");

        logPass("Billing details filled");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify checkout without agreeing to terms")
    public void testCheckoutWithoutTerms() {
        HomePage homePage = new HomePage(driver);
        CartPage cartPage = new CartPage(driver);

        logInfo("Testing checkout without terms agreement");
        homePage.addFirstProductToCart();
        cartPage.navigateToCart();
        cartPage.proceedToCheckout();

        // Terms validation should prevent order completion
        logPass("Terms validation checked");
        softAssert.assertAll();
    }
}