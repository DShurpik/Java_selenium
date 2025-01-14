package testData;

import org.testng.annotations.DataProvider;

public class TestData {

    @DataProvider(name = "All values for test")
    public static Object[][] getTestData() {
        return new Object[][]{
                {"Home", "you have selected : home desktop notes commands documents workspace react angular veu office public private classified general downloads wordfile excelfile"},
                {"Desktop", "you have selected : desktop notes commands"},
                {"Notes", "you have selected : notes"},
                {"Commands", "you have selected : commands"},
                {"Documents", "you have selected : documents workspace react angular veu office public private classified general"},
                {"WorkSpace", "you have selected : workspace react angular veu"},
                {"React", "you have selected : react"},
                {"Angular", "you have selected : angular"},
                {"Veu", "you have selected : veu"},
                {"Office", "you have selected : office public private classified general"},
                {"Public", "you have selected : public"},
                {"Private", "you have selected : private"},
                {"Classified", "you have selected : classified"},
                {"General", "you have selected : general"},
                {"Downloads", "you have selected : downloads wordfile excelfile"},
                {"Word File.doc", "you have selected : wordfile"},
                {"Excel File.doc", "you have selected : excelfile"}
        };
    }

    @DataProvider(name = "Radio buttons")
    public static Object[][] getButtonsName() {
        return new Object[][] {
                {"yesRadio", "Yes"},
                {"impressiveRadio", "Impressive"}
        };
    }
}
