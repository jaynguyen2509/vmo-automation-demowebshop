package vmo.demowebshop;

import org.openqa.selenium.WebDriver;
import vmo.demowebshop.steps.*;

public class PageGenerator {
    public static LoginPageObject getLoginPageObject(WebDriver driver) {
        return new LoginPageObject(driver);
    }

    public static HomePageObject getHomePageObject(WebDriver driver) {
        return new HomePageObject(driver);
    }
    public static BooksPageObject getBooksTagPageObject(WebDriver driver) {
        return new BooksPageObject(driver);
    }
    public static ProductDetailsPageObject getProductDetailsPageObject(WebDriver driver) {
        return new ProductDetailsPageObject(driver);
    }
    public static ShoppingCartPageObject getShoppingCartPageObject(WebDriver driver) {
        return new ShoppingCartPageObject(driver);
    }
    public static DigitalsDownloadPageObject getDigitalsDownloadPageObject(WebDriver driver) {
        return new DigitalsDownloadPageObject(driver);
    }
    public static CheckOutPageObject getCheckOutPageObject(WebDriver driver){
        return new CheckOutPageObject(driver);
    }
}
