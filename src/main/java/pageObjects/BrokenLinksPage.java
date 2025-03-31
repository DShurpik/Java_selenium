package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Log4j2
public class BrokenLinksPage extends BasePage {

    @FindBy(xpath = "//img[@src='/images/ThreadQa.jpg']")
    private WebElement brokenImage;

    @FindBy(xpath = "//a[text()='Click Here for Valid Link']")
    private WebElement validLink;

    @FindBy(xpath = "//a[@aria-label='Приложения Google']")
    private WebElement googleImg;

    @FindBy(xpath = "//a[text()='Click Here for Broken Link']")
    private WebElement brokenLink;

    @FindBy(xpath = "//p[contains(text(), 'This page returned a 500 status code.')]")
    private WebElement statusCodeElement;

    @Step("Get broken image height")
    public int clientHeight() {
        log.info("Get broken image height");
        return Integer.parseInt(brokenImage.getAttribute("naturalHeight"));
    }

    @Step("Get broken image width")
    public int clientWidth() {
        log.info("Get broken image width");
        return Integer.parseInt(brokenImage.getAttribute("naturalWidth"));
    }

    @Step("Click on Valid Link")
    public void validLinkClick() {
        log.info("Click on Valid Link");
        validLink.click();
    }

    @Step("Open google page")
    public String googleTitle() {
        log.info("Google is opened");
        return driver.getTitle();
    }

    @Step("Click on Broken Link")
    public void clickBrokenLink() {
        log.info("Click on Broken Link");
        brokenLink.click();
    }

    @Step("Page with status code 500 is displayed")
    public boolean statusCodeIsDisplayed() {
        log.info("Page with status code 500 is opened");
        return statusCodeElement.isDisplayed();
    }
}
