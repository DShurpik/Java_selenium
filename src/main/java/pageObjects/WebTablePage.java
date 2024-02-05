package pageObjects;

import basePages.BasePage;
import dataGenerator.Generator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    @FindBy(id = "searchBox")
    private WebElement searchBoxField;

    @FindBy(xpath = "//span[@title='Delete']")
    private WebElement deleteBtn;

    private final By fullPersonList = By.xpath("//div[@class='rt-tr-group']");

    public WebTablePage() {
        PageFactory.initElements(driver, this);
    }

    public void clickAddNewPersonBtn() {
        addNewPersonBtn.click();
    }

    public WebTablePage fillFirstName(String firstName) {
        firstNameField.sendKeys(firstName);
        return this;
    }

    public WebTablePage fillLastName(String lastName) {
        lastNameField.sendKeys(lastName);
        return this;
    }

    public WebTablePage fillEmail(String email) {
        emailField.sendKeys(email);
        return this;
    }

    public WebTablePage fillAge(int age) {
        ageField.sendKeys(Integer.toString(age));
        return this;
    }

    public WebTablePage fillSalary(int salary) {
        salaryField.sendKeys(Integer.toString(salary));
        return this;
    }

    public WebTablePage fillDepartment(String department) {
        departmentField.sendKeys(department);
        return this;
    }

    public WebTablePage clickSubmitBtn() {
        submitBtn.click();
        return this;
    }

    public void clickDeleteBtn() {
        deleteBtn.click();
    }

    public void search(String parameter) {
        searchBoxField.sendKeys(parameter);
    }

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

    public boolean checkPersonAdded(List<List<String>> userList, Generator user) {
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
