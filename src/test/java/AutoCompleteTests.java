import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AutoCompletePage;
import pageObjects.Navigation.*;

import java.util.List;

import static pageObjects.Navigation.*;

public class AutoCompleteTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-038")
    @Story("Auto complete tests")
    @Feature("Auto complete features")
    @Description("Check picking up multiple color values")
    @Test(description = "Check picking up multiple color values",
    dataProviderClass = testData.AutoCompleteData.class, dataProvider = "Auto complete colors provider")
    public void multipleColorTest(List<String> colors) {
        AutoCompletePage autoCompletePage = new AutoCompletePage();

        autoCompletePage.open();
        autoCompletePage.navigateTo(WIDGETS);
        autoCompletePage.navigateToMenu(AUTO_COMPLETE);

        autoCompletePage.enterColors(colors);

        Assert.assertTrue(autoCompletePage.getSelectedColors().containsAll(colors),
                "Not all colors were selected correctly");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-039")
    @Story("Auto complete tests")
    @Feature("Auto complete features")
    @Description("Check picking up single color value")
    @Test(description = "Check picking up single color value",
    dataProviderClass = testData.AutoCompleteData.class, dataProvider = "Single auto complete color provider")
    public void singleColorTest(String color) {
        AutoCompletePage autoCompletePage = new AutoCompletePage();

        autoCompletePage.open();
        autoCompletePage.navigateTo(WIDGETS);
        autoCompletePage.navigateToMenu(AUTO_COMPLETE);

        autoCompletePage.enterSingleColor(color);

        Assert.assertEquals(autoCompletePage.getSelectedSingleColor(), color,
                "The color was not selected correctly");
    }

}
