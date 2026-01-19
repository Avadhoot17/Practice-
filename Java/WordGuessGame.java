import java.util.Scanner;

public class WordGuessGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String secretWord = "JAVA";
        char[] guessedWord = new char[secretWord.length()];
        int attempts = 6;
        boolean found;

        // initialize blanks
        for (int i = 0; i < guessedWord.length; i++) {
            guessedWord[i] = '_';
        }

        System.out.println("🎯 Word Guessing Game");
        System.out.println("Guess the programming word");
        System.out.println("Attempts allowed: " + attempts);

        while (attempts > 0) {
            System.out.print("Word: ");
            for (char c : guessedWord) {
                System.out.print(c + " ");
            }

            System.out.print("\nEnter a letter: ");
            char guess = Character.toUpperCase(sc.next().charAt(0));

            found = false;

            for (int i = 0; i < secretWord.length(); i++) {
                if (secretWord.charAt(i) == guess && guessedWord[i] == '_') {
                    guessedWord[i] = guess;
                    found = true;
                }
            }

            if (!found) {
                attempts--;
                System.out.println("Wrong guess. Attempts left: " + attempts);
            }

            if (String.valueOf(guessedWord).equals(secretWord)) {
                System.out.println("🎉 You guessed the word: " + secretWord);
                sc.close();
                return;
            }
        }

        System.out.println("Game Over ❌");
        System.out.println("The word was: " + secretWord);
        sc.close();
    }
}
