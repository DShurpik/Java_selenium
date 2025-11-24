package testData;

import lombok.Getter;

import java.time.LocalDate;
import java.util.*;

@Getter
public class FormData {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String gender;
    private final long mobile;
    private final LocalDate dateOfBirth;
    private final List<String> subjects;
    private final Set<String> hobbies;
    private final String currentAddress;
    private final boolean chooseStateAndCity;

    @Override
    public String toString() {
        return String.format("%s %s, %s, %s, %d, %s, %s, %s",
                getFirstName(),
                getLastName(),
                getEmail(),
                getGender(),
                getMobile(),
                getSubjects(),
                getHobbies(),
                getCurrentAddress()
        );
    }

    public FormData(Builder builder) {
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.gender = builder.gender;
        this.mobile = builder.mobile;
        this.dateOfBirth = builder.dateOfBirth;
        this.subjects = builder.subjects;
        this.hobbies = builder.hobbies;
        this.currentAddress = builder.currentAddress;
        this.chooseStateAndCity = builder.chooseStateAndCity;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String gender;
        private long mobile;
        private LocalDate dateOfBirth;
        private List<String> subjects = new ArrayList<>();
        private Set<String> hobbies = new HashSet<>();
        private String currentAddress;
        private boolean chooseStateAndCity;

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder mobile(long mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder dateOfBirth(int y, int m, int d) {
            this.dateOfBirth = LocalDate.of(y, m, d);
            return this;
        }

        public Builder dateOfBirth(LocalDate date) {
            this.dateOfBirth = date;
            return this;
        }

        public Builder subjects(List<String> subjects) {
            this.subjects = subjects;
            return this;
        }

        public Builder subjects(String... subjects) {
            this.subjects = Arrays.asList(subjects);
            return this;
        }

        public Builder hobbies(Set<String> hobbies) {
            this.hobbies = hobbies;
            return this;
        }

        public Builder hobbies(String... hobbies) {
            this.hobbies = new HashSet<>(Arrays.asList(hobbies));
            return this;
        }

        public Builder currentAddress(String currentAddress) {
            this.currentAddress = currentAddress;
            return this;
        }

        public Builder chooseStateAndCity(boolean chooseStateAndCity) {
            this.chooseStateAndCity = chooseStateAndCity;
            return this;
        }

        public FormData build() {
            return new FormData(this);
        }
    }
}
