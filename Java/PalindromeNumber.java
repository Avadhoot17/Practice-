// Program to check whether a number is a palindrome
public class PalindromeNumber {

    public static void main(String[] args) {

        // Number to be checked
        int number = 121;
        int originalNumber = number;

        // Variable to store reversed number
        int reversed = 0;

        // Reverse the number
        while (number != 0) {
            int digit = number % 10;
            reversed = reversed * 10 + digit;
            number = number / 10;
        }

        // Check if original and reversed numbers are same
        if (originalNumber == reversed) {
            System.out.println("Palindrome number");
        } else {
            System.out.println("Not a palindrome number");
        }
    }
}
