package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver(String browser) {
        log.info("Initializing WebDriver for browser: {}", browser);
        WebDriver webDriver = DriverFactory.createDriver(browser);
        driver.set(webDriver);

        Capabilities capabilities = ((HasCapabilities) driver.get()).getCapabilities();
        log.info("WebDriver created -> Browser: {} | Version: {} | OS: {} | ID: {}",
                capabilities.getBrowserName(),
                capabilities.getBrowserVersion(),
                System.getProperty("os.name"),
                driver.get());
    }

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void closeDriver() {
        if (driver.get() != null) {
            log.info("Closing WebDriver: {}", driver.get());
            driver.get().quit();
            driver.remove();
        }
    }
}
