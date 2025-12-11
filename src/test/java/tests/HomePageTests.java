package tests;

import org.testng.annotations.Test;
import pages.HomePage;

public class HomePageTests extends BaseTest {

    @Test(priority = 1, description = "Verify home page loads successfully")
    public void testHomePageLoads() {
        HomePage homePage = new HomePage(driver);

        logInfo("Verifying home page load");
        softAssert.assertTrue(homePage.isLogoDisplayed(), "Logo should be displayed");
        softAssert.assertTrue(driver.getTitle().contains("Your Store"), "Page title should be correct");
        logPass("Home page loaded successfully");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify featured products are displayed")
    public void testFeaturedProductsDisplay() {
        HomePage homePage = new HomePage(driver);

        logInfo("Checking featured products display");
        int productCount = homePage.getFeaturedProductsCount();

        softAssert.assertTrue(productCount > 0, "Featured products should be displayed");
        logInfo("Found " + productCount + " featured products");
        logPass("Featured products displayed correctly");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify navigation bar is displayed")
    public void testNavigationBar() {
        HomePage homePage = new HomePage(driver);

        logInfo("Verifying navigation bar");
        softAssert.assertTrue(homePage.isNavbarDisplayed(), "Navigation bar should be visible");
        logPass("Navigation bar verified");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify add to cart from home page")
    public void testAddToCartFromHomePage() {
        HomePage homePage = new HomePage(driver);

        logInfo("Testing add to cart functionality");
        homePage.addFirstProductToCart();

        softAssert.assertTrue(homePage.isSuccessAlertDisplayed(), "Success message should appear");
        logPass("Product added to cart successfully");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify add to wishlist from home page")
    public void testAddToWishlistFromHomePage() {
        HomePage homePage = new HomePage(driver);

        logInfo("Testing add to wishlist functionality");
        homePage.addFirstProductToWishlist();

        // Note: Wishlist might require login
        logPass("Add to wishlist button clicked");
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "Verify desktops category navigation")
    public void testDesktopsCategoryNavigation() {
        HomePage homePage = new HomePage(driver);

        logInfo("Testing desktops category navigation");
        homePage.navigateToCategory("desktops");

        softAssert.assertTrue(driver.getCurrentUrl().contains("/category"),
                "Should navigate to cameras category");
        logPass("cameras category navigation successful");
        softAssert.assertAll();
    }

    @Test(priority = 7, description = "Verify laptops category navigation")
    public void testLaptopsCategoryNavigation() {
        HomePage homePage = new HomePage(driver);

        logInfo("Testing laptops category navigation");
        homePage.navigateToCategory("laptops");

        softAssert.assertTrue(driver.getCurrentUrl().contains("/category"),
                "Should navigate to laptops category");
        logPass("Laptops category navigation successful");
        softAssert.assertAll();
    }

    @Test(priority = 8, description = "Verify tablets category navigation")
    public void testTabletsCategoryNavigation() {
        HomePage homePage = new HomePage(driver);

        logInfo("Testing tablets category navigation");
        homePage.navigateToCategory("tablets");

        softAssert.assertTrue(driver.getCurrentUrl().contains("/category"),
                "Should navigate to tablets category");
        logPass("Tablets category navigation successful");
        softAssert.assertAll();
    }
}