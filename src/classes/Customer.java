package classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {
    private String name;
    private String surname;
    private int age;
    private int customerBalance;
    private Check check;

    @Override
    public String toString() {
        return  "\nName        : " + name +
                "\nSurname     : " + surname +
                "\nAge         : " + age +
                "\nCredits     : " + customerBalance + " CZK" ;
    }
}
