package vmo.demowebshop.pages;

public class DigitalsDownloadPageUI {
    public static String URL = "https://demowebshop.tricentis.com/digital-downloads";
    public static String DYNAMIC_ADD_ITEM_TO_CART="//input[@value='Add to cart'][%s]";
    public static String TERM_OF_SERVICE_CHECKBOX = "//input[@id='termsofservice']";
    public static String CHECK_OUT_BTN = "//button[@id='checkout']";
    public static String DYNAMIC_ITEM_PRICE ="(//div[@class='prices']/span)[%s]";
    public static String DYNAMIC_ITEM_NAME = "(//h2[@class='product-title']/a)[%s]";
}
