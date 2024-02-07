package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void doubleClick() {
        goToElement(doubleClickBtn);
        actions.doubleClick(doubleClickBtn).perform();
    }

    public String doubleClickResult() {
        return doubleClickMessageField.getText();
    }

    public void rightClick() {
        goToElement(rightClickBtn);
        actions.contextClick(rightClickBtn).perform();
    }

    public String rightClickResult() {
        return rightClickMessageField.getText();
    }

    public void clickMe() {
        goToElement(clickMeBtn);
        clickMeBtn.click();
    }

    public String clickGetResult() {
        return dynamicClickMessageField.getText();
    }
}
