package classes;

import enums.Country;
import enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LogInAccount {
    private Customer customer;
    private String userName;
    private String password;
    private String phoneNumber;
    private Country countryOfBirth;
    private Gender gender;

    @Override
    public String toString() {
        return "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-" +
                "\n            Account info" +
                "\nUsername    : " + userName +
                "\nPassword    : " + password +
                "\n" + customer +
                "\nPhone number: " + phoneNumber +
                "\nCountry     : " + countryOfBirth +
                "\nGender      : " + gender +
                "\n-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-";
    }
}
