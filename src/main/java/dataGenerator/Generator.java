package dataGenerator;

import com.github.javafaker.Faker;

public class Generator {

    Faker faker = new Faker();

    private final String fullName;
    private final String email;
    private final String currentAddress;
    private final String permanentAddress;

    private final String name;
    private final String lastName;
    private final int age;
    private final int salary;
    private final String department;


    public Generator() {
        this.fullName = faker.name().fullName();
        this.email = faker.internet().emailAddress();
        this.currentAddress = faker.address().fullAddress();
        this.permanentAddress = faker.address().fullAddress();
        this.name = faker.name().name();
        this.lastName = faker.name().lastName();
        this.age = faker.number().numberBetween(18, 65);
        this.salary = faker.number().numberBetween(2000, 200000);
        this.department = faker.job().position();
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getCurrentAddress() {
        return currentAddress;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getSalary() {
        return salary;
    }

    public String getDepartment() {
        return department;
    }
}
