package basePages;

import listeners.ListenerForProperty;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Properties;

import static driver.SingletonDriver.closeDriver;
import static utils.PropertyReader.getProperties;

@Listeners(ListenerForProperty.class)
@Log4j2

public abstract class BaseTest {
    protected Properties properties;

    @BeforeMethod
    public void setUp() {
        log.debug("New web driver is set up");
        properties = getProperties();
    }

    @AfterMethod
    public void closeWebDriver() {
        log.info("Web driver is closed");
        closeDriver();
    }
}