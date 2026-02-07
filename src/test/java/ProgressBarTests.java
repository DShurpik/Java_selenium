import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ProgressBarPage;

import static pageObjects.Navigation.*;

public class ProgressBarTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-042")
    @Story("Progress Bar component tests")
    @Feature("Progress Bar Feature")
    @Description("Verify that progress bar reaches 100%")
    @Test(description = "Progress Bar Test - Verify progress reaches 100%")
    public void progressBarTest() {
        ProgressBarPage progressBarPage = new ProgressBarPage();

        progressBarPage.open();
        progressBarPage.navigateTo(WIDGETS);
        progressBarPage.navigateToMenu(PROGRESS_BAR);

        progressBarPage.startProgressBar();

        String progressPercentage = progressBarPage.getProgressPercentage();

        Assert.assertEquals(progressPercentage, "100%", "Progress bar did not reach 100%");
    }
}
