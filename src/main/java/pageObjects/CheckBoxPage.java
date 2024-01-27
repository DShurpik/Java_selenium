package pageObjects;

import basePages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CheckBoxPage extends BasePage {
    @FindBy(xpath = "//button[@title='Expand all']")
    private WebElement expandAllBtn;

    private final By items = By.xpath("//span[@class='rct-title']");
    private final By itemsChecked = By.cssSelector("svg[class='rct-icon rct-icon-check']");
    private final By titleItemBy = By.xpath(".//ancestor::span[@class='rct-text']");
    private final By outputResult = By.xpath("//span[@class='text-success']");


    public CheckBoxPage() {
        PageFactory.initElements(driver,this);
    }

    public void clickExpandAllBtn() {
        expandAllBtn.click();
    }

    public void clickRandomItem() {
        List<WebElement> itemList = driver.findElements(items);
        Random random = new Random();
        WebElement item = itemList.get(random.nextInt(itemList.size()));
        item.click();
    }

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
        return str.toString();
    }

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
        return str.toString();
    }

    public void clickCheckboxName(String checkboxName) {
        driver.findElement(By.xpath("//span[text()='" + checkboxName + "']")).click();
    }

    public String getExpectedResult(String checkboxName) {
        StringBuilder str = new StringBuilder();
        str.append(checkboxName.toLowerCase()
                .replaceAll("doc", "")
                .replaceAll("\\.", "")
                .replaceAll(" ", ""));
        return str.toString();
    }


}
