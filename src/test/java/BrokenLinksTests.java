import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BrokenLinksPage;

import static pageObjects.Navigation.*;

public class BrokenLinksTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-016")
    @Story("Check that Broken image has 0x0 size")
    @Test(description = "Checking a broken image on the page")
    public void brokenImageTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open();

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        Assert.assertEquals(brokenLinksPage.clientHeight(), 0);
        Assert.assertEquals(brokenLinksPage.clientWidth(), 0);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-017")
    @Story("Check that valid link refers to google.com")
    @Test(description = "Checking valid link")
    public void validLinkTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open();

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.validLinkClick();
        Assert.assertEquals(brokenLinksPage.googleTitle(), "Google");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-018")
    @Story("Check that broken link refers to a page with 500 status code")
    @Test(description = "Checking broken link by other page element")
    public void brokenLinkTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open();

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.clickBrokenLink();

        Assert.assertTrue(brokenLinksPage.statusCodeIsDisplayed());
    }

}
