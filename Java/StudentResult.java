import java.util.Scanner;

public class StudentResult {

    static int calculateTotal(int[] marks) {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total;
    }

    static String calculateGrade(double percentage) {
        if (percentage >= 75) return "A";
        else if (percentage >= 60) return "B";
        else if (percentage >= 50) return "C";
        else return "Fail";
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int n = sc.nextInt();

        int[] marks = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = sc.nextInt();
        }

        int total = calculateTotal(marks);
        double percentage = (double) total / n;
        String grade = calculateGrade(percentage);

        System.out.println("\n--- Student Result ---");
        System.out.println("Name       : " + name);
        System.out.println("Total      : " + total);
        System.out.println("Percentage : " + percentage + "%");
        System.out.println("Grade      : " + grade);

        sc.close();
    }
}
