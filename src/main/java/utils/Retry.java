package utils;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Log4j2
public class Retry implements IRetryAnalyzer {
    private int attempt = 0;
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        Throwable cause = iTestResult.getThrowable();
        log.warn("Test '{}' failed due to: {}", iTestResult.getName(), cause.getMessage());
        if (!iTestResult.isSuccess() && attempt < MAX_RETRY) {
            log.warn("Test '{}' failed. Retrying attempt {}/{}",
                    iTestResult.getName(), attempt, MAX_RETRY);
            attempt++;
            return true;
        }

        if (!iTestResult.isSuccess()) {
            log.error("Test '{}' failed after {} attempts",
                    iTestResult.getName(), MAX_RETRY);
        }

        return false;
    }
}
