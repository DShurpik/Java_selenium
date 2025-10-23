package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import testData.TableUser;

import java.util.*;

@Log4j2
public class WebTablePage extends BasePage {

    @FindBy(xpath = "//button[text()='Add' and @class='btn btn-primary']")
    private WebElement addNewPersonBtn;

    @FindBy(xpath = "//input[@placeholder='First Name' and @class=' mr-sm-2 form-control']")
    private WebElement firstNameField;

    @FindBy(xpath = "//input[@placeholder='Last Name' and @class=' mr-sm-2 form-control']")
    private WebElement lastNameField;

    @FindBy(xpath = "//input[@placeholder='name@example.com' and @class='mr-sm-2 form-control']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@placeholder='Age' and @class=' mr-sm-2 form-control']")
    private WebElement ageField;

    @FindBy(xpath = "//input[@placeholder='Salary' and @class=' mr-sm-2 form-control']")
    private WebElement salaryField;

    @FindBy(xpath = "//input[@placeholder='Department' and @class=' mr-sm-2 form-control']")
    private WebElement departmentField;

    @FindBy(xpath = "//button[@class='btn btn-primary' and text()='Submit']")
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
        click(addNewPersonBtn);
    }

    @Step("Click submit button")
    public void clickSubmitBtn() {
        click(submitBtn);
    }

    @Step("Fill a new user's data with {0}")
    public void fillForm(TableUser userData) {
        log.info("Fill user data with values {}", userData.toString());
        sendText(userData.getName(), firstNameField);
        sendText(userData.getLastName(), lastNameField);
        sendText(userData.getEmail(), emailField);
        sendText(Integer.toString(userData.getAge()), ageField);
        sendText(Integer.toString(userData.getSalary()), salaryField);
        sendText(userData.getDepartment(), departmentField);
        clickSubmitBtn();
    }

    @Step("Click delete button")
    public void clickDeleteBtn() {
        click(deleteBtn);
    }

    @Step("Click edit user button")
    public void clickEditBtn() {
        click(editUserBtn);
    }

    @Step("Fill field search")
    public void search(TableUser userData, boolean expectExisting) {
        log.info("Waiting for table to load");

        if (expectExisting) {
            wait.until(driver -> getPersonsList().stream()
                    .anyMatch(row ->
                            row.contains(userData.getName()) &&
                                    row.contains(userData.getLastName()) &&
                                    row.contains(userData.getEmail()) &&
                                    row.contains(String.valueOf(userData.getAge())) &&
                                    row.contains(String.valueOf(userData.getSalary())) &&
                                    row.contains(userData.getDepartment())));
            log.info("Fill field search");
            searchBoxField.sendKeys(userData.getName());
        } else {
            log.info("Searching non-existing user, no pre-check needed");
            searchBoxField.sendKeys(userData.getName());
        }
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
    public boolean checkPersonAdded(List<List<String>> userList, TableUser user) {
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
