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

    @FindBy(id = "submit")
    private WebElement submitBtn;

    private final By resultName = By.id("name");
    private final By resultEmail = By.id("email");
    private final By resultCurrentAddress = By.xpath("//p[@id='currentAddress']");
    private final By resultPermanentAddress = By.xpath("//p[@id='permanentAddress']");

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

    @Step("Get name result from the form")
    public String getResultName() {
        return getText(resultName);
    }

    @Step("Get email result from the form")
    public String getResultEmail() {
        return getText(resultEmail);
    }

    @Step("Get current address result from the form")
    public String getResultCurrentAddress() {
        return getText(resultCurrentAddress);
    }

    @Step("Get permanent address result from the form")
    public String getResultPermanentAddress() {
        return getText(resultPermanentAddress);
    }
}
