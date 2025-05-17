package driver;

import org.openqa.selenium.WebDriver;

import java.time.Duration;

public abstract class DriverCreator {

    public abstract WebDriver createDriver();

    protected void configure(WebDriver driver) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
    }
}
