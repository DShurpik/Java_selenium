package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class FramesPage extends BasePage {

    @FindBy(id = "frame1")
    private WebElement frame1;

    @FindBy(id = "frame2")
    private WebElement frame2;

    @FindBy(xpath = "//h2")
    private WebElement frameHeader;

    @Step("Switch to Frame 1")
    public void switchToFrame1() {
        log.info("Switch to Frame 1");
        driver.switchTo().frame(frame1);
    }

    @Step("Switch to Frame 2")
    public void switchToFrame2() {
        log.info("Switch to Frame 2");
        driver.switchTo().frame(frame2);
    }

    @Step("Get Frame Header Text")
    public String getFrameHeaderText() {
        return getText(frameHeader);
    }
}
