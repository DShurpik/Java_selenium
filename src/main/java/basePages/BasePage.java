package basePages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageObjects.Navigation;

import java.time.Duration;

import static driver.SingletonDriver.getDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected Actions actions;
    protected WebDriverWait wait;

    public BasePage() {
        driver = getDriver();
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Navigate to {0} menu")
    public void navigateTo(Navigation menuName) {
        driver.findElement(By.xpath("//div[h5=" + menuName.getItem() + "]")).click();

    }

    @Step("Navigate to {0} menu field")
    public void navigateToMenu(Navigation menuItem) {
        driver.findElement(By.xpath("//span[text()=" + menuItem.getItem() + "]")).click();
    }

    @Step("Open website with {0} address")
    public void open(String url) {
        driver.get(url);
    }

    public void goToElement(WebElement element) {
        // Используем JavascriptExecutor для выполнения скрипта
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top - 300);", element);
    }
}
