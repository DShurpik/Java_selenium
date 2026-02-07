package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProgressBarPage extends BasePage {

    @FindBy(css = ".mt-3")
    private WebElement startButton;

    @FindBy(css = ".progress-bar")
    private WebElement progressBar;

    @FindBy(xpath = "//button[text()='Reset']")
    private WebElement resetButton;

    @Step("Start the progress bar")
    public void startProgressBar() {
        click(startButton);
    }

    @Step("Get the current progress percentage")
    public String getProgressPercentage() {
        WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));

        longWait.until(driver ->
                startButton.getText().equals("Reset"));
        return getText(progressBar);
    }
}
