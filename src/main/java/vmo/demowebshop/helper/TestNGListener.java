package vmo.demowebshop.helper;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestNGListener implements ITestListener {
    @Attachment
    public static byte[] saveScreenShot(WebDriver driver){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
    @Override
    public void onTestStart(ITestResult result) {
        Log.info("------------ Test STARTED ------------");;
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("--------------- " + result.getName() + " SUCCESS -----------------");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("--------------- " + result.getName() + " FAILED -----------------");
    }

    @Override
    public void onStart(ITestContext context) {
        Log.info("------------ Test is starting ------------");
    }

    @Override
    public void onFinish(ITestContext context) {
        Log.info("------------ Test FINISHED ------------");
    }

}
