package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropertyReader;

@Log4j2
public class ChromeDriverCreator extends DriverCreator {

    @Override
    public WebDriver createDriver() {
        log.info("Creating ChromeDriver");
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(PropertyReader.getProperty("browser.headless", "false"))) {
            options.addArguments("--headless", "--disable-gpu");
        }
        WebDriver driver = new ChromeDriver(options);
        driver.manage().window().setSize(new Dimension(1920, 1080));
        configure(driver);
        return driver;
    }
}
