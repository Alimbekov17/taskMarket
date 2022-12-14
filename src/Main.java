import classes.Check;
import classes.LogInAccount;
import classes.Market;
import classes.Products;
import service.impl.CustomerImpl;
import service.impl.MarketImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        CustomerImpl customerService = new CustomerImpl();
        MarketImpl marketService = new MarketImpl();
        List<Products> productsAlbert = new ArrayList<>();
        List<Products> productsKaufland = new ArrayList<>();
        List<Products> productsLidl = new ArrayList<>();
        Deque<Market> markets = new LinkedList<>();
        Map<String, LogInAccount> accounts = new HashMap<>();
        Set<LogInAccount> guest = new HashSet<>();
        List<String> shoppingList = new LinkedList<>();
        int marketBalance = 0;
        addProducts(productsAlbert, productsKaufland, productsLidl, markets);

        while (true) {
            commands();
            System.out.print("\nPress: ");
            String command = new Scanner(System.in).next();
            switch (command){
                case "1" -> customerService.sighUp(accounts, guest);
                case "2" -> customerService.allAccounts(accounts);
                case "3" -> marketService.listOfMarkets(markets);
                case "4" -> marketService.listOfProducts(productsAlbert, productsKaufland, productsLidl);
                case "5" -> marketService.listOfProductsInCurtainMarket(markets);
                case "6" -> marketService.searchProduct(markets);
                case "7" -> System.out.println(customerService.checkAccount(accounts, guest));
                case "8" -> marketService.addToShoppingList(markets, accounts, guest, shoppingList, marketBalance);
                case "9" -> marketService.shoppingList(accounts, guest);
                case "10"-> marketService.checkout(accounts, guest, markets, shoppingList, marketBalance);
                case "11"-> System.out.println(customerService.signOut(accounts, guest));
                default -> System.out.println("Wrong command!");
            }
        }



    }

    public static void commands() {

        System.out.println("""
                ~~~~~~~~~~~~ >> Commands << ~~~~~~~~~~~
                1 - Start Shopping
                2 - All accounts
                3 - List of Markets
                4 - List of all products
                5 - List of products in curtain Market
                6 - Search product
                7 - Login to your account
                8 - Add product to your shopping list
                9 - Shopping list
                10 - Proceed to checkout
                11 - Sign out""");
    }

    public static void addProducts(List<Products> productsAlbert, List<Products> productsKaufland,
                                   List<Products> productsLidl, Deque<Market> markets){
        //          products in Albert
        productsAlbert.add(new Products("Tomatoes (1 kg)", 79));
        productsAlbert.add(new Products("Potatoes (1 kg)", 28));
        productsAlbert.add(new Products("Carrots (1 kg)", 21));
        productsAlbert.add(new Products("Garlic (1 pc)", 69));
        productsAlbert.add(new Products("Lemon (1 pc)", 21));
        productsAlbert.add(new Products("Cucumber (1 pc)", 24));
        productsAlbert.add(new Products("Milk (1 L)", 19));
        productsAlbert.add(new Products("Butter (1 pc)", 59));
        productsAlbert.add(new Products("Bread (1 pc)", 32));
        productsAlbert.add(new Products("Eggs (10 pc)", 79));
        productsAlbert.add(new Products("Cheese (200 gr)", 32));
        productsAlbert.add(new Products("Salt (1 kg)", 14));
        productsAlbert.add(new Products("Sugar (1 kg)", 14));
        productsAlbert.add(new Products("Pepper (100 gr)", 21));
        productsAlbert.add(new Products("Apples (1 kg)", 35));
        productsAlbert.add(new Products("Bananas (1 kg)", 35));
        productsAlbert.add(new Products("Oranges (1 kg)", 39));
        productsAlbert.add(new Products("Macaroni (1 kg)", 29));
        productsAlbert.add(new Products("Rice (1 kg)", 69));
        productsAlbert.add(new Products("Spaghetti (1 kg)", 39));
        productsAlbert.add(new Products("Ketchup (1 L)", 49));
        productsAlbert.add(new Products("Mayo (0.5 L)", 69));
        productsAlbert.add(new Products("Oil (1 L)", 44));
        productsAlbert.add(new Products("Vinegar (1 L)", 69));
        productsAlbert.add(new Products("Water (1 L)", 9));
        productsAlbert.add(new Products("Coke (1 L)", 31));
        productsAlbert.add(new Products("Pepsi (1 L)", 31));
        productsAlbert.add(new Products("Mirinda (1 L)", 31));
        productsAlbert.add(new Products("Juice (1 L)", 42));
        productsAlbert.add(new Products("Beer (0.33 L)", 14));

        //          products in Kaufland

        productsKaufland.add(new Products("Tomatoes (1 kg)", 79));
        productsKaufland.add(new Products("Potatoes (1 kg)", 30));
        productsKaufland.add(new Products("Carrots (1 kg)", 23));
        productsKaufland.add(new Products("Garlic (1 pc)", 70));
        productsKaufland.add(new Products("Lemon (1 pc)", 22));
        productsKaufland.add(new Products("Cucumber (1 pc)", 23));
        productsKaufland.add(new Products("Milk (1 L)", 24));
        productsKaufland.add(new Products("Butter (1 pc)", 64));
        productsKaufland.add(new Products("Bread (1 pc)", 32));
        productsKaufland.add(new Products("Eggs (10 pc)", 81));
        productsKaufland.add(new Products("Cheese (200 gr)", 33));
        productsKaufland.add(new Products("Salt (1 kg)", 16));
        productsKaufland.add(new Products("Sugar (1 kg)", 16));
        productsKaufland.add(new Products("Pepper (100 gr)", 21));
        productsKaufland.add(new Products("Apples (1 kg)", 35));
        productsKaufland.add(new Products("Bananas (1 kg)", 35));
        productsKaufland.add(new Products("Macaroni (1 kg)", 32));
        productsKaufland.add(new Products("Rice (1 kg)", 69));
        productsKaufland.add(new Products("Spaghetti (1 kg)", 39));
        productsKaufland.add(new Products("Ketchup (1 L)", 49));
        productsKaufland.add(new Products("Mayo (0.5 L)", 69));
        productsKaufland.add(new Products("Oil (1 L)", 44));
        productsKaufland.add(new Products("Water (1 L)", 9));
        productsKaufland.add(new Products("Coke (1 L)", 31));
        productsKaufland.add(new Products("Mirinda (1 L)", 31));
        productsKaufland.add(new Products("Juice (1 L)", 42));
        productsKaufland.add(new Products("Beer (0.33 L)", 16));

        //              Products in Lidl

        productsLidl.add(new Products("Tomatoes (1 kg)", 77));
        productsLidl.add(new Products("Potatoes (1 kg)", 21));
        productsLidl.add(new Products("Carrots (1 kg)", 21));
        productsLidl.add(new Products("Garlic (1 pc)", 69));
        productsLidl.add(new Products("Lemon (1 pc)", 24));
        productsLidl.add(new Products("Cucumber (1 pc)", 21));
        productsLidl.add(new Products("Milk (1 L)", 19));
        productsLidl.add(new Products("Butter (1 pc)", 55));
        productsLidl.add(new Products("Bread (1 pc)", 29));
        productsLidl.add(new Products("Eggs (10 pc)", 77));
        productsLidl.add(new Products("Salt (1 kg)", 14));
        productsLidl.add(new Products("Sugar (1 kg)", 14));
        productsLidl.add(new Products("Pepper (100 gr)", 21));
        productsLidl.add(new Products("Apples (1 kg)", 33));
        productsLidl.add(new Products("Bananas (1 kg)", 33));
        productsLidl.add(new Products("Oranges (1 kg)", 39));
        productsLidl.add(new Products("Rice (1 kg)", 67));
        productsLidl.add(new Products("Spaghetti (1 kg)", 35));
        productsLidl.add(new Products("Ketchup (1 L)", 49));
        productsLidl.add(new Products("Mayo (0.5 L)", 65));
        productsLidl.add(new Products("Oil (1 L)", 39));
        productsLidl.add(new Products("Vinegar (1 L)", 65));
        productsLidl.add(new Products("Water (1 L)", 8));
        productsLidl.add(new Products("Coke (1 L)", 29));
        productsLidl.add(new Products("Pepsi (1 L)", 29));
        productsLidl.add(new Products("Mirinda (1 L)", 29));
        productsLidl.add(new Products("Juice (1 L)", 39));

        //       markets

        markets.add(new Market("Albert", "Prague 8", productsAlbert, 0, 0));
        markets.add(new Market("Kaufland", "Prague 1", productsKaufland, 25, 0));
        markets.add(new Market("Lidl", "Prague 3", productsLidl, 15, 0));
    }
}