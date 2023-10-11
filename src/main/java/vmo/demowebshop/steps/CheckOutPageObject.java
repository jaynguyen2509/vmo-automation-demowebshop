package vmo.demowebshop.steps;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vmo.demowebshop.common.BasePage;
import vmo.demowebshop.helper.Log;
import vmo.demowebshop.pages.CheckOutPageUI;


import java.util.Objects;

public class CheckOutPageObject extends BasePage {
    private WebDriver driver;

    public CheckOutPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickCheckOutAsGuest() {
        Log.allure("Click checkout as guest button");
        clickToElement(driver, CheckOutPageUI.CHECK_OUT_GUEST_BTN);
    }

    public boolean isBillingAddressDisplayed() {
        Log.allure("Check billing address tab is displayed");
        return isElementDisplay(driver, CheckOutPageUI.BILLING_ADDRESS);
    }

    public boolean isPaymentMethodDisplayed() {
        Log.allure("Check payment method is displayed");
        waitForElementInvisible(driver, CheckOutPageUI.PAGE_LOADING);
        return isElementDisplay(driver, CheckOutPageUI.PAYMENT_METHOD);
    }

    public boolean isPaymentInfoDisplayed() {
        Log.allure("Check payment information is displayed");
        waitForElementInvisible(driver, CheckOutPageUI.PAGE_LOADING);
        waitForElementVisible(driver,CheckOutPageUI.PAYMENT_INFO);
        return isElementDisplay(driver, CheckOutPageUI.PAYMENT_INFO);
    }

    public boolean isConfirmOrderDisplayed() {
        Log.allure("Check confirm order is displayed");
        waitForElementInvisible(driver, CheckOutPageUI.PAGE_LOADING);
        waitForElementVisible(driver,CheckOutPageUI.CONFIRM_ORDER);
        return isElementDisplay(driver, CheckOutPageUI.CONFIRM_ORDER);
    }

    public void fillInBillingAddressForm(String firstName, String lastName, String email, String country, String city, String address, String postalCode, String phone) {
        Log.allure("Fill in billing address form");
        sendKeyToElement(driver, CheckOutPageUI.DYNAMIC_INPUT_FIELDS, firstName, "FirstName");
        sendKeyToElement(driver, CheckOutPageUI.DYNAMIC_INPUT_FIELDS, lastName, "LastName");
        sendKeyToElement(driver, CheckOutPageUI.DYNAMIC_INPUT_FIELDS, email, "Email");
        selectItemInDefaultDropdownByText(driver, CheckOutPageUI.SELECT_COUNTRY, country);
        sendKeyToElement(driver, CheckOutPageUI.DYNAMIC_INPUT_FIELDS, city, "City");
        sendKeyToElement(driver, CheckOutPageUI.DYNAMIC_INPUT_FIELDS, address, "Address1");
        sendKeyToElement(driver, CheckOutPageUI.DYNAMIC_INPUT_FIELDS, postalCode, "ZipPostalCode");
        sendKeyToElement(driver, CheckOutPageUI.DYNAMIC_INPUT_FIELDS, phone, "PhoneNumber");
        clickToElement(driver, CheckOutPageUI.DYNAMIC_CONTINUE_BTN, "billing");
    }

    public String getCashDeliveryFee() {
        Log.allure("Get additional fee");
        String methodName = getTextElement(driver, CheckOutPageUI.DYNAMIC_PAYMENT_METHOD_TEXT, "Cash On Delivery");
        int firstParentIndex = methodName.indexOf("(");
        int secondParentIndex = methodName.indexOf("(", firstParentIndex + 1);
        return methodName.substring(secondParentIndex).trim().replace("(", "").replace(")", "");
    }

    public String getCashDeliveryMethod() {
        Log.allure("Get Cash delivery method");
        String methodName = getTextElement(driver, CheckOutPageUI.DYNAMIC_PAYMENT_METHOD_TEXT, "Cash On Delivery");
        int firstParentIndex = methodName.indexOf("(");
        int secondParentIndex = methodName.indexOf("(", firstParentIndex + 1);
        return methodName.substring(0, secondParentIndex).trim();
    }

    public void clickContinue(String param) {
        Log.allure("Click continue in tab: %s",param);
        clickToElement(driver, CheckOutPageUI.DYNAMIC_CONTINUE_BTN, param);
    }

    public boolean isDefaultPaymentMethodCorrect(String methodName) {
        Log.allure("Check is default method = '%s'", methodName);
        waitForElementInvisible(driver, CheckOutPageUI.PAGE_LOADING);
        String value = getAttributeElement(driver, CheckOutPageUI.DYNAMIC_PAYMENT_METHODS_CHECKBOX, "checked", methodName);
        return Objects.equals(value, "true");
    }

    public String getTextElement(String locator, String... params) {
        waitForElementVisible(driver, locator,params);
        String text = getTextElement(driver, locator,params);
        Log.allure("Get text: '%s'",text);
        return text;
    }

    public void verifyBillingSection(
            String fullName,
            String email,
            String phone,
            String address,
            String cityAndZipCode,
            String country,
            String paymentMethod) {
        Log.allure("Verify Billing Address information");
        String actualFullName = getTextElement(CheckOutPageUI.DYNAMIC_BILLING_ADDRESS_INFO,"name").trim();
        String actualEmail = getTextElement(CheckOutPageUI.DYNAMIC_BILLING_ADDRESS_INFO,"email").trim();
        String actualPhone = getTextElement(CheckOutPageUI.DYNAMIC_BILLING_ADDRESS_INFO,"phone").trim();
        String actualAddress = getTextElement(CheckOutPageUI.DYNAMIC_BILLING_ADDRESS_INFO,"address1").trim();
        String actualCityAndZip = getTextElement(CheckOutPageUI.DYNAMIC_BILLING_ADDRESS_INFO,"city-state-zip").trim();
        String actualCountry = getTextElement(CheckOutPageUI.DYNAMIC_BILLING_ADDRESS_INFO,"country").trim();
        String actualPaymentMethod = getTextElement(CheckOutPageUI.DYNAMIC_BILLING_ADDRESS_INFO,"payment-method").trim();

        Assert.assertEquals(actualFullName,fullName);
        Assert.assertEquals(actualEmail,email);
        Assert.assertEquals(actualPhone,phone);
        Assert.assertEquals(actualAddress,address);
        Assert.assertEquals(actualCityAndZip,cityAndZipCode);
        Assert.assertEquals(actualCountry,country);
        Assert.assertEquals(actualPaymentMethod,paymentMethod);

//        return verifyName && verifyEmail && verifyPhone && verifyAddress && verifyCityAndZip && verifyCountry && verifyPaymentMethod;
    }
    public void verifyProducts(
            String expectedItemName,
            String expectedPrice,
            String expectedQty,
            String expectedSubTotal){
        Log.allure("Verify product information");
        String actualItemName = getTextElement(driver,CheckOutPageUI.DYNAMIC_PRODUCT_NAMES,expectedItemName);
        String actualPrice = getTextElement(driver,CheckOutPageUI.DYNAMIC_PRODUCT_UNIT_PRICE,expectedItemName);
        String actualQty = getTextElement(driver,CheckOutPageUI.DYNAMIC_PRODUCT_QTY_INFO,expectedItemName);
        String actualSubTotal = getTextElement(driver,CheckOutPageUI.DYNAMIC_PRODUCT_SUB_TOTAL,expectedItemName);

        Assert.assertEquals(expectedItemName,actualItemName);
        Assert.assertEquals(expectedPrice,actualPrice);
        Assert.assertEquals(expectedQty,actualQty);
        Assert.assertEquals(expectedSubTotal,actualSubTotal);
    }
    public String calculateSubTotal(String priceValue, String qtyValue){
        Log.allure("Calculate subtotal price");
        float price = Float.parseFloat(priceValue);
        float qty = Float.parseFloat(qtyValue);
        float subTotal = price * qty;
        return String.format("%.2f", subTotal);
    }
    public String calculateTotal(String subTotalValue, String additionalFeeValue){
        Log.allure("Calculate total price");
        float subTotal = Float.parseFloat(subTotalValue);
        float additionalFee = Float.parseFloat(additionalFeeValue);
        float total = subTotal + additionalFee;
        return String.format("%.2f",total);
    }
    public void verifyCalculatingTotal(String expectedTotal){
        Log.allure("Check total price is calculate correct");
        Assert.assertEquals(getTextElement(CheckOutPageUI.TOTAL_PRICE),expectedTotal);
    }
    public void orderSuccessWithMsg(String msg){
        Log.allure("Check msg is display: '%s'",msg);
        clickToElement(driver,CheckOutPageUI.CONFIRM_BTN);
        waitForElementVisible(driver,CheckOutPageUI.ORDER_SUCCESS);
        Assert.assertEquals(getTextElement(CheckOutPageUI.ORDER_SUCCESS),msg);
        clickToElement(driver,CheckOutPageUI.CHECK_OUT_CONTINUE_BTN);
    }
}
