package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import model.Transaction;

public class FileManager {

    private static final String FILE =
            "data/transactions.txt";

    public static void createFileIfNeeded() {

        try {

            // Create data folder if it does not exist
            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            // Create transactions.txt if it does not exist
            File file = new File(FILE);

            if (file.createNewFile()) {

                System.out.println(
                        "Transaction file created successfully.");

            } else {

                System.out.println(
                        "Transaction file already exists.");
            }

        } catch (IOException e) {

            System.out.println(
                    "Error creating transaction file: "
                    + e.getMessage());
        }
    }

    public static void saveTransaction(
            Transaction transaction) {

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer =
                    new FileWriter(FILE, true);

            writer.write(
                    transaction.toString()
                    + System.lineSeparator());

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving transaction: "
                    + e.getMessage());
        }
    }
    public static void generateStatement(
            int accountNumber,
            String holderName,
            double balance,
            java.util.ArrayList<Transaction> transactions) {

        String file =
                "data/account_statement.txt";

        try {

            File folder = new File("data");

            if (!folder.exists()) {
                folder.mkdirs();
            }

            FileWriter writer =
                    new FileWriter(file);

            writer.write(
                    "========================================\n");

            writer.write(
                    "          SMART ATM ACCOUNT STATEMENT\n");

            writer.write(
                    "========================================\n");

            writer.write(
                    "Account Number : "
                    + accountNumber + "\n");

            writer.write(
                    "Account Holder : "
                    + holderName + "\n");

            writer.write(
                    "Current Balance: ₹"
                    + String.format("%.2f", balance)
                    + "\n");

            writer.write(
                    "----------------------------------------\n");

            writer.write(
                    "TRANSACTION HISTORY\n");

            writer.write(
                    "----------------------------------------\n");

            for (Transaction t : transactions) {

                writer.write(
                        t.toString()
                        + "\n");
            }

            writer.write(
                    "========================================\n");

            writer.close();

            System.out.println(
                    "Account statement generated successfully.");

            System.out.println(
                    "Location: data/account_statement.txt");

        } catch (IOException e) {

            System.out.println(
                    "Error generating statement: "
                    + e.getMessage());
        }
    }
}