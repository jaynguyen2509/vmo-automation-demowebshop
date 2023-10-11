package vmo.demowebshop.steps;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import vmo.demowebshop.common.BasePage;
import vmo.demowebshop.pages.LoginPageUI;

import java.util.Objects;

public class LoginPageObject extends BasePage {
    private WebDriver driver;

    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Send keys to Email, Password")
    public void sendKeysEmailPassword(String email, String password) {
        sendKeyToElement(driver, LoginPageUI.EMAIL, email);
        sendKeyToElement(driver, LoginPageUI.PASSWORD, password);
    }

    @Step("Click login button")
    public void clickLoginBtn() {
        clickToElement(driver, LoginPageUI.LOGIN_BTN);
    }
    @Step("Verify Login")
    public Boolean verifyLogin(String expectedMsg) {
        boolean result = false;
        if (Objects.equals(expectedMsg, "Please enter a valid email address.")) {
            result = getTextElement(driver,LoginPageUI.EMAIL_VALIDATION_MSG).equals(expectedMsg);
        } else if (Objects.equals(expectedMsg, "")) {
            result = driver.getCurrentUrl().equals("https://demowebshop.tricentis.com/");
        } else {
            result = getTextElement(driver,LoginPageUI.VALIDATION_SUMMARY).equals(expectedMsg);
        }
        return result;
    }
}
