package service;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ATMDAO;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import model.Account;
import model.Transaction;
import util.FileManager;

public class ATMService {

    private Account account;

    private ATMDAO dao;

    private ArrayList<Transaction> transactions;

    public ATMService(Account account) {

        this.account = account;
        this.dao = new ATMDAO();

        try {

            transactions =
                    dao.getTransactions(
                            account.getAccountNumber());

        } catch (SQLException e) {

            transactions =
                    new ArrayList<>();

            System.out.println(
                    "Unable to load transaction history.");
        }
    }

    public boolean login(String enteredPin) {

        return account.getPin().equals(enteredPin);
    }

    public double checkBalance() {

        return account.getBalance();
    }

    public void deposit(double amount)
            throws InvalidAmountException,
                   SQLException {

        if (amount <= 0) {

            throw new InvalidAmountException(
                    "Amount must be greater than zero.");
        }

        double newBalance =
                account.getBalance() + amount;

        account.setBalance(newBalance);

        Transaction transaction =
                new Transaction(
                        "DEPOSIT",
                        amount,
                        newBalance);

        transactions.add(transaction);

        dao.updateBalance(
                account.getAccountNumber(),
                newBalance);

        dao.saveTransaction(
                account.getAccountNumber(),
                transaction);

        FileManager.saveTransaction(transaction);
    }

    public void withdraw(double amount)
            throws InvalidAmountException,
                   InsufficientBalanceException,
                   SQLException {

        if (amount <= 0) {

            throw new InvalidAmountException(
                    "Amount must be greater than zero.");
        }

        if (amount > account.getBalance()) {

            throw new InsufficientBalanceException(
                    "Insufficient balance.");
        }

        double newBalance =
                account.getBalance() - amount;

        account.setBalance(newBalance);

        Transaction transaction =
                new Transaction(
                        "WITHDRAW",
                        amount,
                        newBalance);

        transactions.add(transaction);

        dao.updateBalance(
                account.getAccountNumber(),
                newBalance);

        dao.saveTransaction(
                account.getAccountNumber(),
                transaction);

        FileManager.saveTransaction(transaction);
    }

    public ArrayList<Transaction>
    getTransactions() {

        return transactions;
    }

    public void changePIN(String newPin)
            throws SQLException {

        account.setPin(newPin);

        dao.updatePIN(
                account.getAccountNumber(),
                newPin);
    }
    public Account getAccount() {

        return account;
    }
}