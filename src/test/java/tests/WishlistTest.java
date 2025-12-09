package tests;

import org.openqa.selenium.By;
import pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WishlistTest extends BaseTest {

    @Test
    public void addProductToWishlist() {
        // لازم تكون لوج إن الأول
        Header header = new Header(driver);
        header.goToLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("reda1765207845064@test.com", "P@ssw0rd");

        driver.findElement(By.xpath("//*[@id=\"account-account\"]/ul/li[1]/a")).click();

        HomePage homePage = new HomePage(driver);
        homePage.addFirstProductToWishlist();

        // بعد الرسالة نروح صفحة الـ Wishlist
        header.goToWishlist();
        WishlistPage wishlistPage = new WishlistPage(driver);

        Assert.assertTrue(wishlistPage.getWishlistItemsCount() > 0,
                "Wishlist should have at least one product");
    }
}
