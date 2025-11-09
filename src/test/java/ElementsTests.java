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
