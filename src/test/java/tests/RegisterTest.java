package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.Header;
import pages.RegisterPage;

public class RegisterTest extends BaseTest {

        @Test
        public void userCanRegisterSuccessfully() {
            Header header = new Header(driver);
            header.goToRegister();

            RegisterPage registerPage = new RegisterPage(driver);

            String randomEmail = "reda" + System.currentTimeMillis() + "@test.com";
            System.out.println(randomEmail);

            registerPage.registerNewUser(
                    "Reda",
                    "Tester",
                    randomEmail,
                    "01234567890",
                    "P@ssw0rd"
            );

            String heading = registerPage.getSuccessTitle();
            Assert.assertTrue(heading.contains("Your Account Has Been Created"),
                    "Registration success heading not found!");
        }
}

