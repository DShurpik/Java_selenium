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
}
