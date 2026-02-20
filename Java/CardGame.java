import java.util.Random;
import java.util.Scanner;

public class AdvancedCardGame {

    static String[] suits = {"♠", "♥", "♦", "♣"};
    static String[] ranks = {
            "A", "2", "3", "4", "5", "6", "7",
            "8", "9", "10", "J", "Q", "K"
    };

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int userScore = 0;
        int computerScore = 0;

        System.out.println("=== Welcome to High Card Game ===");

        boolean play = true;

        while (play) {

            // Generate random cards
            int userRankIndex = random.nextInt(13);
            int userSuitIndex = random.nextInt(4);

            int compRankIndex = random.nextInt(13);
            int compSuitIndex = random.nextInt(4);

            System.out.println("\nYou drew: " +
                    ranks[userRankIndex] + suits[userSuitIndex]);

            System.out.println("Computer drew: " +
                    ranks[compRankIndex] + suits[compSuitIndex]);

            // Compare ranks only
            if (userRankIndex > compRankIndex) {
                System.out.println("You win this round 🎉");
                userScore++;
            } else if (userRankIndex < compRankIndex) {
                System.out.println("Computer wins this round ❌");
                computerScore++;
            } else {
                System.out.println("Round Draw 🤝");
            }

            System.out.println("Score -> You: " + userScore +
                    " | Computer: " + computerScore);

            System.out.print("\nPlay again? (y/n): ");
            String choice = sc.next();
            if (!choice.equalsIgnoreCase("y")) {
                play = false;
            }
        }

        System.out.println("\n=== Final Result ===");
        if (userScore > computerScore)
            System.out.println("🏆 You won the game!");
        else if (userScore < computerScore)
            System.out.println("💻 Computer won the game!");
        else
            System.out.println("🤝 Game ended in a draw!");

        sc.close();
    }
}
