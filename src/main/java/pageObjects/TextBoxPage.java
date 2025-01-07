package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;
@Log4j2
public class TextBoxPage extends BasePage {

    @FindBy(id = "userName")
    private WebElement userNameField;

    @FindBy(id = "userEmail")
    private WebElement userEmailField;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressField;

    @FindBy(id = "permanentAddress")
    private WebElement permanentAddressField;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    @FindBy(xpath = "//div[@class='border col-md-12 col-sm-12']")
    private WebElement result;

    public TextBoxPage() {
        PageFactory.initElements(driver, this);
    }

    @Step("Enter {0} into Full Name field")
    public TextBoxPage fillFullNameField(String fullName) {
        log.info("Enter {} into Full Name field", fullName);
        userNameField.sendKeys(fullName);
        return this;
    }

    @Step("Enter {0} into Email field")
    public TextBoxPage fillEmailField(String email) {
        log.info("Enter {} into Email field", email);
        userEmailField.sendKeys(email);
        return this;
    }

    @Step("Enter {0} into Current Address field")
    public TextBoxPage fillCurrentAddressField(String address) {
        log.info("Enter {} into Current Address field", address);
        currentAddressField.sendKeys(address);
        return this;
    }

    @Step("Enter {0} into Permanent Address field")
    public TextBoxPage fillPermanentAddressField(String address) {
        log.info("Enter {} into Permanent Address field", address);
        permanentAddressField.sendKeys(address);
        return this;
    }

    @Step ("Press 'Submit' button")
    public void clickSubmitBtn() {
        submitBtn.click();
    }

    public Map<String, String> getResult() {
        String[] arr = result.getText().split("[:\n]");

        Map<String, String> valuesMap = new HashMap<>();
        for (int i = 0; i < arr.length; i = i + 2) {
            valuesMap.put(arr[i].trim(), arr[i + 1].trim());
        }
        return valuesMap;
    }
}
