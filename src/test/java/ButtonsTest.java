import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.ButtonsPage;

import static pageObjects.Navigation.BUTTONS;
import static pageObjects.Navigation.ELEMENTS;

public class ButtonsTest extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-015")
    @Story("Mouse clicks")
    @Test(description = "Different kind of clicks")
    public void differentClicksTest() {
        ButtonsPage buttonsPage = new ButtonsPage();

        buttonsPage.open();
        buttonsPage.navigateTo(ELEMENTS);
        buttonsPage.navigateToMenu(BUTTONS);

        buttonsPage.doubleClick();
        buttonsPage.rightClick();
        buttonsPage.clickMe();

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(buttonsPage.doubleClickResult(), "You have done a double click");
        softAssert.assertEquals(buttonsPage.rightClickResult(), "You have done a right click");
        softAssert.assertEquals(buttonsPage.clickGetResult(), "You have done a dynamic click");
        softAssert.assertAll();
    }
}
