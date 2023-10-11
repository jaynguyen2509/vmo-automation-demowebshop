package vmo.demowebshop.pages;

public class ShoppingCartPageUI {
    public static String URL="https://demowebshop.tricentis.com/cart";
    public static String SHOPPING_CART="//li[@id='topcartlink']/a[@class='ico-cart']";
    public static String CART_QTY = "//li[@id='topcartlink']/a[@class='ico-cart']/span[@class='cart-qty']";
    public static String LIST_ITEM_IN_FLYOUT_CART = "//div[@class='name']/a";
    public static String ITEM_NAMES_ON_CART = "//td[@class='product']/a";
    public static String DYNAMIC_ITEM_QTY = "//tr[%s]//input[@class='qty-input']";
    public static String CART_QTY_INPUT = "(//td[@class='qty nobr']/input)[1]";
}
