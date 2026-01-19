import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        String[] options = {"Rock", "Paper", "Scissors"};

        System.out.println("🎮 Rock Paper Scissors Game");
        System.out.println("1. Rock");
        System.out.println("2. Paper");
        System.out.println("3. Scissors");
        System.out.print("Choose (1-3): ");

        int userChoice = sc.nextInt() - 1;
        int computerChoice = random.nextInt(3);

        System.out.println("You chose: " + options[userChoice]);
        System.out.println("Computer chose: " + options[computerChoice]);

        if (userChoice == computerChoice) {
            System.out.println("It's a Draw 🤝");
        } else if (
                (userChoice == 0 && computerChoice == 2) ||
                (userChoice == 1 && computerChoice == 0) ||
                (userChoice == 2 && computerChoice == 1)
        ) {
            System.out.println("You Win 🎉");
        } else {
            System.out.println("You Lose ❌");
        }

        sc.close();
    }
}
