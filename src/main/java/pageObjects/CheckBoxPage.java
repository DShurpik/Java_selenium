package pageObjects;

import basePages.BasePage;
import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.*;
import org.openqa.selenium.support.*;

import java.util.*;

@Log4j2
public class CheckBoxPage extends BasePage {
    @FindBy(xpath = "//button[@title='Expand all']")
    private WebElement expandAllBtn;

    @FindBy(xpath = "//div[@id='result']")
    private WebElement outputResultWebElement;

    private final By items = By.xpath("//span[@class='rct-title']");
    private final By itemsChecked = By.cssSelector("svg[class='rct-icon rct-icon-check']");
    private final By titleItemBy = By.xpath(".//ancestor::span[@class='rct-text']");
    private final By outputResult = By.xpath("//span[@class='text-success']");

    @Step("Click on expand all button")
    public void clickExpandAllBtn() {
        log.info("Click on expand all button");
        click(expandAllBtn);
    }

    @Step("Click a random item")
    public void clickRandomItem() {
        log.info("Click randomly on an item ");
        List<WebElement> itemList = driver.findElements(items);
        Random random = new Random();
        WebElement item = itemList.get(random.nextInt(itemList.size()));
        log.info(item.getText() + " was clicked");
        Allure.addAttachment("Item was clicked", item.getText());
        click(item);
    }

    @Step("Get checked checkboxes name")
    public String getCheckedCheckBoxes() {
        List<WebElement> checkedList = driver.findElements(itemsChecked);
        List<String> data = new ArrayList<>();

        for (WebElement box : checkedList) {
            WebElement titleItem = box.findElement(titleItemBy);
            data.add(titleItem.getText());
        }
        StringBuilder str = new StringBuilder();

        for (String item : data) {
            str.append(item.toLowerCase().replaceAll(" ", "")
                    .replaceAll("doc", "")
                    .replaceAll("\\.", ""));
        }
        log.info("Get checked checkboxes name: " + str);
        Allure.attachment("Checked checkboxes", str.toString());
        return str.toString();
    }

    @Step("Get output value")
    public String getOutputResult() {
        List<WebElement> resultList = driver.findElements(outputResult);
        List<String> data = new ArrayList<>();
        for (WebElement webElement : resultList) {
            data.add(webElement.getText());
        }

        StringBuilder str = new StringBuilder();

        for (String item : data) {
            str.append(item.toLowerCase().replaceAll(" ", "")
                    .replaceAll("doc", "")
                    .replaceAll("\\.", ""));
        }
        log.info("Get output string with values: " + str);
        Allure.attachment("Output result", str.toString());
        return str.toString();
    }

    @Step("Click on {0} checkbox")
    public void clickCheckboxName(String checkboxName) {
        log.info("Click on {} checkbox", checkboxName);
        driver.findElement(By.xpath("//span[text()='" + checkboxName + "']")).click();
    }

    @Step("Get test result")
    public String getExpectedResult() {
        return outputResultWebElement.getText().toLowerCase()
                .replaceAll(".doc", "")
                .replaceAll("\n", " ").trim();
    }
}
