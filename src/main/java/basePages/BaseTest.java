package basePages;

import configLoader.ConfigProvider;
import driver.DriverManager;
import listeners.InvokedMethodListener;
import listeners.ListenerForProperty;
import lombok.extern.log4j.Log4j2;
import org.testng.annotations.*;
import utils.AllureEnvironmentWriter;

@Listeners({ListenerForProperty.class, InvokedMethodListener.class})
@Log4j2

public abstract class BaseTest {

    @BeforeMethod
    public void setUp() {
        log.info("Initializing the environment: {}, config file: {}",
                ConfigProvider.getProfile(), ConfigProvider.getConfigFileName());

        String browser = System.getProperty("browser",
                ConfigProvider.getString("web.browser.name", "chrome")).toUpperCase();

        DriverManager.initDriver(browser);
        log.debug("New web {} driver is set up", browser);
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.closeDriver();
    }

    @AfterSuite(alwaysRun = true)
    public void writeEnvironment() {
        AllureEnvironmentWriter.writeEnvironment();
    }
}