package tests.HomePage;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import vmo.demowebshop.PageGenerator;
import vmo.demowebshop.common.BaseTest;
import vmo.demowebshop.constants.Constants;
import vmo.demowebshop.helper.TestNGListener;
import vmo.demowebshop.pages.ShoppingCartPageUI;
import vmo.demowebshop.steps.*;

@Epic("ADD ITEM")
@Feature(("ADD FEATURED PRODUCT ITEM"))
@Listeners(TestNGListener.class)
public class FeaturedProducts extends BaseTest {
    private WebDriver driver = super.driver;
    HomePageObject homePageObject;
    BooksPageObject booksTagPageObject;
    ProductDetailsPageObject productDetailsPageObject;
    ShoppingCartPageObject shoppingCartPageObject;

    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("CHROME") String browser) {
        driver = getDriverBrowser(browser);
        homePageObject = PageGenerator.getHomePageObject(driver);
        booksTagPageObject = PageGenerator.getBooksTagPageObject(driver);
        productDetailsPageObject = PageGenerator.getProductDetailsPageObject(driver);
        shoppingCartPageObject = PageGenerator.getShoppingCartPageObject(driver);
    }

    @Test()
    @Description("Remove item out of shopping cart")
    public void TC03_Remove_Item_Shopping_Cart() {
        verifyEqual(driver.getCurrentUrl(), Constants.DemoWebShop.URL);
        int currentCartQty = shoppingCartPageObject.getCurrentCartQty();
        String item = productDetailsPageObject.getItemName("2");
        homePageObject.clickDynamicFeaturedProductAndVerifyCartIncrease("2",currentCartQty);
        homePageObject.clickLink(ShoppingCartPageUI.SHOPPING_CART);
        verifyEqual(driver.getCurrentUrl(), ShoppingCartPageUI.URL);
        shoppingCartPageObject.decreaseItemQtyAndUpdateShopingCart(item,"2");
    }
}
