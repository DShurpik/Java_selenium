package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

@Log4j2
public class TextBoxPage extends BasePage {

    @FindBy(xpath = "//input[@id='userName']")
    private WebElement userNameField;

    @FindBy(xpath = "//input[@id='userEmail']")
    private WebElement userEmailField;

    @FindBy(xpath = "//textarea[@id='currentAddress']")
    private WebElement currentAddressField;

    @FindBy(xpath = "//textarea[@id='permanentAddress']")
    private WebElement permanentAddressField;

    @FindBy(xpath = "//p[@id='name']")
    private WebElement nameResult;

    @FindBy(xpath = "//p[@id='email']")
    private WebElement emailResult;

    @FindBy(xpath = "//p[@id='currentAddress']")
    private WebElement currentAddressResult;

    @FindBy(xpath = "//p[@id='permanentAddress']")
    private WebElement permanentAddressResult;

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
    public void fillPermanentAddressField(String address) {
        sendText(address, permanentAddressField);
    }

    @Step("Click the 'Submit' button")
    public void clickSubmitBtn() {
        click(submitButton);
    }

    @Step("Get the result text from results block")
    public String getNameText() {
        return getText(nameResult).split(":")[1].trim();
    }

    @Step("Get the email text from results block")
    public String getEmailText() {
        return getText(emailResult).split(":")[1].trim();
    }

    @Step("Get the current address text from results block")
    public String getCurrentAddressText() {
        return getText(currentAddressResult).split(":")[1].trim();
    }

    @Step("Get the permanent address text from results block")
    public String getPermanentAddressText() {
        return getText(permanentAddressResult).split(":")[1].trim();
    }

    @Step("Email field has red border")
    public Boolean invalidEmailFieldIsDisplayed() {
        return isElementDisplayed(userEmailInvalidField);
    }
}
