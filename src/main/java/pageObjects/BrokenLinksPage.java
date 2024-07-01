package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class BrokenLinksPage extends BasePage {

    @FindBy(xpath = "//img[@src='/images/ThreadQa.jpg']")
    private WebElement brokenImage;

    public BrokenLinksPage() {
        PageFactory.initElements(driver, this);
    }

    public int clientHeight() {
        return Integer.parseInt(brokenImage.getAttribute("naturalHeight"));
    }

    public int clientWidth() {
        return Integer.parseInt(brokenImage.getAttribute("naturalWidth"));
    }
}
