import basePages.BaseTest;
import dataGenerator.DataUserGenerator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebTablePage;
import testData.TableUser;
import testData.TestData;

import static pageObjects.Navigation.*;

public class WebTableTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-008")
    @Story("Delete a user from table")
    @Test(description = "After deleting user, count of lines equals 2")
    public void deleteUserTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        webTablePage.open();
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        int tableSize = webTablePage.getPersonsList().size();

        webTablePage.clickDeleteBtn();

        Assert.assertEquals(webTablePage.getPersonsList().size(), tableSize - 1);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-009")
    @Story("Add a new user")
    @Test(description = "Add a new user in table and check it")
    public void addUserTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        TableUser user = TableUser.fromDataGenerator(new DataUserGenerator());

        webTablePage.open();
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();

        webTablePage.fillForm(user);

        Assert.assertTrue(webTablePage.checkPersonAdded(webTablePage.getPersonsList(), user));
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-010")
    @Story("Add a new user and check him in search")
    @Test(description = "Add a new user in table and check him in search")
    public void searchTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        TableUser user = TableUser.fromDataGenerator(new DataUserGenerator());

        webTablePage.open();
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();
        webTablePage.fillForm(user);

        webTablePage.searchExitingUser(user);

        Assert.assertTrue(webTablePage.checkPersonAdded(webTablePage.getPersonsList(), user));
        Assert.assertEquals(webTablePage.getPersonsList().size(), 1);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-011")
    @Story("Add multiple users from Excel file")
    @Test(dataProviderClass = TestData.class, dataProvider = "Table user data",
            description = "Add multiple users from Excel file and check them")
    public void addUserTableFromExcelTest(TableUser user) {
        WebTablePage webTablePage = new WebTablePage();

        webTablePage.open();
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();

        webTablePage.fillForm(user);

        Assert.assertTrue(webTablePage.checkPersonAdded(webTablePage.getPersonsList(), user));
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-012")
    @Story("Search non-existing user in table, using different data values")
    @Test(dataProviderClass = TestData.class, dataProvider = "Data for web table non-existing user",
            description = "Search non-existing user in table and check that no results are found")
    public void searchNonExistingUserTableTest(Object data) {
        WebTablePage webTablePage = new WebTablePage();

        webTablePage.open();
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.searchNonExistingUser(data);

        Assert.assertEquals(webTablePage.getPersonsList().size(), 0);
    }
}
