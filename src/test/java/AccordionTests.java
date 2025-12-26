import basePages.BaseTest;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccordionPage;
import pageObjects.Navigation.*;

import static pageObjects.Navigation.*;

public class AccordionTests extends BaseTest {

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-031")
    @Story("Accordion Functionality Tests")
    @Feature("Accordion Feature")
    @Description("Verify that the first accordion is visible by default")
    @Test(description = "Verify that the first accordion is visible by default")
    public void verifyFirstAccordionTest() {
        AccordionPage accordionPage = new AccordionPage();

        accordionPage.open();
        accordionPage.navigateTo(WIDGETS);
        accordionPage.navigateToMenu(ACCORDION);

        Assert.assertTrue(accordionPage.verifyFirstSectionContentVisible(),
                "First section content should be visible by default");
        Assert.assertTrue(accordionPage.verifySecondSectionContentNotVisible(),
                "Second section content should not be visible by default");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-032")
    @Story("Accordion Functionality Tests")
    @Feature("Accordion Feature")
    @Description("Verify that the second accordion expands and collapses correctly")
    @Test(description = "Verify that the second accordion expands and collapses correctly")
    public void verifySecondAccordionTest() {
        AccordionPage accordionPage = new AccordionPage();

        accordionPage.open();
        accordionPage.navigateTo(WIDGETS);
        accordionPage.navigateToMenu(ACCORDION);

        accordionPage.clickSecondSectionHeader();

        Assert.assertTrue(accordionPage.verifyFirstSectionContentNotVisible(),
                "First section content is not visible after expanding second section");
        Assert.assertTrue(accordionPage.verifySecondSectionContentVisible(),
                "Second section content is visible");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-033")
    @Story("Accordion Functionality Tests")
    @Feature("Accordion Feature")
    @Description("Verify that the third accordion expands correctly")
    @Test(description = "Verify that the third accordion expands correctly")
    public void verifyThirdAccordionTest() {
        AccordionPage accordionPage = new AccordionPage();

        accordionPage.open();
        accordionPage.navigateTo(WIDGETS);
        accordionPage.navigateToMenu(ACCORDION);

        accordionPage.clickThirdSectionHeader();

        Assert.assertTrue(accordionPage.verifySecondSectionContentNotVisible());
        Assert.assertTrue(accordionPage.verifyThirdSectionContentVisible(),
                "Third section content is visible");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-034")
    @Story("Accordion Functionality Tests")
    @Feature("Accordion Feature")
    @Description("Verify that the first accordion isn't visible after clicking the first accordion header")
    @Test(description = "Verify that the first accordion isn't visible after clicking the first accordion header")
    public void verifyFirstAccordionNotVisibleAfterClickTest() {
        AccordionPage accordionPage = new AccordionPage();

        accordionPage.open();
        accordionPage.navigateTo(WIDGETS);
        accordionPage.navigateToMenu(ACCORDION);

        accordionPage.clickFirstSectionHeader();

        Assert.assertTrue(accordionPage.verifyFirstSectionContentNotVisible(),
                "First section content should not be visible after clicking the header");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-035")
    @Story("Accordion Functionality Tests")
    @Feature("Accordion Feature")
    @Description("Verify that the second accordion isn't visible after clicking the second accordion header")
    @Test(description = "Verify that the second accordion isn't visible after clicking the second accordion header")
    public void verifySecondAccordionNotVisibleAfterClickTest() {
        AccordionPage accordionPage = new AccordionPage();

        accordionPage.open();
        accordionPage.navigateTo(WIDGETS);
        accordionPage.navigateToMenu(ACCORDION);

        accordionPage.clickSecondSectionHeader();
        accordionPage.clickSecondSectionHeader();

        Assert.assertTrue(accordionPage.verifySecondSectionContentNotVisible(),
                "Second section content should not be visible after clicking the header again");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-036")
    @Story("Accordion Functionality Tests")
    @Feature("Accordion Feature")
    @Description("Verify that the third accordion isn't visible after clicking the third accordion header")
    @Test(description = "Verify that the third accordion isn't visible after clicking the third accordion header")
    public void verifyThirdAccordionNotVisibleAfterClickTest() {
        AccordionPage accordionPage = new AccordionPage();

        accordionPage.open();
        accordionPage.navigateTo(WIDGETS);
        accordionPage.navigateToMenu(ACCORDION);

        accordionPage.clickThirdSectionHeader();
        accordionPage.clickThirdSectionHeader();

        Assert.assertTrue(accordionPage.verifySecondSectionContentNotVisible(),
                "Third section content should not be visible after clicking the header again");
    }

    @Owner("John Doe")
    @Severity(SeverityLevel.NORMAL)
    @TmsLink("FORM-TC-037")
    @Story("Accordion Functionality Tests")
    @Feature("Accordion Feature")
    @Description("Verify all accordions are closed")
    @Test(description = "Verify all accordions are closed")
    public void verifyAllAccordionsClosedTest() {
        AccordionPage accordionPage = new AccordionPage();

        accordionPage.open();
        accordionPage.navigateTo(WIDGETS);
        accordionPage.navigateToMenu(ACCORDION);

        accordionPage.clickFirstSectionHeader();

        Assert.assertTrue(accordionPage.verifyFirstSectionContentNotVisible(),
                "First section content should not be visible");
        Assert.assertTrue(accordionPage.verifySecondSectionContentNotVisible(),
                "Second section content should not be visible");
        Assert.assertTrue(accordionPage.verifyThirdSectionContentNotVisible(),
                "Third section content should not be visible");
    }
}
