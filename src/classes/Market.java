package classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

public class Market {
    private String name;
    private String location;
    private List<Products> products;
    private int sale;
    private int marketBalance;

    @Override
    public String toString() {
        return  "\n~~~~~~ *** " + name + " *** ~~~~~~" +
                "\nLocation: " + location +
                "\nSale    : " + sale + " %" +
                "\nBalance : " + marketBalance +
                "\n~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~ * ~";
    }
}
