package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

@Log4j2
public class RadioButtonPage extends BasePage {

    @FindBy(xpath = "//span[@class='text-success']")
    private WebElement resultField;

    @FindBy(xpath = "//input[@id='noRadio']")
    private WebElement noBtn;

    @Step("Click on {0}")
    public void click(String buttonName) {
        log.info("Click on {} button", buttonName);
        driver.findElement(By.xpath("//label[@for='" + buttonName + "']")).click();
    }

    @Step("Get result from result field")
    public String getResult() {
        log.info("Get result from result field");
        return resultField.getText();
    }

    @Step("Check, that No radio button is enabled")
    public boolean noBtnIsEnabled() {
        return noBtn.isEnabled();
    }
}
