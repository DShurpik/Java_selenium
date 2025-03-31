package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

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
        log.info("Click on alert button");
        alertBtn.click();
    }

    @Step("Click on timer alert button")
    public void clickOnTimerAlertBtn() {
        log.info("Click on timer alert button");
        timerAlertBtn.click();
    }

    @Step("Click on confirm button")
    public void clickOnConfirmBtn() {
        log.info("Click on confirm button");
        confirmBtn.click();
    }

    @Step("Get confirm result")
    public String getConfirmResult() {
        log.info("Get confirm result");
        return confirmResult.getText();
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
    public void sendTextToAlert(String text) {
        log.info("Send text to alert");
        driver.switchTo().alert().sendKeys(text);
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
