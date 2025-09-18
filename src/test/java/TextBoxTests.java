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
        String userFullName = getProperties().getProperty("fullName");
        String userEmail = getProperties().getProperty("email");
        String userCurrentAddress = getProperties().getProperty("currentAddress");
        String userPermanentAddress = getProperties().getProperty("permanentAddress");

        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage.open(getProperties().getProperty("url"));
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(userFullName)
                .fillEmailField(userEmail)
                .fillCurrentAddressField(userCurrentAddress)
                .fillPermanentAddressField(userPermanentAddress);
        textBoxPage.clickSubmitBtn();

        Assert.assertEquals(textBoxPage.getNameText(), userFullName, "User name is not correct");
        Assert.assertEquals(textBoxPage.getEmailText(), userEmail, "User email is not correct");
        Assert.assertEquals(textBoxPage.getCurrentAddressText(), userCurrentAddress, "User current address is not correct");
        Assert.assertEquals(textBoxPage.getPermanentAddressText(), userPermanentAddress, "User permanent address is not correct");
    }
}
