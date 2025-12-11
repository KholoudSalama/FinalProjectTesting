package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {

    // Locators
    private By myAccountDropdown = By.xpath("//span[text()='My Account']");
    private By loginLink = By.linkText("Login");
    private By emailInput = By.id("input-email");
    private By passwordInput = By.id("input-password");
    private By loginButton = By.xpath("//input[@value='Login']");
    private By forgotPasswordLink = By.linkText("Forgotten Password");
    private By errorMessage = By.cssSelector(".alert-danger");
    private By myAccountHeader = By.xpath("//h2[text()='My Account']");
    private By logoutLink = By.linkText("Logout");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToLoginPage() {
        click(myAccountDropdown);
        click(loginLink);
    }

    public void login(String email, String password) {
        sendKeys(emailInput, email);
        sendKeys(passwordInput, password);
        click(loginButton);
    }

    public boolean isLoginSuccessful() {
        return isElementDisplayed(myAccountHeader);
    }

    public String getErrorMessage() {
        return getText(errorMessage);
    }

    public void clickForgotPassword() {
        click(forgotPasswordLink);
    }

    public void logout() {
        click(myAccountDropdown);
        click(logoutLink);
    }

    public boolean isLoggedIn() {
        return isElementDisplayed(myAccountHeader);
    }
}