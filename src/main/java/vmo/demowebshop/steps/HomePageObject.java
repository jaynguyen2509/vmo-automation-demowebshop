package vmo.demowebshop.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vmo.demowebshop.common.BasePage;
import vmo.demowebshop.helper.Log;
import vmo.demowebshop.pages.CommonPageUI;
import vmo.demowebshop.pages.HomePageUI;

public class HomePageObject extends BasePage {
    private WebDriver driver;

    public HomePageObject(WebDriver driver) {
        this.driver = driver;
    }
    public void clickLink(String link){
        Log.allure("Click link: %s", link);
        waitForElementInvisible(driver,CommonPageUI.ADD_SUCCESS_MESSAGE);
        clickToElement(driver,link);
    }
    @Step()
    public void clickDynamicHeaderMenu(String headerItem, String params){
        clickToElement(driver,headerItem, params);
        Log.allure("Click link header menu item %s",params);

    }
    public void clickDynamicFeaturedProductAndVerifyCartIncrease(String params, int actualCurrentQty){
        Log.allure("Click dynamic feature product by index: %s",params);
        String expectedAddSuccessMsg = "The product has been added to your shopping cart";
        int expectCurrentCartQty = 0;
        for (int i= 0; i < 3; i ++){
            waitForElementClickable(driver,HomePageUI.DYNAMIC_FEATURED_PRODUCT,params);
            clickToElement(driver, HomePageUI.DYNAMIC_FEATURED_PRODUCT,params);
            waitForElementInvisible(driver, CommonPageUI.LOADING_ICON);
            String actualAddSuccessMsg = getTextElement(driver,CommonPageUI.ADD_SUCCESS_MESSAGE);
            Assert.assertEquals(actualAddSuccessMsg,expectedAddSuccessMsg);
            expectCurrentCartQty++;
            actualCurrentQty++;
            Assert.assertEquals(actualCurrentQty,expectCurrentCartQty);
        }


    }
}
