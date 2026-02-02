package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
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

        int min = Integer.parseInt(Objects.requireNonNull(slider.getAttribute("min")));
        int max = Integer.parseInt(Objects.requireNonNull(slider.getAttribute("max")));

        int width = slider.getSize().getWidth();

        int xOffset = (width * (targetValue - min)) / (max - min);
        int yOffset = slider.getSize().getHeight() / 2 - 1;
        int clickOffsetFromCenter = xOffset - width / 2;

        actions
                .moveToElement(slider, clickOffsetFromCenter, yOffset)
                .click()
                .perform();
    }

    @Step("Get current slider value")
    public int getSliderValue() {
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("sliderValue")));
        String value = wait.until(d -> {
            String v = sliderValue.getAttribute("value");
            return (v != null && !v.isEmpty()) ? v : null;
        });

        return Integer.parseInt(value);
    }


}
