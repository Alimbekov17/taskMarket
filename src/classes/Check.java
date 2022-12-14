package classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Check {

    private Market market;
    private Customer customer;
    private List<String> shoppingList;
    private int amountToPay;
    private LocalDateTime dateTime;

    @Override
    public String toString() {
        return "\n* * * * * *  * * Check * * * * * * * " +
                "\nMarket       : " + market.getName() +
                "\nCustomer     : " + customer.getName() + " " + customer.getSurname() +
                "\nProducts list: " + shoppingList +
                "\nAmount to pay: " + amountToPay + " CZK" +
                "\nSale         : " + market.getSale() + " %" +
                "\nAmount after " +
                "\n     discount: " + (amountToPay - (amountToPay * market.getSale() / 100)) + " CZK" +
                "\n" +
                "\nTime         : " + dateTime +
                "\n* * * * * * * * * * * * * * * * * * " +
                "\nThank for your purchase!";
    }
}
