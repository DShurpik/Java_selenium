package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

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

    public TextBoxPage fillFullNameField(String fullName) {
        userNameField.sendKeys(fullName);
        return this;
    }

    public TextBoxPage fillEmailField(String email) {
        userEmailField.sendKeys(email);
        return this;
    }

    public TextBoxPage fillCurrentAddressField(String address) {
        currentAddressField.sendKeys(address);
        return this;
    }

    public TextBoxPage fillPermanentAddressField(String address) {
        permanentAddressField.sendKeys(address);
        return this;
    }

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
