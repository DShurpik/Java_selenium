package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

@Log4j2
public class ButtonsPage extends BasePage {

    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClickBtn;

    @FindBy(id = "rightClickBtn")
    private WebElement rightClickBtn;

    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement clickMeBtn;

    @FindBy(id = "doubleClickMessage")
    private WebElement doubleClickMessageField;

    @FindBy(id = "rightClickMessage")
    private WebElement rightClickMessageField;

    @FindBy(id = "dynamicClickMessage")
    private WebElement dynamicClickMessageField;

    public ButtonsPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Click on 'Double Click Me'")
    public void doubleClick() {
        log.info("Click on Double Click Me button");
        goToElement(doubleClickBtn);
        actions.doubleClick(doubleClickBtn).perform();
    }

    @Step("Get double click result")
    public String doubleClickResult() {
        log.info("Get double click result text");
        return doubleClickMessageField.getText();
    }

    @Step("Click on 'Right Click Me'")
    public void rightClick() {
        log.info("Click on Right click button");
        goToElement(rightClickBtn);
        actions.contextClick(rightClickBtn).perform();
    }

    @Step("Get right click result")
    public String rightClickResult() {
        log.info("Get right click result text");
        return rightClickMessageField.getText();
    }

    @Step("Click on 'Click Me'")
    public void clickMe() {
        log.info("Click on Click button");
        goToElement(clickMeBtn);
        clickMeBtn.click();
    }

    @Step("Get click me result")
    public String clickGetResult() {
        log.info("Get click me result text");
        return dynamicClickMessageField.getText();
    }
}
