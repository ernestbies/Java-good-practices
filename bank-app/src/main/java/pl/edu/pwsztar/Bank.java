package pl.edu.pwsztar;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

class Bank implements BankOperation {

    private static int accountNumber = 0;
    private List<Account> accounts = new ArrayList<>();

    private int findAccount(int accountNumber) {
        return IntStream.range(0, accounts.size())
                .filter(i -> accounts.get(i).getAccountNumber() == accountNumber)
                .findFirst()
                .orElse(ACCOUNT_NOT_EXISTS);
    }

    public int createAccount() {
        accounts.add(new Account(++accountNumber, 0));
        return accountNumber;
    }

    public int deleteAccount(int accountNumber) {
        int position = findAccount(accountNumber);
        return position != ACCOUNT_NOT_EXISTS ? accounts.get(position).getBalance() : ACCOUNT_NOT_EXISTS;
    }

    public boolean deposit(int accountNumber, int amount) {
        int position = findAccount(accountNumber);
        if(position != ACCOUNT_NOT_EXISTS){
            accounts.get(position).setBalance(accounts.get(position).getBalance() + amount);
            return true;
        } else {
            return false;
        }
    }

    public boolean withdraw(int accountNumber, int amount) {
        int position = findAccount(accountNumber);
        if(position == ACCOUNT_NOT_EXISTS || accounts.get(position).getBalance() < amount){
            return false;
        } else {
            accounts.get(position).setBalance(accounts.get(position).getBalance() - amount);
            return true;
        }
    }

    public boolean transfer(int fromAccount, int toAccount, int amount) {
        int fromAccountPosition = findAccount(fromAccount);
        int toAccountPosition = findAccount(toAccount);

        if(fromAccountPosition == ACCOUNT_NOT_EXISTS || toAccountPosition == ACCOUNT_NOT_EXISTS  || accounts.get(fromAccountPosition).getBalance() < amount){
            return false;
        } else {
            accounts.get(fromAccountPosition).setBalance(accounts.get(fromAccountPosition).getBalance() - amount);
            accounts.get(toAccountPosition).setBalance(accounts.get(toAccountPosition).getBalance() + amount);
            return true;
        }
    }

    public int accountBalance(int accountNumber) {
        int position = findAccount(accountNumber);
        return position != ACCOUNT_NOT_EXISTS ? accounts.get(position).getBalance() : ACCOUNT_NOT_EXISTS;
    }

    public int sumAccountsBalance() {
        return accounts.stream()
                .map(Account::getBalance)
                .reduce(0, Integer::sum);
    }
}
