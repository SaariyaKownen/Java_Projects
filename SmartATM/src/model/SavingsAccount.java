package model;

public class SavingsAccount extends Account {

    public SavingsAccount(int accountNumber,
                          String holderName,
                          double balance,
                          String pin) {

        super(accountNumber, holderName, balance, pin);
    }

    @Override
    public double getMinimumBalance() {
        return 500.0;
    }
}