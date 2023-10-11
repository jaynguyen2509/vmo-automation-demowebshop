package tests.DigitalsDownload;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import vmo.demowebshop.PageGenerator;
import vmo.demowebshop.common.BaseTest;
import vmo.demowebshop.constants.Constants;
import vmo.demowebshop.helper.TestNGListener;
import vmo.demowebshop.pages.*;
import vmo.demowebshop.steps.*;
import vmo.demowebshop.utils.DataFakerUtils;


@Epic("BUY")
@Feature(("BUY DIGITAL DOWNLOAD ITEM SUCCESS"))
@Listeners(TestNGListener.class)
public class DigitalsDownload extends BaseTest {
    private WebDriver driver= super.driver;
    HomePageObject homePageObject;
    BooksPageObject booksTagPageObject;
    ShoppingCartPageObject shoppingCartPageObject;
    DigitalsDownloadPageObject digitalsDownloadPageObject;
    CheckOutPageObject checkOutPageObject;
    String firstName = DataFakerUtils.getData().getFirstName();
    String lastName = DataFakerUtils.getData().getLastName();
    String email = DataFakerUtils.getData().getEmailAddress();
    String country = "Viet Nam";
    String city = DataFakerUtils.getData().getCityName();
    String address = DataFakerUtils.getData().getFullAddress();
    String postalCode = DataFakerUtils.getData().getZipCode();
    String phone = DataFakerUtils.getData().getPhoneNumber();
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("CHROME") String browser){
        driver = getDriverBrowser(browser);
        homePageObject = PageGenerator.getHomePageObject(driver);
        booksTagPageObject = PageGenerator.getBooksTagPageObject(driver);
        shoppingCartPageObject = PageGenerator.getShoppingCartPageObject(driver);
        digitalsDownloadPageObject = PageGenerator.getDigitalsDownloadPageObject(driver);
        checkOutPageObject = PageGenerator.getCheckOutPageObject(driver);
    }

    @Test()
    @Description("Buy digital item successfully")
    public void TC04_Buy_Digital_Item_Success(){
        String actualAddSuccessMsg;
        String expectedAddSuccessMsg = "The product has been added to your shopping cart";
        verifyEqual(driver.getCurrentUrl(), Constants.DemoWebShop.URL);

        homePageObject.clickDynamicHeaderMenu(HomePageUI.DYNAMIC_HEADER_MENU, "Digital downloads");
        verifyEqual(driver.getCurrentUrl(), DigitalsDownloadPageUI.URL);
        int currentCartQty = shoppingCartPageObject.getCurrentCartQty();

        String itemName = digitalsDownloadPageObject.getItemNameByIndex("1");
        String itemPrice = digitalsDownloadPageObject.getItemPriceByIndex("1");
        digitalsDownloadPageObject.addItemToCartByIndex("1");
        actualAddSuccessMsg = booksTagPageObject.getTextElement(CommonPageUI.ADD_SUCCESS_MESSAGE);
        verifyEqual(actualAddSuccessMsg, expectedAddSuccessMsg);
        verifyTrue(shoppingCartPageObject.isCartIncreaseQty(currentCartQty));

        homePageObject.clickLink(ShoppingCartPageUI.SHOPPING_CART);
        String itemQuantity = shoppingCartPageObject.getItemsQtyViaIndex("1");
        verifyEqual(driver.getCurrentUrl(), ShoppingCartPageUI.URL);
        shoppingCartPageObject.checkTermOfServiceBox();
        verifyTrue(shoppingCartPageObject.checkBoxIsChecked(DigitalsDownloadPageUI.TERM_OF_SERVICE_CHECKBOX));
        shoppingCartPageObject.clickCheckOutBtn();
        verifyEqual(driver.getCurrentUrl(), CheckOutPageUI.URL);

        checkOutPageObject.clickCheckOutAsGuest();
        verifyEqual(driver.getCurrentUrl(), CheckOutPageUI.ONE_PAGE_CHECKOUT_URL);
        verifyTrue(checkOutPageObject.isBillingAddressDisplayed());
        checkOutPageObject.fillInBillingAddressForm(firstName,lastName,email,country,city,address,postalCode,phone);

        verifyTrue(checkOutPageObject.isPaymentMethodDisplayed());
        verifyTrue(checkOutPageObject.isDefaultPaymentMethodCorrect("Cash On Delivery"));
        String finalPaymentMethod = checkOutPageObject.getCashDeliveryMethod();
        String additionalFee = checkOutPageObject.getCashDeliveryFee();
        checkOutPageObject.clickContinue("method");

        verifyTrue(checkOutPageObject.isPaymentInfoDisplayed());
        verifyEqual(checkOutPageObject.getTextElement(CheckOutPageUI.PAYMENT_INFO_DETAIL_TEXT),"You will pay by COD");
        checkOutPageObject.clickContinue("info");

        String fullName = firstName + " " + lastName;
        String expectedEmail = "Email: "+ email;
        String expectedPhone = "Phone: "+ phone;
        String cityAndZip = city + " , "+postalCode;

        verifyTrue(checkOutPageObject.isConfirmOrderDisplayed());
        checkOutPageObject.verifyBillingSection(
                fullName,
                expectedEmail,
                expectedPhone,
                address,
                cityAndZip,
                country,
                finalPaymentMethod);
        String expectedSubTotal = checkOutPageObject.calculateSubTotal(itemPrice,itemQuantity);
        String expectedTotal = checkOutPageObject.calculateTotal(expectedSubTotal,additionalFee);
        checkOutPageObject.verifyProducts(itemName,itemPrice,itemQuantity,expectedSubTotal);
        checkOutPageObject.verifyCalculatingTotal(expectedTotal);
        checkOutPageObject.orderSuccessWithMsg("Your order has been successfully processed!");
        verifyEqual(driver.getCurrentUrl(),Constants.DemoWebShop.URL);
    }
}
