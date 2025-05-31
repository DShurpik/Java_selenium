package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

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

    @FindBy(id = "output")
    private WebElement resultField;

    private final By userEmailInvalidField = By.xpath("//input[@id='userEmail' and contains(@class, 'field-error')]");
    private final By submitButton = By.id("submit");

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
        click(submitButton);
    }

    @Step("Get text from the output form")
    public String getResultText() {
        return getText(resultField);
    }

    @Step("Email field has red border")
    public Boolean invalidEmailFieldIsDisplayed() {
        return isElementDisplayed(userEmailInvalidField);
    }
}
