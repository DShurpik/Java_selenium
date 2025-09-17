import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.TextBoxPage;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getProperties;

public class TextBoxTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-1")
    @Story("Filling fields by values from a property file")
    @Test(description = "Using data from property to fill the user's data fields")
    public void fillTextForm() {
        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage.open(getProperties().getProperty("url"));
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(getProperties().getProperty("fullName"))
                .fillEmailField(getProperties().getProperty("email"))
                .fillCurrentAddressField(getProperties().getProperty("currentAddress"))
                .fillPermanentAddressField(getProperties().getProperty("permanentAddress"));
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.getResultText().contains(getProperties().getProperty("fullName")));
        Assert.assertTrue(textBoxPage.getResultText().contains(getProperties().getProperty("email")));
        Assert.assertTrue(textBoxPage.getResultText().contains(getProperties().getProperty("currentAddress")));
        Assert.assertTrue(textBoxPage.getResultText().contains(getProperties().getProperty("permanentAddress")));
    }
}
