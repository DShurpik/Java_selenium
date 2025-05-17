package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.PropertyReader;

@Log4j2
public class FirefoxDriverCreator extends DriverCreator {

    @Override
    public WebDriver createDriver() {
        log.info("Creating FirefoxDriver");
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(PropertyReader.getProperty("browser.headless", "false"))) {
            options.addArguments("--headless", "--disable-gpu");
        }
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        configure(driver);
        return driver;
    }
}
