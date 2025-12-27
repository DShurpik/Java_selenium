package testData;

import org.testng.annotations.DataProvider;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static testData.ExcelDataLoader.readExcelData;
import static utils.FormDataUtils.*;

public class FormDataProvider {

    @DataProvider(name = "Form data provider")
    public static Object[][] practiceFormData() {
        List<String> firstNameList = readExcelData("src/test/resources/test_sheet.xlsx", 4, 0);
        List<String> lastNameList = readExcelData("src/test/resources/test_sheet.xlsx", 4, 1);
        List<String> emailList = readExcelData("src/test/resources/test_sheet.xlsx", 4, 2);
        List<String> genderList = List.of("Male", "Female", "Other");
        List<String> subjectList = readExcelData("src/test/resources/test_sheet.xlsx", 4, 6);
        Collections.shuffle(subjectList);
        Set<String> hobbiesList = Set.of("Sports", "Reading", "Music");
        List<String> currentAddressList = readExcelData("src/test/resources/test_sheet.xlsx", 4, 7);

        int subjectCount = ThreadLocalRandom.current().nextInt(0, subjectList.size() + 1);
        int hobbiesCount = ThreadLocalRandom.current().nextInt(0, hobbiesList.size() + 1);
        int addressIndex = ThreadLocalRandom.current().nextInt(0, currentAddressList.size());

        return new Object[][]{
                {
                        new FormData.Builder()
                                .firstName(safePick(firstNameList))
                                .lastName(safePick(lastNameList))
                                .email(safePick(emailList))
                                .gender(safePick(genderList))
                                .mobile(randomMobile())
                                .subjects(subjectList.subList(0, subjectCount))
                                .hobbies(hobbiesList.stream().limit(hobbiesCount).collect(Collectors.toSet()))
                                .currentAddress(currentAddressList.get(addressIndex))
                                .chooseStateAndCity(true)
                                .build()
                },
                {
                        new FormData.Builder()
                                .firstName(safePick(firstNameList))
                                .lastName(safePick(lastNameList))
                                .email(safePick(emailList))
                                .gender(safePick(genderList))
                                .mobile(randomMobile())
                                .subjects(subjectList.subList(0, subjectCount))
                                .hobbies(hobbiesList.stream().limit(hobbiesCount).collect(Collectors.toSet()))
                                .currentAddress(currentAddressList.get(addressIndex))
                                .chooseStateAndCity(false)
                                .build()
                },
                {
                        new FormData.Builder()
                                .firstName(safePick(firstNameList))
                                .lastName(safePick(lastNameList))
                                .email(safePick(emailList))
                                .gender(safePick(genderList))
                                .mobile(randomMobile())
                                .build()
                }
        };
    }
}
