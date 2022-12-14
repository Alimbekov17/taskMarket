package service.impl;

import classes.*;
import service.service.MarketService;

import java.time.LocalDateTime;
import java.util.*;

public class MarketImpl implements MarketService {
    @Override
    public void listOfMarkets(Deque<Market> markets) {
        for (Market market : markets) {
            System.out.println(market);
        }
    }

    @Override
    public void listOfProducts(List<Products> productsAlbert, List<Products> productsKaufland, List<Products> productsLidl) {
        System.out.println("\nProducts in Albert Supermarket\n");
        for (Products products : productsAlbert) {
            System.out.println(products);
        }
        System.out.println("\nProducts in Kaufland Supermarket\n");
        for (Products products : productsKaufland) {
            System.out.println(products);
        }
        System.out.println("\nProducts in Lidl Supermarket\n");
        for (Products products : productsLidl) {
            System.out.println(products);
        }
    }

    @Override
    public void listOfProductsInCurtainMarket(Deque<Market> markets) {
        System.out.print("Enter market name: ");
        String marketName = new Scanner(System.in).next();
        for (Market market : markets) {
            if (marketName.equalsIgnoreCase(market.getName())){
                for (Products product : market.getProducts()) {
                    System.out.println(product);
                }
            }
        }
    }

    @Override
    public void searchProduct(Deque<Market> markets) {
        boolean isFound = false;

        System.out.print("Name of product: ");
        String productName = new Scanner(System.in).next().toLowerCase();
        for (Market market : markets) {
            for (Products product : market.getProducts()) {
                if (product.getProductName().toLowerCase().contains(productName)){
                    if (market.getSale() > 0) {
                        System.out.println("----------------------------------------------" +
                                "\nThere is a sale for this product in " + market.getName());
                        System.out.println("\n" + product + " (normal price)");
                        System.out.println(product.getProductName() + " ......" +
                                (product.getPrice() - (product.getPrice() * market.getSale() / 100)) + " CZK " + "(" + market.getSale() + " % off)" +
                                "\n----------------------------------------------\n");
                    } else {
                        System.out.println("----------------------------------------------" +
                                "\n" + market.getName() + " (no sale in this market)\n");
                        System.out.println(product +
                                "\n----------------------------------------------\n");
                    }
                    isFound = true;
                }
            }
        }
        if (!isFound) {
            System.out.println("There is no such product!");
        }

    }

    @Override
    public void addToShoppingList(Deque<Market> markets, Map<String, LogInAccount> accounts, Set<LogInAccount> guest, List<String> shoppingList, int marketBalance) {
        boolean isCorrect = false;
        int amountToPay = 0;
        if (guest.isEmpty()) {
            if (!accounts.isEmpty()){
                System.out.print("Enter your username: ");
                String username = new Scanner(System.in).next();
                System.out.print("Enter password: ");
                String password = new Scanner(System.in).next();
                for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                    if (accountEntry.getValue().getUserName().equals(username) && accountEntry.getValue().getPassword().equals(password)) {
                        isCorrect = true;
                        boolean isAdded = false;
                        int counter = 1;
                        listOfMarkets(markets);
                        System.out.print("""
                
                                         Albert   - 1
                                         Kaufland - 2
                                         Lidl     - 3
                                         
                                         Choose market:""");
                        String choice = new Scanner(System.in).next();
                        switch (choice){
                            case "1" -> choice = "Albert";
                            case "2" -> choice = "Kaufland";
                            case "3" -> choice = "Lidl";
                            default -> System.out.println("Wrong command!");
                        }
                        for (Market market : markets) {
                            if (choice.equalsIgnoreCase(market.getName())){
                                if (market.getSale()>0) System.out.println("Sale / Sale / Sale / Sale / Sale" +
                                            "\n" + market.getName()+
                                            "\n" + market.getSale() + " % discount applied to all products.");
                                for (Products product : market.getProducts()) System.out.println((counter++) + " -> " + product);
                                try {
                                    while (true){
                                        System.out.println("Choose a product by name and provide quantity." +
                                                "\nOr press Q to go back to main page.");
                                        System.out.print("Name    : ");
                                        String productName = new Scanner(System.in).next().toLowerCase();
                                        if (productName.equalsIgnoreCase("q"))break;
                                        System.out.print("Quantity: ");
                                        int quantity = new Scanner(System.in).nextInt();
                                        for (Products product : market.getProducts()) {
                                            if (product.getProductName().toLowerCase().contains(productName) && quantity > 0){
                                                for (int i = 0; i < quantity; i++) {
                                                    amountToPay += product.getPrice();
                                                    shoppingList.add(product.getProductName());
                                                }
                                                System.out.println(quantity + "x " + product.getProductName() + " added to shopping list!");
                                            }
                                        }
                                        if (quantity == 0) System.out.println("0 added to shopping list");
                                    }
                                    accountEntry.getValue().getCustomer().setCheck(
                                            new Check(market, accountEntry.getValue().getCustomer(), shoppingList, amountToPay, LocalDateTime.now())
                                    );
                                } catch (InputMismatchException e) {
                                    System.out.println("Wrong input");
                                }
                            }
                        }
                    }
                }
            } else {
                System.out.println("You don't have account yet! Register first!");
            }

        } else {
            for (LogInAccount guestAccount : guest) {
                int counter = 1;
                listOfMarkets(markets);
                System.out.print("""
                
                                         Albert   - 1
                                         Kaufland - 2
                                         Lidl     - 3
                                         
                                         Choose market:""");
                String choice = new Scanner(System.in).next();
                switch (choice){
                    case "1" -> choice = "Albert";
                    case "2" -> choice = "Kaufland";
                    case "3" -> choice = "Lidl";
                    default -> System.out.println("Wrong command!");
                }
                for (Market market : markets) {
                    if (choice.equalsIgnoreCase(market.getName())){
                        if (market.getSale()>0) System.out.println("Sale / Sale / Sale / Sale / Sale" +
                                "\n" + market.getName()+
                                "\n" + market.getSale() + " % discount applied to all products.");
                        for (Products product : market.getProducts()) System.out.println((counter++) + " -> " + product);
                        try {
                            while (true){
                                System.out.println("Choose a product by name and provide quantity." +
                                        "\nOr press Q to go back to main page.");
                                System.out.print("Name    : ");
                                String productName = new Scanner(System.in).next().toLowerCase();
                                if (productName.equalsIgnoreCase("q"))break;
                                System.out.print("Quantity: ");
                                int quantity = new Scanner(System.in).nextInt();
                                for (Products product : market.getProducts()) {
                                    if (product.getProductName().toLowerCase().contains(productName) && quantity > 0){
                                        for (int i = 0; i < quantity; i++) {
                                            amountToPay += product.getPrice();
                                            shoppingList.add(product.getProductName());
                                        }
                                        System.out.println(quantity + "x " + product.getProductName() + " added to shopping list!");
                                    }
                                }
                                if (quantity == 0) System.out.println("0 added to shopping list");
                            }
                            guestAccount.getCustomer().setCheck(new Check(market, guestAccount.getCustomer(), shoppingList, amountToPay, LocalDateTime.now()));

                        } catch (InputMismatchException e) {
                            System.out.println("Wrong input");
                        }
                    }
                }
            }
        }

        if (!isCorrect) {
            System.out.println("Password or username is incorrect!");
           }


    }

    @Override
    public void shoppingList(Map<String, LogInAccount> accounts, Set<LogInAccount> guest) {
        boolean isCorrect = false;
        try {
            if (guest.isEmpty()) {
                if (!accounts.isEmpty()){
                    System.out.print("Enter your username: ");
                    String username = new Scanner(System.in).next();
                    System.out.print("Enter password: ");
                    String password = new Scanner(System.in).next();
                    for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                        if (accountEntry.getValue().getUserName().equals(username) && accountEntry.getValue().getPassword().equals(password)) {
                            if (accountEntry.getValue().getCustomer().getCheck().getShoppingList().isEmpty()) {
                                isCorrect = true;
                                throw new Exception("Your shopping list is empty!");
                            } else {
                                System.out.println("\n * * * Your shopping list * * * ");
                                System.out.println(accountEntry.getValue().getCustomer().getCheck().getShoppingList());
                                System.out.println("\nTotal amount to pay: " + accountEntry.getValue().getCustomer().getCheck().getAmountToPay() + " CZK");
                                isCorrect = true;
                            }

                        }
                    }
                } else {
                    System.out.println("You don't have account yet! Register first!");
                }
            } else {
                for (LogInAccount guestAccount : guest) {
                    if (guestAccount.getCustomer().getCheck().getShoppingList().isEmpty()){
                        isCorrect = true;
                        throw new Exception("Your shopping list is empty!");
                    }
                    System.out.println("\n * * * Your shopping list * * * ");
                    System.out.println(guestAccount.getCustomer().getCheck().getShoppingList());
                    System.out.println("\nTotal amount to pay: " + guestAccount.getCustomer().getCheck().getAmountToPay() + " CZK");
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        if (!isCorrect) System.out.println("Password or username is incorrect!");

    }

    @Override
    public void checkout(Map<String, LogInAccount> accounts, Set<LogInAccount> guest ,Deque<Market> markets, List<String> shoppingList, int marketBalance) {
        int amount;
        int discountAmount = 0;
        int customerBalance = 0;

        boolean isCorrect = false;
        if (guest.isEmpty()){
            if (!accounts.isEmpty()){
                System.out.print("Enter your username: ");
                String username = new Scanner(System.in).next();
                System.out.print("Enter password: ");
                String password = new Scanner(System.in).next();
                for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                    if (accountEntry.getValue().getUserName().equals(username) && accountEntry.getValue().getPassword().equals(password)) {
                        for (Market market : markets) {
                            if (market.getName().equalsIgnoreCase(accountEntry.getValue().getCustomer().getCheck().getMarket().getName())){
                                amount = accountEntry.getValue().getCustomer().getCheck().getAmountToPay();
                                discountAmount = (amount - (amount * market.getSale() / 100));
                                customerBalance = accountEntry.getValue().getCustomer().getCustomerBalance();
                            }
                        }

                        isCorrect = true;
                        while (true) {
                            System.out.println(accountEntry.getValue().getCustomer().getCheck());
                            System.out.println();
                            System.out.print("""
                                    Would you like to pay?
                                    1 - to pay
                                    2 - to go back to main page
                                    press:""");
                            String confirm = new Scanner(System.in).next().toLowerCase();
                            if (confirm.equals("1")){
                                if (accountEntry.getValue().getCustomer().getCustomerBalance() < accountEntry.getValue().getCustomer().getCheck().getAmountToPay()){
                                    System.out.println("You don't have enough money!");
                                } else {
                                    for (Market market : markets) {
                                        if (market.getName().equalsIgnoreCase(accountEntry.getValue().getCustomer().getCheck().getMarket().getName())){
                                            marketBalance += discountAmount;
                                            customerBalance -= discountAmount;
                                            accountEntry.getValue().getCustomer().setCustomerBalance(customerBalance);
                                            market.setMarketBalance(marketBalance);
                                            System.out.println("You paid successfully! Thank you!");
                                            System.out.println("Here is you check again: ");
                                            System.out.println(accountEntry.getValue().getCustomer().getCheck());
                                            shoppingList.clear();
                                        }
                                    }
                                    break;
                                }
                            } else if (confirm.equals("2")) {
                               break;
                            } else {
                                System.out.println("Wrong command!");
                            }

                        }

                    }
                }
            } else {
                System.out.println("You don't have account yet! Register first!");
            }
        } else {
            for (LogInAccount guestAccount : guest) {
                while (true) {
                    System.out.println(guestAccount.getCustomer().getCheck());
                    System.out.println();
                    System.out.print("""
                                    Would you like to pay?
                                    1 - to pay
                                    2 - to go back to main page
                                    press:""");
                    String confirm = new Scanner(System.in).next().toLowerCase();
                    if (confirm.equals("1")){
                        if (guestAccount.getCustomer().getCustomerBalance() < guestAccount.getCustomer().getCheck().getAmountToPay()){
                            System.out.println("You don't have enough money!");
                        } else {
                            for (Market market : markets) {
                                amount = guestAccount.getCustomer().getCheck().getAmountToPay();
                                discountAmount = (amount - (amount * market.getSale() / 100));
                                customerBalance = guestAccount.getCustomer().getCustomerBalance();
                                if (market.getName().equalsIgnoreCase(guestAccount.getCustomer().getCheck().getMarket().getName())){
                                    marketBalance += discountAmount;
                                    customerBalance -= discountAmount;
                                    guestAccount.getCustomer().setCustomerBalance(customerBalance);
                                    market.setMarketBalance(marketBalance);
                                    System.out.println("You paid successfully! Thank you!");
                                    System.out.println("Here is you check again: ");
                                    System.out.println(guestAccount.getCustomer().getCheck());
                                    shoppingList.clear();
                                }
                            }
                            break;
                        }
                    } else if (confirm.equals("2")) {
                        break;
                    } else {
                        System.out.println("Wrong command!");
                    }

                }
                isCorrect = true;
            }
        }
        if (!isCorrect) System.out.println("Incorrect username or password!");
    }
}
