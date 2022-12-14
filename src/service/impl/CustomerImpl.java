package service.impl;

import classes.Customer;
import classes.LogInAccount;
import enums.Country;
import enums.Gender;
import service.service.CustomerService;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CustomerImpl implements CustomerService {

    @Override
    public void sighUp(Map<String, LogInAccount> accounts, Set<LogInAccount> guest) {
        try {
            System.out.print("""
                Welcome!
                1 - Sign up
                    or
                2 - Continue as a guest
                
                Press:""");
            String press = new Scanner(System.in).next();
            if (press.equals("1")) {
                if (guest.isEmpty()){
                    System.out.println(register(accounts));
                } else {
                    System.out.print("""
                            You are logged in as a guest.
                            Once you register, your guest account will be deleted!
                            Continue (yes/no)?:\s""");
                    String confirm = new Scanner(System.in).next();
                    if (confirm.equalsIgnoreCase("yes")){
                        System.out.println(register(accounts));
                        guest.clear();
                    } else {
                        System.out.println("You are logged in as a guest!");
                    }
                }
            }
            if (press.equals("2")){
                System.out.print("Top up your balance to continue (Minimum amount: 200 CZK): ");
                try {
                    int balance = new Scanner(System.in).nextInt();
                    if (balance > 200){
                        guest.add(new LogInAccount(new Customer("Guest", "", 0, balance, null), "guestUsername", null,
                            null, null, null));
                        System.out.println("You are logged in as a guest!");
                    } else System.out.println("Balance must be at least 200 CZK!");
                } catch (InputMismatchException e){
                    System.out.println("Balance must be numbers!");
                }

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String register(Map<String, LogInAccount> accounts) {
        boolean isRegistered = false;
        try {
            System.out.print("Set username: ");
            String username = new Scanner(System.in).next();
            for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                if (accountEntry.getValue().getUserName().equalsIgnoreCase(username))
                    throw new Exception("This username is already taken. Try another username!");
            }
            System.out.print("Set password: ");
            String password = new Scanner(System.in).next();
            if (password.length() < 5)throw new Exception("Password must consist of at least 5 symbols!");
            System.out.print("Top up your balance to continue (Minimum amount: 200 CZK): ");
            int balance = new Scanner(System.in).nextInt();
            if (balance < 200)throw new Exception("Your balance must be 200 CZK minimum!");
            System.out.print("Enter your name: ");
            String name = new Scanner(System.in).next();
            if (name.matches("[0-9]*"))throw new Exception("Name can not contain numbers!");
            System.out.print("Surname: ");
            String surname = new Scanner(System.in).next();
            if (surname.matches("[0-9]*"))throw new Exception("Name can not contain numbers!");
            System.out.print("Age: ");
            int age = new Scanner(System.in).nextInt();
            if (age < 0)throw new Exception("Age must be positive number!");
            System.out.print("Phone number: ");
            String phoneNumber = new Scanner(System.in).next();
            if (phoneNumber.matches("[a-zA-Z]*"))throw new Exception("Phone number cannot contain letters!");
            else if (phoneNumber.length()!=9)throw new Exception("Phone number must be 9 digits!");
            for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                if (accountEntry.getValue().getPhoneNumber().equals(phoneNumber))
                    throw new Exception("Phone number you provided is already registered." +
                            "\nLogin to existing account or try another phone number!");
            }
            Country country = null;
            while (true){
                boolean isInList = false;
                System.out.println("Country of birth: ");
                String countryOfBirth = new Scanner(System.in).nextLine();
                for (Country value: Country.values()) {
                    if (countryOfBirth.equalsIgnoreCase(value.getUrl())) {
                        country = value;
                        isInList = true;
                        break;
                    }
                }
                if (isInList) {
                    break;
                } else {
                    System.out.println("Country you provided does not exist!");
                }
            }
            System.out.println("Choose your gender");
            Gender gender;
            while (true) {
                System.out.print("""
                    1 - Male
                    2 - Female
                    Choose:""");
                String choice1 = new Scanner(System.in).next();
                if (choice1.equals("1")) {
                    gender = Gender.MALE;
                    break;
                } else if (choice1.equals("2")) {
                    gender = Gender.FEMALE;
                    break;
                } else {
                    System.out.println("Wrong command!");
                }
            }
            accounts.put(username, new LogInAccount(new Customer(name, surname, age, balance, null),
                    username, password, phoneNumber, country, gender));

            isRegistered = true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return isRegistered ? "You are successfully registered!" : "Something went wrong. Registration failed!";
    }

    @Override
    public void allAccounts(Map<String, LogInAccount> accounts) {
        int a = 1;
        if (!accounts.isEmpty()){
            if (accounts.size() == 1) {
                System.out.println("\nFound " + accounts.size() + " account registered!");
            } else {
                System.out.println("\nFound " + accounts.size() + " accounts registered!");
            }
            for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                System.out.println((a++) +". Username: "+ accountEntry.getValue().getUserName());
            }
        } else {
            System.out.println("There is no account registered yet!");
        }
    }

    @Override
    public String checkAccount(Map<String, LogInAccount> accounts, Set<LogInAccount> guest) {
        boolean isCorrect = false;
        if (!accounts.isEmpty()){
            System.out.print("Enter your username: ");
            String username = new Scanner(System.in).next();
            System.out.print("Enter password: ");
            String password = new Scanner(System.in).next();
            for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                if (accountEntry.getValue().getUserName().equals(username) && accountEntry.getValue().getPassword().equals(password)) {
                    isCorrect = true;
                    System.out.println(accountEntry.getValue());
                }
            }
        } else {
            System.out.println("You don't have account yet! Register first!");
            isCorrect = true;
        }
        return isCorrect? "":"Password or username is incorrect!";
    }

    @Override
    public String signOut(Map<String, LogInAccount> accounts, Set<LogInAccount> guest) {
        boolean isDeleted = false;
        if (guest.isEmpty()) {
            if (!accounts.isEmpty()){
                System.out.print("Enter your username: ");
                String username = new Scanner(System.in).next();
                for (Map.Entry<String, LogInAccount> accountEntry : accounts.entrySet()) {
                    if (accountEntry.getValue().getUserName().equals(username)) {
                        System.out.println("Your account will be deleted! Are you sure to sign out?" +
                                "\nConfirm (yes/no)");
                        String confirm = new Scanner(System.in).next();
                        if (confirm.equalsIgnoreCase("yes")){
                            accounts.remove(username);
                            isDeleted = true;
                        } else if (confirm.equalsIgnoreCase("no")) {
                            System.out.println("Sign out canceled!");
                        }
                    }
                }
                if (!isDeleted)System.out.println("Incorrect username!");
            } else {
                System.out.println("You don't have account yet! Register first!");
            }
        } else {
            System.out.println("You are signed in as a guest! Your shopping list will be cleared! Continue?" +
                    "\nConfirm (yes/no)");
            String confirm = new Scanner(System.in).next();
            if (confirm.equalsIgnoreCase("yes")){
                guest.clear();
                isDeleted = true;
            } else if (confirm.equalsIgnoreCase("no")) {
                System.out.println("Sign out canceled!");
            }
        }
        return isDeleted? "Sign out successful! Your account has been deleted":"";
    }
}
