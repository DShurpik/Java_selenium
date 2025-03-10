import basePages.BaseTest;
import io.qameta.allure.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AlertsPage;
import pageObjects.BrowserWindowPage;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getProperties;

public class AlertTests extends BaseTest {
    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-26")
    @Story("Check new tab")
    @Test(description = "Check, that new tab has been opened")
    public void newBrowserTabTest() {
        BrowserWindowPage browserWindowPage = new BrowserWindowPage();
        browserWindowPage.open(getProperties().getProperty("url"));
        browserWindowPage.navigateTo(ALERTS);
        browserWindowPage.navigateToMenu(BROWSER_WINDOWS);

        browserWindowPage.clickNewTabButton();
        browserWindowPage.switchToNewTab();
        Assert.assertTrue(browserWindowPage.newTabHeaderIsVisible());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-27")
    @Story("Check new window")
    @Test(description = "Check, that new window has been opened")
    public void newBrowserWindowTest() {
        BrowserWindowPage browserWindowPage = new BrowserWindowPage();
        browserWindowPage.open(getProperties().getProperty("url"));
        browserWindowPage.navigateTo(ALERTS);
        browserWindowPage.navigateToMenu(BROWSER_WINDOWS);

        browserWindowPage.clickNewWindowButton();
        browserWindowPage.switchToNewTab();
        Assert.assertTrue(browserWindowPage.newTabHeaderIsVisible());

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-28")
    @Story("Check that alert is visible")
    @Test(description = "Check, that alert is visible after clicking")
    public void alertTest() {
        AlertsPage alertsPage = new AlertsPage();
        alertsPage.open(getProperties().getProperty("url"));
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnAlertBtn();

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
    }
}
