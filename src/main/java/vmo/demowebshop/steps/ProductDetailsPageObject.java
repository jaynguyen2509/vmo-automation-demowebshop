package vmo.demowebshop.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vmo.demowebshop.common.BasePage;
import vmo.demowebshop.helper.Log;
import vmo.demowebshop.pages.CommonPageUI;
import vmo.demowebshop.pages.ProductDetailsPageUI;

import java.util.List;

public class ProductDetailsPageObject extends BasePage {
    private WebDriver driver;

    public ProductDetailsPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void clickAddToCartBtn(){
        Log.allure("Click add to cart");
        String expectedAddSuccessMsg = "The product has been added to your shopping cart";
        clickToElement(driver, ProductDetailsPageUI.ADD_TO_CART_BTN);
        String actualAddSuccessMsg = getTextElement(driver,CommonPageUI.ADD_SUCCESS_MESSAGE);
        Assert.assertEquals(actualAddSuccessMsg,expectedAddSuccessMsg);
    }
    public String getItemName(String params){
        return (getTextElement(driver,ProductDetailsPageUI.ITEM_NAME,params));
    }
}
