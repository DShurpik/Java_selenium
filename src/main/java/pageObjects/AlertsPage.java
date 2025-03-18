package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class AlertsPage extends BasePage {

    @FindBy(id = "alertButton")
    private WebElement alertBtn;

    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertBtn;

    @Step("Click on alert button")
    public void clickOnAlertBtn() {
        log.info("Click on alert button");
        alertBtn.click();
    }

    @Step("Click on timer alert button")
    public void clickOnTimerAlertBtn() {
        log.info("Click on timer alert button");
        timerAlertBtn.click();
    }

    @Step("Check that alert is present")
    public boolean isAlertPresent() {
        log.info("Alert is present");
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    @Step("Accept alert")
    public void acceptAlert() {
        log.info("Accept alert");
        driver.switchTo().alert().accept();
    }
}
