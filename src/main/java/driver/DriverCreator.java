package driver;

import configLoader.ConfigLoader;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class DriverCreator {

    public abstract WebDriver createDriver();

    protected void configure(WebDriver driver) {
        int width = Integer.parseInt(ConfigLoader.getProperty("browser.window.width"));
        int height = Integer.parseInt(ConfigLoader.getProperty("browser.window.height"));
        driver.manage().window().setSize(new Dimension(width, height));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(ConfigLoader.getProperty("browser.timeout.implicit"))
        ));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Long.parseLong(ConfigLoader.getProperty("browser.timeout.pageLoad"))
        ));

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(
                Long.parseLong(ConfigLoader.getProperty("browser.timeout.script"))
        ));
    }

    protected boolean isHeadless() {
        return Boolean.parseBoolean(ConfigLoader.getProperty("browser.headless", "false"));
    }
}
