package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

@Log4j2
public abstract class DriverManager {
    protected static WebDriver driver;

    public abstract void createDriver();

    {
        if(driver == null){
            createDriver();
            Capabilities capabilities = ((HasCapabilities) driver).getCapabilities();
            log.info("WebDriver is created -> Browser: {} | Version: {} | OS: {} | ID: {}",
                    capabilities.getBrowserName(),
                    capabilities.getBrowserVersion(),
                    System.getProperty("os.name"),
                    driver);
        }
    }

    public static WebDriver getWebDriver() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));

        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
