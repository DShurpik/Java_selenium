package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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
        return Integer.parseInt(Objects.requireNonNull(sliderValue.getAttribute("value")));
    }


}
