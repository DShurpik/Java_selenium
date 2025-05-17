package basePages;

import driver.DriverManager;
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

import static utils.PropertyReader.getProperties;

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
        properties = getProperties();
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

    protected void sendText(String string, WebElement webElement) {
        log.info("Enter {} into {} field", string, webElement);
        webElement.clear();
        webElement.sendKeys(string);
    }

    public void click(WebElement webElement) {
        try {
            log.info("Click on element" + webElement);
            webElement.click();
        } catch (ElementClickInterceptedException e){
            wait.until(ExpectedConditions.elementToBeClickable(webElement));
            webElement.click();
        }
    }
}
