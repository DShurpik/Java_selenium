package dataGenerator;

import org.instancio.Instancio;
import org.instancio.Select;

import java.util.List;

public class User {
    private String fullName;
    private String email;
    private String currentAddress;
    private String permanentAddress;

    List<String> names = List.of("John Doe", "Jack Daniels", "Donald Duck", "Elon Mask", "Jeremy Clarkson");
    //List<String> domains = List.of("gmail.com", "yahoo.com", "hotmail.com", "aol.com", "msn.com");
    List<String> currentAddressList = List.of("London", "New-York", "Washington D.C.", "Paris", "Rome");
    List<String> permanentAddressList = List.of("London", "New-York", "Washington D.C.", "Paris", "Rome");

    public User createUser() {
        return Instancio.of(User.class)
                .generate(Select.field(User::getFullName), gen -> gen.oneOf(names))
                .generate(Select.field(User::getEmail), gen -> gen.net().email())
                .generate(Select.field(User::getCurrentAddress), gen -> gen.oneOf(currentAddressList))
                .generate(Select.field(User::getPermanentAddress), gen -> gen.oneOf(permanentAddressList))
                .create();

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
