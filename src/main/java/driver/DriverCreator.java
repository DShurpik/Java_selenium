package driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

import java.time.Duration;

public abstract class DriverCreator {

    public abstract WebDriver createDriver();

    protected void configure(WebDriver driver) {
        int width = Integer.parseInt(PropertyReader.getProperty("browser.window.width", "1920"));
        int height = Integer.parseInt(PropertyReader.getProperty("browser.window.height", "1080"));
        driver.manage().window().setSize(new Dimension(width, height));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(
                Long.parseLong(PropertyReader.getProperty("browser.timeout.implicit", "10"))
        ));

        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(
                Long.parseLong(PropertyReader.getProperty("browser.timeout.pageLoad", "20"))
        ));

        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(
                Long.parseLong(PropertyReader.getProperty("browser.timeout.script", "10"))
        ));
    }

    protected boolean isHeadless() {
        return Boolean.parseBoolean(PropertyReader.getProperty("browser.headless", "false"));
    }
}
