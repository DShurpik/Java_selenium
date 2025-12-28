import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.DatePickerPage;

import static pageObjects.Navigation.*;

public class DatePickerTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-040")
    @Story("Date Picker tests")
    @Feature("Date Picker features")
    @Description("Check selecting a date from the date picker")
    @Test(description = "Check selecting a date from the date picker")
    public void selectDateTest() {
        DatePickerPage datePickerPage = new DatePickerPage();

        datePickerPage.open();
        datePickerPage.navigateTo(WIDGETS);
        datePickerPage.navigateToMenu(DATE_PICKER);

        datePickerPage.openDatePicker();

        datePickerPage.selectYear("1990");
        datePickerPage.selectMonth("May");
        datePickerPage.selectDay(20);

        Assert.assertEquals(datePickerPage.getSelectedDate(), "05/20/1990",
                "Selected date is not correct");
    }

}
