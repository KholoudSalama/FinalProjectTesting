package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage {

    // Locators - Billing Details
    private By firstNameInput = By.id("input-payment-firstname");
    private By lastNameInput = By.id("input-payment-lastname");
    private By emailInput = By.id("input-payment-email");
    private By telephoneInput = By.id("input-payment-telephone");
    private By addressInput = By.id("input-payment-address-1");
    private By cityInput = By.id("input-payment-city");
    private By postcodeInput = By.id("input-payment-postcode");
    private By countryDropdown = By.id("input-payment-country");
    private By regionDropdown = By.id("input-payment-zone");
    private By continueButton = By.id("button-guest");

    // Delivery Details
    private By deliveryContinueButton = By.id("button-shipping-address");

    // Delivery Method
    private By deliveryMethodContinue = By.id("button-shipping-method");

    // Payment Method
    private By termsCheckbox = By.name("agree");
    private By paymentContinueButton = By.id("button-payment-method");

    // Confirm Order
    private By confirmOrderButton = By.id("button-confirm");
    private By successMessage = By.xpath("//h1[contains(text(),'Your order has been placed')]");

    // Guest Checkout
    private By guestRadioButton = By.xpath("//*[@id=\"collapse-checkout-option\"]/div/div/div[1]/div[2]/label/input");
    private By guestContinueButton = By.id("button-account");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public void selectGuestCheckout() {
        driver.findElement(guestRadioButton).click();
        driver.findElement(guestContinueButton).click();
//        if (isElementDisplayed(guestRadioButton)) {
//            driver.findElement(guestRadioButton).click();
//            driver.findElement(guestContinueButton).click();
//        }
    }

    public void fillBillingDetails(String firstName, String lastName,String email,String telephone, String address,
                                   String city, String postcode, String country, String region) {
        sendKeys(firstNameInput, firstName);
        sendKeys(lastNameInput, lastName);
        sendKeys(emailInput,email );
        sendKeys(telephoneInput, telephone);
        sendKeys(addressInput, address);
        sendKeys(cityInput, city);
        sendKeys(postcodeInput, postcode);
        selectByVisibleText(countryDropdown, country);

        try {
            Thread.sleep(1000); // Wait for region dropdown to load
            selectByVisibleText(regionDropdown, region);
        } catch (Exception e) {
            // Region might not be available for all countries
        }

       driver.findElement(continueButton).click();
    }

    public void continueDeliveryDetails() {
        click(deliveryContinueButton);
    }

    public void selectDeliveryMethod() {
        click(deliveryMethodContinue);
    }

    public void selectPaymentMethod() {
        click(termsCheckbox);
        click(paymentContinueButton);
    }

    public void confirmOrder() {
        click(confirmOrderButton);
    }

    public boolean isOrderSuccessful() {
        return isElementDisplayed(successMessage);
    }

    public String getSuccessMessage() {
        return getText(successMessage);
    }
}