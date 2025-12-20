import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AlertsPage;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getInstance;

public class AlertTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-022")
    @Story("Check that alert is visible")
    @Test(description = "Check, that alert is visible after clicking")
    public void alertTest() {
        AlertsPage alertsPage = new AlertsPage();
        alertsPage.open();
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnAlertBtn();

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-023")
    @Story("Check that timer alert is visible")
    @Test(description = "Check, that timer alert is visible after clicking")
    public void timerAlertTest() {
        AlertsPage alertsPage = new AlertsPage();
        alertsPage.open();
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnTimerAlertBtn();

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-024")
    @Story("Check that confirm alert is visible")
    @Test(description = "Check, that confirm alert is visible after clicking")
    public void confirmAlertTest() {
        AlertsPage alertsPage = new AlertsPage();
        alertsPage.open();
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnConfirmBtn();

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
        Assert.assertEquals(alertsPage.getConfirmResult(), "You selected Ok");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-025")
    @Story("Check that prompt alert is visible")
    @Test(description = "Check, that prompt alert is visible after clicking and check text")
    public void promptAlertTest() {
        AlertsPage alertsPage = new AlertsPage();
        String name = getInstance().getProperty("firstName");

        alertsPage.open();
        alertsPage.navigateTo(ALERTS);
        alertsPage.navigateToMenu(ALERTS_MENU);

        alertsPage.clickOnPromtBtn();
        alertsPage.waitForAlert();
        alertsPage.sendTextToAlert(name);

        Assert.assertTrue(alertsPage.isAlertPresent());
        alertsPage.acceptAlert();
        Assert.assertEquals(alertsPage.getPromptResult(),
                String.format("You entered %s", getInstance().getProperty("firstName")));
    }
}
