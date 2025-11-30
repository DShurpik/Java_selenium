import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.BrowserWindowPage;

import static pageObjects.Navigation.*;

public class BrowserWindowsTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-021")
    @Story("Check that new tab is opened")
    @Test(description = "Check, that new tab has been opened")
    public void newTabTest() {
        BrowserWindowPage browserWindowPage = new BrowserWindowPage();

        browserWindowPage.open();
        browserWindowPage.navigateTo(ALERTS);
        browserWindowPage.navigateToMenu(BROWSER_WINDOWS);

        browserWindowPage.clickNewTabButton();
        browserWindowPage.switchToNewWindowOrTab();

        Assert.assertTrue(browserWindowPage.newTabInformationIsDisplayed());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-022")
    @Story("Check that new window is opened")
    @Test(description = "Check, that new window has been opened")
    public void newWindowTest() {
        BrowserWindowPage browserWindowPage = new BrowserWindowPage();
        browserWindowPage.open();
        browserWindowPage.navigateTo(ALERTS);
        browserWindowPage.navigateToMenu(BROWSER_WINDOWS);

        browserWindowPage.clickNewWindowButton();
        browserWindowPage.switchToNewWindowOrTab();

        Assert.assertTrue(browserWindowPage.newTabInformationIsDisplayed());
    }
}
