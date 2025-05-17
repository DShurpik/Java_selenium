package driver;

import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;

@Log4j2
public class DriverFactory {

    public static WebDriver createDriver(String browser) {
        log.info("Creating DriverCreator for browser: {}", browser);

        DriverCreator driverCreator = switch (browser.toUpperCase()) {
            case "CHROME" -> new ChromeDriverCreator();
            case "FIREFOX" -> new FirefoxDriverCreator();
            default -> throw new IllegalArgumentException("Unsupported browser type: " + browser);
        };
        log.info("Driver instance created for browser: {}", browser);
        return driverCreator.createDriver();
    }
}
