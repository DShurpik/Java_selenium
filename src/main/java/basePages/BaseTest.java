package basePages;

import listeners.ListenerForProperty;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;

import static driver.SingletonDriver.closeDriver;

@Listeners(ListenerForProperty.class)

public abstract class BaseTest {

    @AfterMethod
    public void close() {
        closeDriver();
    }
}
