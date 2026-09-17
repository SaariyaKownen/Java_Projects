package view;

import java.util.Scanner;

public class ATMView {

    private Scanner scanner;

    public ATMView() {

        scanner = new Scanner(System.in);
    }

    public int getAccountNumber() {

        System.out.print(
                "Enter Account Number: ");

        return scanner.nextInt();
    }

    public String getPIN() {

        System.out.print(
                "Enter PIN: ");

        return scanner.next();
    }

    public void showMenu() {

        System.out.println("\n================================");
        System.out.println("          SMART ATM");
        System.out.println("================================");

        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Mini Statement");
        System.out.println("5. Search Transaction");
        System.out.println("6. Daily Summary");
        System.out.println("7. Change PIN");
        System.out.println("8. Generate Statement");
        System.out.println("9. Logout");

        System.out.print("Enter choice: ");
    }

    public double getAmount() {

        System.out.print(
                "Enter amount: ");

        return scanner.nextDouble();
    }

    public String getNewPIN() {

        System.out.print(
                "Enter new 4-digit PIN: ");

        return scanner.next();
    }

    public String getTransactionType() {

        System.out.print(
                "Enter transaction type: ");

        return scanner.next();
    }

    public void closeScanner() {

        scanner.close();
    }
    public int getChoice() {

        return scanner.nextInt();
    }
}