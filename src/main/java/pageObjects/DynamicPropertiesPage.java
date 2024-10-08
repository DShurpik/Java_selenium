package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DynamicPropertiesPage extends BasePage {

    @FindBy(xpath = "//button[@id='enableAfter']")
    private WebElement enableAfterBtn;

    @FindBy(css = ".mt-4.text-danger.btn.btn-primary")
    private WebElement colorChangeRedBtn;

    @FindBy(id = "visibleAfter")
    private WebElement visibleAfter5secBtn;

    public DynamicPropertiesPage() {
        PageFactory.initElements(driver, this);
    }

    public boolean elementIsEnableIn5Sec() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(enableAfterBtn));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public Boolean colorHasChanged() {
        wait.until(driver -> colorChangeRedBtn.getCssValue("color").equals("rgba(220, 53, 69, 1)"));
        return true;
    }

    public Boolean buttonIsVisibleIn5Sec() {
        wait.until(ExpectedConditions.visibilityOf(visibleAfter5secBtn));
        return true;
    }
}
