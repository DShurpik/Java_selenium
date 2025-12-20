package driver;

import configLoader.ConfigProvider;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class DriverCreator {

    public abstract WebDriver createDriver();

    protected void configure(WebDriver driver) {
        int width = ConfigProvider.readConfig().getInt("web.browser.window.width");
        int height = ConfigProvider.readConfig().getInt("web.browser.window.height");
        driver.manage().window().setSize(new Dimension(width, height));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                ConfigProvider.readConfig().getInt("web.browser.timeout.implicit"))
        );

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                ConfigProvider.readConfig().getInt("web.browser.timeout.pageLoad"))
        );

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(
                ConfigProvider.readConfig().getInt("web.browser.timeout.script"))
        );
    }

    protected boolean isHeadless() {
        return ConfigProvider.readConfig().getBoolean("web.browser.headless");
    }
}
