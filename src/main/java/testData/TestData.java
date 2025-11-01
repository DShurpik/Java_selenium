package testData;

import com.github.javafaker.Faker;
import dataGenerator.DataUserGenerator;
import dataGenerator.UserBuilder;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.List;

import static testData.ExcelDataLoader.readExcelData;
import static utils.PropertyReader.getInstance;


public class TestData {

    private static final Faker faker = new Faker();

    @DataProvider(name = "User data for text box")
    public static Object[][] getUserData() {

        return new Object[][]{
                {
                        new UserBuilder.Builder()
                                .withFullName(getInstance().getProperty("fullName"))
                                .withEmail(getInstance().getProperty("email"))
                                .withCurrentAddress(getInstance().getProperty("currentAddress"))
                                .withPermanentAddress(getInstance().getProperty("permanentAddress"))
                                .build()
                },

                {
                        new UserBuilder.Builder()
                                .withFullName(faker.name().fullName())
                                .withEmail(faker.internet().emailAddress())
                                .withCurrentAddress(faker.address().fullAddress())
                                .withPermanentAddress(faker.address().fullAddress())
                                .build()
                },

                {
                        new UserBuilder().createUser()
                }
        };
    }

    @DataProvider(name = "Invalid emails")
    public static Object[][] getInvalidEmails() {
        List<String> invalidEmails = readExcelData("src/test/resources/test_sheet.xlsx", 2, 0);

        Object[][] data = new Object[invalidEmails.size()][1];
        for (int i = 0; i < invalidEmails.size(); i++) {
            data[i][0] = invalidEmails.get(i);
        }

        return data;
    }

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
        return new Object[][]{
                {"yesRadio", "Yes"},
                {"impressiveRadio", "Impressive"}
        };
    }

    @DataProvider(name = "Table user data")
    public static Object[][] getTableUserData() {
        List<String> firstNameList = readExcelData("src/test/resources/test_sheet.xlsx", 3, 0);
        List<String> lastNameList = readExcelData("src/test/resources/test_sheet.xlsx", 3, 1);
        List<String> emailList = readExcelData("src/test/resources/test_sheet.xlsx", 3, 2);
        List<String> ageList = readExcelData("src/test/resources/test_sheet.xlsx", 3, 3);
        List<String> salaryList = readExcelData("src/test/resources/test_sheet.xlsx", 3, 4);
        List<String> departmentList = readExcelData("src/test/resources/test_sheet.xlsx", 3, 5);

        List<TableUser> userList = new ArrayList<>();
        for (int i = 0; i < firstNameList.size(); i++) {
            userList.add(TableUser.fromDataExcel(
                    firstNameList.get(i),
                    lastNameList.get(i),
                    emailList.get(i),
                    Integer.parseInt(ageList.get(i)),
                    Integer.parseInt(salaryList.get(i)), departmentList.get(i)));
        }

        Object[][] data = new Object[userList.size()][1];
        for (int i = 0; i < userList.size(); i++) {
            data[i][0] = userList.get(i);
        }
        return data;
    }

    @DataProvider(name = "Data for web table non-existing user")
    public static Object[][] getNonExistingUserData() {
        return new Object[][]{
                {
                        TableUser.fromDataGenerator(new DataUserGenerator()).getName()
                },
                {
                        TableUser.fromDataGenerator(new DataUserGenerator()).getLastName()
                },
                {
                        TableUser.fromDataGenerator(new DataUserGenerator()).getAge()
                },
                {
                        TableUser.fromDataGenerator(new DataUserGenerator()).getEmail()
                },
                {
                        TableUser.fromDataGenerator(new DataUserGenerator()).getSalary()
                },
                {
                        TableUser.fromDataGenerator(new DataUserGenerator()).getDepartment()
                }
        };
    }

    @DataProvider(name = "columnData")
    public Object[][] columnData() {
        return new Object[][]{
                {0, "First Name", List.of("Alden", "Cierra", "Kierra")},
                {1, "Last Name", List.of("Cantrell", "Gentry", "Vega")},
                {2, "Age", List.of("29", "39", "45")},
                {3, "Email", List.of("alden@example.com", "cierra@example.com", "kierra@example.com")},
                {4, "Salary", List.of("2000", "10000", "12000")},
                {5, "Department", List.of("Compliance", "Insurance", "Legal")}
        };
    }

    @DataProvider(name = "linkTests")
    public Object[][] linkTestData() {
        return new Object[][] {
                {"created", 201, "clickCreatedLink"},
                {"no-content", 204, "clickNoContentLink"},
                {"moved", 301, "clickMovedLink"},
                {"bad-request", 400, "clickBadRequestLink"},
                {"forbidden", 403, "clickForbiddenLink"},
                {"invalid-url", 404, "clickNotFound"}
        };
    }
}
