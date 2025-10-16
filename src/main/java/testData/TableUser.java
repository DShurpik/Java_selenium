package testData;

import dataGenerator.DataUserGenerator;
import lombok.*;

@Getter
@Builder
public class TableUser {

    private final String name;
    private final String lastName;
    private final String email;
    private final int age;
    private final int salary;
    private final String department;

    public static TableUser fromDataExcel(String name, String lastName, String email, int age, int salary, String department) {
        return TableUser.builder()
                .name(name)
                .lastName(lastName)
                .email(email)
                .salary(salary)
                .age(age)
                .department(department)
                .build();
    }

    public static TableUser fromDataGenerator(DataUserGenerator generator) {
        return TableUser.builder()
                .name(generator.getName())
                .lastName(generator.getLastName())
                .email(generator.getEmail())
                .age(generator.getAge())
                .salary(generator.getSalary())
                .department(generator.getDepartment())
                .build();
    }

    @Override
    public String toString() {
        return String.format("Name = %s%nLast name = %s%nEmail = %s%nAge=%d%nSalary=%d%nDepartment=%s",
                name, lastName, email, age, salary, department);
    }
}
