package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

@Log4j2
public class AutoCompletePage extends BasePage {

    @FindBy(xpath = "//div[@id='autoCompleteMultipleContainer']")
    private WebElement colorMultipleField;

    @FindBy(xpath = "//input[@id='autoCompleteMultipleInput']")
    private WebElement colorMultipleInput;

    @FindBy(id = "autoCompleteSingleInput")
    private WebElement colorSingleInput;

    @FindBy(id = "autoCompleteSingleContainer")
    private WebElement colorSingleContainer;

    @FindBy(css = ".css-1uccc91-singleValue")
    private WebElement selectedSingleValue;

    @Step("Enter subjects: {0}")
    public void enterColors(List<String> colors) {
        if (colors == null || colors.isEmpty()) {
            log.info("No colors to enter");
            return;
        }

        click(colorMultipleField);

        for (String color : colors) {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(colorMultipleInput));
            input.sendKeys(color);
            log.info("Enter color: {}", color);

            By optionLocator = By.xpath(
                    "//div[contains(@class, 'auto-complete__option') and normalize-space(.) = '" + color.trim() + "']"
            );
            wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
        }
    }

    @Step("Get selected colors")
    public List<String> getSelectedColors() {
        List<WebElement> selectedColorElements = colorMultipleField.findElements(By.cssSelector(".css-12jo7m5"));
        return selectedColorElements.stream()
                .map(WebElement::getText)
                .toList();
    }

    @Step("Enter single color: {0}")
    public void enterSingleColor(String color) {
        if (color == null || color.isEmpty()) {
            log.info("No color to enter");
            return;
        }

        click(colorSingleContainer);

        wait.until(ExpectedConditions.elementToBeClickable(colorSingleInput));
        sendText(color, colorSingleInput);

        By optionLocator = By.xpath(
                "//div[contains(@class, 'auto-complete__option') and normalize-space(.) = '" + color.trim() + "']"
        );
        wait.until(ExpectedConditions.elementToBeClickable(optionLocator)).click();
    }

    @Step("Get selected single color")
    public String getSelectedSingleColor() {
        return getText(selectedSingleValue);
    }
}
