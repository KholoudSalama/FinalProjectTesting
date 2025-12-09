package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage {

    private WebDriver driver;

    // steps may differ قليلاً لكن ده الشكل العام لــ OpenCart
    private By guestCheckoutRadio = By.xpath("//input[@value='guest']");
    private By continueAccountBtn = By.id("button-account");

    private By firstNameInput = By.id("input-payment-firstname");
    private By lastNameInput = By.id("input-payment-lastname");
    private By emailInput = By.id("input-payment-email");
    private By telephoneInput = By.id("input-payment-telephone");
    private By address1Input = By.id("input-payment-address-1");
    private By cityInput = By.id("input-payment-city");
    private By postCodeInput = By.id("input-payment-postcode");
    private By countrySelect = By.id("input-payment-country");
    private By zoneSelect = By.id("input-payment-zone");
    private By continueBillingBtn = By.id("button-guest");

    private By termsCheckbox = By.name("agree");
    private By continueShippingMethodBtn = By.id("button-shipping-method");
    private By continuePaymentMethodBtn = By.id("button-payment-method");
    private By confirmOrderBtn = By.id("button-confirm");

    private By successHeading = By.cssSelector("#content h1");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void chooseGuestCheckout() {
        driver.findElement(guestCheckoutRadio).click();
        driver.findElement(continueAccountBtn).click();
    }

    public void fillBillingDetails(String firstName, String lastName,
                                   String email, String telephone,
                                   String address, String city,
                                   String postcode, String country, String region) throws InterruptedException {

        driver.findElement(firstNameInput).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(telephoneInput).sendKeys(telephone);
        driver.findElement(address1Input).sendKeys(address);
        driver.findElement(cityInput).sendKeys(city);
        driver.findElement(postCodeInput).sendKeys(postcode);

        // dropdowns
        org.openqa.selenium.support.ui.Select countrySel =
                new org.openqa.selenium.support.ui.Select(driver.findElement(countrySelect));
        countrySel.selectByVisibleText(country);

        Thread.sleep(1000); // استبدلها بـ WebDriverWait لو حابب

        org.openqa.selenium.support.ui.Select zoneSel =
                new org.openqa.selenium.support.ui.Select(driver.findElement(zoneSelect));
        zoneSel.selectByVisibleText(region);

        driver.findElement(continueBillingBtn).click();
    }

    public void completeCheckoutFlow() {
        driver.findElement(continueShippingMethodBtn).click();
        driver.findElement(termsCheckbox).click();
        driver.findElement(continuePaymentMethodBtn).click();
        driver.findElement(confirmOrderBtn).click();
    }

    public String getSuccessHeading() {
        return driver.findElement(successHeading).getText();
    }
}

