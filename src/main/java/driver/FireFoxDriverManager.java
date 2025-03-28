package driver;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.PropertyReader;

public class FireFoxDriverManager extends DriverManager{
    @Override
    public void createDriver() {
        FirefoxOptions options = new FirefoxOptions();
        if (Boolean.parseBoolean(PropertyReader.getProperties().getProperty("browser.headless", "false"))) {
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920x1080", "--disable-web-security");
        }
        driver = new FirefoxDriver(options);
    }
}
