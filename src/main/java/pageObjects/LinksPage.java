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
}
