package basePages;

import org.testng.annotations.AfterMethod;

import static driver.SingletonDriver.closeDriver;

public abstract class BaseTest {

    @AfterMethod
    public void close() {
        closeDriver();
    }
}
