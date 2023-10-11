package vmo.demowebshop.common;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import vmo.demowebshop.constants.Constants;
import vmo.demowebshop.factoryEnviroment.LocalFactory;
import vmo.demowebshop.helper.Log;

import java.io.IOException;
import java.time.Duration;


public class BaseTest {
    protected WebDriver driver;

    protected WebDriver getDriverBrowser(String browser) {
        driver = new LocalFactory().createDriver(browser);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Constants.LONG_WAIT));
        driver.get(Constants.DemoWebShop.URL);
        return driver;
    }
    public WebDriver getDriver() {
        return this.driver;
    }

    protected void cleanBrowserAndDriver() {
        String cmd = "";
        try {
            String osName = System.getProperty("os.name").toLowerCase();
            System.out.println("OS name = " + osName);

            String driverInstanceName = driver.toString().toLowerCase();
            System.out.println("Driver instance name = " + osName);

            if (driverInstanceName.contains("chrome")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq chromedriver*\"";
                } else {
                    cmd = "pkill chromedriver";
                }
            } else if (driverInstanceName.contains("internetexplorer")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq IEDriverServer*\"";
                }
            } else if (driverInstanceName.contains("firefox")) {
                if (osName.contains("windows")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq geckodriver*\"";
                } else {
                    cmd = "pkill geckodriver";
                }
            } else if (driverInstanceName.contains("edge")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq msedgedriver*\"";
                } else {
                    cmd = "pkill msedgedriver";
                }
            } else if (driverInstanceName.contains("opera")) {
                if (osName.contains("window")) {
                    cmd = "taskkill /F /FI \"IMAGENAME eq operadriver*\"";
                } else {
                    cmd = "pkill operadriver";
                }
            } else if (driverInstanceName.contains("safari")) {
                if (osName.contains("mac")) {
                    cmd = "pkill safaridriver";
                }
            }
            //Browser
            if (driver != null) {
                driver.manage().deleteAllCookies();
                driver.quit();
            }
        } catch (Exception e) {
            System.out.println("Close browser and clean excutable driver:" + e.getMessage());
        } finally {
            try {
                //Excutable driver
                Process process = Runtime.getRuntime().exec(cmd);
                process.waitFor();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void verifyTrue(boolean condition, String... message){
        Log.allure("Verify if %s is true", String.valueOf(condition));
        Assert.assertTrue(condition);
    }
    public void verifyEqual(String actualValue, String expectedValue){
        Log.allure("Verify String %s and %s",actualValue,expectedValue);
        Assert.assertEquals(actualValue,expectedValue);
    }
    public void verifyNotEqual(String value1, String value2){
        Assert.assertNotEquals(value1,value2);
    }

    @AfterMethod
    public void tearDown(){
//        Log.info("Terminate Driver");
        cleanBrowserAndDriver();
    }
}
