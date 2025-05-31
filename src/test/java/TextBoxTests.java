import basePages.BaseTest;
import dataGenerator.*;
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

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-2")
    @Story("Filling fields by values from Faker library")
    @Test(description = "Using data from Faker library to fill the user's data fields")
    public void fillFieldByFaker() {
        DataUserGenerator user = new DataUserGenerator();
        TextBoxPage textBoxPage = new TextBoxPage();

        textBoxPage.open(getProperties().getProperty("url"));
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(user.getFullName())
                .fillEmailField(user.getEmail())
                .fillCurrentAddressField(user.getCurrentAddress())
                .fillPermanentAddressField(user.getPermanentAddress());
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.getResultText().contains(user.getFullName()));
        Assert.assertTrue(textBoxPage.getResultText().contains(user.getEmail()));
        Assert.assertTrue(textBoxPage.getResultText().contains(user.getCurrentAddress()));
        Assert.assertTrue(textBoxPage.getResultText().contains(user.getPermanentAddress()));
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-3")
    @Story("Filling fields with invalid email")
    @Test(description = "Using invalid email to fill the user's data fields")
    public void fillFieldWithInvalidEmail() {
        TextBoxPage textBoxPage = new TextBoxPage();
        String invalidEmail = RandomStringGenerator.generateRandomString();

        textBoxPage.open(getProperties().getProperty("url"));
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillEmailField(invalidEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.invalidEmailFieldIsDisplayed());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-4")
    @Story("Filling fields with invalid email and changing it to valid values")
    @Test(description = "Using invalid email to fill the user's data fields and then changing it to valid values")
    public void fillFieldWithInvalidEmailAndChangeToValid() {
        TextBoxPage textBoxPage = new TextBoxPage();
        String invalidEmail = RandomStringGenerator.generateRandomString();
        DataUserGenerator user = new DataUserGenerator();
        String validEmail = user.getEmail();

        textBoxPage.open(getProperties().getProperty("url"));
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillEmailField(invalidEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.invalidEmailFieldIsDisplayed());

        textBoxPage.fillEmailField(validEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.getResultText().contains(user.getEmail()));
    }
}
