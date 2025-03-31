package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropertyReader;

public class ChromeDriverManager extends DriverManager {
    @Override
    public void createDriver() {
        WebDriver webDriver;
        ChromeOptions options = new ChromeOptions();
        if (Boolean.parseBoolean(PropertyReader.getProperties().getProperty("browser.headless", "false"))) {
            options.addArguments("--headless", "--disable-gpu", "--window-size=1920x1080", "--disable-web-security", "--remote-allow-origins=*");
        }
        webDriver = new ChromeDriver(options);
        driver.set(webDriver);
    }
}
