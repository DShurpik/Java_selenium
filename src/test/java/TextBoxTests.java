import basePages.BaseTest;
import dataGenerator.DataUserGenerator;
import dataGenerator.RandomStringGenerator;
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

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-2")
    @Story("Filling fields by values from Faker library")
    @Test(description = "Using data from Faker library to fill the user's data fields")
    public void fillFieldByFaker() {
        TextBoxPage textBoxPage = new TextBoxPage();
        DataUserGenerator user = new DataUserGenerator();
        String userFullName = user.getFullName();
        String userEmail = user.getEmail();
        String userCurrentAddress = user.getCurrentAddress();
        String userPermanentAddress = user.getPermanentAddress();

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

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-3")
    @Story("Filling fields by random values")
    @Test(description = "Using random data to fill the user's data fields")
    public void fillFieldByInvalidEmail() {
        TextBoxPage textBoxPage = new TextBoxPage();
        RandomStringGenerator randomString = new RandomStringGenerator();
        String invalidEmail = randomString.generateRandomString();

        textBoxPage.open(getProperties().getProperty("url"));
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillEmailField(invalidEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.invalidEmailFieldIsDisplayed(), "The email field is not highlighted in red");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-4")
    @Story("Filling fields with invalid email and changing it to valid values")
    @Test(description = "Using invalid email to fill the user's data fields and then changing it to valid values")
    public void fillFieldWithInvalidEmailAndChangeToValid() {
        TextBoxPage textBoxPage = new TextBoxPage();
        RandomStringGenerator randomString = new RandomStringGenerator();
        String invalidEmail = randomString.generateRandomString();
        DataUserGenerator user = new DataUserGenerator();
        String validEmail = user.getEmail();

        textBoxPage.open(getProperties().getProperty("url"));
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillEmailField(invalidEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.invalidEmailFieldIsDisplayed(), "The email field is not highlighted in red");

        textBoxPage.fillEmailField(validEmail);
        textBoxPage.clickSubmitBtn();

        Assert.assertTrue(textBoxPage.getEmailText().contains(user.getEmail()), "User email is not correct");
    }
}
