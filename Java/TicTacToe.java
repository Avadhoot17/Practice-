import java.util.Scanner;

public class TicTacToe {

    static char[][] board = {
        {'1','2','3'},
        {'4','5','6'},
        {'7','8','9'}
    };

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char player = 'X';
        int moves = 0;

        while (true) {
            printBoard();
            System.out.print("Player " + player + ", choose position: ");
            int pos = sc.nextInt();
            moves++;

            if (!placeMove(pos, player)) {
                System.out.println("Invalid move");
                moves--;
                continue;
            }

            if (checkWin(player)) {
                printBoard();
                System.out.println("Player " + player + " wins!");
                break;
            }

            if (moves == 9) {
                printBoard();
                System.out.println("Draw!");
                break;
            }

            player = (player == 'X') ? 'O' : 'X';
        }
        sc.close();
    }

    static void printBoard() {
        for (char[] row : board) {
            System.out.println(row[0]+" | "+row[1]+" | "+row[2]);
        }
    }

    static boolean placeMove(int pos, char player) {
        int r = (pos-1)/3;
        int c = (pos-1)%3;
        if (board[r][c] != 'X' && board[r][c] != 'O') {
            board[r][c] = player;
            return true;
        }
        return false;
    }

    static boolean checkWin(char p) {
        for (int i=0;i<3;i++)
            if (board[i][0]==p && board[i][1]==p && board[i][2]==p) return true;
        for (int i=0;i<3;i++)
            if (board[0][i]==p && board[1][i]==p && board[2][i]==p) return true;
        return (board[0][0]==p && board[1][1]==p && board[2][2]==p) ||
               (board[0][2]==p && board[1][1]==p && board[2][0]==p);
    }
}
