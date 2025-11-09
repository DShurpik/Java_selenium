import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pageObjects.DynamicPropertiesPage;

import static pageObjects.Navigation.DYNAMIC_PROPERTIES;
import static pageObjects.Navigation.ELEMENTS;

public class DynamicPropertiesTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-019")
    @Story("Check that element will be enable in 5 seconds")
    @Test(description = "Checking element will enable in 5 seconds")
    public void dynamicPropertiesTest() {
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();
        dynamicPropertiesPage.open();

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(dynamicPropertiesPage.elementIsEnableIn5Sec());
        softAssert.assertTrue(dynamicPropertiesPage.colorHasChanged());
        softAssert.assertTrue(dynamicPropertiesPage.buttonIsVisibleIn5Sec());
        softAssert.assertAll();
    }
}
