import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckBoxPage;

import static pageObjects.Navigation.CHECK_BOX;
import static pageObjects.Navigation.ELEMENTS;

public class CheckBoxTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-004")
    @Story("Click checkbox randomly, and check result")
    @Test(description = "Click checkbox randomly, and check result")
    public void clickCheckboxRandomly() {
        CheckBoxPage checkBoxPage = new CheckBoxPage();
        checkBoxPage.open();
        checkBoxPage.navigateTo(ELEMENTS);
        checkBoxPage.navigateToMenu(CHECK_BOX);

        checkBoxPage.clickExpandAllBtn();
        checkBoxPage.clickRandomItem();

        Assert.assertEquals(checkBoxPage.getCheckedCheckBoxes(), checkBoxPage.getOutputResult());
    }
}
