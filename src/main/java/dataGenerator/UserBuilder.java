package dataGenerator;

import lombok.Getter;
import org.instancio.Instancio;
import org.instancio.Select;

import java.util.List;

import static testData.ExcelDataLoader.readExcelData;

@Getter
public class UserBuilder {
    private String fullName;
    private String email;
    private String currentAddress;
    private String permanentAddress;

    List<String> names = readExcelData("src/test/resources/test_sheet.xlsx", 1, 0);
    List<String> domains = readExcelData("src/test/resources/test_sheet.xlsx", 1, 1);
    List<String> currentAddressList = readExcelData("src/test/resources/test_sheet.xlsx", 1, 2);
    List<String> permanentAddressList = readExcelData("src/test/resources/test_sheet.xlsx", 1, 3);

    public UserBuilder createUser() {
        return Instancio.of(UserBuilder.class)
                .generate(Select.field(UserBuilder::getFullName), gen -> gen.oneOf(names))
                .generate(Select.field(UserBuilder::getEmail), gen -> gen.oneOf(domains))
                .generate(Select.field(UserBuilder::getCurrentAddress), gen -> gen.oneOf(currentAddressList))
                .generate(Select.field(UserBuilder::getPermanentAddress), gen -> gen.oneOf(permanentAddressList))
                .create();
    }

    public UserBuilder() {}

    public UserBuilder(Builder builder) {
        this.fullName = builder.fullName;
        this.email = builder.email;
        this.currentAddress = builder.currentAddress;
        this.permanentAddress = builder.permanentAddress;
    }

    public static class Builder {
        private String fullName;
        private String email;
        private String currentAddress;
        private String permanentAddress;

        public Builder withFullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withCurrentAddress(String currentAddress) {
            this.currentAddress = currentAddress;
            return this;
        }

        public Builder withPermanentAddress(String permanentAddress) {
            this.permanentAddress = permanentAddress;
            return this;
        }

        public UserBuilder build() {
            return new UserBuilder(this);
        }
    }

    @Override
    public String toString() {
        return String.format(
                "FullName = %s%nEmail = %s%nCurrentAddress = %s%nPermanentAddress = %s",
                fullName, email, currentAddress, permanentAddress
        );
    }

}
