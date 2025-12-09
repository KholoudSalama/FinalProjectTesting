package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
        private final WebDriver driver ;

        private By firstNameInput = By.id("input-firstname");
        private By lastNameInput = By.id("input-lastname");
        private By emailInput = By.id("input-email");
        private By telephoneInput = By.id("input-telephone");
        private By passwordInput = By.id("input-password");
        private By confirmPasswordInput = By.id("input-confirm");
        private By newsletterNoRadio = By.xpath("//input[@name='newsletter' and @value='0']");
        private By privacyPolicyCheckbox = By.name("agree");
        private By continueButton = By.cssSelector("input[type='submit'][value='Continue']");
        private By successTitle = By.cssSelector("#content h1");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void registerNewUser(String firstName, String lastName,
                                String email, String telephone,
                                String password) {

            driver.findElement(firstNameInput).sendKeys(firstName);
            driver.findElement(lastNameInput).sendKeys(lastName);
            driver.findElement(emailInput).sendKeys(email);
            driver.findElement(telephoneInput).sendKeys(telephone);
            driver.findElement(passwordInput).sendKeys(password);
            driver.findElement(confirmPasswordInput).sendKeys(password);
            driver.findElement(newsletterNoRadio).click();
            driver.findElement(privacyPolicyCheckbox).click();
            driver.findElement(continueButton).click();
        }

        public String getSuccessTitle() {
            return driver.findElement(successTitle).getText();
        }
    }

