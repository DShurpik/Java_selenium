import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.ModalDialogsPage;

import static pageObjects.Navigation.*;

public class ModalDialogsTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-028")
    @Story("Check that small modal dialog window is visible and can be closed")
    @Test(description = "Check, that small modal dialog window is visible and can be closed")
    public void smallModalDialogTest() {
        ModalDialogsPage modalDialogsPage = new ModalDialogsPage();
        modalDialogsPage.open();
        modalDialogsPage.navigateTo(ALERTS);
        modalDialogsPage.navigateToMenu(MODAL_DIALOGS);

        modalDialogsPage.openSmallModal();

        Assert.assertEquals(
                modalDialogsPage.getModalTitleText(),
                "Small Modal",
                "Modal title is incorrect");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-029")
    @Story("Check that large modal dialog window is visible and can be closed")
    @Test(description = "Check, that large modal dialog window is visible and can be closed")
    public void largeModalDialogTest() {
        ModalDialogsPage modalDialogsPage = new ModalDialogsPage();
        modalDialogsPage.open();
        modalDialogsPage.navigateTo(ALERTS);
        modalDialogsPage.navigateToMenu(MODAL_DIALOGS);

        modalDialogsPage.openLargeModal();

        Assert.assertEquals(
                modalDialogsPage.getModalTitleText(),
                "Large Modal",
                "Modal title is incorrect");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-030")
    @Story("Check that small modal dialog window can be closed")
    @Test(description = "Check, that small modal dialog window can be closed")
    public void closeSmallModalDialogTest() {
        ModalDialogsPage modalDialogsPage = new ModalDialogsPage();
        modalDialogsPage.open();
        modalDialogsPage.navigateTo(ALERTS);
        modalDialogsPage.navigateToMenu(MODAL_DIALOGS);

        modalDialogsPage.openSmallModal();
        modalDialogsPage.closeModal();

        Assert.assertTrue(modalDialogsPage.isModalDisplayed());
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-031")
    @Story("Check that large modal dialog window can be closed")
    @Test(description = "Check, that large modal dialog window can be closed")
    public void closeLargeModalDialogTest() {
        ModalDialogsPage modalDialogsPage = new ModalDialogsPage();
        modalDialogsPage.open();
        modalDialogsPage.navigateTo(ALERTS);
        modalDialogsPage.navigateToMenu(MODAL_DIALOGS);

        modalDialogsPage.openLargeModal();
        modalDialogsPage.closeModal();

        Assert.assertTrue(modalDialogsPage.isModalDisplayed());
    }
}
