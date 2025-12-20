import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.PracticeFormPage;
import testData.FormData;
import testData.FormDataProvider;

import static pageObjects.Navigation.*;

public class PracticeFormTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-020")
    @Story("Fill practice form by valid values")
    @Test(description = "Fill practice form by valid values",
            dataProviderClass = FormDataProvider.class, dataProvider = "Form data provider")
    public void fillPracticeFormTest(FormData formData) {
        PracticeFormPage practiceFormPage = new PracticeFormPage();

        practiceFormPage.open();
        practiceFormPage.navigateTo(FORMS);
        practiceFormPage.navigateToMenu(PRACTICE_FORM);

        practiceFormPage.fillPracticeForm(formData);
        practiceFormPage.clickSubmitBtn();

        Assert.assertTrue(
                practiceFormPage.assertResults(
                        practiceFormPage.getExpectedValues(formData),
                        practiceFormPage.getResultTable()
                )
        );

    }
}
