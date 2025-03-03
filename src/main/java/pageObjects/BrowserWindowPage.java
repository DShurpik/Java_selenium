package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

import java.util.ArrayList;


@Log4j2
public class BrowserWindowPage extends BasePage {

    @FindBy(id = "tabButton")
    private WebElement newTabBtn;

    @FindBy(id = "sampleHeading")
    private WebElement newPageHeader;

    @FindBy(id = "windowButton")
    private WebElement newWindowBtn;

    public BrowserWindowPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Click on new tab button")
    public void clickNewTabButton() {
        log.info("Click on {}", newTabBtn.toString());
        newTabBtn.click();
    }

    @Step("New tab header is visible")
    public boolean newTabHeaderIsVisible() {
        log.info("Header is visible");
        return newPageHeader.isDisplayed();
    }

    @Step("Switch to new tab")
    public void switchToNewTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
        System.out.println(tabs + " TABS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        log.info("Switched to new tab: " + tabs.get(1));
    }

    @Step("Click on new window button")
    public void clickNewWindowButton() {
        log.info("Click on {}", newWindowBtn.toString());
        newWindowBtn.click();
    }
}
