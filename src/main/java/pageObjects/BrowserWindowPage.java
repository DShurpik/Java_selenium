package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;


@Log4j2
public class BrowserWindowPage extends BasePage {

    @FindBy(xpath = "//button[text()='New Tab']")
    private WebElement newTabBtn;

    @FindBy(xpath = "//h2[text()='This page could not be found.']")
    private WebElement newPageInfo;

    @FindBy(xpath = "//button[text()='New Window']")
    private WebElement newWindowBtn;

    @FindBy(xpath = "//button[text()='New Window Message']")
    private WebElement newWindowMessageBtn;

    @Step("Click on new tab button")
    public void clickNewTabButton() {
        click(newTabBtn);
    }

    @Step("New tab information is visible")
    public boolean newTabInformationIsDisplayed() {
        return isElementDisplayed(newPageInfo);
    }

    @Step("Click on new window button")
    public void clickNewWindowButton() {
        click(newWindowBtn);
    }

    @Step("Click on new window message button")
    public void clickNewWindowMessageButton() {
       click(newWindowMessageBtn);
    }
}
