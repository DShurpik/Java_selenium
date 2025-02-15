package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Log4j2
public class PracticeFormPage extends BasePage {

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

    public PracticeFormPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Enter {0} like first name")
    public void enterFirstName(String name) {
        log.info("Enter {} in first name field", name);
        firstNameField.sendKeys(name);
    }

    @Step("Enter {0} like last name")
    public void enterLastName(String lastName) {
        log.info("Enter {} in last name field", lastName);
        lastNameField.sendKeys(lastName);
    }

    @Step("Enter {0} like email")
    public void enterEmail(String email) {
        log.info("Enter {} in email field", email);
        emailField.sendKeys(email);
    }

    @Step("Click on {0}")
    public void chooseGender(String genderName) {
        log.info("Click on {} radio button", genderName);
        Map<String, String> genderMap = Map.of(
                "Male", "gender-radio-1",
                "Female", "gender-radio-2",
                "Other", "gender-radio-3"
        );
        driver.findElement(By.cssSelector("label[for='" + genderMap.get(genderName) + "']")).click();
    }

    @Step("Enter {0} in number field")
    public void enterNumber(String number) {
        log.info("Enter {} in number field", number);
        userNumberField.sendKeys(number);
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

    @Step("Enter {0} like a subject")
    public void enterSubject(String subject) {
        WebElement container = wait.until(ExpectedConditions.elementToBeClickable(subjectField));
        log.info("Click on {}", subjectField.toString());
        container.click();
        WebElement inputField = wait.until(ExpectedConditions.elementToBeClickable(subjectInput));
        log.info("Enter {} in subject field", subject);
        inputField.sendKeys(subject);
        inputField.sendKeys(Keys.ENTER);
    }

    @Step("Select hobbies randomly")
    public void selectRandomHobbies() {
        Random random = new Random();
        List<WebElement> labels = driver.findElements(By.cssSelector(".col-md-9 .custom-control.custom-checkbox.custom-control-inline .custom-control-input + .custom-control-label"));
        int count = random.nextInt(4);
        java.util.Collections.shuffle(labels);
        log.info("Select {} hobbies", random.toString());
        for (int i = 0; i < count; i++) {
            if (!labels.get(i).isSelected()) {
                labels.get(i).click();
            }
        }
    }
}
