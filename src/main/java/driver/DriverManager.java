package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Log4j2
public abstract class DriverManager {
    protected static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public abstract void createDriver();

    {
        if(driver.get() == null){
            createDriver();
            Capabilities capabilities = ((HasCapabilities) driver.get()).getCapabilities();
            log.info("WebDriver is created -> Browser: {} | Version: {} | OS: {} | ID: {}",
                    capabilities.getBrowserName(),
                    capabilities.getBrowserVersion(),
                    System.getProperty("os.name"),
                    driver.get());
        }
    }

    public static WebDriver getWebDriver() {
        driver.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get().manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.get().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        return driver.get();
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.get().close();
            driver.get().quit();
            driver.remove();
        }
    }
}
