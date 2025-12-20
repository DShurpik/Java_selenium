import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.FramesPage;
import pageObjects.Navigation.*;

import static pageObjects.Navigation.*;

public class FramesTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-026")
    @Story("Check that frame is visible and switchable")
    @Test(description = "Check, that frame is visible and switchable")
    public void frame1Test() {
        FramesPage framesPage = new FramesPage();
        framesPage.open();
        framesPage.navigateTo(ALERTS);
        framesPage.navigateToMenu(FRAMES);

        framesPage.switchToFrame1();

        Assert.assertEquals(
                framesPage.getFrameHeaderText(),
                "This page could not be found.",
                "Frame 1 header text does not match expected value");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-027")
    @Story("Check that frame 2 is visible and switchable")
    @Test(description = "Check, that frame 2 is visible and switchable")
    public void frame2Test() {
        FramesPage framesPage = new FramesPage();
        framesPage.open();
        framesPage.navigateTo(ALERTS);
        framesPage.navigateToMenu(FRAMES);

        framesPage.switchToFrame2();

        Assert.assertEquals(
                framesPage.getFrameHeaderText(),
                "This page could not be found.",
                "Frame 2 header text does not match expected value");
    }
}
