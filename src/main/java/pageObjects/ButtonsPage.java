package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ButtonsPage extends BasePage {

    @FindBy(xpath = "//button[@class='btn btn-primary' and text()='Double Click Me']")
    private WebElement doubleClickBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary' and text()='Right Click Me']")
    private WebElement rightClickBtn;

    @FindBy(xpath = "//button[@class='btn btn-primary' and text()='Click Me']")
    private WebElement clickMeBtn;

    @FindBy(id = "doubleClickMessage")
    private WebElement doubleClickMessageField;

    @FindBy(id = "rightClickMessage")
    private WebElement rightClickMessageField;

    @FindBy(id = "dynamicClickMessage")
    private WebElement dynamicClickMessageField;

    @Step("Get double click result")
    public String doubleClickResult() {
        log.info("Get double click result text");
        return getText(doubleClickMessageField);
    }

    @Step("Double click on 'Double Click Me' button")
    public void doubleClick() {
        executeActionWithRetry(doubleClickBtn, "Double Click Me", "doubleClick");
    }

    @Step("Right click on 'Right Click Me' button")
    public void rightClick() {
        executeActionWithRetry(rightClickBtn, "Right Click Me", "rightClick");
    }


    @Step("Click on {0} with {1} action")
    private void executeActionWithRetry(WebElement webElement, String actionName, String actionType) {
        log.info("Performing {} on {}", actionType, actionName);
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(webElement));
                if (actionType.equals("doubleClick")) {
                    actions.doubleClick(webElement).perform();
                } else if (actionType.equals("rightClick")) {
                    actions.contextClick(webElement).perform();
                }
                log.info("{} performed successfully", actionType);
                return;
            } catch (StaleElementReferenceException e) {
                log.warn("StaleElementReferenceException caught during {}, retrying... Attempt: {}", actionType, attempts + 1);
                attempts++;
                if (attempts == 3) {
                    log.error("Failed to perform {} after 3 attempts", actionType);
                    throw e;
                }
            } catch (Exception e) {
                log.error("Error performing {}: {}", actionType, e.getMessage());
                throw e;
            }
        }
    }


    @Step("Get right click result")
    public String rightClickResult() {
        log.info("Get right click result text");
        return getText(rightClickMessageField);
    }

    @Step("Click on 'Click Me'")
    public void clickMe() {
        log.info("Click on Click button");
        click(clickMeBtn);
    }

    @Step("Get click me result")
    public String clickGetResult() {
        log.info("Get click me result text");
        return getText(dynamicClickMessageField);
    }
}
