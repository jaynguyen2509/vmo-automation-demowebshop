package vmo.demowebshop.factoryEnviroment;

import org.openqa.selenium.WebDriver;
import vmo.demowebshop.factoryBrowser.BrowserNotSupportedException;
import vmo.demowebshop.factoryBrowser.ChromeDriverManager;
import vmo.demowebshop.factoryBrowser.FireFoxDriverManager;

public class LocalFactory {
    private WebDriver driver;
    public WebDriver createDriver(String browser){
        browser = browser.toUpperCase();
        switch (browser){
            case "CHROME":
                driver  = new ChromeDriverManager().getBrowserDriver();
                break;
            case "FIREFOX":
                driver  = new FireFoxDriverManager().getBrowserDriver();
                break;
            default:
                throw new BrowserNotSupportedException(browser);
        }
        return driver;
    }
}
