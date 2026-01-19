import java.util.Random;
import java.util.Scanner;

public class DiceGame {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();

        System.out.print("Press enter to roll dice");
        sc.nextLine();

        int dice = r.nextInt(6) + 1;
        System.out.println("You rolled: " + dice);

        sc.close();
    }
}
