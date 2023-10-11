package tests.Login;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import vmo.demowebshop.PageGenerator;
import vmo.demowebshop.common.BaseTest;
import vmo.demowebshop.helper.TestNGListener;
import vmo.demowebshop.pages.HomePageUI;
import vmo.demowebshop.steps.HomePageObject;
import vmo.demowebshop.steps.LoginPageObject;
import vmo.demowebshop.utils.ExcelUtils;

@Epic("LOGIN")
@Feature(("LOGIN"))
@Listeners(TestNGListener.class)
public class Login extends BaseTest {
    private WebDriver driver = super.driver;
    LoginPageObject loginPageObject;
    HomePageObject homePageObject;
    @Parameters({"browser"})
    @BeforeMethod
    public void setUp(@Optional("CHROME") String browser) {
        driver = getDriverBrowser(browser);
        loginPageObject = PageGenerator.getLoginPageObject(driver);
        homePageObject = PageGenerator.getHomePageObject(driver);
    }

    @Test(dataProvider = "Test data Login")
    @Description("Login Success")
    public void TC01_Login_Success(String STT, String email, String password, String expectedMsg) {
        homePageObject.clickLogInLink();
        loginPageObject.sendKeysEmailPassword(email, password);
        loginPageObject.clickLoginBtn();
        verifyTrue(loginPageObject.verifyLogin(expectedMsg));
    }

    @DataProvider(name = "Test data Login")
    public Object[][] getTestData() {
        return ExcelUtils.getExcelData("Test data");
    }
}
