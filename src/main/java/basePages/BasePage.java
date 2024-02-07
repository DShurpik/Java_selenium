package basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import pageObjects.Navigation;

import static driver.SingletonDriver.getDriver;

public abstract class BasePage {

    protected WebDriver driver;
    protected Actions actions;

    public BasePage() {
        driver = getDriver();
        actions = new Actions(driver);
    }

    public void navigateTo(Navigation menuName) {
        driver.findElement(By.xpath("//div[h5=" + menuName.getItem() + "]")).click();

    }

    public void navigateToMenu(Navigation menuItem) {
        driver.findElement(By.xpath("//span[text()=" + menuItem.getItem() + "]")).click();
    }

    public void open(String url) {
        driver.get(url);
    }

    public void goToElement(WebElement element) {
        // Используем JavascriptExecutor для выполнения скрипта
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, arguments[0].getBoundingClientRect().top - 300);", element);
    }
}
