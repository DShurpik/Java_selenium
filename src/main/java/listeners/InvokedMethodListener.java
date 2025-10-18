package listeners;

import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestContext;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult, ITestContext context) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null && testResult.getThrowable() != null) {
            if (((org.openqa.selenium.remote.RemoteWebDriver) driver).getSessionId() != null) {
                byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
                saveScreenshots(screenshot);
            }
        }
    }

    /**
    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!testResult.isSuccess() && DriverManager.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            saveScreenshots(screenshot);
        }
    }
     **/

    @Attachment(value = "Screenshots", type = "image/png")
    private byte[] saveScreenshots(byte[] bytes) {
        return bytes;
    }
}