import basePages.BaseTest;
import io.qameta.allure.*;
import models.ResponseData;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.*;
import testData.*;

import java.util.*;

import static pageObjects.Navigation.*;
import static utils.PropertyReader.getInstance;

public class ElementsTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("TC-18")
    @Story("Check Unauthorized request response through devtools")
    @Test(description = "Validate that Unauthorized response has 401 code")
    public void unauthorizedTest() {
        LinksPage linksPage = new LinksPage();
        linksPage.enableNetworkInterceptor();

        linksPage.addRequestListener(getInstance().getProperty("url") + "unauthorized");
        linksPage.addResponseListener(getInstance().getProperty("url") + "unauthorized");

        linksPage.open(getInstance().getProperty("url"));

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
        brokenLinksPage.open(getInstance().getProperty("url"));

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
        brokenLinksPage.open(getInstance().getProperty("url"));

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
        brokenLinksPage.open(getInstance().getProperty("url"));

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
        dynamicPropertiesPage.open(getInstance().getProperty("url"));

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
        dynamicPropertiesPage.open(getInstance().getProperty("url"));

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
        dynamicPropertiesPage.open(getInstance().getProperty("url"));

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
                .firstName(getInstance().getProperty("url"))
                .lastName(getInstance().getProperty("url"))
                .email(getInstance().getProperty("url"))
                .number(getInstance().getProperty("url"))
                .gender(getInstance().getProperty("url"))
                .subject(getInstance().getProperty("url"))
                .day("20")
                .month("January")
                .year("1990")
                .hobbies(Set.of("Sports", "Reading"))
                .build();

        List<String> expectedValues = List.of(
                getInstance().getProperty("url")
        );

        practiceFormPage.open(getInstance().getProperty("url"));

        practiceFormPage.navigateTo(FORMS);
        practiceFormPage.navigateToMenu(PRACTICE_FORM);
        practiceFormPage.fillPracticeForm(formData);
        practiceFormPage.clickSubmitBtn();

        expectedValues.forEach(value ->
                Assert.assertTrue(practiceFormPage.getResultTable().contains(value), "Expected value '" + value + "' not found in the result table")
        );
    }
}
