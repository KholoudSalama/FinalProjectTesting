package tests;

import org.testng.annotations.Test;
import pages.RegisterPage;
import utils.TestDataGenerator;

import static sun.font.FontUtilities.logInfo;
import static utils.DriverManager.driver;

public class RegisterTests extends tests.BaseTest {

    @Test(priority = 1, description = "Verify successful registration with valid data")
    public void testSuccessfulRegistration() {
        RegisterPage registerPage = new RegisterPage(driver);

        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();
        String email = TestDataGenerator.generateEmail();
        String phone = TestDataGenerator.generatePhoneNumber();
        String password = TestDataGenerator.generatePassword();

        logInfo("Starting registration with email: " + email);
        registerPage.navigateToRegisterPage();
        registerPage.fillRegistrationForm(firstName, lastName, email, phone, password, true);
        registerPage.clickContinue();

        softAssert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration should be successful");
        logPass("Registration completed successfully");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify registration with existing email fails")
    public void testRegistrationWithExistingEmail() {
        RegisterPage registerPage = new RegisterPage(driver);

        logInfo("Attempting registration with existing email");
        registerPage.navigateToRegisterPage();
        registerPage.fillRegistrationForm("Test", "User", "test@test.com", "1234567890", "Test@123", false);
        registerPage.clickContinue();

        softAssert.assertTrue(registerPage.getErrorMessage().contains("already registered"),
                "Should show duplicate email error");
        logPass("Duplicate email validation working correctly");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify registration without accepting privacy policy")
    public void testRegistrationWithoutPrivacyPolicy() {
        RegisterPage registerPage = new RegisterPage(driver);

        String email = TestDataGenerator.generateEmail();

        logInfo("Attempting registration without privacy policy");
        registerPage.navigateToRegisterPage();
        registerPage.fillRegistrationForm("Test", "User", email, "1234567890", "Test@123", true);
        // Don't check privacy policy

        softAssert.assertFalse(registerPage.isRegistrationSuccessful(),
                "Registration should fail without privacy policy");
        logPass("Privacy policy validation working");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify registration with newsletter subscription")
    public void testRegistrationWithNewsletter() {
        RegisterPage registerPage = new RegisterPage(driver);

        String email = TestDataGenerator.generateEmail();

        logInfo("Registering with newsletter subscription");
        registerPage.navigateToRegisterPage();
        registerPage.fillRegistrationForm("Newsletter", "Subscriber", email, "9876543210", "Test@456", true);
        registerPage.clickContinue();

        softAssert.assertTrue(registerPage.isRegistrationSuccessful(), "Registration with newsletter should succeed");
        logPass("Newsletter subscription registration successful");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify registration without newsletter subscription")
    public void testRegistrationWithoutNewsletter() {
        RegisterPage registerPage = new RegisterPage(driver);

        String email = TestDataGenerator.generateEmail();

        logInfo("Registering without newsletter subscription");
        registerPage.navigateToRegisterPage();
        registerPage.fillRegistrationForm("No", "Newsletter", email, "5551234567", "Test@789", false);
        registerPage.clickContinue();

        softAssert.assertTrue(registerPage.isRegistrationSuccessful(),
                "Registration without newsletter should succeed");
        logPass("Non-newsletter registration successful");
        softAssert.assertAll();
    }
}