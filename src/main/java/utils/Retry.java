package utils;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Log4j2
public class Retry implements IRetryAnalyzer {
    private final ThreadLocal<Integer> attempt = ThreadLocal.withInitial(() -> 0);
    private static final int MAX_RETRY = 3;

    @Override
    public boolean retry(ITestResult iTestResult) {
        Throwable cause = iTestResult.getThrowable();
        String testName = iTestResult.getName();
        if (cause != null) {
            log.warn("Test '{}' failed due to: {}", testName, cause.getMessage());
        }

        int currentAttempt = attempt.get();

        if (!iTestResult.isSuccess() && currentAttempt < MAX_RETRY) {
            log.warn("Test '{}' failed. Retrying attempt {}/{}",
                    testName, currentAttempt, MAX_RETRY);
            currentAttempt++;
            return true;
        }

        if (!iTestResult.isSuccess()) {
            log.error("Test '{}' failed after {} attempts",
                    iTestResult.getName(), MAX_RETRY);
        }
        attempt.remove();

        return false;
    }
}
