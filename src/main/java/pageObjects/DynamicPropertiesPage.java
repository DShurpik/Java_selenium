package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class DynamicPropertiesPage extends BasePage {

    @FindBy(xpath = "//button[@id='enableAfter']")
    private WebElement enableAfterBtn;

    @FindBy(css = ".mt-4.text-danger.btn.btn-primary")
    private WebElement colorChangeRedBtn;

    @FindBy(id = "visibleAfter")
    private WebElement visibleAfter5secBtn;

    @Step("Element is enable in 5 seconds")
    public boolean elementIsEnableIn5Sec() {
        try {
            log.info("Element is clickable");
            wait.until(ExpectedConditions.elementToBeClickable(enableAfterBtn));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    @Step("Color has changed")
    public Boolean colorHasChanged() {
        log.info("Color has changed");
        wait.until(driver -> colorChangeRedBtn.getCssValue("color").equals("rgba(220, 53, 69, 1)"));
        return true;
    }

    @Step("Button is visible after 5 seconds")
    public Boolean buttonIsVisibleIn5Sec() {
        log.info("Button is visible");
        wait.until(ExpectedConditions.visibilityOf(visibleAfter5secBtn));
        return true;
    }
}
