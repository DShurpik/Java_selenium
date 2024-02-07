import basePages.BaseTest;
import dataGenerator.Generator;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.util.List;

import static pageObjects.Navigation.*;

public class ElementsTests extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();
    CheckBoxPage checkBoxPage = new CheckBoxPage();
    RadioButtonPage radioButtonPage = new RadioButtonPage();
    WebTablePage webTablePage = new WebTablePage();
    ButtonsPage buttonsPage = new ButtonsPage();

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

    @Test(description = "Adding a new person, and checking that there is a new person in a web table")
    public void addNewUserInWebTable() {
        Generator user = new Generator();

        webTablePage.open("http://85.192.34.140:8081/");
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();
        webTablePage
                .fillFirstName(user.getName())
                .fillLastName(user.getLastName())
                .fillEmail(user.getEmail())
                .fillAge(user.getAge())
                .fillSalary(user.getSalary())
                .fillDepartment(user.getDepartment())
                .clickSubmitBtn();

        List<List<String>> usersList = webTablePage.getPersonsList();
        Assert.assertTrue(webTablePage.checkPersonAdded(usersList, user));
    }

    @Test(description = "Checking that user is in a web table after searching")
    public void searchTableTest() {
        Generator user = new Generator();

        webTablePage.open("http://85.192.34.140:8081/");
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();
        webTablePage
                .fillFirstName(user.getName())
                .fillLastName(user.getLastName())
                .fillEmail(user.getEmail())
                .fillAge(user.getAge())
                .fillSalary(user.getSalary())
                .fillDepartment(user.getDepartment())
                .clickSubmitBtn();

        webTablePage.search(user.getDepartment());

        List<List<String>> usersList = webTablePage.getPersonsList();
        Assert.assertTrue(webTablePage.checkPersonAdded(usersList, user));
    }

    @Test(description = "Checking that there is not user after deleting")
    public void deleteUser() {
        Generator user = new Generator();

        webTablePage.open("http://85.192.34.140:8081/");
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();

        webTablePage
                .fillFirstName(user.getName())
                .fillLastName(user.getLastName())
                .fillEmail(user.getEmail())
                .fillAge(user.getAge())
                .fillSalary(user.getSalary())
                .fillDepartment(user.getDepartment())
                .clickSubmitBtn();

        webTablePage.search(user.getDepartment());
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        webTablePage.clickDeleteBtn();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        List<List<String>> usersList = webTablePage.getPersonsList();
        Assert.assertFalse(webTablePage.checkPersonAdded(usersList, user));
    }

    @Test(description = "Testing different clicks, double lmb, RMB, ordinary click")
    public void doubleCLickTest() {
        buttonsPage.open("http://85.192.34.140:8081/");

        buttonsPage.navigateTo(ELEMENTS);
        buttonsPage.navigateToMenu(BUTTONS);

        buttonsPage.doubleClick();

        Assert.assertEquals(buttonsPage.doubleClickResult(), "You have done a double click");
    }

    @Test(description = "Testing different clicks, double lmb, RMB, ordinary click")
    public void rightCLickTest() {
        buttonsPage.open("http://85.192.34.140:8081/");

        buttonsPage.navigateTo(ELEMENTS);
        buttonsPage.navigateToMenu(BUTTONS);

        buttonsPage.rightClick();

        Assert.assertEquals(buttonsPage.rightClickResult(), "You have done a right click");
    }

    @Test(description = "Testing different clicks, double lmb, RMB, ordinary click")
    public void cLickTest() {
        buttonsPage.open("http://85.192.34.140:8081/");

        buttonsPage.navigateTo(ELEMENTS);
        buttonsPage.navigateToMenu(BUTTONS);

        buttonsPage.clickMe();

        Assert.assertEquals(buttonsPage.clickGetResult(), "You have done a dynamic click");
    }


}
