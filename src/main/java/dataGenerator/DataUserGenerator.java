package dataGenerator;

import com.github.javafaker.Faker;
import lombok.Getter;

@Getter
public class DataUserGenerator {

    Faker faker = new Faker();

    // Data for Text Box tests
    private final String fullName = faker.name().fullName();
    private final String email = faker.internet().emailAddress();
    private final String currentAddress = faker.address().fullAddress();
    private final String permanentAddress = faker.address().fullAddress();


    // Data for Web Table tests
    private final String name = faker.name().firstName();
    private final String lastName = faker.name().lastName();
    private final int age = faker.number().numberBetween(18, 65);
    private final int salary = faker.number().numberBetween(1000, 200000);
    private final String department = faker.job().position();
}
