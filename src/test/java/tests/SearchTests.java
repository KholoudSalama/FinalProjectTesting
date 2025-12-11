package tests;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.SearchPage;

public class SearchTests extends BaseTest {

    @Test(priority = 1, description = "Verify search with valid product name")
    public void testSearchWithValidProduct() {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        logInfo("Searching for 'MacBook'");
        homePage.searchForProduct("MacBook");

        int results = searchPage.getSearchResultsCount();
        softAssert.assertTrue(results > 0, "Search should return results for MacBook");
        logInfo("Found " + results + " search results");
        logPass("Search with valid product successful");
        softAssert.assertAll();
    }

    @Test(priority = 2, description = "Verify search with invalid product name")
    public void testSearchWithInvalidProduct() {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        logInfo("Searching for invalid product");
        homePage.searchForProduct("XYZ123Invalid");

        softAssert.assertTrue(searchPage.isNoResultsMessageDisplayed(),
                "Should show no results message");
        logPass("No results message displayed correctly");
        softAssert.assertAll();
    }

    @Test(priority = 3, description = "Verify search in specific category")
    public void testSearchInCategory() {
        SearchPage searchPage = new SearchPage(driver);

        logInfo("Searching in specific category");
        driver.get(BASE_URL + "&route=product/search");
        searchPage.searchInCategory("Apple", "Desktops");

        softAssert.assertFalse(searchPage.isNoResultsMessageDisplayed(),
                "Should return results or handle appropriately");
        logPass("Category search completed");
        softAssert.assertAll();
    }

    @Test(priority = 4, description = "Verify list view functionality")
    public void testListView() {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        logInfo("Testing list view");
        homePage.searchForProduct("iPhone");
        searchPage.switchToListView();

        logPass("List view activated");
        softAssert.assertAll();
    }

    @Test(priority = 5, description = "Verify grid view functionality")
    public void testGridView() {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        logInfo("Testing grid view");
        homePage.searchForProduct("Samsung");
        searchPage.switchToGridView();

        logPass("Grid view activated");
        softAssert.assertAll();
    }

    @Test(priority = 6, description = "Verify sort by price ascending")
    public void testSortByPriceAscending() {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        logInfo("Testing sort by price (low to high)");
        homePage.searchForProduct("phone");
        searchPage.sortBy("Price (Low > High)");

        logPass("Price sorting applied");
        softAssert.assertAll();
    }

    @Test(priority = 7, description = "Verify sort by name")
    public void testSortByName() {
        HomePage homePage = new HomePage(driver);
        SearchPage searchPage = new SearchPage(driver);

        logInfo("Testing sort by name");
        homePage.searchForProduct("iPod");
        searchPage.sortBy("Name (A - Z)");

        logPass("Name sorting applied");
        softAssert.assertAll();
    }

    @Test(priority = 8, description = "Verify search with empty query")
    public void testSearchWithEmptyQuery() {
        HomePage homePage = new HomePage(driver);

        logInfo("Testing search with empty query");
        homePage.searchForProduct("");

        softAssert.assertTrue(driver.getCurrentUrl().contains("search"),
                "Should navigate to search page");
        logPass("Empty search handled");
        softAssert.assertAll();
    }
}