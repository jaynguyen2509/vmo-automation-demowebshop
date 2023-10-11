package vmo.demowebshop.steps;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import vmo.demowebshop.common.BasePage;
import vmo.demowebshop.helper.Log;
import vmo.demowebshop.pages.BooksPageUI;
import vmo.demowebshop.pages.CommonPageUI;
import vmo.demowebshop.pages.ShoppingCartPageUI;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BooksPageObject extends BasePage {
    private WebDriver driver;

    public BooksPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextElement(String locator) {
        Log.allure("Get text of element %s",locator);
        waitForElementVisible(driver,locator);
        return getTextElement(driver, locator);
    }

    public List<String> listItemInShoppingCart() {
        Log.allure("Get current item names in shopping cart");
        List<WebElement> listItemElements = getListWebElements(driver, ShoppingCartPageUI.LIST_ITEM_IN_FLYOUT_CART);
        List<String> listBookNames = new ArrayList<>();
        for (WebElement element : listItemElements) {
            String bookName = element.getText();
            listBookNames.add(bookName);
        }
        return listBookNames;
    }

    public List<String> listRating() {
        Log.allure("Get all item's rating value to a list");
        List<WebElement> elementList = getListWebElements(driver, BooksPageUI.AVAILABLE_BOOKS);
        List<String> ratingList = new ArrayList<>();
        for (WebElement element : elementList) {
            String attrValue = element.getAttribute("style");
            ratingList.add(attrValue.replace(";", ""));
        }
        return ratingList;
    }

    public List<String> sortListItemsByRating(List<String> list) {
        Log.allure("Sort rating value in list in DESC order");
        list = list.stream().sorted((s1, s2) -> {
            int value1 = Integer.parseInt(s1.split(":")[1].trim().replace("%", "").replace(";", ""));
            int value2 = Integer.parseInt(s2.split(":")[1].trim().replace("%", "").replace(";", ""));
            return Integer.compare(value2, value1);
        }).collect(Collectors.toList());
        return list;
    }

    public void addItemToCartViaIndex(List<String> list, int index) {
        Log.allure("Add %s via index to cart",list.get(index));
        String expectedAddSuccessMsg = "The product has been added to your shopping cart";

        clickToElement(driver, BooksPageUI.DYNAMIC_ADD_TO_CART_BOOK, list.get(index));
        waitForElementInvisible(driver, CommonPageUI.LOADING_ICON);
        String actualAddSuccessMsg = getTextElement(BooksPageUI.ADD_SUCCESS_MESSAGE);
        Assert.assertEquals(actualAddSuccessMsg,expectedAddSuccessMsg);
    }

    public List<String> listItemNames(List<String> list) {
        Log.allure("Get all item name into a list");
        List<String> listNames = new ArrayList<>();
        for (String item : list) {
            listNames.add(getTextElement(driver, BooksPageUI.DYNAMIC_BOOK_NAME, item));
        }
        return listNames;
    }

    public void hoverOnShoppingCart() {
        Log.allure("Hover mouse on shopping cart");
        waitForElementInvisible(driver,BooksPageUI.ADD_SUCCESS_MESSAGE);
        hoverMouseToElement(driver, ShoppingCartPageUI.SHOPPING_CART);
    }

    public boolean isShoppingCartContainsAddedItem(List<String> listExpectedItem) {
        Log.allure("Check if item is in the list");
        boolean result = false;
        List<String> listActualItemsName = listItemInShoppingCart();
        for (String name : listActualItemsName) {
            result = listExpectedItem.contains(name);
        }
        return  result;
    }
}
