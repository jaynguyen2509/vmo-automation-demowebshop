package vmo.demowebshop.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import vmo.demowebshop.common.BasePage;
import vmo.demowebshop.helper.Log;
import vmo.demowebshop.pages.DigitalsDownloadPageUI;
import vmo.demowebshop.pages.ShoppingCartPageUI;

import java.awt.event.KeyEvent;
import java.security.Key;
import java.util.List;

public class ShoppingCartPageObject extends BasePage {
    private WebDriver driver;

    public ShoppingCartPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public ShoppingCartPageObject decreaseItemQtyAndUpdateShopingCart(String itemName,String newValue){
        Log.allure("Decrease item quantity and update shopping cart btn");
        Assert.assertEquals(getTextElement(driver,ShoppingCartPageUI.ITEM_NAMES_ON_CART),itemName);
        clickToElement(driver,ShoppingCartPageUI.CART_QTY_INPUT);
        sendKeyToElement(driver,ShoppingCartPageUI.CART_QTY_INPUT,newValue);
        driver.findElement(By.xpath(ShoppingCartPageUI.CART_QTY_INPUT)).sendKeys(Keys.ENTER);
        sleepInSecond(2);
        Assert.assertEquals(String.valueOf(getCurrentCartQty()),newValue);
        return this;
    }
    public int getCurrentCartQty() {
        Log.allure("Get current cart's quantity");
        return Integer.parseInt(getTextElement(driver,ShoppingCartPageUI.CART_QTY).replace("(", "").replace(")", ""));
    }

    public Boolean isCartIncreaseQty(int currentQty) {
        Log.allure("Verify cart is increased");
        return getCurrentCartQty() == currentQty + 1;
    }
    public void checkTermOfServiceBox(){
        Log.allure("Check term of service box");
        checkToDefaultCheckboxOrDefaultRadio(driver, DigitalsDownloadPageUI.TERM_OF_SERVICE_CHECKBOX);
    }
    public boolean checkBoxIsChecked(String locator){
        return isElementSelected(driver,locator);
    }
    public void clickCheckOutBtn(){
        Log.allure("Click checkout button");
        clickToElement(driver,DigitalsDownloadPageUI.CHECK_OUT_BTN);
    }
    public String getItemsQtyViaIndex(String index){
        Log.allure("Get item quantity in index: %s", index);
        waitForElementVisible(driver,ShoppingCartPageUI.DYNAMIC_ITEM_QTY,index);
        String qty = getAttributeElement(driver,ShoppingCartPageUI.DYNAMIC_ITEM_QTY,"value",index);
        Log.allure("Get item name: %s", qty);
        return qty;
    }

}
