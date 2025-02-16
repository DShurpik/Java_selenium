package testData;

import java.util.HashSet;
import java.util.Set;

public class FormData {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String number;
    private final String gender;
    private final String subject;
    private final String day;
    private final String month;
    private final String year;
    private final Set<String> hobbies;

    public FormData(String firstName, String lastName, String email, String number, String gender,
                    String subject, String day, String month, String year, Set<String> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.gender = gender;
        this.subject = subject;
        this.day = day;
        this.month = month;
        this.year = year;
        this.hobbies = hobbies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public String getGender() {
        return gender;
    }

    public String getSubject() {
        return subject;
    }

    public String getDay() {
        return day;
    }

    public String getMonth() {
        return month;
    }

    public String getYear() {
        return year;
    }

    public Set<String> getHobbies() {
        return hobbies;
    }

    public static class Builder {
        private String firstName;
        private String lastName;
        private String email;
        private String number;
        private String gender;
        private String subject;
        private String day;
        private String month;
        private String year;
        private Set<String> hobbies = new HashSet<>();

        public Builder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder number(String number) {
            this.number = number;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder subject(String subject) {
            this.subject = subject;
            return this;
        }

        public Builder day(String day) {
            this.day = day;
            return this;
        }

        public Builder month(String month) {
            this.month = month;
            return this;
        }

        public Builder year(String year) {
            this.year = year;
            return this;
        }

        public Builder hobbies(Set<String> hobbies) {
            this.hobbies = hobbies;
            return this;
        }

        public FormData build() {
            return new FormData(firstName, lastName, email, number, gender, subject, day, month, year, hobbies);
        }
    }
}
