package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinksPage extends BasePage {

    @FindBy(xpath = "//img[@src='/images/ThreadQa.jpg']")
    private WebElement brokenImage;

    @FindBy(xpath = "//a[text()='Click Here for Valid Link']")
    private WebElement validLink;

    @FindBy(xpath = "//img[@alt='Google']")
    private WebElement googleImg;

    @FindBy(xpath = "//a[text()='Click Here for Broken Link']")
    private WebElement brokenLink;

    @FindBy(xpath = "//p[contains(text(), 'This page returned a 500 status code.')]")
    private WebElement statusCodeElement;

    public BrokenLinksPage() {
        PageFactory.initElements(driver, this);
    }

    public int clientHeight() {
        return Integer.parseInt(brokenImage.getAttribute("naturalHeight"));
    }

    public int clientWidth() {
        return Integer.parseInt(brokenImage.getAttribute("naturalWidth"));
    }

    public void validLinkClick() {
        validLink.click();
    }

    public boolean googleIsVisible() {
        return googleImg.isDisplayed();
    }

    public void clickBrokenLink() {
        brokenLink.click();
    }

    public boolean statusCodeIsDisplayed() {
        return statusCodeElement.isDisplayed();
    }
}
