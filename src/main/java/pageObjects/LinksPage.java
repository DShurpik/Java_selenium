package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinksPage extends BasePage {

    @FindBy(id = "created")
    private WebElement createdLink;

    @FindBy(id = "no-content")
    private WebElement noContentLink;

    @FindBy(id = "moved")
    private WebElement movedLink;

    @FindBy(id = "bad-request")
    private WebElement badRequestLink;

    @FindBy(id = "unauthorized")
    private WebElement unauthorizedLink;

    @FindBy(id = "forbidden")
    private WebElement forbiddenLink;

    @FindBy(id = "invalid-url")
    private WebElement notFoundLink;

    public LinksPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickCreatedLink() {
        createdLink.click();
    }

    public void clickNoContentLink() {
        noContentLink.click();
    }

    public void clickMovedLink() {
        movedLink.click();
    }

    public void clickBadRequestLink() {
        badRequestLink.click();
    }

    public void clickUnauthorizedLink() {
        unauthorizedLink.click();
    }

    public void clickForbiddenLink() {
        forbiddenLink.click();
    }

    public void clickNotFound() {
        notFoundLink.click();
    }
}
