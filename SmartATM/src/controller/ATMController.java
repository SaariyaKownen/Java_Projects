package controller;
import java.util.HashMap;
import java.time.LocalDate;
import util.FileManager;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ATMDAO;
import exception.InsufficientBalanceException;
import exception.InvalidAmountException;
import model.Account;
import model.Transaction;
import service.ATMService;
import view.ATMView;

public class ATMController {
	private HashMap<Integer, Account> accountCache =
	        new HashMap<>();
    private ATMView view;

    private ATMDAO dao;

    public ATMController() {

        view = new ATMView();
        dao = new ATMDAO();
    }

    public void start() {

        try {

            int accountNumber =
                    view.getAccountNumber();

          
            Account account =
                    accountCache.get(accountNumber);

            if (account == null) {

                account = dao.getAccount(accountNumber);

                if (account != null) {
                    accountCache.put(accountNumber, account);
                }
            }

            if (account == null) {

                System.out.println(
                        "Account not found.");

                return;
            }

            ATMService service =
                    new ATMService(account);

            int attempts = 3;

            boolean loggedIn = false;

            while (attempts > 0) {

                String pin = view.getPIN();

                if (service.login(pin)) {

                    loggedIn = true;
                    break;

                } else {

                    attempts--;

                    System.out.println(
                            "Invalid PIN.");

                    System.out.println(
                            "Attempts remaining: "
                            + attempts);
                }
            }

            if (!loggedIn) {

                System.out.println(
                        "Account temporarily blocked.");

                return;
            }

            System.out.println(
                    "\nLogin successful!");

            System.out.println(
                    "Welcome, "
                    + account.getHolderName());

            boolean running = true;

            while (running) {

                view.showMenu();

                int choice =
                        getChoice();

                switch (choice) {

                case 1:

                    double balance =
                            service.checkBalance();

                    System.out.println(
                            "\nCurrent Balance: ₹"
                            + String.format("%.2f", balance));

                    if (balance < 1000) {

                        System.out.println(
                                "⚠ WARNING: Your balance is low!");
                    }

                    break;

                case 2:

                    deposit(service);

                    break;

                case 3:

                    withdraw(service);

                    break;

                case 4:

                    showTransactions(service);

                    break;

                case 5:

                    searchTransaction(service);

                    break;

                case 6:

                    dailySummary(service);

                    break;

                case 7:

                    changePIN(service);

                    break;

                case 8:

                    generateStatement(service);

                    break;

                case 9:

                    running = false;

                    System.out.println(
                            "Thank you for using Smart ATM.");

                    break;

                default:

                    System.out.println(
                            "Invalid choice.");
                }
            }

        } catch (SQLException e) {

            System.out.println(
                    "Database error: "
                    + e.getMessage());

        } finally {

            view.closeScanner();
        }
    }
    private int getChoice() {

        return view.getChoice();
    }
    

    private void deposit(ATMService service) {

        try {

            double amount =
                    view.getAmount();

            service.deposit(amount);

            System.out.println(
                    "Deposit successful.");

        } catch (InvalidAmountException |
                 SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    private void withdraw(ATMService service) {

        try {

            double amount =
                    view.getAmount();

            service.withdraw(amount);

            System.out.println(
                    "Withdrawal successful.");

        } catch (InvalidAmountException |
                 InsufficientBalanceException |
                 SQLException e) {

            System.out.println(
                    e.getMessage());
        }
    }

    private void showTransactions(
            ATMService service) {

        ArrayList<Transaction> list =
                service.getTransactions();

        System.out.println(
                "\n------ MINI STATEMENT ------");

        if (list.isEmpty()) {

            System.out.println(
                    "No transactions found.");

            return;
        }

        for (Transaction t : list) {

            System.out.println(t);
        }
    }

    private void searchTransaction(
            ATMService service) {

        String type =
                view.getTransactionType();

        boolean found = false;

        System.out.println(
                "\n------ SEARCH RESULTS ------");

        for (Transaction t :
                service.getTransactions()) {

            if (t.getType()
                    .equalsIgnoreCase(type)) {

                System.out.println(t);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No transactions found for: "
                    + type);
        }
    }
    private void dailySummary(
            ATMService service) {

        double deposits = 0;
        double withdrawals = 0;

        int count = 0;

        LocalDate today =
                LocalDate.now();

        for (Transaction t :
                service.getTransactions()) {

            if (t.getDateTime()
                    .toLocalDate()
                    .equals(today)) {

                count++;

                if (t.getType()
                        .equalsIgnoreCase("DEPOSIT")) {

                    deposits += t.getAmount();

                } else if (t.getType()
                        .equalsIgnoreCase("WITHDRAW")) {

                    withdrawals += t.getAmount();
                }
            }
        }

        System.out.println(
                "\n================================");

        System.out.println(
                "        TODAY'S SUMMARY");

        System.out.println(
                "================================");

        System.out.println(
                "Total Deposits    : ₹"
                + String.format("%.2f", deposits));

        System.out.println(
                "Total Withdrawals : ₹"
                + String.format("%.2f", withdrawals));

        System.out.println(
                "Transactions      : " + count);

        System.out.println(
                "Current Balance   : ₹"
                + String.format("%.2f",
                        service.checkBalance()));

        System.out.println(
                "================================");
    }

    private void changePIN(
            ATMService service) {

        try {

            String newPIN =
                    view.getNewPIN();

            if (!newPIN.matches("\\d{4}")) {

                System.out.println(
                        "PIN must contain exactly 4 digits.");

                return;
            }

            service.changePIN(newPIN);

            System.out.println(
                    "PIN changed successfully.");

        } catch (SQLException e) {

            System.out.println(
                    "Unable to change PIN: "
                    + e.getMessage());
        }
    }

    private void generateStatement(
            ATMService service) {

        FileManager.generateStatement(

                service.getAccount()
                        .getAccountNumber(),

                service.getAccount()
                        .getHolderName(),

                service.checkBalance(),

                service.getTransactions()
        );
    }
}