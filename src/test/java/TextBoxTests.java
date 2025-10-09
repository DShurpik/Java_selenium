import basePages.BaseTest;
import dataGenerator.DataUserGenerator;
import dataGenerator.RandomStringGenerator;
import dataGenerator.UserBuilder;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.TextBoxPage;
import testData.TestData;

import static pageObjects.Navigation.*;

public class TextBoxTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-001")
    @Story("Filling fields using different ways to create user")
    @Test(dataProviderClass = TestData.class, dataProvider = "User data for text box",
            description = "Filling fields using different ways to create user")
    public void fillFieldsByDataProviderTest(UserBuilder user) {
        TextBoxPage textBoxPage = new TextBoxPage();
        textBoxPage.open();
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage.fillFields(user)
                .clickSubmitBtn();

        Assert.assertEquals(textBoxPage.getNameText(), user.getFullName(), "User name is not correct");
        Assert.assertEquals(textBoxPage.getEmailText(), user.getEmail(), "User email is not correct");
        Assert.assertEquals(textBoxPage.getCurrentAddressText(), user.getCurrentAddress(), "User current address is not correct");
        Assert.assertEquals(textBoxPage.getPermanentAddressText(), user.getPermanentAddress(), "User permanent address is not correct");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-002")
    @Story("Filling email field by invalid values")
    @Test(dataProviderClass = TestData.class, dataProvider = "Invalid emails",
            description = "Filling email field by invalid values")
    public void fillFieldByInvalidEmail(String invalidEmail) {
        TextBoxPage textBoxPage = new TextBoxPage();

        textBoxPage.open();
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillEmailField(invalidEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.invalidEmailFieldIsDisplayed(), "The email field is not highlighted in red");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-003")
    @Story("Filling fields with invalid email and changing it to valid values")
    @Test(description = "Using invalid email to fill the user's data fields and then changing it to valid values")
    public void fillFieldWithInvalidEmailAndChangeToValid() {
        TextBoxPage textBoxPage = new TextBoxPage();
        String invalidEmail = new RandomStringGenerator().generateRandomString();
        String validEmail = new DataUserGenerator().getEmail();

        textBoxPage.open();
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillEmailField(invalidEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.invalidEmailFieldIsDisplayed(), "The email field is not highlighted in red");

        textBoxPage.fillEmailField(validEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.getEmailText().contains(validEmail), "User email is not correct");
    }
}
