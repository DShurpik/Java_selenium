import basePages.BaseTest;
import dataGenerator.DataUserGenerator;
import io.qameta.allure.*;
import models.ResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testData.*;

import java.util.*;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getProperties;

public class ElementsTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-9")
    @Story("Add a new user and check him in searh")
    @Test(description = "Add a new user in table and check him in search")
    public void searchTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        DataUserGenerator user = new DataUserGenerator();

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
        DataUserGenerator user = new DataUserGenerator();

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
    public void createdTest() {
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

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-16")
    @Story("Check forbidden response through devtools")
    @Test(description = "Validate that forbidden response has 403 code")
    public void forbiddenTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getProperties().getProperty("url.api") + "forbidden");
        linksPage.addResponseListener(getProperties().getProperty("url.api") + "forbidden");

        linksPage.open(getProperties().getProperty("url"));

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickForbiddenLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertEquals(resp.get(0).getStatusCode(), 403);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-17")
    @Story("Check forbidden response through devtools")
    @Test(description = "Validate that forbidden response has 403 code")
    public void notFoundTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getProperties().getProperty("url.api") + "invalid-url");
        linksPage.addResponseListener(getProperties().getProperty("url.api") + "invalid-url");

        linksPage.open(getProperties().getProperty("url"));

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickNotFound();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertEquals(resp.get(0).getStatusCode(), 404);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-18")
    @Story("Check Unauthorized request response through devtools")
    @Test(description = "Validate that Unauthorized response has 401 code")
    public void unauthorizedTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getProperties().getProperty("url.api") + "unauthorized");
        linksPage.addResponseListener(getProperties().getProperty("url.api") + "unauthorized");

        linksPage.open(getProperties().getProperty("url"));

        linksPage.navigateTo(ELEMENTS);
        linksPage.navigateToMenu(LINKS);

        linksPage.clickUnauthorizedLink();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        List<ResponseData> resp = linksPage.getInterceptedResponses();
        Assert.assertEquals(resp.get(0).getStatusCode(), 401);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-19")
    @Story("Check that Broken image has 0x0 size")
    @Test(description = "Checking a broken image on the page")
    public void brokenImageTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open(getProperties().getProperty("url"));

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        Assert.assertEquals(brokenLinksPage.clientHeight(), 0);
        Assert.assertEquals(brokenLinksPage.clientWidth(), 0);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-20")
    @Story("Check that valid link refers to google.com")
    @Test(description = "Checking valid link")
    public void validLinkTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open(getProperties().getProperty("url"));

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.validLinkClick();
        Assert.assertEquals(brokenLinksPage.googleTitle(), "Google");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-21")
    @Story("Check that broken link refers to a page with 500 status code")
    @Test(description = "Checking broken link by other page element")
    public void brokenLinkTest() {
        BrokenLinksPage brokenLinksPage = new BrokenLinksPage();
        brokenLinksPage.open(getProperties().getProperty("url"));

        brokenLinksPage.navigateTo(ELEMENTS);
        brokenLinksPage.navigateToMenu(BROKEN_LINKS);

        brokenLinksPage.clickBrokenLink();

        Assert.assertTrue(brokenLinksPage.statusCodeIsDisplayed());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-22")
    @Story("Check that element will be enable in 5 seconds")
    @Test(description = "Checking element will enable in 5 seconds")
    public void elementWillBeEnableIn5sec() {
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();
        dynamicPropertiesPage.open(getProperties().getProperty("url"));

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.elementIsEnableIn5Sec());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-23")
    @Story("Check that color has changed")
    @Test(description = "Checking that color has changed")
    public void colorChangeTest() {
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();
        dynamicPropertiesPage.open(getProperties().getProperty("url"));

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.colorHasChanged());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-24")
    @Story("Check that element will be visible after 5 sec")
    @Test(description = "Checking that element will be visible after 5 sec")
    public void visibleAfter5Sec() {
        DynamicPropertiesPage dynamicPropertiesPage = new DynamicPropertiesPage();
        dynamicPropertiesPage.open(getProperties().getProperty("url"));

        dynamicPropertiesPage.navigateTo(ELEMENTS);
        dynamicPropertiesPage.navigateToMenu(DYNAMIC_PROPERTIES);

        Assert.assertTrue(dynamicPropertiesPage.buttonIsVisibleIn5Sec());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-25")
    @Story("Fill practice form by valid values")
    @Test(description = "Fill practice form by valid values")
    public void practiceFormTest() {
        PracticeFormPage practiceFormPage = new PracticeFormPage();
        FormData formData = new FormData.Builder()
                .firstName(getProperties().getProperty("firstName"))
                .lastName(getProperties().getProperty("lastName"))
                .email(getProperties().getProperty("email"))
                .number(getProperties().getProperty("phoneNumber"))
                .gender(getProperties().getProperty("gender"))
                .subject(getProperties().getProperty("subject"))
                .day("20")
                .month("January")
                .year("1990")
                .hobbies(Set.of("Sports", "Reading"))
                .build();

        List<String> expectedValues = List.of(
                getProperties().getProperty("firstName"),
                getProperties().getProperty("lastName"),
                getProperties().getProperty("email"),
                getProperties().getProperty("gender"),
                getProperties().getProperty("phoneNumber"),
                getProperties().getProperty("subject")
        );

        practiceFormPage.open(getProperties().getProperty("url"));

        practiceFormPage.navigateTo(FORMS);
        practiceFormPage.navigateToMenu(PRACTICE_FORM);
        practiceFormPage.fillPracticeForm(formData);
        practiceFormPage.clickSubmitBtn();

        expectedValues.forEach(value ->
                Assert.assertTrue(practiceFormPage.getResultTable().contains(value), "Expected value '" + value + "' not found in the result table")
        );
    }
}
