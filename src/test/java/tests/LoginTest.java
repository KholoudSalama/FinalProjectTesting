package tests;

//import pages.BaseTest;
import pages.Header;
import pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test
    public void userCanLogin() {
        Header header = new Header(driver);
        header.goToLogin();

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("reda1765207845064@test.com", "P@ssw0rd");

        Assert.assertTrue(loginPage.getLastBreadcrumb().contains("Account"),
                "User is not on Account page after login");
    }
}
