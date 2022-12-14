package classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Products {
    private String productName;
    private int price;

    @Override
    public String toString() {
        return productName + " ......" +  price + " CZK";
    }
}
