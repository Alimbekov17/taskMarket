package service.service;

import classes.*;

import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MarketService {
    void listOfMarkets(Deque<Market> markets);
    void listOfProducts(List<Products> productsAlbert, List<Products> productsKaufland,List<Products> productsLidl);
    void listOfProductsInCurtainMarket(Deque<Market> markets);
    void searchProduct(Deque<Market> markets);
    void addToShoppingList(Deque<Market> markets, Map<String, LogInAccount> accounts, Set<LogInAccount> guest, List<String> shoppingList, int marketBalance);
    void shoppingList(Map<String, LogInAccount> accounts, Set<LogInAccount> guest);
    void checkout(Map<String, LogInAccount> accounts, Set<LogInAccount> guest, Deque<Market> markets, List<String> shoppingList, int marketBalance);
}
