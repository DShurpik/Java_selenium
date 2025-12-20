package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class ModalDialogsPage extends BasePage {

    @FindBy(css = ".mt-2.mr-4")
    private WebElement smallModalButton;

    @FindBy(xpath = "//button[text()='Large modal']")
    private WebElement largeModalButton;

    @FindBy(className = "modal-title")
    private WebElement modalTitle;

    @FindBy(xpath = "//button[text()='Close']")
    private WebElement closeModalButton;

    @Step("Open small modal dialog")
    public void openSmallModal() {
        click(smallModalButton);
    }

    @Step("Open large modal dialog")
    public void openLargeModal() {
        click(largeModalButton);
    }

    @Step("Get modal title text")
    public String getModalTitleText() {
        return getText(modalTitle);
    }

    @Step("Close modal dialog")
    public void closeModal() {
        click(closeModalButton);
    }

    @Step("Check if modal is displayed")
    public boolean isModalDisplayed() {
        log.info("Checking if modal is displayed");
        wait.until(ExpectedConditions.invisibilityOf(modalTitle));
        return true;
    }
}
