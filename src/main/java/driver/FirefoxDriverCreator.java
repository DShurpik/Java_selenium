package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

@Log4j2
public class FirefoxDriverCreator extends DriverCreator {

    @Override
    public WebDriver createDriver() {
        log.info("Creating FirefoxDriver");
        FirefoxOptions options = new FirefoxOptions();
        if (isHeadless()) {
            options.addArguments("--headless", "--disable-gpu");
        }

        WebDriver driver = new FirefoxDriver(options);
        configure(driver);
        return driver;
    }
}
