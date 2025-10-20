package basePages;

import configLoader.ConfigProvider;
import driver.DriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Navigation;

import java.time.Duration;
import java.util.Properties;

import static utils.PropertyReader.getInstance;

@Log4j2
public abstract class BasePage {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;
    protected Properties properties;

    public BasePage() {
        driver = DriverManager.getDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        properties = getInstance().getProperties();
        PageFactory.initElements(driver, this);
    }

    @Step("Navigate to {0} menu")
    public void navigateTo(Navigation menuName) {
        log.info("Navigate to -> {}", menuName.getItem());
        actions.moveToElement(driver.findElement(By.xpath("//div[h5=" + menuName.getItem() + "]")));
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//div[h5=" + menuName.getItem() + "]")))).click();
    }

    @Step("Navigate to {0} menu field")
    public void navigateToMenu(Navigation menuItem) {
        log.info("Navigate to -> {}", menuItem.getItem());
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath("//span[contains(text(), " + menuItem.getItem() + ")]")))).click();
    }

    @Step("Open website with {0} address")
    public void open(String url) {
        log.info("Open page: {}", url);
        driver.get(url);
    }

    @Step("Open website")
    public void open() {
        String url = ConfigProvider.readConfig().getString("url.web");
        log.info("Open page: {}", url);
        Allure.step("Opening URL: " + url);
        driver.get(url);
    }

    protected void sendText(String string, WebElement webElement) {
        log.info("Enter {} into {} field", string, webElement);
        webElement.clear();
        webElement.sendKeys(string);
    }

    protected void click(Object locator) {
        int attempts = 0;
        int maxAttempts = 3;
        while (attempts < maxAttempts) {
            try {
                log.info("Attempt #{} to click on element: {}", attempts + 1, locator.toString());
                WebElement element;
                if (locator instanceof By) {
                    element = wait.until(ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable((By) locator)));
                } else if (locator instanceof WebElement) {
                    element = wait.until(ExpectedConditions.refreshed(
                            ExpectedConditions.elementToBeClickable((WebElement) locator)));
                } else {
                    throw new IllegalArgumentException("Locator must be of type By or WebElement, but was: " + locator.getClass().getSimpleName());
                }
                element.click();
                return;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                log.warn("Click attempt #{} failed due to: {}", attempts + 1, e.getClass().getSimpleName(), e);
            } catch (Exception e) {
                log.warn("Unexpected exception during click attempt #{}", attempts + 1, e);
                throw e;
            }
            attempts++;
        }

        log.error("Failed to click on element after {} attempts: {}", attempts, locator.toString());
    }

    protected String getText(WebElement webElement) {
        log.info("Get text from element: {}", webElement);
        wait.until(ExpectedConditions.visibilityOf(webElement));
        return webElement.getText();
    }

    protected Boolean isElementDisplayed(By by) {
        log.info("Check if element is displayed: {}", by);
        try {
            return driver.findElement(by).isDisplayed();
        } catch (NoSuchElementException e) {
            log.error("Element not found: {}", driver.findElement(by), e);
            return false;
        }
    }
}
