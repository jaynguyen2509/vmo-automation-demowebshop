package tests.Books;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import vmo.demowebshop.PageGenerator;
import vmo.demowebshop.common.BaseTest;
import vmo.demowebshop.helper.Log;
import vmo.demowebshop.helper.TestNGListener;
import vmo.demowebshop.pages.BooksPageUI;
import vmo.demowebshop.pages.HomePageUI;
import vmo.demowebshop.pages.ShoppingCartPageUI;
import vmo.demowebshop.steps.BooksPageObject;
import vmo.demowebshop.steps.HomePageObject;
import vmo.demowebshop.steps.ShoppingCartPageObject;

import java.util.List;
@Epic("CART")
@Feature(("ADD BOOKS TO CART"))
@Listeners(TestNGListener.class)
public class BuyBooks extends BaseTest {
    private WebDriver driver= super.driver;
    HomePageObject homePageObject;
    BooksPageObject booksTagPageObject;
    ShoppingCartPageObject shoppingCartPageObject;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("CHROME") String browser){
        driver = getDriverBrowser(browser);
        homePageObject = PageGenerator.getHomePageObject(driver);
        booksTagPageObject = PageGenerator.getBooksTagPageObject(driver);
        shoppingCartPageObject = PageGenerator.getShoppingCartPageObject(driver);
    }
    @Test()
    @Description("Add to cart books with highest rate")
    public void TC02_Book_AddToCart_HighestRate(){
        homePageObject.clickDynamicHeaderMenu(HomePageUI.DYNAMIC_HEADER_MENU, "Books");
        verifyEqual(driver.getCurrentUrl(), BooksPageUI.URL);
        List<String> listRating = booksTagPageObject.listRating();
        List<String>lisTopTwoRating = booksTagPageObject.sortListItemsByRating(listRating);
        int currentCartQty = shoppingCartPageObject.getCurrentCartQty();

        booksTagPageObject.addItemToCartViaIndex(lisTopTwoRating,0);
        verifyTrue(shoppingCartPageObject.isCartIncreaseQty(currentCartQty));
        currentCartQty = shoppingCartPageObject.getCurrentCartQty();

        booksTagPageObject.addItemToCartViaIndex(lisTopTwoRating,1);
        verifyTrue(shoppingCartPageObject.isCartIncreaseQty(currentCartQty));

        booksTagPageObject.hoverOnShoppingCart();
        List<String> highestRatingBooksNames = booksTagPageObject.listItemNames(lisTopTwoRating);
        verifyTrue(booksTagPageObject.isShoppingCartContainsAddedItem(highestRatingBooksNames));
    }
}
