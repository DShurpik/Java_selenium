package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RadioButtonPage extends BasePage {

    @FindBy(xpath = "//label[@for='yesRadio']")
    private WebElement yesBtn;

    @FindBy(xpath = "//label[@for='impressiveRadio']")
    private WebElement impressiveBtn;

    @FindBy(xpath = "//span[@class='text-success']")
    private WebElement resultField;

    @FindBy(xpath = "//input[@id='noRadio']")
    private WebElement noBtn;

    public RadioButtonPage() {
        PageFactory.initElements(driver, this);
    }

    public void clickYesBtn() {
        yesBtn.click();
    }

    public void clickImpressiveBtn() {
        impressiveBtn.click();
    }

    public String getResult() {
        return resultField.getText();
    }

    public boolean noBtnIsDisabled() {
        String res = noBtn.getAttribute("disabled");
        return res.equals("true");
    }
}
