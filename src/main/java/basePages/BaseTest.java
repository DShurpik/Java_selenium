package basePages;

import driver.DriverManager;
import driver.DriverManagerFactory;
import listeners.InvokedMethodListener;
import listeners.ListenerForProperty;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.PropertyReader;

import static driver.DriverManager.closeDriver;
import static driver.DriverManager.getWebDriver;

@Listeners({ListenerForProperty.class, InvokedMethodListener.class})
@Log4j2

public abstract class BaseTest {

    @Getter
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        // Получаем браузер с учетом -Dbrowser= и значения из properties
        String browser = PropertyReader.getProperty("browser", "chrome").toUpperCase();

        // Передаем правильное значение в фабрику DriverManagerFactory
        DriverManager driverManager = DriverManagerFactory.getManager(browser);
        driver = getWebDriver();
        log.debug("New web driver is set up");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            closeDriver();
        }
        log.info("Web driver is closed");
    }

}