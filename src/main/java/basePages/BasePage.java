package basePages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Navigation;

import java.time.Duration;
import java.util.Properties;

import static driver.SingletonDriver.getWebDriver;
import static utils.PropertyReader.getProperties;

@Log4j2
public abstract class BasePage {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected Properties properties;

    public BasePage() {
        driver = getWebDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        properties = getProperties();
    }

    @Step("Navigate to {0} menu")
    public void navigateTo(Navigation menuName) {
        log.info("Navigate to -> {}", menuName.getItem());
        driver.findElement(By.xpath("//div[h5=" + menuName.getItem() + "]")).click();
    }

    @Step("Navigate to {0} menu field")
    public void navigateToMenu(Navigation menuItem) {
        log.info("Navigate to -> {}", menuItem.getItem());
        driver.findElement(By.xpath("//span[text()=" + menuItem.getItem() + "]")).click();
    }

    @Step("Open website with {0} address")
    public void open(String url) {
        log.info("Open page: {}", url);
        driver.get(url);
    }

    public void goToElement(WebElement element) {
        // Используем JavascriptExecutor для выполнения скрипта
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top - 300);", element);
    }
}
