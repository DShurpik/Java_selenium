package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@Log4j2
public class ChromeDriverCreator extends DriverCreator {

    @Override
    public WebDriver createDriver() {
        log.info("Creating ChromeDriver");
        ChromeOptions options = new ChromeOptions();
        if (isHeadless()) {
            options.addArguments("--headless", "--disable-gpu");
        }

        WebDriver driver = new ChromeDriver(options);
        configure(driver);
        return driver;
    }
}
