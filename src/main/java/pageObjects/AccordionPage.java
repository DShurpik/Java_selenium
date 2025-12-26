package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class AccordionPage extends BasePage{

    @FindBy(id = "section1Heading")
    private WebElement section1Header;

    @FindBy(xpath = "//div[@id='section1Content']//p")
    private WebElement section1Content;

    @FindBy(id = "section2Heading")
    private WebElement section2Header;

    @FindBy(xpath = "//div[@id='section2Content']//p[1]")
    private WebElement section2_1Content;

    @FindBy(xpath = "//div[@id='section2Content']//p[2]")
    private WebElement section2_2Content;

    @FindBy(id = "section3Heading")
    private WebElement section3Header;

    @FindBy(xpath = "//div[@id='section3Content']//p")
    private WebElement section3Content;

    @FindBy(xpath = "//div[@class='card-body']//p")
    private WebElement allSectionsContent;

    @Step("Verify the first section content is visible")
    public Boolean verifyFirstSectionContentVisible() {
        wait.until(ExpectedConditions.visibilityOf(section1Content));
        return isElementDisplayed(section1Content);
    }

    @Step("Verify the first section content is not visible")
    public Boolean verifyFirstSectionContentNotVisible() {
        wait.until(ExpectedConditions.invisibilityOf(section1Content));
        return !isElementDisplayed(section1Content);
    }

    @Step("Verify the second sections are visible")
    public Boolean verifySecondSectionContentVisible() {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.invisibilityOf(section1Content),
                ExpectedConditions.visibilityOf(section2_1Content),
                ExpectedConditions.visibilityOf(section2_2Content)
        ));
        return isElementDisplayed(section2_1Content) && isElementDisplayed(section2_2Content);
    }

    @Step("Verify the second sections are not visible")
    public Boolean verifySecondSectionContentNotVisible() {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.invisibilityOf(section2_1Content),
                ExpectedConditions.invisibilityOf(section2_2Content)
        ));
        return !isElementDisplayed(section2_1Content) && !isElementDisplayed(section2_2Content);
    }

    @Step("Verify the third section content is visible")
    public Boolean verifyThirdSectionContentVisible() {
        wait.until(ExpectedConditions.and(
                ExpectedConditions.visibilityOf(section3Content),
                ExpectedConditions.invisibilityOf(section2_1Content),
                ExpectedConditions.invisibilityOf(section2_2Content)
        ));
        return isElementDisplayed(section3Content);
    }

    @Step("Verify the third section content is not visible")
    public Boolean verifyThirdSectionContentNotVisible() {
        wait.until(ExpectedConditions.invisibilityOf(section3Content));
        return !isElementDisplayed(section3Content);
    }

    @Step("Click the first section header")
    public void clickFirstSectionHeader() {
        click(section1Header);
    }

    @Step("Click the second section header")
    public void clickSecondSectionHeader() {
        click(section2Header);
    }

    @Step("Click the third section header")
    public void clickThirdSectionHeader() {
        click(section3Header);
    }

}
