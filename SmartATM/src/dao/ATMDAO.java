package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.DatabaseConnection;
import model.Account;
import model.SavingsAccount;
import model.Transaction;

public class ATMDAO {

    public Account getAccount(int accountNumber)
            throws SQLException {

        String sql =
                "SELECT * FROM accounts WHERE account_number = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, accountNumber);

            ResultSet result =
                    statement.executeQuery();

            if (result.next()) {

                return new SavingsAccount(
                        result.getInt("account_number"),
                        result.getString("holder_name"),
                        result.getDouble("balance"),
                        result.getString("pin")
                );
            }
        }

        return null;
    }

    public void updateBalance(
            int accountNumber,
            double balance) throws SQLException {

        String sql =
                "UPDATE accounts SET balance = ? " +
                "WHERE account_number = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setDouble(1, balance);
            statement.setInt(2, accountNumber);

            statement.executeUpdate();
        }
    }

    public void updatePIN(
            int accountNumber,
            String newPin) throws SQLException {

        String sql =
                "UPDATE accounts SET pin = ? " +
                "WHERE account_number = ?";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setString(1, newPin);
            statement.setInt(2, accountNumber);

            statement.executeUpdate();
        }
    }

    public void saveTransaction(
            int accountNumber,
            Transaction transaction)
            throws SQLException {

        String sql =
                "INSERT INTO transactions " +
                "(account_number, transaction_type, amount, balance_after) " +
                "VALUES (?, ?, ?, ?)";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, accountNumber);
            statement.setString(2, transaction.getType());
            statement.setDouble(3, transaction.getAmount());
            statement.setDouble(4,
                    transaction.getBalanceAfter());

            statement.executeUpdate();
        }
    }

    public ArrayList<Transaction> getTransactions(
            int accountNumber) throws SQLException {

        ArrayList<Transaction> list =
                new ArrayList<>();

        String sql =
                "SELECT transaction_type, amount, "
                + "balance_after, transaction_date "
                + "FROM transactions "
                + "WHERE account_number = ? "
                + "ORDER BY transaction_date DESC";

        try (Connection connection =
                     DatabaseConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, accountNumber);

            ResultSet result =
                    statement.executeQuery();

            while (result.next()) {

                java.sql.Timestamp timestamp =
                        result.getTimestamp("transaction_date");

                Transaction transaction =
                        new Transaction(
                                result.getString(
                                        "transaction_type"),

                                result.getDouble("amount"),

                                result.getDouble(
                                        "balance_after"),

                                timestamp.toLocalDateTime()
                        );

                list.add(transaction);
            }
        }

        return list;
    }
}