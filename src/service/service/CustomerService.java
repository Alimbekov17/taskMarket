package service.service;

import classes.LogInAccount;
import classes.Market;

import java.util.Deque;
import java.util.Map;
import java.util.Set;

public interface CustomerService {
    void sighUp(Map<String, LogInAccount> accounts, Set<LogInAccount> guest);
    String register(Map<String, LogInAccount> accounts);
    void allAccounts(Map<String, LogInAccount> accounts);
    String checkAccount(Map<String, LogInAccount> accounts, Set<LogInAccount> guest);
    String signOut(Map<String, LogInAccount> accounts, Set<LogInAccount> guest);

}
