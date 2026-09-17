package model;

public abstract class Account {

    private int accountNumber;
    private String holderName;
    private double balance;
    private String pin;

    public Account(int accountNumber, String holderName,
                   double balance, String pin) {

        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.pin = pin;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public String getPin() {
        return pin;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public abstract double getMinimumBalance();
}