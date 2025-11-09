package listeners;

import driver.DriverManager;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

public class InvokedMethodListener implements IInvokedMethodListener {

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (!testResult.isSuccess() && DriverManager.getDriver() != null) {
            byte[] screenshot = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
            String testName = testResult.getMethod().getMethodName();
            saveScreenshots(screenshot, testName);
        }
    }

    @Attachment(value = "{testName} - Screenshot on failure", type = "image/png")
    public byte[] saveScreenshots(byte[] bytes, String testName) {
        return bytes;
    }
}