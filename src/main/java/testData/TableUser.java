package testData;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TableUser {
    private String firstName;
    private String lastName;
    private String email;
    private int age;
    private int salary;
    private String department;
}
