import basePages.BaseTest;
import dataGenerator.DataUserGenerator;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.WebTablePage;
import testData.TableUser;

import static pageObjects.Navigation.ELEMENTS;
import static pageObjects.Navigation.WEB_TABLES;

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

        webTablePage.clickDeleteBtn();

        Assert.assertEquals(webTablePage.getPersonsList().size(), 2);
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-009")
    @Story("Add a new user")
    @Test(description = "Add a new user in table and check it")
    public void addUserTableTest() {
        WebTablePage webTablePage = new WebTablePage();
        DataUserGenerator user = new DataUserGenerator();

        webTablePage.open();
        webTablePage.navigateTo(ELEMENTS);
        webTablePage.navigateToMenu(WEB_TABLES);

        webTablePage.clickAddNewPersonBtn();

        webTablePage.fillForm(TableUser.builder().build()
                .withFirstName(user.getName())
                .withLastName(user.getLastName())
                .withAge(user.getAge())
                .withEmail(user.getEmail())
                .withSalary(user.getSalary())
                .withDepartment(user.getDepartment()));

        Assert.assertTrue(webTablePage.checkPersonAdded(webTablePage.getPersonsList(), user));
    }
}
