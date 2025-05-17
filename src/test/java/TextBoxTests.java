import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DynamicPropertiesPage;
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

        Assert.assertEquals(getProperties().getProperty("fullName"), textBoxPage.getResult().get("Name"));
        Assert.assertEquals(getProperties().getProperty("email"), textBoxPage.getResult().get("Email"));
        Assert.assertEquals(getProperties().getProperty("currentAddress"), textBoxPage.getResult().get("Current Address"));
        Assert.assertEquals(getProperties().getProperty("permanentAddress"), textBoxPage.getResult().get("Permananet Address"));
    }
}
