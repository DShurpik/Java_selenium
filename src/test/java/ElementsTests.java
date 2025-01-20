import basePages.BaseTest;
import dataGenerator.Generator;
import io.qameta.allure.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testData.TableUser;
import testData.TestData;

import java.io.IOException;

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


    @Test(description = "API testing using network tab")
    public void unauthorizedTest() throws IOException {
        LinksPage linksPage = new LinksPage();
        linksPage.open("http://85.192.34.140:8081/");

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickUnauthorizedLink();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://85.192.34.140/api/unauthorized");
        CloseableHttpResponse response = httpClient.execute(request);

        Assert.assertEquals(response.getCode(), 401);
    }

    @Test(description = "API testing using network tab")
    public void forbiddenTest() throws IOException {
        LinksPage linksPage = new LinksPage();
        linksPage.open("http://85.192.34.140:8081/");

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickForbiddenLink();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://85.192.34.140/api/forbidden");
        CloseableHttpResponse response = httpClient.execute(request);

        Assert.assertEquals(response.getCode(), 403);
    }

    @Test(description = "API testing using network tab")
    public void notFoundTest() throws IOException {
        LinksPage linksPage = new LinksPage();
        linksPage.open("http://85.192.34.140:8081/");

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickForbiddenLink();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://85.192.34.140/api/invalid-url");
        CloseableHttpResponse response = httpClient.execute(request);

        Assert.assertEquals(response.getCode(), 404);
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

    @Test(description = "Checking broken link by http server")
    public void brokenLinkByServer() throws IOException {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open("http://85.192.34.140:8081/");

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.clickBrokenLink();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("https://the-internet.herokuapp.com/status_codes/500");
        CloseableHttpResponse response = httpClient.execute(request);

        Assert.assertEquals(response.getCode(), 500);
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
