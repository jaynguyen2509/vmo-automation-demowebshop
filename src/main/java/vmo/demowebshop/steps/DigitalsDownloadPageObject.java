package vmo.demowebshop.steps;

import org.openqa.selenium.WebDriver;
import vmo.demowebshop.common.BasePage;
import vmo.demowebshop.helper.Log;
import vmo.demowebshop.pages.DigitalsDownloadPageUI;

public class DigitalsDownloadPageObject extends BasePage {
    private WebDriver driver;

    public DigitalsDownloadPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public String getItemPriceByIndex(String index){
        String price = getTextElement(driver,DigitalsDownloadPageUI.DYNAMIC_ITEM_PRICE,index);
        Log.allure("Get item price: %s", price);
        return price;
    }
    public String getItemNameByIndex(String index){
        String item = getTextElement(driver,DigitalsDownloadPageUI.DYNAMIC_ITEM_NAME,index);
        Log.allure("Get item name: %s", item);
        return item;
    }
    public void addItemToCartByIndex(String index){
        Log.allure("Add item index: %s", index);
        clickToElement(driver, DigitalsDownloadPageUI.DYNAMIC_ADD_ITEM_TO_CART,index);
    }
}
