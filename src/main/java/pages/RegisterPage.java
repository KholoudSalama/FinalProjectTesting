package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage extends BasePage {

    // Locators
    private By myAccountDropdown = By.xpath("//span[text()='My Account']");
    private By registerLink = By.linkText("Register");
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By confirmPasswordInput = By.id("input-confirm");
    private By privacyPolicyCheckbox = By.name("agree");
    private By continueButton = By.xpath("//input[@value='Continue']");
    private By successMessage = By.xpath("//h1[text()='Your Account Has Been Created!']");
    private By errorMessage = By.cssSelector(".alert-danger");
    private By subscribeYesRadio = By.xpath("//input[@name='newsletter'][@value='1']");
    private By subscribeNoRadio = By.xpath("//input[@name='newsletter'][@value='0']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToRegisterPage() {
        click(myAccountDropdown);
        click(registerLink);
    }

    public void fillRegistrationForm(String firstName, String lastName, String email,
                                     String telephone, String password, boolean subscribe) {
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(emailInput, email);
        sendKeys(telephoneInput, telephone);
        sendKeys(passwordInput, password);
        sendKeys(confirmPasswordInput, password);

        if (subscribe) {
            click(subscribeYesRadio);
        } else {
            click(subscribeNoRadio);
        }

        click(privacyPolicyCheckbox);
    }

    public void clickContinue() {
        click(continueButton);
    }

    public boolean isRegistrationSuccessful() {
        return isElementDisplayed(successMessage);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}