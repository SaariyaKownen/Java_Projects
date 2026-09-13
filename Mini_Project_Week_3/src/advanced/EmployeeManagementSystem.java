package advanced;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

class Employee {

    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    @Override
    public String toString() {
        return id + "," + name + "," + department + "," + salary;
    }
}

public class EmployeeManagementSystem {

    static ArrayList<Employee> employees = new ArrayList<>();

    static Scanner sc = new Scanner(System.in);

    static String fileName = "employees.txt";

    // Add employee
    public static void addEmployee() {

        try {
            System.out.print("Enter Employee ID: ");
            int id = Integer.parseInt(sc.nextLine());

            System.out.print("Enter Employee Name: ");
            String name = sc.nextLine();

            System.out.print("Enter Department: ");
            String department = sc.nextLine();

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(sc.nextLine());

            if (id <= 0 || salary < 0) {
                throw new IllegalArgumentException(
                        "ID must be positive and salary cannot be negative.");
            }

            employees.add(new Employee(id, name, department, salary));

            System.out.println("Employee added successfully.");

        } catch (NumberFormatException e) {

            System.out.println("Invalid input! Please enter numbers for ID and salary.");

        } catch (IllegalArgumentException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }

    // Display employees
    public static void displayEmployees() {

        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }

        System.out.println("\nEmployee Details");
        System.out.println("-----------------------------");

        for (Employee emp : employees) {

            System.out.println("ID         : " + emp.id);
            System.out.println("Name       : " + emp.name);
            System.out.println("Department : " + emp.department);
            System.out.println("Salary     : " + emp.salary);
            System.out.println("-----------------------------");
        }
    }

    // Save employees to file
    public static void saveToFile() {

        try {

            BufferedWriter writer =
                    new BufferedWriter(new FileWriter(fileName));

            for (Employee emp : employees) {
                writer.write(emp.toString());
                writer.newLine();
            }

            writer.close();

            System.out.println("Employee data saved successfully.");

        } catch (IOException e) {

            System.out.println("Error while saving data: " + e.getMessage());
        }
    }

    // Load employees from file
    public static void loadFromFile() {

        employees.clear();

        try {

            BufferedReader reader =
                    new BufferedReader(new FileReader(fileName));

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String department = data[2];
                double salary = Double.parseDouble(data[3]);

                employees.add(
                        new Employee(id, name, department, salary)
                );
            }

            reader.close();

            System.out.println("Employee data loaded successfully.");

        } catch (FileNotFoundException e) {

            System.out.println("Employee file not found.");

        } catch (IOException e) {

            System.out.println("Error while reading file: " + e.getMessage());

        } catch (NumberFormatException e) {

            System.out.println("Invalid data found in file.");
        }
    }

    // Main method
    public static void main(String[] args) {

        int choice;

        do {

            System.out.println("\n===== Employee Management System =====");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Save Employees to File");
            System.out.println("4. Load Employees from File");
            System.out.println("5. Exit");

            try {

                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(sc.nextLine());

                switch (choice) {

                    case 1:
                        addEmployee();
                        break;

                    case 2:
                        displayEmployees();
                        break;

                    case 3:
                        saveToFile();
                        break;

                    case 4:
                        loadFromFile();
                        break;

                    case 5:
                        System.out.println("Thank you!");
                        break;

                    default:
                        System.out.println(
                                "Invalid choice! Please choose 1-5.");
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Invalid input! Please enter a number.");
                choice = 0;
            }

        } while (choice != 5);

        sc.close();
    }
}