import java.util.Scanner;

public class QuizGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int score = 0;

        System.out.println("Q1: Java is?");
        System.out.println("1) OS  2) Language  3) Browser");
        if (sc.nextInt() == 2) score++;

        System.out.println("Q2: JVM stands for?");
        System.out.println("1) Java Virtual Machine  2) Java Variable Method");
        if (sc.nextInt() == 1) score++;

        System.out.println("Score: " + score + "/2");
        sc.close();
    }
}
