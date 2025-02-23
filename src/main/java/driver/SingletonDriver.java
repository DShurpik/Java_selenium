package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.PropertyReader;

import java.time.Duration;

public class SingletonDriver {
    private static WebDriver driver;

    private SingletonDriver() {}

    public static WebDriver getWebDriver() {
        if (driver == null) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");

            if (Boolean.parseBoolean(PropertyReader.getProperties().getProperty("browser.headless", "false"))) {
                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                options.addArguments("--window-size=1920x1080");
                options.addArguments("--disable-web-security");
            }

            driver = new ChromeDriver(options);
            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
            driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
            driver.quit();
            driver = null;
        }
    }

}
