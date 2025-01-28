import basePages.BaseTest;
import dataGenerator.Generator;
import io.qameta.allure.*;
import models.ResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testData.*;

import java.util.List;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getProperties;

public class ElementsTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-1")
    @Story("Values from a property file")
    @Test(description = "Have used from property to fill the user's data fields")
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

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-2")
    @Story("Values from a Faker library")
    @Test(description = "Have used a faker to fill user's data")
    public void fillFieldByFaker() {
        Generator user = new Generator();
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

        Assert.assertEquals(user.getFullName(), textBoxPage.getResult().get("Name"));
        Assert.assertEquals(user.getEmail(), textBoxPage.getResult().get("Email"));
        Assert.assertEquals(user.getCurrentAddress(), textBoxPage.getResult().get("Current Address"));
        Assert.assertEquals(user.getPermanentAddress(), textBoxPage.getResult().get("Permananet Address"));
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-3")
    @Story("Click checkbox randomly, and check result")
    @Test(description = "Click checkbox randomly, and check result")
    public void clickCheckboxRandomly() {
        CheckBoxPage checkBoxPage = new CheckBoxPage();
        checkBoxPage.open(getProperties().getProperty("url"));
        checkBoxPage.navigateTo(ELEMENTS);
        checkBoxPage.navigateToMenu(CHECK_BOX);

        checkBoxPage.clickExpandAllBtn();
        checkBoxPage.clickRandomItem();

        Assert.assertEquals(checkBoxPage.getCheckedCheckBoxes(), checkBoxPage.getOutputResult());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-4")
    @Story("Click a define checkbox and check result")
    @Test(description = "Click a define checkbox and check result",
            dataProviderClass = TestData.class,
            dataProvider = "All values for test")
    public void defineCheckBoxTest(String checkBoxName, String expectedResult) {
        CheckBoxPage checkBoxPage = new CheckBoxPage();
        checkBoxPage.open(getProperties().getProperty("url"));
        checkBoxPage.navigateTo(ELEMENTS);
        checkBoxPage.navigateToMenu(CHECK_BOX);

        checkBoxPage.clickExpandAllBtn();
        checkBoxPage.clickCheckboxName(checkBoxName);
        Assert.assertEquals(checkBoxPage.getExpectedResult(), expectedResult);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-5")
    @Story("Radio buttons check")
    @Test(description = "Check radio button work",
            dataProviderClass = TestData.class,
            dataProvider = "Radio buttons")
    public void radioButtonTest(String buttonName, String expectedResult) {
        RadioButtonPage radioButtonPage = new RadioButtonPage();
        radioButtonPage.open(getProperties().getProperty("url"));
        radioButtonPage.navigateTo(ELEMENTS);
        radioButtonPage.navigateToMenu(RADIO_BUTTON);

        radioButtonPage.click(buttonName);

        Assert.assertEquals(radioButtonPage.getResult(), expectedResult);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-6")
    @Story("No radio button check")
    @Test(description = "Check No radio button isDisabled")
    public void radioButtonNoTest() {
        RadioButtonPage radioButtonPage = new RadioButtonPage();
        radioButtonPage.open(getProperties().getProperty("url"));
        radioButtonPage.navigateTo(ELEMENTS);
        radioButtonPage.navigateToMenu(RADIO_BUTTON);

        Assert.assertFalse(radioButtonPage.noBtnIsEnabled());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-7")
    @Story("Delete a user from table")
    @Test(description = "After deleting user, count of lines equals 2")
    public void deleteUserTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        webTablePage.open(getProperties().getProperty("url"));
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickDeleteBtn();

        Assert.assertEquals(webTablePage.getPersonsList().size(), 2);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-8")
    @Story("Add a new user")
    @Test(description = "Add a new user in table and check it")
    public void addUserTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        Generator user = new Generator();

        webTablePage.open(getProperties().getProperty("url"));
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();
        webTablePage.fillForm(TableUser.builder()
                .firstName(user.getName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .salary(user.getSalary())
                .department(user.getDepartment())
                .build());

        Assert.assertTrue(webTablePage.checkPersonAdded(webTablePage.getPersonsList(), user));
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-9")
    @Story("Add a new user and check him in searh")
    @Test(description = "Add a new user in table and check him in search")
    public void searchTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        Generator user = new Generator();

        webTablePage.open(getProperties().getProperty("url"));
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        TableUser tableUser = TableUser.builder()
                .firstName(user.getName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .salary(user.getSalary())
                .department(user.getDepartment())
                .build();
        webTablePage.clickAddNewPersonBtn();
        webTablePage.fillForm(tableUser);

        webTablePage.search(tableUser);

        Assert.assertTrue(webTablePage.checkPersonAdded(webTablePage.getPersonsList(), user));
        Assert.assertEquals(webTablePage.getPersonsList().size(), 1);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-10")
    @Story("Edit user info")
    @Test(description = "Edit informtion bout user")
    public void editTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        Generator user = new Generator();

        webTablePage.open(getProperties().getProperty("url"));
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        TableUser tableUser = TableUser.builder()
                .firstName(user.getName())
                .lastName(user.getLastName())
                .age(user.getAge())
                .email(user.getEmail())
                .salary(user.getSalary())
                .department(user.getDepartment())
                .build();
        webTablePage.clickEditBtn();
        webTablePage.fillForm(tableUser);

        webTablePage.search(tableUser);

        Assert.assertTrue(webTablePage.checkPersonAdded(webTablePage.getPersonsList(), user));
        Assert.assertEquals(webTablePage.getPersonsList().size(), 1);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-11")
    @Story("Mouse clicks")
    @Test(description = "Different kind of clicks")
    public void doubleClickTest() {
        ButtonsPage buttonsPage = new ButtonsPage();

        buttonsPage.open(getProperties().getProperty("url"));
        buttonsPage.navigateTo(ELEMENTS);
        buttonsPage.navigateToMenu(BUTTONS);

        buttonsPage.doubleClick();
        buttonsPage.rightClick();
        buttonsPage.clickMe();

        Assert.assertEquals(buttonsPage.doubleClickResult(), "You have done a double click");
        Assert.assertEquals(buttonsPage.rightClickResult(), "You have done a right click");
        Assert.assertEquals(buttonsPage.clickGetResult(), "You have done a dynamic click");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-12")
    @Story("Check Created response through devtools")
    @Test(description = "Validate that Created response has 201 code")
    public void unauthorizedTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getProperties().getProperty("url.api") + "created");
        linksPage.addResponseListener(getProperties().getProperty("url.api") + "created");

        linksPage.open(getProperties().getProperty("url"));

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickCreatedLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertEquals(resp.get(0).getStatusCode(), 201);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-13")
    @Story("Check No Content response through devtools")
    @Test(description = "Validate that Created response has 204 code")
    public void noContentTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getProperties().getProperty("url.api") + "no-content");
        linksPage.addResponseListener(getProperties().getProperty("url.api") + "no-content");

        linksPage.open(getProperties().getProperty("url"));

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickNoContentLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertEquals(resp.get(0).getStatusCode(), 204);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-14")
    @Story("Check Moved response through devtools")
    @Test(description = "Validate that Moved response has 301 code")
    public void movedTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getProperties().getProperty("url.api") + "moved");
        linksPage.addResponseListener(getProperties().getProperty("url.api") + "moved");

        linksPage.open(getProperties().getProperty("url"));

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickMovedLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertEquals(resp.get(0).getStatusCode(), 301);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-15")
    @Story("Check Bad request response through devtools")
    @Test(description = "Validate that Bad request response has 400 code")
    public void badRequestTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getProperties().getProperty("url.api") + "bad-request");
        linksPage.addResponseListener(getProperties().getProperty("url.api") + "bad-request");

        linksPage.open(getProperties().getProperty("url"));

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickBadRequestLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertEquals(resp.get(0).getStatusCode(), 400);
    }

    @Test(description = "Checking a broken image on the page")
    public void brokenImageTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open("http://85.192.34.140:8081/");

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        Assert.assertEquals(brokenLinksPage.clientHeight(), 0);
        Assert.assertEquals(brokenLinksPage.clientWidth(), 0);
    }

    @Test(description = "Checking valid link")
    public void validLinkTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open("http://85.192.34.140:8081/");

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.validLinkClick();

        Assert.assertTrue(brokenLinksPage.googleIsVisible());
    }

    @Test(description = "Checking broken link by other page element")
    public void brokenLinkTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open("http://85.192.34.140:8081/");

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.clickBrokenLink();

        Assert.assertTrue(brokenLinksPage.statusCodeIsDisplayed());
    }


    @Test(description = "Checking element will enable in 5 seconds")
    public void elementWillBeEnableIn5sec() {
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();
        dynamicPropertiesPage.open("http://85.192.34.140:8081/");

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.elementIsEnableIn5Sec());
    }

    @Test(description = "Checking that color has changed")
    public void colorChangeTest() {
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();
        dynamicPropertiesPage.open("http://85.192.34.140:8081/");

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.colorHasChanged());
    }

    @Test(description = "Checking that element will be visible after 5 sec")
    public void visibleAfter5Sec() {
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();
        dynamicPropertiesPage.open("http://85.192.34.140:8081/");

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.buttonIsVisibleIn5Sec());
    }


}
