package javaproject;
	import java.util.Scanner;

	public class BankManagementApp {

	    public static void main(String[] args) {

	        Scanner sc = new Scanner(System.in);

	        // Account details
	        String accountHolder = "Student";
	        int pin = 1234;
	        double balance = 5000.00;

	        // Transaction history
	        String history = "";

	        System.out.println("=================================");
	        System.out.println("       SMART BANKING SYSTEM");
	        System.out.println("=================================");

	        // PIN Login
	        int attempts = 3;
	        boolean loginSuccess = false;

	        while (attempts > 0) {
	            System.out.print("\nEnter your 4-digit PIN: ");
	            int enteredPin = sc.nextInt();

	            if (enteredPin == pin) {
	                loginSuccess = true;
	                System.out.println("Login successful! Welcome, " + accountHolder + ".");
	                break;
	            } else {
	                attempts--;
	                System.out.println("Incorrect PIN!");

	                if (attempts > 0) {
	                    System.out.println("Attempts remaining: " + attempts);
	                }
	            }
	        }

	        // Stop program if login fails
	        if (!loginSuccess) {
	            System.out.println("\nToo many incorrect attempts.");
	            System.out.println("Account temporarily locked.");
	            sc.close();
	            return;
	        }

	        int choice;

	        // Main Menu
	        do {
	            System.out.println("\n=================================");
	            System.out.println("          BANK MENU");
	            System.out.println("=================================");
	            System.out.println("1. Deposit Money");
	            System.out.println("2. Withdraw Money");
	            System.out.println("3. Check Balance");
	            System.out.println("4. Transaction History");
	            System.out.println("5. Exit");
	            System.out.println("=================================");

	            System.out.print("Enter your choice: ");
	            choice = sc.nextInt();

	            if (choice == 1) {

	                // Deposit
	                System.out.print("Enter deposit amount: ₹");
	                double deposit = sc.nextDouble();

	                if (deposit > 0) {
	                    balance += deposit;
	                    history += "Deposited ₹" + deposit + "\n";

	                    System.out.println("Deposit successful!");
	                    System.out.println("New Balance: ₹" + balance);
	                } else {
	                    System.out.println("Invalid amount!");
	                }

	            } else if (choice == 2) {

	                // Withdraw
	                System.out.print("Enter withdrawal amount: ₹");
	                double withdraw = sc.nextDouble();

	                if (withdraw <= 0) {
	                    System.out.println("Invalid amount!");
	                } else if (withdraw > balance) {
	                    System.out.println("Insufficient balance!");
	                } else {
	                    balance -= withdraw;
	                    history += "Withdrawn ₹" + withdraw + "\n";

	                    System.out.println("Withdrawal successful!");
	                    System.out.println("Remaining Balance: ₹" + balance);
	                }

	            } else if (choice == 3) {

	                // Check Balance
	                System.out.println("\n------ ACCOUNT BALANCE ------");
	                System.out.println("Account Holder : " + accountHolder);
	                System.out.println("Current Balance: ₹" + balance);

	            } else if (choice == 4) {

	                // Transaction History
	                System.out.println("\n------ TRANSACTION HISTORY ------");

	                if (history.isEmpty()) {
	                    System.out.println("No transactions yet.");
	                } else {
	                    System.out.print(history);
	                }

	            } else if (choice == 5) {

	                System.out.println("\nThank you for using Smart Banking System!");
	                System.out.println("Have a great day!");

	            } else {

	                System.out.println("Invalid choice! Please select 1-5.");

	            }

	        } while (choice != 5);

	        sc.close();
	    }
	}

