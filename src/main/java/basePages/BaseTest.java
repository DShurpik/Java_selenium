package basePages;

import driver.DriverManager;
import listeners.InvokedMethodListener;
import listeners.ListenerForProperty;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import utils.PropertyReader;

@Listeners({ListenerForProperty.class, InvokedMethodListener.class})
@Log4j2

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverManager.initDriver(PropertyReader.getProperty("browser", "chrome").toUpperCase());
        log.debug("New web driver is set up");
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.closeDriver();
    }

}