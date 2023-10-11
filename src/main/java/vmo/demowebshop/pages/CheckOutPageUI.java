package vmo.demowebshop.pages;

public class CheckOutPageUI {
    public static String URL = "https://demowebshop.tricentis.com/login/checkoutasguest?returnUrl=%2Fcart";
    public static String ONE_PAGE_CHECKOUT_URL = "https://demowebshop.tricentis.com/onepagecheckout";
    public static String CHECK_OUT_GUEST_BTN = "//input[@value='Checkout as Guest']";
    public static String BILLING_ADDRESS = "//div[@id='checkout-step-billing']";
    public static String PAYMENT_METHOD ="//div[@id='checkout-step-payment-method']";
    public static String DYNAMIC_PAYMENT_METHOD_TEXT ="//label[contains(text(),'%s')]";
    public static String DYNAMIC_PAYMENT_METHODS_CHECKBOX ="//label[contains(text(),'%s')]/../input";
    public static String PAYMENT_INFO = "//div[@id='checkout-step-payment-info']";
    public static String PAYMENT_INFO_DETAIL_TEXT = "//div[@class='section payment-info']//p";
    public static String CONFIRM_ORDER = "//div[@id='checkout-confirm-order-load']";
    public static String DYNAMIC_INPUT_FIELDS="//input[contains(@id,'BillingNewAddress_%s')]";
    public static String SELECT_COUNTRY = "//select[@id='BillingNewAddress_CountryId']";
    public static String DYNAMIC_CONTINUE_BTN = "//div[contains(@id,'%s')]//input[@value='Continue']";
    public static String CHECK_OUT_CONTINUE_BTN = "//input[@value='Continue']";
    public static String DYNAMIC_BILLING_ADDRESS_INFO = "//li[@class='%s']";
    public static String DYNAMIC_PRODUCT_NAMES = "//a[@class='product-name'] [contains(text(),'%s')]";
    public static String DYNAMIC_PRODUCT_UNIT_PRICE = "//a[@class='product-name'] [contains(text(),'%s')]/..//following-sibling::td[@class='unit-price nobr']/span[2]";
    public static String DYNAMIC_PRODUCT_QTY_INFO = "//a[@class='product-name'] [contains(text(),'%s')]/..//following-sibling::td[@class='qty nobr']/span[2]";
    public static String DYNAMIC_PRODUCT_SUB_TOTAL = "//a[@class='product-name'] [contains(text(),'%s')]/..//following-sibling::td[@class='subtotal nobr end']/span[2]";
    public static String TOTAL_PRICE = "//span[@class='product-price order-total']/strong";
    public static String CONFIRM_BTN = "//input[@value='Confirm']";
    public static String ORDER_SUCCESS = "//div[@class='section order-completed']//strong";
    public static String PAGE_LOADING = "//span[@class='please-wait'] [contains(@id,'wait')]";

}
