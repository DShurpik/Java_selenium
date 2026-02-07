package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class SliderPage extends BasePage {

    @FindBy(css = ".range-slider")
    private WebElement slider;

    @FindBy(id = "sliderValue")
    private WebElement sliderValue;

    @Step("Move slider to value: {targetValue}")
    public void moveSliderTo(int targetValue) {

        wait.until(ExpectedConditions.visibilityOf(slider));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block: 'center'});", slider);

        int min = Integer.parseInt(slider.getAttribute("min"));
        int max = Integer.parseInt(slider.getAttribute("max"));

        int width = slider.getSize().getWidth();

        float percent = (float)(targetValue - min) / (max - min);
        int xOffset = Math.round(width * percent);

        actions.moveToElement(slider, -width / 2 + xOffset, 0)
                .click()
                .perform();

        wait.until(d -> {
            int actual = Integer.parseInt(slider.getAttribute("value"));
            return Math.abs(actual - targetValue) <= 2;
        });
    }

    @Step("Get current slider value")
    public int getSliderValue() {
        return Integer.parseInt(Objects.requireNonNull(sliderValue.getAttribute("value")));
    }
}
