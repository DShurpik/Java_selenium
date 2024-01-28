import basePages.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CheckBoxPage;
import pageObjects.RadioButtonPage;
import pageObjects.TextBoxPage;

import static pageObjects.Navigation.*;

public class ElementsTests extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();
    CheckBoxPage checkBoxPage = new CheckBoxPage();
    RadioButtonPage radioButtonPage = new RadioButtonPage();

    @Test(description = "Have used ordinary data for filling the user's data")
    public void fillingTextForm() {
        String fullName = "D S";
        String email = "ds@gmail.com";
        String currentAddress = "Minsk";
        String permanentAddress = "Minsk";

        textBoxPage.open("http://85.192.34.140:8081/");
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(fullName)
                .fillEmailField(email)
                .fillCurrentAddressField(currentAddress)
                .fillPermanentAddressField(permanentAddress);
        textBoxPage.clickSubmitBtn();

        Assert.assertEquals(fullName, textBoxPage.getResult().get("Name"));
        Assert.assertEquals(email, textBoxPage.getResult().get("Email"));
        Assert.assertEquals(currentAddress, textBoxPage.getResult().get("Current Address"));
        Assert.assertEquals(permanentAddress, textBoxPage.getResult().get("Permananet Address"));
    }

    @Test(description = "Have used a faker for filling the user's data")
    public void fillFieldByFaker() {
        Generator user = new Generator();

        textBoxPage.open("http://85.192.34.140:8081/");
        textBoxPage.navigateTo(ELEMENTS);
        textBoxPage.navigateToMenu(TEXT_BOX);

        textBoxPage
                .fillFullNameField(user.getFullName())
                .fillEmailField(user.getEmail())
                .fillCurrentAddressField(user.getCurrentAddress())
                .fillPermanentAddressField(user.getPermanentAddress());
        textBoxPage.clickSubmitBtn();

        Assert.assertEquals(user.getFullName(), textBoxPage.getResult().get("Name"));
        Assert.assertEquals(user.getEmail(), textBoxPage.getResult().get("Email"));
        Assert.assertEquals(user.getCurrentAddress(), textBoxPage.getResult().get("Current Address"));
        Assert.assertEquals(user.getPermanentAddress(), textBoxPage.getResult().get("Permananet Address"));
    }

    @Test(description = "Click checkbox randomly, and check result")
    public void clickCheckboxRandomly() {
        checkBoxPage.open("http://85.192.34.140:8081/");
        checkBoxPage.navigateTo(ELEMENTS);
        checkBoxPage.navigateToMenu(CHECK_BOX);

        checkBoxPage.clickExpandAllBtn();
        checkBoxPage.clickRandomItem();

        String actualResult = checkBoxPage.getCheckedCheckBoxes();
        String expectedResult = checkBoxPage.getOutputResult();

        Assert.assertEquals(actualResult, expectedResult);

    }

    @Test(description = "Click checkbox on name, and check result")
    public void clickCheckboxName() {
        checkBoxPage.open("http://85.192.34.140:8081/");
        checkBoxPage.navigateTo(ELEMENTS);
        checkBoxPage.navigateToMenu(CHECK_BOX);

        checkBoxPage.clickExpandAllBtn();

        String checkboxName = "Notes";
        checkBoxPage.clickCheckboxName(checkboxName);
        String expectedResult = checkBoxPage.getExpectedResult(checkboxName);
        String actualResult = checkBoxPage.getOutputResult();

        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test(description = "Radio button click Yes test")
    public void radioBtnTest1() {
        radioButtonPage.open("http://85.192.34.140:8081/");
        radioButtonPage.navigateTo(ELEMENTS);
        radioButtonPage.navigateToMenu(RADIO_BUTTON);

        radioButtonPage.clickYesBtn();
        String result = radioButtonPage.getResult();

        Assert.assertEquals(result, "Yes");
    }

    @Test(description = "Radio button click Impressive test")
    public void radioBtnTest2() {
        radioButtonPage.open("http://85.192.34.140:8081/");
        radioButtonPage.navigateTo(ELEMENTS);
        radioButtonPage.navigateToMenu(RADIO_BUTTON);

        radioButtonPage.clickImpressiveBtn();
        String result = radioButtonPage.getResult();

        Assert.assertEquals(result, "Impressive");
    }

    @Test(description = "Radio button No is disabled")
    public void radioBtnTest3() {
        radioButtonPage.open("http://85.192.34.140:8081/");
        radioButtonPage.navigateTo(ELEMENTS);
        radioButtonPage.navigateToMenu(RADIO_BUTTON);

        Assert.assertTrue(radioButtonPage.noBtnIsDisabled());
    }
}
