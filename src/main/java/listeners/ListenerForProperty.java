package listeners;

import lombok.extern.log4j.Log4j2;
import org.testng.*;
import utils.PropertyReader;

@Log4j2
public class ListenerForProperty implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {
        ITestListener.super.onTestStart(result);
        log.info("Running test: {}", result.getTestClass().getName());
        log.info("{} is started", result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info("Yes, this test was completed => {}", result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info("Ohh, this test was failed => {}", result.getName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        log.info("Starting test suite: {}", context.getName());
        String propertyName = context
                .getSuite()
                .getParameter("config") == null ? System.getProperty("config") : context.getSuite().getParameter("config");
        new PropertyReader(propertyName);
    }

    @Override
    public void onFinish(ITestContext context) {
        ITestListener.super.onFinish(context);
    }
}
