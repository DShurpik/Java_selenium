package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class DatePickerPage extends BasePage {

    @FindBy(id = "datePickerMonthYearInput")
    private WebElement dateInput;

    @FindBy(css = ".react-datepicker__year-select")
    private WebElement yearSelect;

    @FindBy(css = ".react-datepicker__month-select")
    private WebElement monthSelect;

    @Step("Click on date input to open date picker")
    public void openDatePicker() {
        click(dateInput);
    }

    @Step("Select year {0}")
    public void selectYear(String year) {
        Select yearSelectField = new Select(yearSelect);
        // Select an option in value attribute of <option value= TEXT> </option>
        yearSelectField.selectByValue(year);
    }

    @Step("Select month {0}")
    public void selectMonth(String month) {
        Select monthSelectField = new Select(monthSelect);
        // Select an option between <option> TEXT </option> by visible text
        monthSelectField.selectByVisibleText(month);
    }

    @Step("Select day {0}")
    public void selectDay(int day) {
        String dayFormatted = String.format("%03d", day);
        WebElement dayElement = driver.findElement(
                By.cssSelector(".react-datepicker__day--" + dayFormatted +
                        ":not(.react-datepicker__day--outside-month)")
        );
        dayElement.click();
    }

    @Step("Get selected date from input field")
    public String getSelectedDate() {
        return dateInput.getAttribute("value");
    }
}
