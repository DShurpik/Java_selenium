import basePages.BaseTest;
import dataGenerator.Generator;
import io.qameta.allure.*;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;

import java.io.IOException;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getProperties;

public class ElementsTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("TC-1")
    @Story("Values from different property files")
    @Test(description = "Have used from property to fill the user's data fields")
    public void fillingTextForm() {
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

    @Test(description = "Have used a faker for filling the user's data")
    public void fillFieldByFaker() {
        Generator user = new Generator();
        TextBoxPage textBoxPage = new TextBoxPage();

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
        CheckBoxPage checkBoxPage = new CheckBoxPage();
        checkBoxPage.open("http://85.192.34.140:8081/");
        checkBoxPage.navigateTo(ELEMENTS);
        checkBoxPage.navigateToMenu(CHECK_BOX);

        checkBoxPage.clickExpandAllBtn();
        checkBoxPage.clickRandomItem();

        String actualResult = checkBoxPage.getCheckedCheckBoxes();
        String expectedResult = checkBoxPage.getOutputResult();

        Assert.assertEquals(actualResult, expectedResult);

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
