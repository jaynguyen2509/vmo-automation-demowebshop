package vmo.demowebshop.pages;

public class BooksPageUI {
    public static String URL = "https://demowebshop.tricentis.com/books";
    public static String AVAILABLE_BOOKS="//input//ancestor::div[@class='item-box']//div[@class='rating']/div";
    public static String DYNAMIC_ADD_TO_CART_BOOK = "//div[@class='rating']/div[@style='%s']//following::input[1]";
    public static String DYNAMIC_BOOK_NAME = "//div[@class='rating']/div[@style='%s']//ancestor::div[@class='item-box']//h2/a";
    public static String ADD_SUCCESS_MESSAGE = "//div[@id='bar-notification']//p";
}
