package Assignments;
class Student {
    // Attributes
    String name;
    int age;
    int rollNo;
    String department;

    // Constructor
    Student(String name, int age, int rollNo, String department) {
        this.name = name;
        this.age = age;
        this.rollNo = rollNo;
        this.department = department;
    }

    // Method to display student information
    void displayInfo() {
        System.out.println("Student Information");
        System.out.println("-------------------");
        System.out.println("Name       : " + name);
        System.out.println("Age        : " + age);
        System.out.println("Roll No    : " + rollNo);
        System.out.println("Department : " + department);
    }
}

public class StudentDemo {
    public static void main(String[] args) {

        Student student = new Student(
            "Saariya",
            21,
            101,
            "Computer Science"
        );

        student.displayInfo();
    }
}