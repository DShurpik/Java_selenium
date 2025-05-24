package pageObjects;

import basePages.BasePage;
import dataGenerator.DataUserGenerator;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testData.TableUser;

import java.util.*;

@Log4j2
public class WebTablePage extends BasePage {

    @FindBy(id = "addNewRecordButton")
    private WebElement addNewPersonBtn;

    @FindBy(id = "firstName")
    private WebElement firstNameField;

    @FindBy(id = "lastName")
    private WebElement lastNameField;

    @FindBy(id = "userEmail")
    private WebElement emailField;

    @FindBy(id = "age")
    private WebElement ageField;

    @FindBy(id = "salary")
    private WebElement salaryField;

    @FindBy(id = "department")
    private WebElement departmentField;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(xpath = "//input[@id='searchBox']")
    private WebElement searchBoxField;

    @FindBy(xpath = "//span[@title='Delete']")
    private WebElement deleteBtn;

    @FindBy(xpath = "//span[@title='Edit']")
    private WebElement editUserBtn;

    private final By fullPersonList = By.xpath("//div[@class='rt-tr-group']");

    @Step("Click add new person button")
    public void clickAddNewPersonBtn() {
        log.info("Click add new person button");
        addNewPersonBtn.click();
    }

    @Step("Click submit button")
    public void clickSubmitBtn() {
        log.info("Click submit button");
        submitBtn.click();
    }

    @Step("Fill a new user's data with {0}")
    public void fillForm(TableUser userData) {
        log.info("Fill user data with values {}", userData.toString());
        firstNameField.clear();
        firstNameField.sendKeys(userData.getFirstName());
        lastNameField.clear();
        lastNameField.sendKeys(userData.getLastName());
        emailField.clear();
        emailField.sendKeys(userData.getEmail());
        ageField.clear();
        ageField.sendKeys(Integer.toString(userData.getAge()));
        salaryField.clear();
        salaryField.sendKeys(Integer.toString(userData.getSalary()));
        departmentField.clear();
        departmentField.sendKeys(userData.getDepartment());
        clickSubmitBtn();
    }

    @Step("Click delete button")
    public void clickDeleteBtn() {
        log.info("Click delete button");
        deleteBtn.click();
    }

    @Step("Click edit user button")
    public void clickEditBtn() {
        log.info("Click edit button");
        editUserBtn.click();
    }

    @Step("Fill field search")
    public void search(TableUser userData) {
        wait.until(driver -> getPersonsList().stream()
                .anyMatch(row -> row.contains(userData.getFirstName()) &&
                        row.contains(userData.getLastName()) &&
                        row.contains(userData.getEmail()) &&
                        row.contains(String.valueOf(userData.getAge())) &&
                        row.contains(String.valueOf(userData.getSalary())) &&
                        row.contains(userData.getDepartment())));
        log.info("Fill field search");
        searchBoxField.sendKeys(userData.getFirstName());
    }

    @Step("Get list of users")
    public List<List<String>> getPersonsList() {
        List<WebElement> list = driver.findElements(fullPersonList);
        List<List<String>> str = new ArrayList<>();

        for (WebElement w : list) {
            String text = w.getText().trim();
            if (!text.isEmpty()) {
                List<String> rowData = Arrays.asList(text.split("\\n"));
                str.add(rowData);
            }
        }
        return str;
    }

    @Step("Check that a user has been added to table {0}")
    public boolean checkPersonAdded(List<List<String>> userList, DataUserGenerator user) {
        boolean userFound = false;
        for (List<String> userData : userList) {
            if (userData.contains(user.getName())
                    && userData.contains(user.getLastName())
                    && userData.contains(user.getEmail())
                    && userData.contains(Integer.toString(user.getAge()))
                    && userData.contains(Integer.toString(user.getSalary()))
                    && userData.contains(user.getDepartment())) {
                userFound = true;
                break;
            }
        }
        return userFound;
    }

}
