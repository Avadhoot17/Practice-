import java.util.Scanner;

public class UserInputConceptPractice {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Taking input from user
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter your age: ");
        int age = sc.nextInt();

        System.out.print("Enter marks (0-100): ");
        int marks = sc.nextInt();

        // Creating object using user input
        Student student = new Student(name, age, marks);

        System.out.println("\n--- Student Details ---");
        student.display();

        // Calling logic-based methods
        student.checkEligibility();
        student.checkResult();

        sc.close();
    }
}

/* ===================== STUDENT CLASS ===================== */
class Student {

    private String name;
    private int age;
    private int marks;

    // Constructor
    public Student(String name, int age, int marks) {
        this.name = name;
        this.age = age;
        this.marks = marks;
    }

    // Display method
    public void display() {
        System.out.println("Name  : " + name);
        System.out.println("Age   : " + age);
        System.out.println("Marks : " + marks);
    }

    // Logic using if-else
    public void checkEligibility() {
        if (age >= 18) {
            System.out.println("Eligibility: Eligible");
        } else {
            System.out.println("Eligibility: Not Eligible");
        }
    }

    // Result logic
    public void checkResult() {
        if (marks >= 75) {
            System.out.println("Result: Distinction");
        } else if (marks >= 60) {
            System.out.println("Result: First Class");
        } else if (marks >= 40) {
            System.out.println("Result: Pass");
        } else {
            System.out.println("Result: Fail");
        }
    }
}
