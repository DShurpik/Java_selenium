import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AlertsPage;

import static java.lang.System.getProperties;
import static pageObjects.Navigation.*;


public class AlertTests extends BaseTest {

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

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-29")
    @Story("Check that timer alert is visible")
    @Test(description = "Check, that timer alert is visible after clicking")
    public void timerAlertTest() {
        AlertsPage alertsPage = new AlertsPage();
        alertsPage.open(getProperties().getProperty("url"));
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnTimerAlertBtn();

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-30")
    @Story("Check that confirm alert is visible")
    @Test(description = "Check, that confirm alert is visible after clicking")
    public void confirmAlertTest() {
        AlertsPage alertsPage = new AlertsPage();
        alertsPage.open(getProperties().getProperty("url"));
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnConfirmBtn();

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
        Assert.assertEquals(alertsPage.getConfirmResult(), "You selected Ok");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-31")
    @Story("Check that prompt alert is visible")
    @Test(description = "Check, that prompt alert is visible after clicking and check text")
    public void promptAlertTest() {
        AlertsPage alertsPage = new AlertsPage();
        alertsPage.open(getProperties().getProperty("url"));
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnPromtBtn();
        alertsPage.sendTextToAlert(getProperties().getProperty("firstName"));

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
        Assert.assertEquals(alertsPage.getPromptResult(), "You entered John");
    }
}
