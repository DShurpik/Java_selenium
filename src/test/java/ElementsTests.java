import basePages.BaseTest;
import dataGenerator.Generator;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getProperties;

public class ElementsTests extends BaseTest {

    TextBoxPage textBoxPage = new TextBoxPage();
    CheckBoxPage checkBoxPage = new CheckBoxPage();
    RadioButtonPage radioButtonPage = new RadioButtonPage();
    WebTablePage webTablePage = new WebTablePage();
    ButtonsPage buttonsPage = new ButtonsPage();
    LinksPage linksPage = new LinksPage();
    BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
    DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();

    @Test(description = "Have used ordinary data for filling the user's data")
    public void fillingTextForm() {
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

    @Test(description = "API testing using network tab")
    public void createdApiTest() throws IOException {
        linksPage.open("http://85.192.34.140:8081/");

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickCreatedLink();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://85.192.34.140/api/created");
        CloseableHttpResponse response = httpClient.execute(request);

        Assert.assertEquals(response.getCode(), 201);

    }

    @Test(description = "API testing using network tab")
    public void noContentTest() throws IOException, ProtocolException {
        linksPage.open("http://85.192.34.140:8081/");

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickNoContentLink();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://85.192.34.140/api/no-content");
        CloseableHttpResponse response = httpClient.execute(request);

        //Можно брать либо хеддер по имени, либо весь хеддер, также можно проверить есть ли хеддер

        System.out.println(Arrays.toString(response.getHeaders()));
        System.out.println(response.getHeader("X-Frame-Options") + " X-Frame-Options header information");
        System.out.println(response.getLocale() + " LOCALE");
        System.out.println(response.getVersion() + " PROTOCOL VERSION");
        Assert.assertEquals(response.getCode(), 204);
    }

    @Test(description = "API testing using network tab")
    public void movedTest() throws IOException {
        linksPage.open("http://85.192.34.140:8081/");

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickMovedLink();

        /* Создаем HttpClient и отключаем автоматическую обработку перенаправлений
        disableRedirectHandling() используется для отключения автоматической обработки перенаправлений. */

        CloseableHttpClient httpClient = HttpClients.custom()
                .disableRedirectHandling()
                .build();

        HttpGet request = new HttpGet("http://85.192.34.140/api/moved");
        CloseableHttpResponse response = httpClient.execute(request);

        Assert.assertEquals(response.getCode(), 301);
    }

    @Test(description = "API testing using network tab")
    public void badRequestTest() throws IOException {
        linksPage.open("http://85.192.34.140:8081/");

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickBadRequestLink();

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet("http://85.192.34.140/api/bad-request");
        CloseableHttpResponse response = httpClient.execute(request);

        Assert.assertEquals(response.getCode(), 400);
    }

    @Test(description = "API testing using network tab")
    public void unauthorizedTest() throws IOException {
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
        brokenLinksPage.open("http://85.192.34.140:8081/");

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        Assert.assertEquals(brokenLinksPage.clientHeight(), 0);
        Assert.assertEquals(brokenLinksPage.clientWidth(), 0);
    }

    @Test(description = "Checking valid link")
    public void validLinkTest() {
        brokenLinksPage.open("http://85.192.34.140:8081/");

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.validLinkClick();

        Assert.assertTrue(brokenLinksPage.googleIsVisible());
    }

    @Test(description = "Checking broken link by other page element")
    public void brokenLinkTest() {
        brokenLinksPage.open("http://85.192.34.140:8081/");

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.clickBrokenLink();

        Assert.assertTrue(brokenLinksPage.statusCodeIsDisplayed());
    }

    @Test(description = "Checking broken link by http server")
    public void brokenLinkByServer() throws IOException {
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
        dynamicPropertiesPage.open("http://85.192.34.140:8081/");

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.elementIsEnableIn5Sec());
    }

    @Test(description = "Checking that color has changed")
    public void colorChangeTest() throws InterruptedException {
        dynamicPropertiesPage.open("http://85.192.34.140:8081/");

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.colorHasChanged());
    }

    @Test(description = "Checking that element will be visible after 5 sec")
    public void visibleAfter5Sec() {
        dynamicPropertiesPage.open("http://85.192.34.140:8081/");

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.buttonIsVisibleIn5Sec());
    }
}
