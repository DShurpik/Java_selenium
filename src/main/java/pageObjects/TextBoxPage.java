package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

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

    @Step("Fill in the 'Full Name' field with {0}")
    public TextBoxPage fillFullNameField(String fullName) {
        sendText(fullName, userNameField);
        return this;
    }

    @Step("Fill in the 'Email' field with {0}")
    public TextBoxPage fillEmailField(String email) {
        sendText(email, userEmailField);
        return this;
    }

    @Step("Fill in the 'Current Address' field with {0}")
    public TextBoxPage fillCurrentAddressField(String address) {
        sendText(address, currentAddressField);
        return this;
    }

    @Step("Fill in the 'Permanent Address' field with {0}")
    public TextBoxPage fillPermanentAddressField(String address) {
        sendText(address, permanentAddressField);
        return this;
    }

    @Step("Click the 'Submit' button")
    public void clickSubmitBtn() {
        click(submitBtn);
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
