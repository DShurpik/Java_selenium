package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;
import org.openqa.selenium.support.ui.*;
import testData.FormData;

import java.util.*;

@Log4j2
public class PracticeFormPage extends BasePage {

    private String selectedState;
    private String selectedCity;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "userNumber")
    private WebElement userNumberField;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirth;

    @FindBy(id = "subjectsContainer")
    private WebElement subjectField;

    @FindBy(id = "subjectsInput")
    private WebElement subjectInput;


    @FindBy(id = "currentAddress")
    private WebElement currentAddressField;

    @FindBy(xpath = "//div[text()='Select State']")
    private WebElement stateDropdown;

    @FindBy(id = "city")
    private WebElement cityDropdown;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(xpath = "//tbody//td[2]")
    private WebElement resultTable;

    public String getSelectedState() {
        return selectedState;
    }

    public String getSelectedCity() {
        return selectedCity;
    }

    @Step("Fill out the practice form with provided data")
    public void fillPracticeForm(FormData formData) {
        enterFirstName(formData.getFirstName());
        enterLastName(formData.getLastName());
        enterEmail(formData.getEmail());
        enterNumber(formData.getMobile());
        chooseGender(formData.getGender());
        //chooseDate(data.getDay(), data.getMonth(), data.getYear());
        enterSubject(formData.getSubjects());
        selectHobbies(formData.getHobbies());
        enterCurrentAddress(formData.getCurrentAddress());

        chooseState(formData.isChooseStateAndCity());
        chooseCity(formData.isChooseStateAndCity());
    }

    @Step("Enter {0} like first name")
    public PracticeFormPage enterFirstName(String name) {
        log.info("Enter {} in first name field", name);
        sendText(name, firstNameField);
        return this;
    }

    @Step("Enter {0} like last name")
    public PracticeFormPage enterLastName(String lastName) {
        log.info("Enter {} in last name field", lastName);
        sendText(lastName, lastNameField);
        return this;
    }

    @Step("Enter {0} like email")
    public PracticeFormPage enterEmail(String email) {
        log.info("Enter {} in email field", email);
        sendText(email, emailField);
        return this;
    }

    @Step("Click on {0}")
    public PracticeFormPage chooseGender(String genderName) {
        log.info("Click on {} radio button", genderName);
        Map<String, String> genderMap = Map.of(
                "Male", "gender-radio-1",
                "Female", "gender-radio-2",
                "Other", "gender-radio-3"
        );
        driver.findElement(By.cssSelector("label[for='" + genderMap.get(genderName) + "']")).click();
        return this;
    }

    @Step("Enter {0} in number field")
    public PracticeFormPage enterNumber(long number) {
        log.info("Enter {} in number field", number);
        sendText(String.valueOf(number), userNumberField);
        return this;
    }

    @Step("Choose data with {0} day, {1} month, {2} year")
    public void chooseDate(String day, String month, String year) {
        log.info("Enter {} {} {} as a data", day, month, year);
        dateOfBirth.click();
        Select monthSelect = new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__month-select']")));
        monthSelect.selectByVisibleText(month);
        Select yearSelect = new Select(driver.findElement(By.xpath("//select[@class='react-datepicker__year-select']")));
        yearSelect.selectByValue(year);
        String formattedDay = String.format("%02d", Integer.parseInt(day));
        driver.findElement(By.xpath("//div[contains(@class, 'react-datepicker__day') and text()='" + formattedDay + "']")).click();
    }

    @Step("Enter subjects: {0}")
    public PracticeFormPage enterSubject(List<String> subjects) {
        if (subjects == null || subjects.isEmpty()) {
            log.info("No subjects to enter");
            return this;
        }
        click(subjectField);

        for (String subject : subjects) {
            WebElement input = wait.until(ExpectedConditions.visibilityOf(subjectInput));
            input.sendKeys(subject);
            log.info("Enter subject: {}", subject);

            By suggestionLocator = By.xpath("//div[contains(@class,'subjects-auto-complete__option') and contains(.,'" + subject + "')]");
            wait.until(ExpectedConditions.elementToBeClickable(suggestionLocator)).click();
        }
        return this;
    }

    @Step("Select hobbies {0}")
    public PracticeFormPage selectHobbies(Set<String> hobbies) {
        if (hobbies == null || hobbies.isEmpty()) {
            log.info("No hobbies requested - skipping");
            return this;
        }

        for (String hobby : hobbies) {
            WebElement webElement = driver.findElement(By.xpath("//label[text()='" + hobby + "']"));
            log.info("Select hobby: {}", hobby);
            click(webElement);
        }
        return this;
    }

    @Step("Enter current address {0}")
    public PracticeFormPage enterCurrentAddress(String address) {
        if (address == null || address.isEmpty()) {
            log.info("No current address provided - skipping");
            return this;
        }
        log.info("Enter current address: {}", address);
        sendText(address, currentAddressField);
        return this;
    }

    @Step("Choose state randomly")
    public PracticeFormPage chooseState(boolean chooseState) {
        if (!chooseState) {
            log.info("State selection not requested - skipping");
            return this;
        }
        click(stateDropdown);

        By optionsLocator = By.xpath("//div[@id='state']//div[contains(@class,'option') and not(contains(@class,'disabled'))]");

        List<WebElement> stateOptions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(optionsLocator));

        if (stateOptions.isEmpty()) {
            throw new RuntimeException("No state options available to select");
        }

        int randomIndex = new Random().nextInt(stateOptions.size());
        WebElement randomState = stateOptions.get(randomIndex);
        selectedState = randomState.getText().trim();
        log.info("Selected state: {}", selectedState);
        click(randomState);

        return this;
    }

    @Step("Choose city randomly")
    public PracticeFormPage chooseCity(boolean chooseCity) {
        if (!chooseCity) {
            log.info("City selection not requested - skipping");
            return this;
        }
        click(cityDropdown);

        By optionLocator = By.xpath("//div[@id='city']//div[contains(@class,'option') and not(contains(@class,'disabled'))]");

        List<WebElement> cityOptions = wait.until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(optionLocator)
        );

        if (cityOptions.isEmpty()) {
            throw new RuntimeException("No city options available to select");
        }

        int randomIndex = new Random().nextInt(cityOptions.size());
        WebElement randomState = cityOptions.get(randomIndex);
        selectedCity = randomState.getText().trim();
        log.info("Selected city: {}", selectedCity);
        click(randomState);

        return this;
    }

    @Step("Click submit button")
    public void clickSubmitBtn() {
        log.info("Click submit button");
        click(submitBtn);
    }

    @Step("Create form data list from provided form data")
    public List<String> getExpectedValues(FormData formData) {
        List<String> expectedValues = new ArrayList<>();
        expectedValues.add(String.format("%s %s",formData.getFirstName(), formData.getLastName()));
        expectedValues.add(formData.getEmail());
        expectedValues.add(formData.getGender());
        expectedValues.add(String.valueOf(formData.getMobile()));
        //expectedValues.add(formData.getDay() + " " + formData.getMonth() + "," + formData.getYear());
        expectedValues.add(String.join(", ", formData.getSubjects()));
        expectedValues.add(String.join(", ", formData.getHobbies()));
        if (formData.getCurrentAddress() != null) expectedValues.add(formData.getCurrentAddress());
        if (formData.isChooseStateAndCity()) expectedValues.add(getSelectedState() + " " + getSelectedCity());
        log.info("Expected values: {}", expectedValues);
        return expectedValues;
    }

    @Step("Get result table")
    public List<String> getResultTable() {
        wait.until(ExpectedConditions.visibilityOf(resultTable));
        log.info("Get result table");
        List<WebElement> rows = driver.findElements(By.xpath("//tbody//td[2]"));
        List<String> results = new ArrayList<>();
        for (WebElement row : rows) {
            results.add(row.getText().trim());
        }
        log.info("Result table values: {}", results);
        return results;
    }

    @Step("Assert that all expected values are present in result table")
    public boolean assertResults(List<String> expectedValues, List<String> actualValues) {
        if (expectedValues == null || expectedValues.isEmpty()) {
            log.warn("No expected values provided for assertion");
            return false;
        }

        boolean allMatch = true;

        for (String expected : expectedValues) {
            if (expected == null || expected.trim().isEmpty()) {
                continue;
            }

            boolean found = actualValues.stream()
                    .anyMatch(row -> row != null && row.contains(expected.trim()));

            if (!found) {
                log.error("Not found expected value: '{}'. Actual values: \n{}",
                        expected, String.join("\n", actualValues));
                allMatch = false;
            } else {
                log.info("Found expected value: '{}' in actual values list", expected);
            }
        }
        return allMatch;
    }

}
