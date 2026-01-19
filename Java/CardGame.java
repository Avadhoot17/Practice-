import java.util.Random;

public class CardGame {

    public static void main(String[] args) {
        Random r = new Random();

        int userCard = r.nextInt(13) + 1;
        int computerCard = r.nextInt(13) + 1;

        System.out.println("You: " + userCard);
        System.out.println("Computer: " + computerCard);

        if (userCard > computerCard)
            System.out.println("You win 🎉");
        else if (userCard < computerCard)
            System.out.println("Computer wins ❌");
        else
            System.out.println("Draw 🤝");
    }
}
