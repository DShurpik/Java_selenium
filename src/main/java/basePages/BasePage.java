package basePages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pageObjects.Navigation;

import static driver.SingletonDriver.getDriver;

public abstract class BasePage {

    protected WebDriver driver;

    public BasePage() {
        driver = getDriver();
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
}
