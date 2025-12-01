package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static utils.PropertyReader.getInstance;

@Log4j2
public class AlertsPage extends BasePage {

    @FindBy(id = "alertButton")
    private WebElement alertBtn;

    @FindBy(id = "timerAlertButton")
    private WebElement timerAlertBtn;

    @FindBy(id = "confirmButton")
    private WebElement confirmBtn;

    @FindBy(id = "confirmResult")
    private WebElement confirmResult;

    @FindBy(id = "promtButton")
    private WebElement promtBtn;

    @FindBy(id = "promptResult")
    private WebElement promptResult;

    @Step("Click on alert button")
    public void clickOnAlertBtn() {
        click(alertBtn);
    }

    @Step("Click on timer alert button")
    public void clickOnTimerAlertBtn() {
        click(timerAlertBtn);
    }

    @Step("Click on confirm button")
    public void clickOnConfirmBtn() {
        click(confirmBtn);
    }

    @Step("Get confirm result")
    public String getConfirmResult() {
        return getText(confirmResult);
    }

    @Step("Click on promt button")
    public void clickOnPromtBtn() {
        log.info("Click on promt button");
        promtBtn.click();
    }

    @Step("Get prompt result")
    public String getPromptResult() {
        log.info("Get prompt result");
        return promptResult.getText();
    }

    @Step("Send text to alert")
    public void sendTextToAlert(String name) {
        log.info("Send text {} to alert", name);
        driver.switchTo().alert().sendKeys(name);
    }

    @Step("Check that alert is present")
    public boolean isAlertPresent() {
        try {
            wait.until(ExpectedConditions.alertIsPresent());
            log.info("Alert is present");
            return true;
        } catch (NoAlertPresentException e) {
            log.info("Alert isn't present");
            return false;
        }
    }

    @Step("Accept alert")
    public void acceptAlert() {
        driver.switchTo().alert().accept();
        log.info("Accept alert");
    }

    @Step("Wait for alert to be present")
    public void waitForAlert() {
        wait.until(ExpectedConditions.alertIsPresent());
        log.info("Alert is present and switched to it");
    }
}
