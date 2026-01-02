public class StarHeart {
    public static void main(String[] args) {

        // Upper part of heart
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 17; j++) {
                if ((j >= 5 - i && j <= 6 + i) || (j >= 12 - i && j <= 13 + i)) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }

        // Lower inverted triangle
        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= 17; j++) {
                if (j >= i + 1 && j <= 17 - i) {
                    System.out.print("*");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
