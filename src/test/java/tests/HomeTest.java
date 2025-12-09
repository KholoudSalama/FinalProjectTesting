package tests;



import pages.HomePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomeTest extends BaseTest {

    @Test
    public void featuredProductsAreVisible() {
        HomePage homePage = new HomePage(driver);
        Assert.assertTrue(homePage.getFeaturedProductCount() > 0,
                "No featured products found on home page");
    }

    @Test
    public void addToCartFromHomeShowsSuccessMessage() {
        HomePage homePage = new HomePage(driver);
        homePage.addFirstProductToCart();
        String msg = homePage.getSuccessMessage();
        Assert.assertTrue(msg.toLowerCase().contains("success"),
                "Success message not displayed after Add to Cart");
    }
}

