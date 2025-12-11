package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchPage extends BasePage {

    // Locators
    private By searchInput = By.id("input-search");
    private By searchButton = By.id("button-search");
    private By categoryDropdown = By.name("category_id");
    private By subcategoryCheckbox = By.name("sub_category");
    private By searchInDescriptionCheckbox = By.name("description");
    private By searchResults = By.cssSelector(".product-layout");
    private By noResultsMessage = By.xpath("//p[contains(text(),'There is no product')]");
    private By productNames = By.cssSelector(".product-layout h4 a");
    private By productPrices = By.cssSelector(".product-layout .price");
    private By listViewButton = By.id("list-view");
    private By gridViewButton = By.id("grid-view");
    private By sortByDropdown = By.id("input-sort");
    private By showLimitDropdown = By.id("input-limit");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String productName) {
        sendKeys(searchInput, productName);
        click(searchButton);
    }

    public void searchInCategory(String productName, String category) {
        sendKeys(searchInput, productName);
        selectByVisibleText(categoryDropdown, category);
        click(searchButton);
    }

    public void searchWithSubcategories(String productName, String category, boolean includeSubcategories) {
        sendKeys(searchInput, productName);
        selectByVisibleText(categoryDropdown, category);
        if (includeSubcategories) {
            click(subcategoryCheckbox);
        }
        click(searchButton);
    }

    public void searchInDescription(String productName) {
        sendKeys(searchInput, productName);
        click(searchInDescriptionCheckbox);
        click(searchButton);
    }

    public int getSearchResultsCount() {
        return driver.findElements(searchResults).size();
    }

    public boolean isNoResultsMessageDisplayed() {
        return isElementDisplayed(noResultsMessage);
    }

    public List<WebElement> getSearchResults() {
        return driver.findElements(searchResults);
    }

    public void switchToListView() {
        click(listViewButton);
    }

    public void switchToGridView() {
        click(gridViewButton);
    }

    public void sortBy(String sortOption) {
        selectByVisibleText(sortByDropdown, sortOption);
    }

    public void setShowLimit(String limit) {
        selectByVisibleText(showLimitDropdown, limit);
    }

    public List<String> getProductNames() {
        List<WebElement> elements = driver.findElements(productNames);
        return elements.stream().map(WebElement::getText).toList();
    }
}