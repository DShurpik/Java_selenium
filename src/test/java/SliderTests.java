import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.SliderPage;
import testData.TestData;

import static pageObjects.Navigation.*;

public class SliderTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-041")
    @Story("Slider component tests")
    @Feature("Slider Feature")
    @Description("Verify that slider moves to the value of 100")
    @Test(description = "Slider Test - Move slider to 100"
    ,dataProvider = "sliderValues", dataProviderClass = TestData.class)
    public void moveToTargetTest(int target) {
        SliderPage sliderPage = new SliderPage();

        sliderPage.open();
        sliderPage.navigateTo(WIDGETS);
        sliderPage.navigateToMenu(SLIDER);

        sliderPage.moveSliderTo(target);

        int currentValue = sliderPage.getSliderValue();
        Assert.assertTrue(Math.abs(currentValue - target) <= 2);
    }
}
