package javaproject.Assignments;
import java.util.Scanner;

public class GradeEvaluation {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = input.nextLine();

        System.out.print("Enter marks (0-100): ");
        int marks = input.nextInt();

        String grade;

        if (marks >= 90 && marks <= 100) {
            grade = "A";
        } else if (marks >= 75) {
            grade = "B";
        } else if (marks >= 50) {
            grade = "C";
        } else if (marks >= 0) {
            grade = "Fail";
        } else {
            grade = "Invalid Marks";
        }

        System.out.println("\n--- Grade Evaluation ---");
        System.out.println("Student : " + name);
        System.out.println("Marks   : " + marks);
        System.out.println("Grade   : " + grade);

        input.close();
    }
}
