import com.github.javafaker.Faker;

public class Generator {

    Faker faker = new Faker();

    private final String fullName;
    private final String email;
    private final String currentAddress;
    private final String permanentAddress;

    Generator() {
        this.fullName = faker.name().fullName();
        this.email = faker.internet().emailAddress();
        this.currentAddress = faker.address().fullAddress();
        this.permanentAddress = faker.address().fullAddress();
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
}
