package tests;

import org.testng.annotations.Test;
import pages.LoginPage;
import utils.TestDataGenerator;


public class LoginTests extends BaseTest {

    @Test(priority = 1, description = "Verify login with invalid credentials")
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        String email = TestDataGenerator.generateEmail();
        logInfo("Attempting login with invalid credentials");
        loginPage.navigateToLoginPage();
        loginPage.login(email, "wrongpassword");

        softAssert.assertTrue(loginPage.getErrorMessage().contains("No match"),
                "Should show invalid credentials error");
        logPass("Invalid credentials validation working");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify login with empty email")
    public void testLoginWithEmptyEmail() {
        LoginPage loginPage = new LoginPage(driver);

        logInfo("Attempting login with empty email");
        loginPage.navigateToLoginPage();
        loginPage.login("", "password123");

        softAssert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with empty email");
        logPass("Empty email validation working");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify login with empty password")
    public void testLoginWithEmptyPassword() {
        LoginPage loginPage = new LoginPage(driver);

        logInfo("Attempting login with empty password");
        loginPage.navigateToLoginPage();
        loginPage.login("test@test.com", "");

        softAssert.assertFalse(loginPage.isLoginSuccessful(), "Login should fail with empty password");
        logPass("Empty password validation working");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify forgot password link is accessible")
    public void testForgotPasswordLink() {
        LoginPage loginPage = new LoginPage(driver);

        logInfo("Testing forgot password link");
        loginPage.navigateToLoginPage();
        loginPage.clickForgotPassword();

        softAssert.assertTrue(driver.getCurrentUrl().contains("forgotten"),
                "Should navigate to forgot password page");
        logPass("Forgot password link working");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify login page elements are displayed")
    public void testLoginPageElements() {
        LoginPage loginPage = new LoginPage(driver);

        logInfo("Verifying login page elements");
        loginPage.navigateToLoginPage();

        softAssert.assertTrue(driver.getTitle().contains("Account Login"), "Page title should contain 'Account Login'");
        softAssert.assertTrue(driver.getCurrentUrl().contains("login"), "URL should contain 'login'");
        logPass("Login page elements verified");
        softAssert.assertAll();
    }
}