package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private double amount;
    private double balanceAfter;
    private LocalDateTime dateTime;

    public Transaction(String type, double amount,
                       double balanceAfter) {

        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = LocalDateTime.now();
    }

    // Constructor used when loading transactions from database
    public Transaction(String type, double amount,
                       double balanceAfter,
                       LocalDateTime dateTime) {

        this.type = type;
        this.amount = amount;
        this.balanceAfter = balanceAfter;
        this.dateTime = dateTime;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }

    public double getBalanceAfter() {
        return balanceAfter;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss");

        return dateTime.format(formatter)
                + " | "
                + type
                + " | Amount: ₹"
                + String.format("%.2f", amount)
                + " | Balance: ₹"
                + String.format("%.2f", balanceAfter);
    }
}