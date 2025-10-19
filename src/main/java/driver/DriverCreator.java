package driver;

import configLoader.ConfigLoader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class DriverCreator {

    ConfigLoader configLoader = new ConfigLoader();
    public abstract WebDriver createDriver();

    protected void configure(WebDriver driver) {
        int width = Integer.parseInt(configLoader.getProperty("browser.window.width"));
        int height = Integer.parseInt(configLoader.getProperty("browser.window.height"));
        driver.manage().window().setSize(new Dimension(width, height));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(configLoader.getProperty("browser.timeout.implicit"))
        ));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Long.parseLong(configLoader.getProperty("browser.timeout.pageLoad"))
        ));

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(
                Long.parseLong(configLoader.getProperty("browser.timeout.script"))
        ));
    }

    protected boolean isHeadless() {
        return Boolean.parseBoolean(configLoader.getProperty("browser.headless", "false"));
    }
}
