import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.RadioButtonPage;
import testData.TestData;

import static pageObjects.Navigation.*;

public class RadioButtonTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-006")
    @Story("Radio buttons check")
    @Test(description = "Check radio button works correctly",
            dataProviderClass = TestData.class,
            dataProvider = "Radio buttons")
    public void radioButtonTest(String buttonName, String expectedResult) {
        RadioButtonPage radioButtonPage = new RadioButtonPage();
        radioButtonPage.open();
        radioButtonPage.navigateTo(ELEMENTS);
        radioButtonPage.navigateToMenu(RADIO_BUTTON);

        radioButtonPage.click(buttonName);

        Assert.assertEquals(radioButtonPage.getResult(), expectedResult, "Radio button selection result is not correct");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-007")
    @Story("No radio button check")
    @Test(description = "Check No radio button isDisabled")
    public void radioButtonNoTest() {
        RadioButtonPage radioButtonPage = new RadioButtonPage();
        radioButtonPage.open();
        radioButtonPage.navigateTo(ELEMENTS);
        radioButtonPage.navigateToMenu(RADIO_BUTTON);

        Assert.assertFalse(radioButtonPage.noBtnIsEnabled(), "No radio button is enabled");
    }
}
