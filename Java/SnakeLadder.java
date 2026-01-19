import java.util.Random;

public class SnakeLadder {

    public static void main(String[] args) {

        Random r = new Random();
        int position = 0;

        int[] board = new int[101];
        board[14] = 7;   // snake
        board[31] = -10;
        board[4] = 10;  // ladder
        board[20] = 20;

        while (position < 100) {
            int dice = r.nextInt(6) + 1;
            position += dice;

            if (position > 100) position -= dice;

            position += board[position];
            System.out.println("Dice: " + dice + " Position: " + position);
        }

        System.out.println("You reached 100! 🎉");
    }
}
